package com.algerd.testjfxspring;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import org.springframework.beans.factory.annotation.Autowired;

public class MainController implements Initializable {
    
    @Autowired
    private Service service;
    
    @FXML
    private Label label;
    
    @FXML
    private void handleButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");
        label.setText(service.getText());
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }    
}
