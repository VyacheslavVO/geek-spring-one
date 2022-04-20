package ru.geekbrains.persist;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

public class ProductRepository {

    private final Map<Long, Product> productMap = new ConcurrentHashMap<>();

    private final AtomicLong identity = new AtomicLong(0);

    @PostConstruct
    public void init() {
        this.save(new Product("User 1"));
        this.save(new Product("User 2"));
        this.save(new Product("User 3"));
        this.save(new Product("User 4"));
        this.save(new Product("User 5"));
    }

    public List<Product> findAll() {
        return new ArrayList<>(productMap.values());
    }

    public Optional<Product> findById(long id) {
        return Optional.ofNullable(productMap.get(id));
    }

    public Product save(Product product) {
        if(product.getId() == null) { product.setId(identity.incrementAndGet()); }
        productMap.put(product.getId(), product);
        return product;
    }

    public void delete(long id) {
        productMap.remove(id);
    }
}
