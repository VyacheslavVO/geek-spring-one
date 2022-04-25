package ru.geekbrains;

import org.hibernate.cfg.Configuration;
import ru.geekbrains.model.Contact;
import ru.geekbrains.model.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        // входная точка в базу данных открыть сессию
        EntityManagerFactory emFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .buildSessionFactory();
        // менеджер для выполнения операций с БД
        EntityManager em = emFactory.createEntityManager();

        // INSERT
        // начать транзакцию (любые операции изменения делаются через транзакцию)
//        em.getTransaction().begin();
//
//        em.persist(new User("User 1", "tryecf@bffdbb.com", "ascaecacaece"));
//        em.persist(new User("User 2", "dfrtyty@dfewfe.ru", "aeccu,u,i,uyujuwqe"));
//        em.persist(new User("User 3", "asccc@sca.org", "zxcukyukuyddd"));
//        // закончить транзакцию
//        em.getTransaction().commit();

        // SELECT
//        User user = em.find(User.class, 1L);
//        System.out.println("User with id 1 " + user);

        // SELECT HQL/JPQL
//        List<User> users = em.createQuery("from User u where u.id in (1, 2)", User.class)
//                .getResultList();
//        users.forEach(System.out::println);

        // UPDATE
        // 1
//        em.getTransaction().begin();
//
//        User user = em.find(User.class, 1L);
//        user.setUsername("User test");
//
//        em.getTransaction().commit();

        // 2
//        em.getTransaction().begin();
//
//        User user = new User("User 222", "user_222@mail.com", "super_password_123");
//        user.setId(2L);
//        em.merge(user);
//
//        em.getTransaction().commit();

        // DELETE
//        em.getTransaction().begin();
//
//        User user = em.find(User.class, 2L);
//        em.remove(user);

//        em.createQuery("delete from User u where u.id = 123")
//                .executeUpdate();

//        em.getTransaction().commit();


        // INSERT
        // пример записи контактов в таблицу
//        User user = em.find(User.class, 2L);
//
//        em.getTransaction().begin();
//
//        em.persist(new Contact(Contact.ContactType.HOME_PHONE, "3456834", user));
//        em.persist(new Contact(Contact.ContactType.MOBILE_PHONE, "78645376", user));
//        em.persist(new Contact(Contact.ContactType.HOME_ADDRESS, "State, City, Street 567/4", user));
//
//        em.getTransaction().commit();

        // пример записи контактов в таблицу с созданием пользователя
//        User user = new User("Slava", "vyacheslavvo@mail.ru", "Q123456y");
//
//        em.getTransaction().begin();
//
//        user.getContacts().add(new Contact(Contact.ContactType.HOME_PHONE, "967-854-67", user));
//        user.getContacts().add(new Contact(Contact.ContactType.MOBILE_PHONE, "56747865", user));
//        user.getContacts().add(new Contact(Contact.ContactType.HOME_ADDRESS, "State, City, Street 567/1", user));
//        em.persist(user);
//
//        em.getTransaction().commit();

        // SELECT
//        List<User> users = em.createNamedQuery("findAllUsers", User.class)
//                .getResultList();
        // fetch — жадная загрузка данных
//        List<User> users = em.createQuery("select distinct u from User u inner join fetch u.contacts c", User.class)
//                .getResultList();
//
//        for (User user : users) {
//            user.getContacts().forEach(System.out::println);
//        }

        // DELETE
//        em.getTransaction().begin();
//
//        User user = em.find(User.class, 3L);
//
//        user.getContacts().remove(0);   // удалить контакт под id = 0
////        user.getContacts().clear();   // удалить все контакты пользователя
//        em.merge(user);
//
//        em.getTransaction().commit();

        // закрыть менеджер
        em.close();
        // закрыть сессию
        emFactory.close();
    }
}
