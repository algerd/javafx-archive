package choicecheckboxfxml;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.AnchorPane;

public class ChoiceCheckBoxController implements Initializable {
        
    @FXML
    private ChoiceBox<String> choiceBox;
    
    @FXML
    private AnchorPane checkBoxListView;
    @FXML
    private CheckBoxListViewController includedCheckBoxListViewController;
       
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        includedCheckBoxListViewController.setChoiceBox(choiceBox); 
    }

    public CheckBoxListViewController getIncludedCheckBoxListViewController() {
        return includedCheckBoxListViewController;
    }
    
}
