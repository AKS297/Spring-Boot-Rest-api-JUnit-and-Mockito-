package com.example.example4.service;

import com.example.example4.ProductRepo.ProductRepository;
import com.example.example4.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProductRepository repository;

    public Product saveProduct(Product product){
        return repository.save(product);
    }
    public List<Product> saveProducts(List<Product> products){
        return repository.saveAll(products);
    }
    public List<Product> getProducts(){
        return  repository.findAll();
    }
    public  Product getProduct(int id)
    {
        return repository.findById(id).orElse(null);
    }

    public Product getProductByName(String name){
        return  repository.findByName(name);
    }

    public String deleteById(int id){
        repository.deleteById(id);
        return "Product removed..!"+id;
    }
 /*   public Product updateProduct(Product product){
        Product exproduct=repository.findById(product.getId()).orElse(null);
        exproduct.setName(product.getName());
        exproduct.setQuantity(product.getQuantity());
        exproduct.setId(product.getId());
        exproduct.setPrice(product.getPrice());
        return repository.save(exproduct);
    }*/
}
