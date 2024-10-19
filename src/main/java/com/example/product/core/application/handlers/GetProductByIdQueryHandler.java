package com.example.product.core.application.handlers;

import com.example.product.core.application.queries.GetProductByIdQuery;
import com.example.product.core.application.results.GetProductByIdQueryResult;
import com.example.product.core.services.ProductService;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
public class GetProductByIdQueryHandler {

    private final ProductService productService;

    public GetProductByIdQueryHandler(ProductService productService) {
        this.productService = productService;
    }

    public CompletableFuture<GetProductByIdQueryResult> handle(GetProductByIdQuery query) {
        return productService.getProductById(query.getId())
                .thenApply(product -> product.map(p -> new GetProductByIdQueryResult(
                                p.getId(), p.getName(), p.getDescription(), p.getPrice()))
                        .orElseThrow(() -> new IllegalArgumentException("Product not found with ID: " + query.getId())));
    }
}
