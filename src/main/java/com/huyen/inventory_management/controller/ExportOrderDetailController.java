package com.huyen.inventory_management.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.huyen.inventory_management.dto.ExportOrderDetailDto;
import com.huyen.inventory_management.dto.ExportOrderDetailResponseDto;
import com.huyen.inventory_management.dto.ExportOrderDetailUpdateDto;
import com.huyen.inventory_management.payload.ResponseData;
import com.huyen.inventory_management.service.ExportOrderDetailService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/export-order-details")
public class ExportOrderDetailController {

    @Autowired
    private ExportOrderDetailService exportOrderDetailService;

    @PostMapping
    public ResponseEntity<ResponseData> createExportOrderDetail(
            @Valid @RequestBody ExportOrderDetailDto exportOrderDetailDto) {
        ExportOrderDetailResponseDto responseDto = exportOrderDetailService
                .createExportOrderDetail(exportOrderDetailDto);

        ResponseData response = new ResponseData(
                HttpStatus.CREATED.value(),
                true,
                "Tạo chi tiết phiếu nhập hàng thành công",
                responseDto);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
    
    @PatchMapping("/{id}")
    public ResponseEntity<ResponseData> updateExportOrderDetail(@Valid @PathVariable UUID id,
            @RequestBody ExportOrderDetailUpdateDto exportOrderDetailUpdateDto) {
        ExportOrderDetailResponseDto responseDto = exportOrderDetailService.updateExportOrderDetail(id,
                exportOrderDetailUpdateDto);

        ResponseData response = new ResponseData(
                HttpStatus.OK.value(),
                true,
                "Chỉnh sửa chi tiết phiếu nhập hàng thành công",
                responseDto);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseData> deleteExportOrderDetail(@Valid @PathVariable UUID id) {
        exportOrderDetailService.deleteExportOrderDetail(id);

        ResponseData response = new ResponseData(
                HttpStatus.OK.value(),
                true,
                "Xoá chi tiết phiếu nhập hàng thành công",
                null);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
