package com.hae5.sfaas.quality.service;

import com.hae5.sfaas.SfaasApplicationTests;
import com.hae5.sfaas.quality.dto.response.QualityDefectsResponse;
import com.hae5.sfaas.quality.mapper.QualityDefectsMapper;
import com.hae5.sfaas.quality.model.QualityDefects;
import com.hae5.sfaas.user.enums.UserRole;
import com.hae5.sfaas.user.mapper.UserMapper;
import com.hae5.sfaas.user.model.User;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class QualityDefectsServiceTest extends SfaasApplicationTests {

    @Mock
    private QualityDefectsMapper qualityDefectsMapper;

    @Mock
    private UserMapper userMapper;

    @InjectMocks
    private QualityDefectsService qualityDefectsService;

    @DisplayName("우리 공장 불량 품질 내역 조회")
    @Test
    public void getOurQualityDefectsTest () {
        //given
        User user = User.builder()
                .userId(1L)
                .employId("test")
                .password("pwd")
                .factoryId(1L)
                .role(UserRole.MEMBER)
                .build();

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

        when(userMapper.findById(eq(1L))).thenReturn(Optional.ofNullable(user));
        when(qualityDefectsMapper.getOurQualityDefects(1L)).thenReturn(qualityDefectsList);

        //when
        List<QualityDefectsResponse> ourQualityDefects = qualityDefectsService.getOurQualityDefects(1L);

        //then
        Assertions.assertThat(ourQualityDefects.size()).isEqualTo(1);
    }

}
