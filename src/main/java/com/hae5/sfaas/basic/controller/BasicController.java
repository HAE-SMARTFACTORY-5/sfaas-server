package com.hae5.sfaas.basic.controller;

import com.hae5.sfaas.basic.dto.response.BasicResponse;
import com.hae5.sfaas.basic.service.BasicService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 패키지 구조 전달을 위한 Controller 입니다.
 * 해당 Controller는 사용하지 마시고, 새로운 Controller를 생성하여 이용하시길 바랍니다.
 */

@RestController
@RequestMapping("/api/v1/basic")
@RequiredArgsConstructor
public class BasicController {

    private final BasicService basicService;

    @GetMapping("/{basicId}")
    public ResponseEntity<BasicResponse> getBasicById(@PathVariable Long basicId) {
        BasicResponse response = basicService.getBasicById(basicId);
        return ResponseEntity.ok().body(response);
    }

}
