package ru.javafx.jfxintegrity;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Lazy;

import ru.javafx.jfxintegrity.BaseJavaFxApplication;
import ru.javafx.jfxintegrity.complex.ComplexView;

@Lazy
@SpringBootApplication
public class Starter extends BaseJavaFxApplication {
	
	public static void main(String[] args) {
		launchApp(Starter.class, ComplexView.class, args);
	}
	
}
