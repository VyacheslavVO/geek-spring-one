package ru.geekbrains;

import org.springframework.stereotype.Service;
import ru.geekbrains.persist.User;
import ru.geekbrains.persist.UserRepository;

//@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public  void  insert(User user) {
        if(user.getRole().equals( "ADMIN" ) || user.getRole().equals( "GUEST" )) {
            this.userRepository.insert( user );
        } else {
            throw new IllegalArgumentException("Incorrect role");
        }
    }

    public long getCount() {
        return userRepository.getCount();
    }
}
