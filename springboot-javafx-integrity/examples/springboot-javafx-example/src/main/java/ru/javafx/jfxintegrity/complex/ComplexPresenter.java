package ru.javafx.jfxintegrity.complex;

import org.springframework.beans.factory.annotation.Autowired;
import ru.javafx.jfxintegrity.FXMLController;
import ru.javafx.jfxintegrity.buttons.ButtonsView;
import ru.javafx.jfxintegrity.list.SomeListView;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ScrollPane;

@FXMLController
public class ComplexPresenter {

    @Autowired
    private ButtonsView buttonsView;
   
    @Autowired
    private SomeListView someListView;
    
    @FXML
    private ScrollPane scrollPane;
    
    @FXML
    private void aboutMenuItem() {
        System.out.println("Du hast etwas ausgewaehlt!");
        scrollPane.setContent(buttonsView.getView());
    }

    @FXML
    private void showSomeList() {
        scrollPane.setContent(someListView.getView());
    }

    @FXML
    private void showAlert() {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText("Look, an Information Dialog");
        alert.setContentText("I have a great message for you!");
        alert.showAndWait();
    }
}
