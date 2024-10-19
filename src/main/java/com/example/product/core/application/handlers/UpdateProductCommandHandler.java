package com.example.product.core.application.handlers;

import com.example.product.core.application.commands.UpdateProductCommand;
import com.example.product.core.domain.Product;
import com.example.product.core.services.ProductService;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
public class UpdateProductCommandHandler {

    private final ProductService productService;

    public UpdateProductCommandHandler(ProductService productService) {
        this.productService = productService;
    }

    public CompletableFuture<Void> handle(UpdateProductCommand command) {
        return productService.getProductById(command.getId())
                .thenCompose(productOpt -> {
                    Product product = productOpt.orElseThrow(() -> new IllegalArgumentException(
                            "Product not found with ID: " + command.getId()));
                    product.setName(command.getName());
                    product.setDescription(command.getDescription());
                    product.setPrice(command.getPrice());
                    return productService.updateProduct(product);
                });
    }
}
