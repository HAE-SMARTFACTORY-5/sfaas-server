package com.hae5.sfaas.common.config.security.filter;

import com.hae5.sfaas.common.config.security.UserDetailsImpl;
import com.hae5.sfaas.common.jwt.AccessTokenInfo;
import com.hae5.sfaas.common.jwt.JwtProvider;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;


@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtProvider jwtProvider;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        String accessToken = getTokenByHeader(request);

        if (accessToken != null) {
            Authentication authentication = getAuthentication(accessToken);
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        filterChain.doFilter(request, response);
    }

    private String getTokenByHeader(HttpServletRequest request) {
        return request.getHeader("Authorization");
    }

    private Authentication getAuthentication(String accessToken) {
        AccessTokenInfo accessTokenInfo = jwtProvider.resolveToken(accessToken);
        UserDetails userDetails = new UserDetailsImpl(Long.parseLong(accessTokenInfo.getUserId()),
                accessTokenInfo.getRole());
        return new UsernamePasswordAuthenticationToken(userDetails, accessToken, userDetails.getAuthorities());
    }

}
