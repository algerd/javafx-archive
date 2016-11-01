package com.mycompany.jfxspringgi;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import org.springframework.beans.factory.annotation.Autowired;

public class MainController implements Initializable {
    
    @Autowired
    private Service service;
    
    @FXML
    private StackPane mainWindow;
    
    @FXML
    private Label label;
    
    @FXML
    private void handleButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");
        label.setText("MainController" + service.getText());
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    public void show(AnchorPane pane) {   
        mainWindow.getChildren().clear();
        mainWindow.getChildren().add(pane);
    } 
}
