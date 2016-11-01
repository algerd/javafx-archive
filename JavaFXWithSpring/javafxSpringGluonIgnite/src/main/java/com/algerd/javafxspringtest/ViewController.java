package com.algerd.javafxspringtest;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import javax.inject.Inject;
import java.net.URL;
import java.util.ResourceBundle;

public class ViewController implements Initializable {

    @Inject 
    private Service service;
    @FXML 
    private Label label;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        label.setText(service.getText());
    }
    
    @Override
    public String toString() {
        return "ViewController";
    }
}
