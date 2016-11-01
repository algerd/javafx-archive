package com.algerd.testjfxspring;

import java.io.IOException;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;


public class Main extends Application {
    public static void main(String[] args) {
        launch(args);
    }
        
    @Override
    public void start(Stage primaryStage) throws IOException {
        FXMLLoader loader = SpringFxmlLoader.getLoader("/fxml/Main.fxml");
        
        Parent root = (Parent) loader.load();
        MainController mainController = loader.getController();
        
        Scene scene = new Scene(root, 768, 480);
        primaryStage.setScene(scene);
        primaryStage.setTitle("JavaFX demo");
        primaryStage.show();
    }

}
