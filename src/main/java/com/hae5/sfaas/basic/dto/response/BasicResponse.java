package com.hae5.sfaas.basic.dto.response;

import com.hae5.sfaas.basic.model.Basic;
import lombok.*;

/**
 * 패키지 구조 전달을 위한 ResponseDto 입니다.
 * 해당 ResponseDto는 사용하지 마시고, 새로운 ResponseDto를 생성하여 이용하시길 바랍니다.
 */

@Getter
@Builder(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class BasicResponse {

    private Long basicId;
    private String name;

    public static BasicResponse from(Basic basic) {
        return BasicResponse.builder()
                .basicId(basic.getBasicId())
                .name(basic.getName())
                .build();
    }

}
