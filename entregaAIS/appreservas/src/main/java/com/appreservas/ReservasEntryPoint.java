package com.appreservas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ConfigurableApplicationContext;

import com.appreservas.security.RsaKeyProperties;

/**
 * The entry point for the {@link com.speedment.runtime.config.Project} named
 * appreservas
 * <p>
 * This file is safe to edit. It will not be overwritten by the code generator.
 * 
 * @author company
 */
@EnableConfigurationProperties(RsaKeyProperties.class)
@SpringBootApplication
public class ReservasEntryPoint {
    public static ConfigurableApplicationContext app;
    
    public static void main(String... args) {
        app = SpringApplication.run(ReservasEntryPoint.class, args);
    }
}