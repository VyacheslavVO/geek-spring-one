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
import java.math.BigDecimal;
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
            @RequestParam Optional<Integer> page,
            @RequestParam Optional<Integer> size,
            Model model
    ) {
//        if(titleFilter.isEmpty() || titleFilter.get().isBlank()) {
//            model.addAttribute("product", productRepository.findAll());
//        } else {
//            model.addAttribute("product", productRepository.findProductByTitleLike("%" + titleFilter.get() + "%"));
//        }

        String titleFilterValue = titleFilter.
                filter(val -> !val.isBlank()).
                orElse(null);
        Integer pageValue = page.orElse(1) - 1;
        Integer sizeValue = size.orElse(3);
        model.addAttribute("products", productService.findProductByFilter(
                titleFilterValue,
                pageValue,
                sizeValue));

        return "product";
    }

    // показать в представлении редактирование продукта
    @GetMapping("/{id}")
    public String form(@PathVariable long id, Model model) {
        model.addAttribute("product", productRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Product not found")));
        return "product_form";
    }

    // показать в представлении добавление продукта
    @GetMapping("/new")
    public String form(Model model) {
        model.addAttribute("product", new Product("", "", null));
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
    public String notFoundExсeptionHandler (Model model, NotFoundException exс) {
        model.addAttribute("message", exс.getMessage());
        return "not_found";
    }
}
