package com.example.product.core.application.queries;

public class GetProductByIdQuery {

    private Long id;

    public GetProductByIdQuery(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
