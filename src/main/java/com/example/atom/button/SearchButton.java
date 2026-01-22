package com.example.atom.button;

import com.example.atom.BaseComponent;

import javafx.scene.control.Button;

public class SearchButton extends BaseComponent {
    private Button button;

    @Override
    protected void createComponents() {
        button = new Button();
    }

    @Override
    protected void setupLayout() {
        this.getChildren().add(button);
    }

    @Override
    protected void applyDefaultStyles() {
        button.setStyle("-fx-font-size: 14px; " +
                        "-fx-padding: 8px 16px; " +
                        "-fx-background-color: #007BFF; " +
                        "-fx-text-fill: white; " +
                        "-fx-border-radius: 4px; " +
                        "-fx-background-radius: 4px;");
    }

    @Override
    protected void setupEventHandlers() {
        // TODO Auto-generated method stub
        
    }

    @Override
    protected void initialize() {
        button.setText("Search");
    }
}