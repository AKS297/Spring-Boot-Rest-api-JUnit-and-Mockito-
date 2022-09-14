package com.example.example4;



import com.example.example4.ProductController.ProductController;
import com.example.example4.ProductRepo.ProductRepository;
import com.example.example4.entity.Product;
import com.example.example4.service.ProductService;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Order;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.internal.bytebuddy.matcher.ElementMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)

@WebMvcTest(ProductController.class)
public class controllertest {
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext context;



    @Mock
    private ProductRepository productRepository;

    @MockBean
    private ProductService productService;

    Product product1=new Product(1,"phone",2,2000.0);
    Product product2=new Product(1,"tv",1,12000.0);
    Product product3=new Product(1,"pen",2,20.0);

    ObjectMapper objectMapper = new ObjectMapper();

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @Test
    @Order(1)
    public void addProduct() throws Exception {
        Product product = new Product();
        product.setId(1);
        product.setName("phone");
        product.setPrice(2000.0);
        product.setQuantity(2);

        when(productService.saveProduct(any(Product.class))).thenReturn(product);

        mockMvc.perform(MockMvcRequestBuilders.post("/ProductService/addProduct")
                .content (objectMapper.writeValueAsString(product))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("phone"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.price").value("2000.0"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.quantity").value("2"));
    }

    @Test
    public void testAllProducts() throws Exception {
        List<Product> record=new ArrayList<>(Arrays.asList(product1,product2,product3));
      Mockito.  when(productRepository.findAll()).thenReturn(record);
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/ProductService/products")

                .contentType(MediaType.APPLICATION_JSON))


                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$",hasSize(3)));


    }

    @Test
    public void deleteproduct() throws Exception {
        when(productRepository.findById(product1.getId())).thenReturn(Optional.of(product2));
        mockMvc.perform(MockMvcRequestBuilders
                .delete("/ProductService/delete/2")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

    }

}
