package com.huyen.inventory_management.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.huyen.inventory_management.dto.ExportOrderDto;
import com.huyen.inventory_management.dto.ExportOrderResponseDto;
import com.huyen.inventory_management.dto.ExportOrderUpdateDto;
import com.huyen.inventory_management.payload.ResponseData;
import com.huyen.inventory_management.service.ExportOrderService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/export-orders")
public class ExportOrderController {

    @Autowired
    private ExportOrderService exportOrderService;

    @PostMapping
    public ResponseEntity<ResponseData> createExportOrder(@Valid @RequestBody ExportOrderDto exportOrderDto) {
        ExportOrderResponseDto responseDto = exportOrderService.createExportOrder(exportOrderDto);

        ResponseData response = new ResponseData(
                HttpStatus.CREATED.value(),
                true,
                "Tạo phiếu xuất hàng thành công!",
                responseDto);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<ResponseData> getExportOrder(@Valid @PathVariable UUID id) {
        ExportOrderResponseDto responseDto = exportOrderService.getExportOrder(id);

        ResponseData response = new ResponseData(
                HttpStatus.OK.value(),
                true,
                "Lấy phiếu xuất hàng thành công!",
                responseDto);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    
    @GetMapping
    public ResponseEntity<ResponseData> getAllExportOrders() {
        List<ExportOrderResponseDto> responseDto = exportOrderService.getAllExportOrders();

        ResponseData response = new ResponseData(
                HttpStatus.OK.value(),
                true,
                "Lấy danh sách phiếu xuất hàng thành công!",
                responseDto);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ResponseData> updateExportOrder(@RequestBody ExportOrderUpdateDto exportOrderUpdateDto,
            @Valid @PathVariable UUID id) {
        ExportOrderResponseDto responseDto = exportOrderService.updateExportOrder(id, exportOrderUpdateDto);

        ResponseData response = new ResponseData(
                HttpStatus.OK.value(),
                true,
                "Chỉnh sửa phiếu xuất hàng thành công!",
                responseDto);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseData> deleteExportOrder(@Valid @PathVariable UUID id) {
        exportOrderService.deleteExportOrder(id);;

        ResponseData response = new ResponseData(
                HttpStatus.OK.value(),
                true,
                "Xoá phiếu xuất hàng thành công!",
                null);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
