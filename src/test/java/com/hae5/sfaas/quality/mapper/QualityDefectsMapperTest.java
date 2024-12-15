package com.hae5.sfaas.quality.mapper;

import com.hae5.sfaas.SfaasApplicationTests;
import com.hae5.sfaas.quality.model.QualityDefects;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class QualityDefectsMapperTest extends SfaasApplicationTests {

    @Autowired
    private QualityDefectsMapper qualityDefectsMapper;

    @AfterEach
    public void clear() {
        qualityDefectsMapper.deleteAll();
    }

    @DisplayName("QualityDefects 저장")
    @Test
    public void saveTest() {
        //given
        QualityDefects qualityDefects = QualityDefects.builder()
                .date(LocalDate.now())
                .model("model")
                .defectType("defectType")
                .resolved(false)
                .defectiveQuantity(10)
                .shift("shift")
                .factoryId(1L)
                .build();

        //when
        qualityDefectsMapper.save(qualityDefects);

        //then
        assertThat(qualityDefectsMapper.findAll().size()).isEqualTo(1);
    }

    @DisplayName("QualityDefects 전체 삭제")
    @Test
    public void deleteAllTest() {
        //given
        QualityDefects qualityDefects = QualityDefects.builder()
                .date(LocalDate.now())
                .model("model")
                .defectType("defectType")
                .resolved(false)
                .defectiveQuantity(10)
                .shift("shift")
                .factoryId(1L)
                .build();
        qualityDefectsMapper.save(qualityDefects);

        //when
        qualityDefectsMapper.deleteAll();

        //then
        assertThat(qualityDefectsMapper.findAll().size()).isEqualTo(0);
    }

    @DisplayName("QualityDefects 전체 조회")
    @Test
    public void findAllTest() {
        //given
        QualityDefects qualityDefects1 = QualityDefects.builder()
                .date(LocalDate.now())
                .model("model")
                .defectType("defectType")
                .resolved(false)
                .defectiveQuantity(10)
                .shift("shift")
                .factoryId(1L)
                .build();
        QualityDefects qualityDefects2 = QualityDefects.builder()
                .date(LocalDate.now())
                .model("model")
                .defectType("defectType")
                .resolved(false)
                .defectiveQuantity(10)
                .shift("shift")
                .factoryId(1L)
                .build();
        qualityDefectsMapper.save(qualityDefects1);
        qualityDefectsMapper.save(qualityDefects2);

        //when
        List<QualityDefects> qualityDefects = qualityDefectsMapper.findAll();

        //then
        assertThat(qualityDefects.size()).isEqualTo(2);
    }

    @DisplayName("특정 공장의 불량 품질 조회")
    @Test
    public void getOurQualityDefectsTest() {
        //given
        QualityDefects qualityDefects1 = QualityDefects.builder()
                .date(LocalDate.now())
                .model("model")
                .defectType("defectType")
                .resolved(false)
                .defectiveQuantity(10)
                .shift("shift")
                .factoryId(1L)
                .build();
        qualityDefectsMapper.save(qualityDefects1);

        //when
        List<QualityDefects> qualityDefects = qualityDefectsMapper.getOurQualityDefects(1L);

        //then
        assertThat(qualityDefects.size()).isEqualTo(1);
    }
}
