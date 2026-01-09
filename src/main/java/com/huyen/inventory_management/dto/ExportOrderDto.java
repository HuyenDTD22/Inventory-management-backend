package com.huyen.inventory_management.dto;

import java.util.List;
import java.util.UUID;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class ExportOrderDto {
    @NotBlank(message = "Payment status is required")
    private Boolean paymentStatus;

    @NotNull(message = "Agent ID is required")
    private UUID agentId;

    @NotNull(message = "Warehouse ID is required")
    private UUID warehouseId;

    @NotNull(message = "User ID is required")
    private UUID userId;

    @NotNull(message = "Details ID is required")
    private List<ExportOrderDetailDto> exportOrderDetails;

    public Boolean getPaymentStatus() {
        return paymentStatus;
    }

    public UUID getAgentId() {
        return agentId;
    }

    public UUID getWarehouseId() {
        return warehouseId;
    }

    public UUID getUserId() {
        return userId;
    }

    public List<ExportOrderDetailDto> getExportOrderDetails() {
        return exportOrderDetails;
    }

    

    
}
