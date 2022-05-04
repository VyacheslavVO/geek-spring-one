package ru.geekbrains.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.dto.UserDto;
import ru.geekbrains.service.UserService;

import javax.validation.Valid;
import java.util.Optional;

@RequestMapping("/user")
@Controller
public class  UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String listPage(
            @RequestParam Optional<String> usernameFilter,
            @RequestParam Optional<String> emailFilter,
            @RequestParam Optional<Integer> page,
            @RequestParam Optional<Integer> size,
            @RequestParam Optional<String> sortField,
            @RequestParam Optional<String> sortDirection,
            Model model
    ) {
        String usernameFilterValue = usernameFilter
                .filter(val -> !val.isBlank())
                .orElse(null);
        String emailFilterValue = emailFilter
                .filter(val -> !val.isBlank())
                .orElse(null);
        Integer pageValue = page.orElse(1) - 1;
        Integer sizeValue = size.orElse(3);
        String sortFieldValue = sortField
                .filter(val -> !val.isBlank())
                .orElse("id");
        String sortDirectionValue = sortDirection
                .filter(val -> !val.isBlank())
                .orElse("up");
        model.addAttribute("users", userService.findUsersByFilter(
                usernameFilterValue,
                emailFilterValue,
                pageValue,
                sizeValue,
                sortFieldValue,
                sortDirectionValue));

        return "user";
    }

    // показать в представлении
    @GetMapping("/{id}")
    public String form(@PathVariable long id, Model model) {
        model.addAttribute("user", userService.findById(id)
                .orElseThrow(() -> new NotFoundException("User not found")));
        return "user_form";
    }

    // показать в представлении
    @GetMapping("/new")
    public String form(Model model) {
        model.addAttribute("user", new UserDto());
        return "user_form";
    }

    // удалить пользователя
    @DeleteMapping("/{id}")
    public String delete(@PathVariable long id) {
        userService.deleteById(id);
        return "redirect:/user";
    }

    // отправка формы
    @PostMapping
    public String save(@Valid @ModelAttribute(name = "user") UserDto user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "user_form";
        }
        // проверяем на совпадение вовторного ввода пароля
        if (!user.getPassword().equals(user.getMatchingPassword())) {
            bindingResult.rejectValue("password", "","Password not match");
            return "user_form";
        }
        userService.save(user);
        return "redirect:/user";
    }

    // если кто-то из контроллеров кидает исключение, то управление передается сюда...
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler
    public String notFoundExceptionHandler (Model model, NotFoundException exception) {
        model.addAttribute("message", exception.getMessage());
        return "not_found";
    }
}
