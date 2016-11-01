package com.algerd.javafxspringtest;

import com.gluonhq.ignite.spring.SpringContext;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import javax.inject.Inject;
import java.io.IOException;
import java.net.URL;
import java.util.Arrays;

public class SpringApp extends Application {
    public static void main(String[] args) {
        launch(args);
    }
    
    //private final SpringContext context = new SpringContext(this, () -> Arrays.asList("com.algerd.javafxspringtest"));
    private final SpringContext context = new SpringContext(this, () -> Arrays.asList(SpringApp.class.getPackage().getName()));

    @Inject
    FXMLLoader fxmlLoader;

    @Override
    public void start(Stage primaryStage) throws IOException {
        context.init();
        URL fxmlUrl = getClass().getResource("/fxml/view.fxml");
        fxmlLoader.setLocation(fxmlUrl);
        Parent view = fxmlLoader.<Parent>load();
        ViewController viewController = fxmlLoader.getController();
        
        System.out.println(viewController);
   
        Scene scene = new Scene(view);
        scene.getStylesheets().add("/styles/Styles.css");
        primaryStage.setScene(scene);
        primaryStage.setTitle("Spring Example");
        primaryStage.show();
    }
}
