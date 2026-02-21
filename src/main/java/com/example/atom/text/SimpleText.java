package com.example.atom.text;

import com.example.atom.BaseComponent;
import javafx.scene.layout.HBox;
import javafx.scene.control.Label;

public class SimpleText extends BaseComponent {
    private Label text;
    private HBox hbox;

    @Override
    protected void createComponents() {
        text = new Label();
        hbox = new HBox();
    };
   
    @Override
    protected void setupLayout() {
        hbox.getChildren().add(text);

    };

    @Override
    protected void applyDefaultStyles() {};

    @Override
    protected void setupEventHandlers() {};

    @Override
    protected void initialize() {};
}
