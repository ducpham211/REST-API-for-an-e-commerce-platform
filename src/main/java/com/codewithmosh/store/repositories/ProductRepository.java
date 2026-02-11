package com.codewithmosh.store.repositories;

import com.codewithmosh.store.entities.Product;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    @EntityGraph(attributePaths = "category")
    List<Product> findByCategoryId(Byte categoryId);
    @EntityGraph(attributePaths = "category")//dùng JOIN để bê luôn cả cục dữ liệu Category về trong một lần duy nhất
    @Query("SELECT p FROM Product p")
    List<Product> findAllWithCategory();
}