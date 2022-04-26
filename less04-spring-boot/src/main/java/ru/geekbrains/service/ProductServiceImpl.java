package ru.geekbrains.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import ru.geekbrains.controller.ProductSpecifications;
import ru.geekbrains.controller.UserSpecifications;
import ru.geekbrains.dto.ProductDto;
import ru.geekbrains.dto.UserDto;
import ru.geekbrains.persist.Product;
import ru.geekbrains.persist.ProductRepository;
import ru.geekbrains.persist.User;
import ru.geekbrains.persist.UserRepository;

import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Page<ProductDto> findProductsByFilter(String titleFilter, Integer page, Integer size, String sortField, String sortDirection) {

        // фильтр через CriteriaBuilder и спецификации
        Specification<Product> spec = Specification.where(null);
        if (titleFilter != null) {
            spec = spec.and(ProductSpecifications.titleContaining(titleFilter));
        }
        return productRepository.findAll(spec, PageRequest.of(page, size, Sort.by("id")))
                .map(ProductServiceImpl::productToDto);
    }

    @Override
    public Optional<ProductDto> findById(long id) {
        return productRepository.findById(id).map(ProductServiceImpl::productToDto);
    }

    @Override
    public ProductDto save(ProductDto product) {
        return productToDto(
                productRepository.save(
                        new Product (product.getTitle(), product.getDescription(), product.getPrice())
                )
        );
    }

    @Override
    public void deleteById(long id) {
        productRepository.deleteById(id);
    }

    private static ProductDto productToDto (Product product) {
        return new ProductDto(product.getId(), product.getTitle(), product.getDescription(), product.getPrice());
    }
}
