package com.huyen.inventory_management.dto;

import java.math.BigDecimal;
import java.util.UUID;

import jakarta.validation.constraints.NotNull;

public class ExportOrderDetailDto {
    @NotNull(message = "Export order ID is required")
    private UUID ExportOrderId;

    @NotNull(message = "Material ID is required")
    private UUID materialId;

    @NotNull(message = "Quantity is required")
    private BigDecimal quantity;

    @NotNull(message = "Unit price is required")
    private BigDecimal unitPrice;

    public UUID getExportOrderId() {
        return ExportOrderId;
    }

    public void setExportOrderId(UUID exportOrderId) {
        ExportOrderId = exportOrderId;
    }

    public UUID getMaterialId() {
        return materialId;
    }

    public void setMaterialId(UUID materialId) {
        this.materialId = materialId;
    }

    public BigDecimal getQuantity() {
        return quantity;
    }

    public void setQuantity(BigDecimal quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    
}
