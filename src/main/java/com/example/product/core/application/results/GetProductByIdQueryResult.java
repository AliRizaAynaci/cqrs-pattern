package com.example.product.core.application.results;

import java.math.BigDecimal;

public class GetProductByIdQueryResult {

    private Long id;
    private String name;
    private String description;
    private BigDecimal price;

    public GetProductByIdQueryResult(Long id, String name, String description, BigDecimal price) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
    }

    public Long getId() {
        return id;
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
