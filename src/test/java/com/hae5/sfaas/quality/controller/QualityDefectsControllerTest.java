package com.hae5.sfaas.quality.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hae5.sfaas.SfaasApplicationTests;
import com.hae5.sfaas.common.jwt.AccessTokenInfo;
import com.hae5.sfaas.common.jwt.JwtProvider;
import com.hae5.sfaas.quality.dto.response.QualityDefectsResponse;
import com.hae5.sfaas.quality.model.QualityDefects;
import com.hae5.sfaas.quality.service.QualityDefectsService;
import com.hae5.sfaas.user.enums.UserRole;
import com.hae5.sfaas.user.model.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
public class QualityDefectsControllerTest extends SfaasApplicationTests {

    @MockitoBean
    private QualityDefectsService qualityDefectsService;

    @MockitoBean
    private JwtProvider jwtProvider;

    @Autowired
    private MockMvc mockMvc;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @DisplayName("우리 공장 불량 품질 내역 조회")
    @Test
    public void getOurQualityDefectsTest() throws Exception {
        //given
        User user = User.builder()
                .userId(1L)
                .employId("test")
                .password("pwd")
                .role(UserRole.MEMBER)
                .build();

        AccessTokenInfo accessTokenInfo = AccessTokenInfo.of(user.getUserId().toString(), user.getRole().name());
        QualityDefects qualityDefects = QualityDefects.builder()
                .date(LocalDate.now())
                .model("model")
                .defectType("defectType")
                .resolved(false)
                .defectiveQuantity(10)
                .shift("shift")
                .factoryId(1L)
                .build();

        List<QualityDefects> qualityDefectsList = new ArrayList<>();
        qualityDefectsList.add(qualityDefects);

        List<QualityDefectsResponse> response = qualityDefectsList.stream().map(QualityDefectsResponse::from).toList();

        when(jwtProvider.resolveToken(any(String.class))).thenReturn(accessTokenInfo);
        when(qualityDefectsService.getOurQualityDefects(eq(1L))).thenReturn(response);

        //when & then
        mockMvc.perform(get("/api/v1/quality-defects/our")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("Authorization", "Baerer accessToken"))
                .andExpect(status().isOk());
    }

}
