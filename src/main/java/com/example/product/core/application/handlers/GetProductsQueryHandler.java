package com.example.product.core.application.handlers;

import com.example.product.core.application.results.GetProductQueryResult;
import com.example.product.core.services.ProductService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@Service
public class GetProductsQueryHandler {

    private final ProductService productService;

    public GetProductsQueryHandler(ProductService productService) {
        this.productService = productService;
    }

    public CompletableFuture<List<GetProductQueryResult>> handle() {
        return productService.getProducts()
                .thenApply(products -> products.stream()
                        .map(product -> new GetProductQueryResult(
                                product.getId(),
                                product.getName(),
                                product.getDescription(),
                                product.getPrice()
                        ))
                        .collect(Collectors.toList())
        );
    }
}
