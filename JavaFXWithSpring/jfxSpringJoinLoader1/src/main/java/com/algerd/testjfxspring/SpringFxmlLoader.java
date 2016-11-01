
package com.algerd.testjfxspring;

import java.net.URL;
import javafx.fxml.FXMLLoader;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class SpringFxmlLoader {

    private static final ApplicationContext APPLICATION_CONTEXT = new AnnotationConfigApplicationContext(AppConfig.class);

    public static FXMLLoader getLoader(String url) {
        URL fxmlUrl = SpringFxmlLoader.class.getResource(url);
        FXMLLoader loader = new FXMLLoader(); 
        loader.setLocation(fxmlUrl);
        loader.setControllerFactory(type -> {
            Object instance = null;          
            try {
                instance = APPLICATION_CONTEXT.getBean(type);
                //System.out.println("bean: " + instance);
            } catch (NoSuchBeanDefinitionException ex) {
                try {
                    instance = type.newInstance();
                    APPLICATION_CONTEXT.getAutowireCapableBeanFactory().autowireBean(instance);
                    //System.out.println("autowireBean: " + instance);
                } catch (InstantiationException | IllegalAccessException e) {
                    e.printStackTrace();
                }
            } 
            if (instance == null) {
                throw new NullPointerException("Bean is not exist");
            }
            return instance;
        });             
        return loader;
    }
    
}
