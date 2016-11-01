package com.mycompany.jfxspringgi;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import org.springframework.beans.factory.annotation.Autowired;

public class PaneController implements Initializable {
    
    @Autowired
    private Service service;

    @FXML
    private AnchorPane anchorPane;
   
    @FXML
    private Label label;
    
    @FXML
    private void handleButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");
        label.setText("PaneController" + service.getText());
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }  
   
}
