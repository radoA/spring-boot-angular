package com.baeldung.ecommerce;

import com.baeldung.application.Application;
import com.baeldung.application.entities.User;
import com.baeldung.application.repositories.UserRepository;
import com.baeldung.ecommerce.model.Product;
import com.baeldung.ecommerce.service.ProductService;

import java.util.stream.Stream;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class EcommerceApplication {

    public static void main(String[] args) {
        SpringApplication.run(EcommerceApplication.class, args);
        //SpringApplication.run(Application.class, args);
    }

    @Bean
    CommandLineRunner runner(ProductService productService) {
        return args -> {
            productService.save(new Product(1L, "TV Set", 300.00, "http://placehold.it/200x100"));
            productService.save(new Product(2L, "Game Console", 200.00, "http://placehold.it/200x100"));
            productService.save(new Product(3L, "Sofa", 100.00, "http://placehold.it/200x100"));
            productService.save(new Product(4L, "Icecream", 5.00, "http://placehold.it/200x100"));
            productService.save(new Product(5L, "Beer", 3.00, "http://placehold.it/200x100"));
            productService.save(new Product(6L, "Phone", 500.00, "http://placehold.it/200x100"));
            productService.save(new Product(7L, "Watch", 30.00, "http://placehold.it/200x100"));
            
            
        };
    }
    /*
    @Bean
    CommandLineRunner init(UserRepository userRepository) {
        return args -> {
            Stream.of("John", "Julie", "Jennifer", "Helen", "Rachel").forEach(name -> {
                User user = new User(name, name.toLowerCase() + "@domain.com");
                userRepository.save(user);
            });
            userRepository.findAll().forEach(System.out::println);
        };
    }
  */
}
