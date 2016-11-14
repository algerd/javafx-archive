package ru.javafx.jfxintegrity.buttons;

import org.springframework.beans.factory.annotation.Autowired;

import ru.javafx.jfxintegrity.FXMLController;
import ru.javafx.jfxintegrity.Starter;
import javafx.fxml.FXML;

@FXMLController
public class ButtonsController  {

	@Autowired
	private Starter starter;
	
    @FXML
    private void topButtonClicked() {
        System.out.println("Du hast den topButton geklickt!");
    }
    
    @FXML
    private void clickMiddleButton() {
        System.out.println("MiddleButton!");
        System.out.println("ApplicationHash: " + starter.hashCode());
    }
}
