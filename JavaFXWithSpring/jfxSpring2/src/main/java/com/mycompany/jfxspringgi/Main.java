package com.mycompany.jfxspringgi;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import static javafx.application.Application.launch;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;


public class Main extends Application {
    public static void main(String[] args) {
        launch(args);
    }
        
    private MainController mainController; 
    private Stage primaryStage; 
    
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader loader = SpringFxmlLoader.getLoader("/fxml/Main.fxml");       
        BorderPane root = loader.<BorderPane>load();
        mainController = loader.getController();
            
        loadPane();
              
        Scene scene = new Scene(root);
        primaryStage = stage;
        primaryStage.setScene(scene);
        primaryStage.setTitle("JavaFX with Spring");
        primaryStage.show();
    }
    
    public void loadPane() throws IOException {
        FXMLLoader loader = SpringFxmlLoader.getLoader("/fxml/Pane.fxml");       
        AnchorPane page = loader.<AnchorPane>load();
        PaneController paneController = loader.getController();
        
        mainController.show(page);                                  
    }


}
