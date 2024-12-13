package com.hae5.sfaas.common.jwt;

import com.hae5.sfaas.SfaasApplicationTests;
import com.hae5.sfaas.common.exception.SfaasException;
import com.hae5.sfaas.user.enums.UserRole;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class JwtProviderTest extends SfaasApplicationTests {

    @Autowired
    private JwtProvider jwtProvider;

    @DisplayName("엑세스토큰 생성")
    @Test
    public void createAccessTokenTest() {
        //given
        Long userId = 1L;
        UserRole role = UserRole.MEMBER;

        //when
        String accessToken = jwtProvider.createAccessToken(userId, role.toString());

        //then
        AccessTokenInfo accessTokenInfo = jwtProvider.resolveToken("Bearer " + accessToken);
        assertThat(accessTokenInfo.getUserId()).isEqualTo(userId.toString());
        assertThat(accessTokenInfo.getRole()).isEqualTo(role.toString());
    }

    @DisplayName("잘못된 엑세스토큰 예외")
    @Test
    public void resolveToken_Not_Valid_Token_Exception_Test() {
        //given
        String accessToken = "accessToken";

        //when & when
        assertThatThrownBy(() -> jwtProvider.resolveToken("Bearer " + accessToken))
                .isInstanceOf(SfaasException.class)
                .hasMessageContaining("잘못된 토큰");
    }

}
