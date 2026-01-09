package com.huyen.inventory_management.service;

import java.util.List;
import java.util.UUID;

import com.huyen.inventory_management.dto.ExportOrderDto;
import com.huyen.inventory_management.dto.ExportOrderResponseDto;
import com.huyen.inventory_management.dto.ExportOrderUpdateDto;

public interface ExportOrderService {
    ExportOrderResponseDto createExportOrder(ExportOrderDto exportOrderDto);

    ExportOrderResponseDto getExportOrder(UUID id);

    List<ExportOrderResponseDto> getAllExportOrders();

    ExportOrderResponseDto updateExportOrder(UUID id, ExportOrderUpdateDto exportOrderUpdateDto);

    void deleteExportOrder(UUID id);
}
