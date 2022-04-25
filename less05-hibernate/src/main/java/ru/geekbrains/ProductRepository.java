package ru.geekbrains;

import ru.geekbrains.model.Product;

import javax.persistence.*;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;

public class ProductRepository {

    private EntityManagerFactory emFactory;

    public ProductRepository(EntityManagerFactory emFactory) {
        this.emFactory = emFactory;
    }

    public List<Product> findAll() {
        return executeForEntityManager(
                em -> em.createNamedQuery("Product.findAll", Product.class)
                        .getResultList()
        );
    }

    public void save(Product product) {
        executeInTransaction(em -> {
            if(product.getId() != null) {
                em.persist(product);
            } else {
                em.merge(product);
            }
        });
    }

    public Optional<Product> findById(long id) {
        return executeForEntityManager(
                em -> Optional.ofNullable(em.find(Product.class, id))
        );
    }

    public void exit() {
        EntityManager em = emFactory.createEntityManager();
        em.close();
    }

    public void delete(Long id) {
        executeInTransaction(em -> em.createNamedQuery("Product.delete")
                .setParameter("id", id)
                .executeUpdate());
    }

    private <R> R executeForEntityManager(Function<EntityManager, R> func) {
        EntityManager em = emFactory.createEntityManager();
        try {
            return func.apply(em);
        } finally {
            em.close();
        }
    }

    private void executeInTransaction(Consumer<EntityManager> consumer) {
        EntityManager em = emFactory.createEntityManager();
        try {
            em.getTransaction().begin();
            consumer.accept(em);
            em.getTransaction().commit();
        } catch (Exception ex) {
            em.getTransaction().rollback();
        }
        finally {
            em.close();
        }
    }
}
