package ru.javafx.jfxintegrity;

import java.io.IOException;
import java.net.URL;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import javafx.application.Platform;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;

public abstract class BaseFxmlView implements ApplicationContextAware, FXMLViewable {

	private static final String FXML_PATH = "/fxml/";
    
	protected ObjectProperty<Object> presenterProperty = new SimpleObjectProperty<>();
	protected StringProperty title = new SimpleStringProperty();
	protected FXMLLoader fxmlLoader;
	protected ResourceBundle resourceBundle;
	protected URL resource;  
	private ApplicationContext applicationContext;
	private String fxmlRoot;
    
    public BaseFxmlView() {
		this(FXML_PATH);
	}

	public BaseFxmlView(String path) {
		fxmlRoot = path.endsWith("/") ? path : path + "/";   
		FXMLView annotation = getFxmlAnnotation();
        String fxmlPath = (annotation == null || annotation.value().equals("")) ?
            fxmlRoot + getConventionalName(".fxml") : annotation.value();
        resource = getClass().getResource(fxmlPath);      
		resourceBundle = getResourceBundle(getBundleName());
	}
    
	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		if (this.applicationContext == null) {
			this.applicationContext = applicationContext;
		}		
	}

    @Override
    public Parent getView() {
		initializeFxmlLoader();
		Parent parent = fxmlLoader.getRoot();
		addCss(parent);
		return parent;
	}
       
    @Override
	public void getView(Consumer<Parent> consumer) {
		CompletableFuture.supplyAsync(this::getView, Platform::runLater).thenAccept(consumer);
	}
    
    @Override
    public Node getChildView() {
		ObservableList<Node> children = getView().getChildrenUnmodifiable();
		return !children.isEmpty() ? children.listIterator().next() : null;
	}
    
    @Override
	public Object getPresenter() {
		initializeFxmlLoader();
		return presenterProperty.get();
	}

	public void getPresenter(Consumer<Object> presenterConsumer) {
		presenterProperty.addListener((observableValue, oldValue, newValue) -> presenterConsumer.accept(newValue));
	}
    
    public ResourceBundle getResourceBundle() {
		return resourceBundle;
	}
	
	public void setTitle(String title) {
	    this.title.setValue(title);
	}
	
	public StringProperty titleProperty() {
	    return title;
	}
    
	private FXMLView getFxmlAnnotation() {
		return getClass().getAnnotation(FXMLView.class);
	}

    private void initializeFxmlLoader() {
		if (fxmlLoader == null) {
			fxmlLoader = loadSynchronously(resource, resourceBundle);
            presenterProperty.set(fxmlLoader.getController());
		}	
	}
    
	private FXMLLoader loadSynchronously(URL resource, ResourceBundle bundle) throws IllegalStateException {
		FXMLLoader loader = new FXMLLoader(resource, bundle);
        loader.setControllerFactory(applicationContext::getBean);
		try {
			loader.load();
		} catch (IOException ex) {
			throw new IllegalStateException("Cannot load " + getConventionalName(), ex);
		}
		return loader;
	}

	private void addCss(Parent parent) {
        FXMLView annotation = getFxmlAnnotation();
		if (annotation != null && annotation.css().length > 0) {
			for (String cssFile : annotation.css()) {
				parent.getStylesheets().add(getClass().getResource(cssFile).toExternalForm());
			}
		}
        else {
            URL uri = getClass().getResource(fxmlRoot + getConventionalName(".css"));
            if (uri != null) {
                parent.getStylesheets().add(uri.toExternalForm());
            }
        }    
	}

	private String getConventionalName(String ending) {
		return getConventionalName() + ending;
	}

	private String getConventionalName() {
        String clazz = getClass().getSimpleName().toLowerCase();
        return !clazz.endsWith("view") ? clazz : clazz.substring(0, clazz.lastIndexOf("view"));
	}

	private String getBundleName() {
        FXMLView annotation = getFxmlAnnotation();
        return (annotation == null || annotation.bundle().equals("")) ?
            getClass().getPackage().getName() + "." + getConventionalName() :    
            annotation.bundle();    
	}

	private ResourceBundle getResourceBundle(String name) {
		try {
			return ResourceBundle.getBundle(name);
		} catch (MissingResourceException ex) {
			return null;
		}
	}

}
