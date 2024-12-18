package com.hae5.sfaas.production.controller;

import com.hae5.sfaas.production.service.ProductProcessStatusService;
import com.hae5.sfaas.spareParts.dto.response.SparePartsResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/product-process/status")
@RequiredArgsConstructor
public class ProductProcessStatusController {

    private final ProductProcessStatusService productProcessStatusService;

    @GetMapping("")
    public ResponseEntity<SparePartsResponse> getProductProcessStatus(@RequestParam("serial") String productId) {
        SparePartsResponse response = productProcessStatusService.getProductProcessStatus(productId);
        return ResponseEntity.ok().body(response);
    }


}
