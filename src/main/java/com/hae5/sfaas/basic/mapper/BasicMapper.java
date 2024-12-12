package com.hae5.sfaas.basic.mapper;

import com.hae5.sfaas.basic.model.Basic;
import org.apache.ibatis.annotations.Mapper;

/**
 * 패키지 구조 전달을 위한 Mapper 입니다.
 * 해당 Mapper는 사용하지 마시고, 새로운 Mapper를 생성하여 이용하시길 바랍니다.
 */

import java.util.Optional;

@Mapper
public interface BasicMapper {
    Optional<Basic> findById(Long basicId);
}
