package com.huyen.inventory_management.dto;

import java.math.BigDecimal;
import java.util.UUID;

public class ExportOrderDetailUpdateDto {
    private UUID materialId;

    private BigDecimal quantity;

    private BigDecimal unitPrice;

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
