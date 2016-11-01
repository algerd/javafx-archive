package choicecheckboxfxml;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.DoubleBinding;
import javafx.beans.binding.NumberBinding;
import javafx.beans.binding.When;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import javafx.scene.control.cell.CheckBoxListCell;
import javafx.scene.layout.AnchorPane;
import javafx.util.Callback;

public class CheckBoxListViewController implements Initializable {
    
    private final int COUNT_VISIBLE_RAW = 10;
    private ChoiceBox choiceBox;
    private final Map<String, ObservableValue<Boolean>> map = new HashMap<>();   
    private final BooleanProperty showing = new SimpleBooleanProperty(false);   
    
    @FXML
    private AnchorPane checkBoxListView;
    @FXML
    private ListView<String> listView;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {         
		Callback<String, ObservableValue<Boolean>> itemToBoolean = item -> map.get(item);		
		listView.setCellFactory(CheckBoxListCell.forListView(itemToBoolean));
        setHeightList(listView, COUNT_VISIBLE_RAW); 
        
        listView.visibleProperty().bind(showing);      
        showing.addListener((observable, oldValue, newValue) -> {
            placeListView(choiceBox);
            if (isShowing()) checkBoxListView.toFront();               
            else checkBoxListView.toBack();
        });
        // при выборе элемента в списке активируется CheckBox
        listView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            ((SimpleBooleanProperty) map.get(newValue)).set(true);
        });
    } 
    
    public void addItems(Map<String, ObservableValue<Boolean>> map) {
        this.map.putAll(map);
        listView.getItems().addAll(map.keySet());	
    } 
    
    public void setChoiceBox(ChoiceBox choiceBox) {
        this.choiceBox = choiceBox;
        showingProperty().bind(choiceBox.showingProperty());
        listView.prefWidthProperty().bind(choiceBox.prefWidthProperty());
    }
    
    //Helper - external
    public void setHeightList(ListView listView, int maxCountVisibleRows) {      
        listView.setFixedCellSize(25);
        
        NumberBinding maxFactor =   
            new When(Bindings.size(listView.getItems()).greaterThan(maxCountVisibleRows)).
                then(maxCountVisibleRows).
                otherwise(Bindings.size(listView.getItems())); 
        
        DoubleBinding factor = 
            new When(Bindings.size(listView.getItems()).isEqualTo(0)).
                then(1.05).
                otherwise(0.05);
        
        listView.prefHeightProperty().bind(listView.fixedCellSizeProperty().multiply(maxFactor.add(factor)));        
        listView.minHeightProperty().bind(listView.prefHeightProperty());
        listView.maxHeightProperty().bind(listView.prefHeightProperty());       
    }
    
    // Helper - external
    private void placeListView(Node newNode) {
        // It gets the (x, y) coordinates of the top left corner of the
        // bounding box of the node in focus in the local coordinate space:
		double nodeMinX = newNode.getLayoutBounds().getMinX();
		double nodeMaxY = newNode.getLayoutBounds().getMaxY();
        
        // It transforms the coordinates of the top left corner of the node from the local coordinate space to the
        //coordinate space of the scene:
		Point2D nodeInScene = newNode.localToScene(nodeMinX, nodeMaxY);
        
        // Now the coordinates of the top left corner of the node are transformed from the coordinate space of the
        // scene to the coordinate space of the searchList:
		Point2D nodeInListViewLocal = checkBoxListView.sceneToLocal(nodeInScene);
        
        // Finally, the coordinate of the top left corner of the node is transformed to the coordinate space of the
        // parent of the searchList:
		Point2D nodeInListViewParent = checkBoxListView.localToParent(nodeInListViewLocal);
        
        // Position the circle approperiately
		checkBoxListView.relocate(
            nodeInListViewParent.getX() + checkBoxListView.getLayoutBounds().getMinX(),
			nodeInListViewParent.getY() + checkBoxListView.getLayoutBounds().getMinY()
        );     
    }
    
    public boolean isShowing() {
        return showing.get();
    }
    public void setShowing(boolean value) {
        showing.set(value);
    }
    public BooleanProperty showingProperty() {
        return showing;
    }
    
}
