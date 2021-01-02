package ru.pankov.store.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.pankov.store.entity.Product;
import ru.pankov.store.service.ProductService;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping
    public List<Product> productList(@RequestParam(value = "min", defaultValue = "0") BigDecimal min,
                                     @RequestParam(value = "max", defaultValue = "999999") BigDecimal max) {
        return productService.findAll(min, max);
    }

    @GetMapping("/{id}")
    public Product product(@PathVariable Long id) {
        return productService.findById(id).orElseThrow(() -> new RuntimeException("There is no product with id = " + id));
    }

    @PostMapping("/")
    public Product addProduct(@RequestBody Product product) {
        productService.save(product);

        return product;
    }

    @PutMapping("/")
    public Product updProduct(@RequestBody Product product) {
        productService.save(product);
        return product;
    }

    @DeleteMapping("/{id}")
    public void delProduct(@PathVariable Long id) {
        productService.deleteById(id);
    }
}