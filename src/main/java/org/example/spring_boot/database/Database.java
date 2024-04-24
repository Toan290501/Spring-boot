package org.example.spring_boot.database;

import org.example.spring_boot.models.Products;
import org.example.spring_boot.repositories.ProductRepository;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.logging.Logger;

@Configuration
public class Database {
    private static final Logger logger = Logger.getLogger(String.valueOf(Database.class));

    @Bean
    CommandLineRunner initDatabase(ProductRepository productRepository) {

        return new CommandLineRunner() {
            @Override
            public void run(String... args) throws Exception {

            }
        };
    }
}
