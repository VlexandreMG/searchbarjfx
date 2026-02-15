package com.example.atom.button;

import com.example.atom.BaseComponent;

import javafx.scene.control.Button;


public class ClassicButton extends BaseComponent {
    private Button button;
    private String text;

    @Override
    protected void createComponents() {
        button = new Button();
        text = new String();
    }

    @Override
    protected void setupLayout() {
        this.getChildren().add(button);
    }
    
    @Override
    protected void applyDefaultStyles() {
        button.setStyle("-fx-padding: 8px; " +  // Moins de padding car pas de texte
        "-fx-background-radius: 4px;");
        
        //Style pour que l'image soit bien centrée
    }
    
    @Override
    protected void setupEventHandlers() {
        button.setOnAction(event -> {
            //Algorthme qui permet de fermer un alert
            System.out.println("Alert fermé ");
        });
        
    }
    
    @Override
    protected void initialize() {
        //button.setText("Search");
        button.setText(text);
    }
}