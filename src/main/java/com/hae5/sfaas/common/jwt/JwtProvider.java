package com.hae5.sfaas.common.jwt;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hae5.sfaas.common.exception.SfaasException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Base64;
import java.util.Date;

import static com.hae5.sfaas.common.exception.ExceptionCode.TOKEN_EXPIRED_ERROR;
import static com.hae5.sfaas.common.exception.ExceptionCode.TOKEN_INVALIDATE_ERROR;

@Component
@RequiredArgsConstructor
public class JwtProvider {

    @Value("${jwt.secret}")
    private String SECRET_KEY;

    @Value("${jwt.token.access-expiration-time}")
    private Long ACCESS_TOKEN_EXPIRATION_TIME;

    private final static String TOKEN_TYPER = "Bearer";

    @PostConstruct
    protected void init() {
        SECRET_KEY = Base64.getEncoder().encodeToString(SECRET_KEY.getBytes(StandardCharsets.UTF_8));
    }

    private Key getSecretKey() {
        return Keys.hmacShaKeyFor(SECRET_KEY.getBytes(StandardCharsets.UTF_8));
    }

    public String createAccessToken(Long memberId, String role) {
        Claims claims = Jwts.claims().setSubject(memberId.toString());
        Date now = new Date();

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + ACCESS_TOKEN_EXPIRATION_TIME * 1000))
                .signWith(getSecretKey(), SignatureAlgorithm.HS256)
                .claim("role", role)
                .compact();
    }

    public AccessTokenInfo resolveToken(String token) {
        String claimsJws = getClaimsJws(token);
        try {
            Claims body = Jwts.parserBuilder()
                    .setSigningKey(SECRET_KEY.getBytes()).build()
                    .parseClaimsJws(claimsJws)
                    .getBody();
            return AccessTokenInfo.of(body.getSubject(), (String) body.get("role"));
        } catch (ExpiredJwtException exception) {
            throw SfaasException.create(TOKEN_EXPIRED_ERROR);
        } catch (Exception exception) {
            throw SfaasException.create(TOKEN_INVALIDATE_ERROR);
        }

    }

    private String getClaimsJws(String token) {
        String[] splitToken = token.split(TOKEN_TYPER + " ");
        if (!token.startsWith(TOKEN_TYPER + " ") && splitToken.length != 2) {
            throw SfaasException.create(TOKEN_INVALIDATE_ERROR);
        }
        return splitToken[1];
    }

    public Long getSubByExpiredToken(String token) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            String[] splitToken = token.split("\\.");
            String base64EncodedBody = splitToken[1];
            String body = new String(Base64.getUrlDecoder().decode(base64EncodedBody));
            JsonNode payloadJson = objectMapper.readTree(body);
            return Long.parseLong(payloadJson.get("sub").asText());
        } catch (Exception exception) {
            throw SfaasException.create(TOKEN_INVALIDATE_ERROR);
        }
    }
}
