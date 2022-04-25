package ru.geekbrains.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.persist.Product;
import ru.geekbrains.persist.ProductRepository;

import javax.validation.Valid;
import java.util.Optional;

@RequestMapping("/product")
@Controller
public class ProductController {

    private final ProductRepository productRepository;

    @Autowired
    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @GetMapping
    public String listPage(
            @RequestParam Optional<String> titleFilter,
            Model model
    ) {
        if(titleFilter.isEmpty() || titleFilter.get().isBlank()) {
            model.addAttribute("product", productRepository.findAll());
        } else {
            model.addAttribute("product", productRepository.findProductByTitleLike("%" + titleFilter.get() + "%"));
        }
        return "product";
    }

    // показать в представлении редактирование продукта
    @GetMapping("/{id}")
    public String form(@PathVariable long id, Model model) {
        model.addAttribute("product", productRepository.findById(id)
                .orElseThrow(() -> new NotFoundExeption("Product not found")));
        return "product_form";
    }

    // показать в представлении добавление продукта
    @GetMapping("/new")
    public String form(Model model) {
        model.addAttribute("product", new Product(""));
        return "product_form";
    }

    // удалить продукт
    @DeleteMapping("/{id}")
    public String delete(@PathVariable long id) {
        productRepository.deleteById(id);
        return "redirect:/product";
    }

    // отправка формы
    @PostMapping
    public String save(@Valid Product product, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "product_form";
        }
        productRepository.save(product);
        return "redirect:/product";
    }

    // если кто-то из контроллеров кидает исключение, то управление передается сюда...
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler
    public String notFoundExсeptionHandler (Model model, NotFoundExeption exс) {
        model.addAttribute("message", exс.getMessage());
        return "not_found";
    }
}
