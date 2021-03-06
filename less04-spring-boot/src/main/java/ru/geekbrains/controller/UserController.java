package ru.geekbrains.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.persist.User;
import ru.geekbrains.persist.UserRepository;

import javax.validation.Valid;

@RequestMapping("/user")
@Controller
public class  UserController {

    private final UserRepository userRepository;

    @Autowired
    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping
    public String listPage(Model model) {
        model.addAttribute("users", userRepository.findAll());
        return "user";
    }

    // показать в представлении
    @GetMapping("/{id}")
    public String form(@PathVariable("id") long id, Model model) {
        model.addAttribute("user", userRepository.findById(id));
        return "user_form";
    }

    // показать в представлении
    @GetMapping("/new")
    public String form(Model model) {
        model.addAttribute("user", new User(""));
        return "user_form";
    }

    // удалить пользователя
    @PostMapping("/{id}")
    public String delete(@PathVariable("id") long id) {
        userRepository.delete(id);
        return "redirect:/user";
    }

    // отправка формы
    @PostMapping
    public String save(@Valid User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "user_form";
        }
        // проверяем на совпадение вовторного ввода пароля
        if (!user.getPassword().equals(user.getMatchingPassword())) {
            bindingResult.rejectValue("password", "","Password not match");
            return "user_form";
        }
        userRepository.save(user);
        return "redirect:/user";
    }
}
