package org.example.toolrental.data.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
public class Tool {

    @Id
    private String code;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tool_type_id")
    private ToolType type;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "brand_id")
    private Brand brand;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public ToolType getType() {
        return type;
    }

    public void setType(ToolType type) {
        this.type = type;
    }

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }
}
