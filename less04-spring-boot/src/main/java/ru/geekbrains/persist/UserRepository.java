package ru.geekbrains.persist;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findUserByUsernameLike(@Param("username") String username);
    List<User> findUserByEmailLike(@Param("email") String email);
}
