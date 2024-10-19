package com.example.product.presentation;

import com.example.product.core.application.commands.CreateProductCommand;
import com.example.product.core.application.commands.DeleteProductCommand;
import com.example.product.core.application.commands.UpdateProductCommand;
import com.example.product.core.application.handlers.*;
import com.example.product.core.application.queries.GetProductByIdQuery;
import com.example.product.core.application.results.GetProductByIdQueryResult;
import com.example.product.core.application.results.GetProductQueryResult;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final GetProductsQueryHandler getProductsQueryHandler;
    private final GetProductByIdQueryHandler getProductByIdQueryHandler;
    private final CreateProductCommandHandler createProductCommandHandler;
    private final DeleteProductCommandHandler deleteProductCommandHandler;
    private final UpdateProductCommandHandler updateProductCommandHandler;

    public ProductController(GetProductsQueryHandler getProductsQueryHandler,
                             GetProductByIdQueryHandler getProductByIdQueryHandler,
                             CreateProductCommandHandler createProductCommandHandler,
                             DeleteProductCommandHandler deleteProductCommandHandler,
                             UpdateProductCommandHandler updateProductCommandHandler) {
        this.getProductsQueryHandler = getProductsQueryHandler;
        this.getProductByIdQueryHandler = getProductByIdQueryHandler;
        this.createProductCommandHandler = createProductCommandHandler;
        this.deleteProductCommandHandler = deleteProductCommandHandler;
        this.updateProductCommandHandler = updateProductCommandHandler;
    }

    @GetMapping
    public CompletableFuture<ResponseEntity<List<GetProductQueryResult>>> getProducts() {
        return getProductsQueryHandler.handle()
                .thenApply(products -> ResponseEntity.ok(products))
                .exceptionally(ex -> ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null));
    }

    @GetMapping("/{id}")
    public CompletableFuture<ResponseEntity<GetProductByIdQueryResult>> getProductById(@PathVariable Long id) {
        return getProductByIdQueryHandler.handle(new GetProductByIdQuery(id))
                .thenApply(product -> ResponseEntity.ok(product))
                .exceptionally(ex -> ResponseEntity.status(HttpStatus.NOT_FOUND).body(null));
    }

    @PostMapping
    public CompletableFuture<ResponseEntity<Object>> createProduct(@RequestBody CreateProductCommand command) {
        return createProductCommandHandler.handle(command)
                .thenApply(v -> ResponseEntity.status(HttpStatus.CREATED).build())
                .exceptionally(ex -> {
                    ex.printStackTrace();
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
                });
    }

    @PutMapping
    public CompletableFuture<ResponseEntity<Object>> updateProduct(@RequestBody UpdateProductCommand command) {
        return updateProductCommandHandler.handle(command)
                .thenApply(v -> ResponseEntity.ok().build())
                .exceptionally(ex -> ResponseEntity.status(HttpStatus.BAD_REQUEST).build());
    }

    @DeleteMapping("/{id}")
    public CompletableFuture<ResponseEntity<Object>> deleteProduct(@PathVariable Long id) {
        return deleteProductCommandHandler.handle(new DeleteProductCommand(id))
                .thenApply(v -> ResponseEntity.ok().build())
                .exceptionally(ex -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }
}
