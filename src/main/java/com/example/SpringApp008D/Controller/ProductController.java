package com.example.SpringApp008D.Controller;

import com.example.SpringApp008D.Model.Product;
import com.example.SpringApp008D.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping
    public String getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/{id}")
    public String getProductById(@PathVariable int id) {
        return productService.getProductById(id);
    }

    @PostMapping
    public String addProduct(@RequestBody Product product) {
        return productService.addProduct(product);
    }

    @DeleteMapping("/{id}")
    public String deleteProduct(@PathVariable int id) {
        return productService.deleteProduct(id);
    }
    @PutMapping("/{id}")
    public String updateProduct(@PathVariable int id, @RequestBody Product product) {
        return productService.updateProduct(id, product);
    }
}
