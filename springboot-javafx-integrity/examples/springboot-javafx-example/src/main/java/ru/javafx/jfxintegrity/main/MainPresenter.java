package ru.javafx.jfxintegrity.main;

import javafx.event.ActionEvent;
import javafx.scene.input.MouseEvent;

public interface MainPresenter {

    void onButtonClicked(ActionEvent e);

    void onLabelClicked(MouseEvent e);

}