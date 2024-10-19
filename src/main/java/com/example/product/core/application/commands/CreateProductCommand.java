package com.example.product.core.application.commands;

import java.math.BigDecimal;

public class CreateProductCommand {

    private String name;
    private String description;
    private BigDecimal price;

    public CreateProductCommand(String name, String description, BigDecimal price) {
        this.name = name;
        this.description = description;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public BigDecimal getPrice() {
        return price;
    }
}
