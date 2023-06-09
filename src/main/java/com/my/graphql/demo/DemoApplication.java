package com.my.graphql.demo;

import com.my.graphql.demo.accessor.RequestAttributesAccessor;
import io.micrometer.context.ContextRegistry;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        ContextRegistry contextRegistry = ContextRegistry.getInstance();
        contextRegistry.registerThreadLocalAccessor(new RequestAttributesAccessor() );
        contextRegistry.loadThreadLocalAccessors();
    }
}