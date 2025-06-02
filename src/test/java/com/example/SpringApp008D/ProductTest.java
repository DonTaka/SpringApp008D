package com.example.SpringApp008D;

import com.example.SpringApp008D.Model.Product;
import com.example.SpringApp008D.Repository.ProductRepository;
import com.example.SpringApp008D.Service.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.context.bean.override.mockito.MockitoBeans;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;

@SpringBootTest
public class ProductTest {

    @Autowired
    ProductRepository productRepository;

    @MockitoBean
    ProductService productService;

    @Test
    void findAllTest(){
        List<Product> products = productRepository.findAll();

        assertNotNull(products);
        assertEquals(1, products.size());
    }

    @Test
    void checkNameProduct(){
        Product product = productRepository.findById(1).get();
        assertNotNull(product);
        assertEquals("Telefono", product.getName());
    }
}
