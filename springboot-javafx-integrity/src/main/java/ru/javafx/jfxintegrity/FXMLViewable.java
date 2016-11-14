
package ru.javafx.jfxintegrity;

import java.util.ResourceBundle;
import java.util.function.Consumer;
import javafx.beans.property.StringProperty;
import javafx.scene.Node;
import javafx.scene.Parent;

public interface FXMLViewable {
    
    Parent getView();
    
    /**
	 * Initializes the view synchronously and invokes the consumer with the
	 * created parent Node within the FX UI thread.
	 * @param consumer - an object interested in received the {@link Parent} as callback
	 */
    void getView(Consumer<Parent> consumer);
    
    Node getChildView();
    
    /**
	 * In case the view was not initialized yet, the conventional fxml
	 * (airhacks.fxml for the HelloView and HelloPresenter) are loaded and
	 * the specified presenter / controller is going to be constructed and
	 * returned.
	 * @return the corresponding controller / presenter (usually for a HelloView the HelloPresenter)
	 */
	Object getPresenter();
    
    /**
	 * Does not initialize the view. Only registers the Consumer and waits until
	 * the the view is going to be created / the method FXMLView#getView or
	 * FXMLView#getViewAsync invoked.
	 * @param presenterConsumer listener for the presenter construction
	 */
    void getPresenter(Consumer<Object> presenterConsumer);
    
    ResourceBundle getResourceBundle();
    
    void setTitle(String title);
    
    StringProperty titleProperty();
   
}
