package ru.geekbrains.controller;

import org.springframework.data.jpa.domain.Specification;
import ru.geekbrains.persist.Product;

public final class ProductSpecifications {

    public static Specification<Product> titleContaining(String title) {
        return (root, query, cb) -> cb.like(root.get("title"), "%" + title + "%");
    }

}
