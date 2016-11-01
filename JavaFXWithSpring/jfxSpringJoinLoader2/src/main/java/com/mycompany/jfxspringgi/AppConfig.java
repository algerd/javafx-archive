
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
    
    /*
    Если создавать бин контроллера, то SpringFxmlLoader будет возвращать этот бин.
    Иначе он будет создавать на лету объект контроллера и связывать его с зависимыми или инжектированными в него объектами,
    но тогда нельзя будет вызвать бин этого контроллера в другом объекте.
    В данном примере SpringFxmlLoader вызовет бин MainController и создаст и свяжет контроллер PaneController   
    */
    @Bean
    public MainController mainController() {
        return new MainController();
    }
    /*
    @Bean
    public PaneController mainController() {
        return new PaneController();
    }
    */   
}
