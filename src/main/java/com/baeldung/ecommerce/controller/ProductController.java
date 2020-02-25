package com.baeldung.ecommerce.controller;

import com.baeldung.ecommerce.exception.ResourceNotFoundException;
import com.baeldung.ecommerce.model.Product;
import com.baeldung.ecommerce.repository.ProductRepository;
import com.baeldung.ecommerce.service.ProductService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@RestController 
@RequestMapping("/api/products")
public class ProductController {

    private ProductService productService;
    @Autowired
    private ProductRepository productRepository;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping(value = { "", "/" })
    public @NotNull Iterable<Product> getProducts() {
        return productService.getAllProducts();
    }
   /* 
    @GetMapping(value = {"","/{id}"})
    public ResponseEntity<Product> getProductById(@PathVariable(value = "id") Long productId)
        throws ResourceNotFoundException {
        Product product = productRepository.findById(productId)
          .orElseThrow(() -> new ResourceNotFoundException("Products not found for this id :: " + productId));
        return ResponseEntity.ok().body(product);
    }
    */
    @PostMapping("/")
    public Product createProduct(@Valid @RequestBody Product product) {
        return productRepository.save(product);
    }
    
    @PutMapping(value = {"","/{id}"})
    public ResponseEntity<Product> updateProduct(@PathVariable(value = "id") Long productId,
         @Valid @RequestBody Product productDetails) throws ResourceNotFoundException {
        Product product = productRepository.findById(productId)
        .orElseThrow(() -> new ResourceNotFoundException("Product not found for this id :: " + productId));

        product.setName(productDetails.getName());
        product.setPictureUrl(productDetails.getPictureUrl());
        product.setPrice(productDetails.getPrice());
        final Product updatedProduct = productRepository.save(product);
        return ResponseEntity.ok(updatedProduct);
    }
    
    @DeleteMapping(value = {"","/{id}"})
    public Map<String, Boolean> deleteProduct(@PathVariable(value = "id") Long productId)
         throws ResourceNotFoundException {
        Product product = productRepository.findById(productId)
       .orElseThrow(() -> new ResourceNotFoundException("Product not found for this id :: " + productId));

        productRepository.delete(product);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}
