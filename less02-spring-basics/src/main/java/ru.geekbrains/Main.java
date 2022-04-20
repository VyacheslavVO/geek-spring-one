package ru.geekbrains;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.geekbrains.persist.Product;
import ru.geekbrains.persist.ProductRepository;
import ru.geekbrains.persist.ProductRepositoryImpl;
import ru.geekbrains.persist.User;
import ru.geekbrains.persist.UserRepository;
import ru.geekbrains.persist.UserRepositoryImpl;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
//        UserRepository userRepository = new UserRepositoryImpl();
//        UserService userService = new UserService( userRepository );
        ApplicationContext context = new ClassPathXmlApplicationContext("spring-context.xml");
        // получим доступ к userService через AppContext
        UserService userService = context.getBean( "userService", UserService.class );

        // нужно получить cartService в несколько экземпляров
        CartService cartService = context.getBean( "cartService", CartService.class );

        // добавить пять продуктов в репозиторий продуктов
        ProductRepository productRepository = new ProductRepositoryImpl();

        Scanner sc = new Scanner( System.in );
        for (;;) {
            System.out.print("Enter user name: ");
            String name = sc.nextLine();

            System.out.print("Enter user role: ");
            String role = sc.nextLine();

            try {
                userService.insert( new User(name, role) );
            } catch (IllegalArgumentException ex) {
                System.out.println("Incorrect role name");
            }

            System.out.println("New user added. Now " + userService.getCount() + " users in repository.");
        }
    }
}
