package ru.geekbrains;


import org.hibernate.cfg.Configuration;
import ru.geekbrains.model.Product;
import ru.geekbrains.model.ProductRepository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;
import java.util.Scanner;

public class ProductDao {

    public static void main(String[] args) {
        // входная точка в базу данных открыть сессию
        EntityManagerFactory emFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .buildSessionFactory();
        // менеджер для выполнения операций с БД
        EntityManager em = emFactory.createEntityManager();

        ProductRepository products = new ProductRepository(em);

        Scanner scn = new Scanner(System.in);

        Product product = new Product();

        while (true) {
            System.out.println("Enter command: ADD, DELETE, LIST, PRODUCT or EXIT");
            String cmd = scn.nextLine().trim().toUpperCase();
            switch (cmd) {
                case "ADD":
                    System.out.print("Enter title: ");
                    product.setTitle(scn.nextLine());
                    System.out.print("Enter price: ");
                    product.setPrice(scn.nextInt());
                    products.saveOrUpdate(product);
                    System.out.println("Product added");
                    break;
                case "DELETE":
                    System.out.println("Enter id or skip: ");
                    products.deleteById(scn.nextLong());
                    System.out.println("Product deleted");
                    break;
                case "LIST":
                    List<Product> productList = products.findAll();
                    productList.forEach(System.out::println);
                    break;
                case "PRODUCT":
                    System.out.println("Enter id or skip: ");
                    product = products.findById(scn.nextLong());
                    System.out.println(product);
                    break;
                case "EXIT":
                    em.close();
                    emFactory.close();
                    return;
            }
        }
    }
}
