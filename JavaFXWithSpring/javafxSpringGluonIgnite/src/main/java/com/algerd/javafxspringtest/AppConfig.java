
package com.algerd.javafxspringtest;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
    
    @Bean
    public Service provideService() {
        return new Service();
    }

}
