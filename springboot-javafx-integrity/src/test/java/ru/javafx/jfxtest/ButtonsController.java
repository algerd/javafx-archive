package ru.javafx.jfxtest;

import ru.javafx.jfxintegrity.FXMLController;

@FXMLController
public class ButtonsController  {

    public void topButtonClicked() {
        System.out.println("Du hast den topButton geklickt!");
    }
    
    public void clickMiddleButton() {
        System.out.println("MiddleButton!");
    }
}
