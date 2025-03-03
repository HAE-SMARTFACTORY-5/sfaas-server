package com.hae5.sfaas.production.controller;

import com.hae5.sfaas.production.dto.response.request.ProductProcessStatusRequest;
import com.hae5.sfaas.production.service.ProductProcessStatusService;
import com.hae5.sfaas.spareParts.dto.response.SparePartsResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("")
    public ResponseEntity<Void> saveProductProcessStatus(@RequestBody ProductProcessStatusRequest request) {
        productProcessStatusService.saveProductProcessStatus(request.serial());
        return ResponseEntity.ok().build();
    }


}
