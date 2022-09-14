package com.example.example4;

import com.example.example4.ProductRepo.ProductRepository;
import com.example.example4.entity.Product;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class Example4ApplicationTests {

	@Autowired
	ProductRepository productRepo;

	@Test
	@Order(1)
	public void testCreate(){
		Product product=new Product();
		product.setId(1);
		product.setName("mobile");
		product.setPrice(2000.0);
		product.setQuantity(2);
		productRepo.save(product);
		assertNotNull(productRepo.findById(1).get());
	}

	@Test
	@Order(2)
	public void testProductlist(){
		List<Product>list=productRepo.findAll();
		assertThat(list) .size().isGreaterThan(0);
	}
      @Test
	  @Order(3)
	public void testSingleProduct(){
		Product product=productRepo.findById(1).get();
		assertEquals(2000.0,product.getPrice());
	}
	@Test
	@Order(4)
	public  void testUpdate(){
		Product product=productRepo.findById(1).get();
		product.setPrice(3000.0);
		productRepo.save(product);
		assertNotEquals(2000.0,product.getPrice());
	}
	@Test
    @Order(5)
	public void testDelete(){
		productRepo.deleteById(1);
		assertThat(productRepo.existsById(1)).isFalse();
	}

}
