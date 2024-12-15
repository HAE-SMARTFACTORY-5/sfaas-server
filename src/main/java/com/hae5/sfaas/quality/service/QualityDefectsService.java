package com.hae5.sfaas.quality.service;

import com.hae5.sfaas.common.exception.ExceptionCode;
import com.hae5.sfaas.common.exception.SfaasException;
import com.hae5.sfaas.quality.dto.response.QualityDefectsResponse;
import com.hae5.sfaas.quality.mapper.QualityDefectsMapper;
import com.hae5.sfaas.user.mapper.UserMapper;
import com.hae5.sfaas.user.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class QualityDefectsService {

    private final QualityDefectsMapper qualityDefectsMapper;
    private final UserMapper userMapper;

    @Transactional(readOnly = true)
    public List<QualityDefectsResponse> getOurQualityDefects(Long userId) {
        User user = userMapper.findById(userId)
                .orElseThrow(() -> SfaasException.create(ExceptionCode.USER_NOT_FOUNT_ERROR));
        return qualityDefectsMapper.getOurQualityDefects(user.getFactoryId()).stream()
                .map(QualityDefectsResponse::from)
                .toList();
    }
}
