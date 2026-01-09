package com.huyen.inventory_management.dto;

import java.math.BigDecimal;
import java.util.UUID;

public class ImportOrderDetailResponseDto {
    private UUID id;

    private BigDecimal quantity;

    private BigDecimal unitPrice;

    private String material;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
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

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    

}
