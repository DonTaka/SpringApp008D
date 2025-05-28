package com.example.SpringApp008D;

import com.example.SpringApp008D.Model.Product;
import com.example.SpringApp008D.Repository.ProductRepository;
import com.example.SpringApp008D.Service.ProductService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

@SpringBootTest
@AutoConfigureMockMvc
public class ProductTest {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    MockMvc mockMvc;

    @MockitoBean
    ProductService productService;

    @Test
    void findAllProductsTest() {
        List<Product> productos = productRepository.findAll();
        assertNotNull(productos);
        assertEquals(1, productos.size());
    }

    @Test
    void checkNameProductTest() {
        Product product = productRepository.findById(1).get();
        assertNotNull(product);
        assertEquals("Telefono", product.getName());
    }

    @Test
    void getAllProductsControllerTest() {
        //When
        Mockito.when(productService.getAllProducts()).thenReturn("ListaCompleta");
        try {
            //Intentar ejecutar un codigo
            mockMvc.perform(get("/products"))
                    .andExpect(status().isOk())
                    .andExpect(content().string("ListaCompleta"));
        } catch (Exception e) {
            //Capturar la excepcion
            System.out.println("Error: " + e.getMessage());
            fail();
        }
    }
}
