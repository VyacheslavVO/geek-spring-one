package ru.geekbrains;

import ru.geekbrains.model.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;

public class UserRepository {

    private EntityManagerFactory emFactory;

    public UserRepository(EntityManagerFactory emFactory) {
        this.emFactory = emFactory;
    }

    public Optional<User> findById(long id) {
/*        EntityManager em = emFactory.createEntityManager();
        try {
            return Optional.ofNullable(em.find(User.class, id));
        } finally {
            em.close();
        }*/
        return executeForEntityManager(
                em -> Optional.ofNullable(em.find(User.class, id))
        );
    }

    public List<User> findAll() {
/*        EntityManager em = emFactory.createEntityManager();
        try {
            return em.createQuery("select u from User u", User.class)
                    .getResultList();
        } finally {
            em.close();
        }*/
        return executeForEntityManager(
//                em -> em.createQuery("select u from User u", User.class)
                em -> em.createNamedQuery("findAllUsers", User.class)
                        .getResultList()
        );
    }

    public long count() {
/*        EntityManager em = emFactory.createEntityManager();
        try {
            return em.createQuery("select count(u) from User u", Long.class)
                    .getSingleResult();
        } finally {
            em.close();
        }*/
        return executeForEntityManager(
//                em -> em.createQuery("select count(u) from User u", Long.class)
                em -> em.createNamedQuery("countAllUsers", Long.class)
                        .getSingleResult()
        );
    }

    private void save(User user) {
/*        EntityManager em = emFactory.createEntityManager();
        try {
            em.getTransaction().begin();
            if(user.getId() != null) {
                em.persist(user);
            } else {
                em.merge(user);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            em.getTransaction().rollback();
        }
        finally {
            em.close();
        }*/
        executeInTransaction(em -> {
            if(user.getId() != null) {
                em.persist(user);
            } else {
                em.merge(user);
            }
        });
    }

    private void delete(long id) {
        executeInTransaction(em -> em.createNamedQuery("deleteUser")
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
