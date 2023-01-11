package com.appreservas;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import com.appreservas.generated.GeneratedReservasConfiguration;

/**
 * The spring configuration file
 * <p>
 * This file is safe to edit. It will not be overwritten by the code generator.
 * 
 * @author company
 */
@Configuration
public class ReservasConfiguration extends GeneratedReservasConfiguration {
    @Autowired
    public Environment env;

    @Value("server.port")
    public String Port;
}