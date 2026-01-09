package com.huyen.inventory_management.service;

import java.util.UUID;

import com.huyen.inventory_management.dto.ExportOrderDetailDto;
import com.huyen.inventory_management.dto.ExportOrderDetailResponseDto;
import com.huyen.inventory_management.dto.ExportOrderDetailUpdateDto;

public interface ExportOrderDetailService {
    ExportOrderDetailResponseDto createExportOrderDetail(ExportOrderDetailDto exportOrderDetailDto);

    ExportOrderDetailResponseDto updateExportOrderDetail(UUID id, ExportOrderDetailUpdateDto exportOrderDetailUpdateDto);

    void deleteExportOrderDetail(UUID id);
}
