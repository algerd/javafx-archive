
package com.algerd.testjfxspring;

import java.net.URL;
import javafx.fxml.FXMLLoader;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class SpringFxmlLoader {

    private static final ApplicationContext APPLICATION_CONTEXT = new AnnotationConfigApplicationContext(AppConfig.class);

    public static FXMLLoader getLoader(String url) {
        URL fxmlUrl = SpringFxmlLoader.class.getResource(url);
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(fxmlUrl);     
        loader.setControllerFactory(typeClass -> APPLICATION_CONTEXT.getBean(typeClass));       
        return loader;
    }
    
}
