package com.example.product.core.application.handlers;

import com.example.product.core.application.commands.DeleteProductCommand;
import com.example.product.core.services.ProductService;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
public class DeleteProductCommandHandler {

    private final ProductService productService;

    public DeleteProductCommandHandler(ProductService productService) {
        this.productService = productService;
    }

    public CompletableFuture<Void> handle(DeleteProductCommand command) {
        return productService.deleteProduct(command.getId());
    }
}
