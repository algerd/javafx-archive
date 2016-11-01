package choicecheckbox;

import java.util.HashMap;
import java.util.Map;
import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.DoubleBinding;
import javafx.beans.binding.NumberBinding;
import javafx.beans.binding.When;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.cell.CheckBoxListCell;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.util.Callback;

public class ChoiceCheckBox extends Application {
    
    private boolean showing = false;
    private ChoiceBox<String> choiceBox = new ChoiceBox<>();
    private AnchorPane listViewPane = new AnchorPane();
    private ListView<String> listView = new ListView<>();
    private Map<String, ObservableValue<Boolean>> map = new HashMap<>();
    
    
    
    @Override
    public void start(Stage stage) { 
        initChoiceBox();
        initListView();
                    
        listViewPane.getChildren().add(listView);
        
        HBox hb = new HBox();
        hb.getChildren().addAll(new Label("Item List: "), choiceBox);
        
        AnchorPane root = new AnchorPane();
        root.getChildren().addAll(hb, listViewPane);                 
            
        Scene scene = new Scene(root);
        stage.setScene(scene);
		stage.setTitle("ChoiceCheckBox");
		stage.show();                     
    }
    
    private void initChoiceBox() {
        choiceBox.setPrefWidth(200);
        choiceBox.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> handle(e));
    }
    
    public void handle(MouseEvent e) {
        showing = !showing;
        listView.setVisible(showing);
        placeListView(choiceBox);
    }
    
    private void initListView() {
		map.put("Apple", new SimpleBooleanProperty(false));
		map.put("Banana", new SimpleBooleanProperty(false));
		map.put("Donut", new SimpleBooleanProperty(false));
		map.put("Hash Brown", new SimpleBooleanProperty(false));
        
		listView.setEditable(true);
		listView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);       
        listView.getItems().addAll(map.keySet());		
		// Create a Callback object
		Callback<String, ObservableValue<Boolean>> itemToBoolean = (String item) -> map.get(item);		
		// Set the cell factory
		listView.setCellFactory(CheckBoxListCell.forListView(itemToBoolean));
        setHeightList(listView, 10);
        
        listView.setVisible(false);
        listView.prefWidthProperty().bind(choiceBox.widthProperty());
    }
    
    //Helper
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
    
    public void placeListView(Node newNode) {
        // It gets the (x, y) coordinates of the top left corner of the
        // bounding box of the node in focus in the local coordinate space:
		double nodeMinX = newNode.getLayoutBounds().getMinX();
		double nodeMaxY = newNode.getLayoutBounds().getMaxY();
        
        // It transforms the coordinates of the top left corner of the node from the local coordinate space to the
        //coordinate space of the scene:
		Point2D nodeInScene = newNode.localToScene(nodeMinX, nodeMaxY);
        
        // Now the coordinates of the top left corner of the node are transformed from the coordinate space of the
        // scene to the coordinate space of the searchList:
		Point2D nodeInListViewLocal = listViewPane.sceneToLocal(nodeInScene);
        
        // Finally, the coordinate of the top left corner of the node is transformed to the coordinate space of the
        // parent of the searchList:
		Point2D nodeInListViewParent = listViewPane.localToParent(nodeInListViewLocal);
        
        // Position the circle approperiately
		listViewPane.relocate(
            nodeInListViewParent.getX() + listViewPane.getLayoutBounds().getMinX(),
			nodeInListViewParent.getY() + listViewPane.getLayoutBounds().getMinY()
        );     
    }

    public static void main(String[] args) {
        launch(args);
    }
    
}
