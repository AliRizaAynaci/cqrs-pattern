package com.example.product.core.interfaces;

import com.example.product.core.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IRepository extends JpaRepository<Product, Long>{

}
