package com.huyen.inventory_management.mapper;

import java.util.List;
import java.util.stream.Collectors;

import com.huyen.inventory_management.dto.ExportOrderDetailResponseDto;
import com.huyen.inventory_management.dto.ExportOrderResponseDto;
import com.huyen.inventory_management.model.ExportOrder;
import com.huyen.inventory_management.model.ExportOrderDetail;

public class ExportOrderMapper {

    public static ExportOrderResponseDto toSumaryDto(ExportOrder exportOrder) {
        ExportOrderResponseDto dto = new ExportOrderResponseDto();

        dto.setId(exportOrder.getId());
        dto.setCode(exportOrder.getCode());
        dto.setPaymentStatus(exportOrder.getPaymentStatus());
        dto.setCreatedAt(exportOrder.getCreatedAt());
        dto.setTotalAmount(exportOrder.getTotalAmount());
        dto.setAgent(exportOrder.getAgent().getName());
        dto.setWarehouse(exportOrder.getWarehouse().getName());
        dto.setUser(exportOrder.getUser().getFullName());

        return dto;
    }

    public static ExportOrderDetailResponseDto toDetailResponseDto(ExportOrderDetail exportOrderDetail) {
        ExportOrderDetailResponseDto dto = new ExportOrderDetailResponseDto();

        dto.setId(exportOrderDetail.getId());
        dto.setMaterial(exportOrderDetail.getMaterial().getName());
        dto.setQuantity(exportOrderDetail.getQuantity());
        dto.setUnitPrice(exportOrderDetail.getUnitPrice());

        return dto;
    }

    public static ExportOrderResponseDto toResponseDto(ExportOrder exportOrder) {
        ExportOrderResponseDto dto = toSumaryDto(exportOrder);

        List<ExportOrderDetailResponseDto> detailDtos = exportOrder.getExportOrderDetails()
                .stream()
                .map(ExportOrderMapper::toDetailResponseDto)
                .collect(Collectors.toList());

        dto.setDetails(detailDtos);

        return dto;
    }
}
