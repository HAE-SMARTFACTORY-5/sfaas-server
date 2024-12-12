package com.hae5.sfaas.basic.model;

import lombok.*;

/**
 * 패키지 구조 전달을 위한 Model 입니다.
 * 해당 Model는 사용하지 마시고, 새로운 Model를 생성하여 이용하시길 바랍니다.
 */

@Getter
@Builder(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Basic {

    private Long basicId;
    private String name;


    public static Basic create(String name) {
        return Basic.builder()
                .name(name)
                .build();
    }

}
