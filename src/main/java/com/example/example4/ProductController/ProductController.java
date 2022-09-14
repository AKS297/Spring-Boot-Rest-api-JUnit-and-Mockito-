package com.example.example4.ProductController;

import com.example.example4.entity.Product;
import com.example.example4.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin
@RestController
@RequestMapping("/ProductService")
public class ProductController {
    @Autowired
    private ProductService service;
    @PostMapping("/addProduct")
    public Product addProduct(@RequestBody Product product){
        return service.saveProduct(product);
    }
    @PostMapping("/addProducts")
    public List<Product> addProducts(@RequestBody List<Product> products){
        return service.saveProducts(products);
    }
       @GetMapping("/products")
       public List<Product> findAllProducts(){
        return service.getProducts();
       }
      @GetMapping("/product/{id}")
       public Product findProductById(@PathVariable int id){
        return  service.getProduct(id);
       }

 /*   @GetMapping("/product/{id}")
     public ResponseEntity<Product> findProductById(@PathVariable Integer id){
         return new ResponseEntity<>(service.getProduct(id),HttpStatus.OK); }
*/

       @GetMapping("/product/{name}")
       public Product findProductByName(@PathVariable String name){
        return service.getProductByName(name);
       }

   /*    @PutMapping("/update")
       public Product upadeteProduct(@RequestBody Product product){
        return  service.updateProduct(product);} */
       
       @DeleteMapping("/delete/{id}")
       public String deleteProduct(@PathVariable int id){
        return  service.deleteById(id);
       }
}
