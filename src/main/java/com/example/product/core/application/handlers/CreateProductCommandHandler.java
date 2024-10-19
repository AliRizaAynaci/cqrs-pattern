package com.example.product.core.application.handlers;

import com.example.product.core.application.commands.CreateProductCommand;
import com.example.product.core.domain.Product;
import com.example.product.core.services.ProductService;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
public class CreateProductCommandHandler {

    private final ProductService productService;

    public CreateProductCommandHandler(ProductService productService) {
        this.productService = productService;
    }

    public CompletableFuture<Void> handle(CreateProductCommand command) {
        return productService.createProduct(new Product(
                command.getName(),
                command.getDescription(),
                command.getPrice()
        ));
    }
}
