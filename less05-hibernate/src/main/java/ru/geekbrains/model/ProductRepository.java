package ru.geekbrains.model;

import ru.geekbrains.model.Product;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

public class ProductRepository {

    EntityManager em;

    public ProductRepository(EntityManager em) {
        this.em = em;
    }

    public List<Product> findAll() {
        em.getTransaction().begin();
//        List<Product> products = em.createQuery("select u from Product u", Product.class)
//                .getResultList();
        List<Product> products = em.createNamedQuery("Product.findAll", Product.class)
                .getResultList();
        em.getTransaction().commit();
        return products;
    }

    public Product saveOrUpdate(Product product) {
        em.getTransaction().begin();
        em.merge(product);
        em.getTransaction().commit();
        return product;
    }

    public Product findById(long id) {
//        Product product = em.find(Product.class, id);
        Product product = em.createNamedQuery("Product.findById",
                Product.class).setParameter("id", id).getSingleResult();
        return product;
    }

    public void exit() {
        em.close();
    }

    public void deleteById(Long id) {
        em.getTransaction().begin();
        Product product = em.find(Product.class, id);
        em.remove(product);
        em.getTransaction().commit();
    }
}
