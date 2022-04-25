package ru.geekbrains.persist;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findProductByTitleLike(@Param("title") String title);

//    @PersistenceContext
//    private EntityManager em;

//    @PostConstruct
//    public void init() {
//        try {
//            em.getTransaction().begin();
//            this.save(new Product("Product 1"));
//            this.save(new Product("Product 2"));
//            this.save(new Product("Product 3"));
//            this.save(new Product("Product 4"));
//            this.save(new Product("Product 5"));
//            em.getTransaction().commit();
//        } catch (Exception e) {
//            em.getTransaction().rollback();
//        }
//    }
//
//
//    public List<Product> findAll() {
//        return em.createQuery("select p from Product p", Product.class).getResultList();
//    }
//
//    public Optional<Product> findById(long id) {
//        return Optional.ofNullable(em.find(Product.class, id));
//    }
//
//    @Transactional
//    public Product save(Product product) {
//        if(product.getId() == null) {
//            em.persist(product);
//        }
//        em.merge(product);
//        return product;
//    }
//
//    @Transactional
//    public void delete(long id) {
//        em.createQuery("delete from Product where id =: id").setParameter("id", id)
//                .executeUpdate();
//    }
}
