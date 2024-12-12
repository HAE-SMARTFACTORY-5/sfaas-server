package com.hae5.sfaas.basic.mapper;

/**
 * 패키지 구조 전달을 위한 Test 코드입니다.
 * 해당 Test 코드는 사용하지 마시고, 새로운 Test 코드를 생성하여 이용하시길 바랍니다.
 */

import com.hae5.sfaas.basic.model.Basic;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class BasicMapperTest {

    @Autowired
    private BasicMapper basicMapper;

    @AfterEach
    public void clear() {
        basicMapper.deleteAll();
    }

    @DisplayName("Basic 저장")
    @Test
    public void saveTest() {
        //given
        Basic basic = Basic.create("basic");

        //when
        basicMapper.save(basic);

        //then
        Assertions.assertThat(basicMapper.findAll().size()).isEqualTo(1);
    }

    @DisplayName("Basic 전체 삭제")
    @Test
    public void deleteAllTest() {
        //given
        Basic basic = Basic.create("basic");
        basicMapper.save(basic);

        //when
        basicMapper.deleteAll();

        //then
        Assertions.assertThat(basicMapper.findAll().size()).isEqualTo(0);
    }

    @DisplayName("Basic 전체 조회")
    @Test
    public void findAllTest() {
        //given
        Basic basic1 = Basic.create("basic1");
        Basic basic2 = Basic.create("basic2");
        basicMapper.save(basic1);
        basicMapper.save(basic2);

        //when
        List<Basic> basics = basicMapper.findAll();

        //then
        Assertions.assertThat(basics.size()).isEqualTo(2);
    }

    @DisplayName("BasicId로 조회")
    @Test
    public void findByIdTest() {
        //given
        String name = "basic";
        Basic newBasic = Basic.create(name);
        basicMapper.save(newBasic);

        //when
        Basic basic = basicMapper.findById(newBasic.getBasicId()).orElse(null);

        //then
        Assertions.assertThat(basic).isNotNull();
        Assertions.assertThat(basic.getName()).isEqualTo(name);
    }

}
