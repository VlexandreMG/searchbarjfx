package com.example.atom.input;

import com.example.atom.BaseComponent;

import javafx.scene.control.TextField;

public class Textfield extends BaseComponent {
    private TextField textField;

    @Override
    protected void createComponents() {
        textField = new TextField();
    }

    @Override
    protected void setupLayout() {
        this.getChildren().add(textField);
    }

    @Override
    protected void applyDefaultStyles() {
        textField.setStyle("-fx-font-size: 14px; " +
                           "-fx-padding: 8px; " +
                           "-fx-border-color: #ccc; " +
                           "-fx-border-radius: 4px; " +
                           "-fx-background-radius: 4px;");
        textField.setPrefWidth(400);
    }

    @Override
    protected  void setupEventHandlers() {

    }

    @Override
    protected void initialize() {
        textField.setPromptText("Recherche....");
    }
}