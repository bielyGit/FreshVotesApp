package com.freshvotes.repositories;

import com.freshvotes.domain.Product;
import com.freshvotes.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByUser(User user);

    //this (roughtly) create this SQL statement: select * from product where name = :name
    Optional<Product> findByName(String name);
}
