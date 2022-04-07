package ru.geekbrains;


import ru.geekbrains.persist.User;
import ru.geekbrains.persist.UserRepository;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class BootstrapListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        UserRepository userRepository = new UserRepository();
        userRepository.insert(new User("Alex"));
        userRepository.insert(new User("Petr"));
        userRepository.insert(new User("Felip"));

        // чтобы репозиторий был доступен на протяжении всего приложения
        sce.getServletContext().setAttribute( "userRepository", userRepository );
    }
}
