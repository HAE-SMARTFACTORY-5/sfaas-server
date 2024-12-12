package com.hae5.sfaas.basic.service;

import com.hae5.sfaas.basic.dto.response.BasicResponse;
import com.hae5.sfaas.basic.mapper.BasicMapper;
import com.hae5.sfaas.basic.model.Basic;
import com.hae5.sfaas.common.exception.SfaasException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.hae5.sfaas.common.exception.ExceptionCode.BASIC_NOT_FOUND_ERROR;

/**
 * 패키지 구조 전달을 위한 Service 입니다.
 * 해당 Service는 사용하지 마시고, 새로운 Service를 생성하여 이용하시길 바랍니다.
 */

@Service
@RequiredArgsConstructor
public class BasicService {

    private final BasicMapper basicMapper;

    @Transactional(readOnly = true)
    public BasicResponse getBasicById(Long basicId) {
        Basic basic = basicMapper.findById(basicId).orElseThrow(() -> SfaasException.create(BASIC_NOT_FOUND_ERROR));
        return BasicResponse.from(basic);
    }

}
