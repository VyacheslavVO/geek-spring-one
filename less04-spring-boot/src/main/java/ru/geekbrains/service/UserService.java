package ru.geekbrains.service;

import org.springframework.data.domain.Page;
import ru.geekbrains.dto.UserDto;

import java.util.Optional;

public interface UserService {

    Page<UserDto> findUsersByFilter(String usernameFilter, String emailFilter, Integer page, Integer size, String sortField, String sortDirection);

    Optional<UserDto> findById(long id);

    UserDto save (UserDto user);

    void deleteById(long id);
}
