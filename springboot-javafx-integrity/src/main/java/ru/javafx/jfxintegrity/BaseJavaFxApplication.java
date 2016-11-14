package ru.javafx.jfxintegrity;

import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public abstract class BaseJavaFxApplication extends Application {

	private static String[] initialArgs;
	private static Class<? extends BaseFxmlView> initialView;

	private ConfigurableApplicationContext applicationContext;
	private Stage stage;

	@Override
	public void init() throws Exception {
		applicationContext = SpringApplication.run(getClass(), initialArgs);
	}

	@Override
	public void start(Stage stage) throws Exception {
		this.stage = stage;
        showInitialView();
        this.stage.show();
	}
    
    /*
    override or extend in child class:
        @Override
        showInitialView() {
            super.showInitialView();
            stage.setResizable(true);
            stage.centerOnScreen();
        }    
    */
    public void showInitialView() {
        BaseFxmlView view = applicationContext.getBean(initialView);
        stage.titleProperty().bind(view.titleProperty());
        Scene scene = new Scene(view.getView());
        stage.setScene(scene);      
    }

	@Override
	public void stop() throws Exception {
		super.stop();
		applicationContext.close();
	}

	protected static void launchApp(
            Class<? extends BaseJavaFxApplication> appClass,
			Class<? extends BaseFxmlView> view, 
            String[] args) {
		initialView = view;
		initialArgs = args;
		Application.launch(appClass, args);
	}
}
