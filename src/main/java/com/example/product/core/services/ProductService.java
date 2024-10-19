package com.example.product.core.services;

import com.example.product.core.domain.Product;
import com.example.product.core.interfaces.IRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.function.Predicate;

@Service
public class ProductService {

    private final IRepository repository;

    @Autowired
    public ProductService(IRepository repository) {
        this.repository = repository;
    }

    @Async
    @Transactional
    public CompletableFuture<Void> createProduct(Product product) {
        repository.save(product);
        return CompletableFuture.completedFuture(null);
    }

    @Async
    @Transactional
    public CompletableFuture<Void> updateProduct(Product product) {
        repository.save(product);
        return CompletableFuture.completedFuture(null);
    }

    @Async
    @Transactional
    public CompletableFuture<Void> deleteProduct(Long id) {
        repository.deleteById(id);
        return CompletableFuture.completedFuture(null);
    }

    @Async
    public CompletableFuture<Optional<Product>> getProductById(Long id) {
        return CompletableFuture.completedFuture(repository.findById(id));
    }

    @Async
    public CompletableFuture<List<Product>> getProducts() {
        return CompletableFuture.completedFuture(repository.findAll());
    }

    @Async
    public CompletableFuture<List<Product>> getProductsBy(Predicate<Product> predicate) {
        return CompletableFuture.completedFuture(repository.findAll().stream().filter(predicate).toList());
    }
}
