
package com.mycompany.jfxspringgi;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("com.mycompany.jfxspringgi")
public class AppConfig {
    
    @Bean
    public Service provideService() {
        return new Service();
    }
    
    @Bean
    public MainController mainController() {
        return new MainController();
    } 
    
    @Bean
    public PaneController paneController() {
        return new PaneController();
    }
    
       
}
