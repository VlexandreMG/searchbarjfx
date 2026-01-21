package com.example.atom.text;

import com.example.atom.BaseComponent;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;

public class TitleText extends BaseComponent {
    private Label title;
    private HBox hbox;

    @Override
    protected void createComponents() {
        title = new Label();
        hbox = new HBox();
    }

    @Override
    protected void setupLayout() {
        hbox.getChildren().add(title);
        hbox.setAlignment(Pos.CENTER);
        this.getChildren().add(hbox);   
    }

    @Override
    protected void applyDefaultStyles() {
        // 1. Cherche le nom exact de ta police JapanKimono
        String japanKimonoName = javafx.scene.text.Font.getFontNames().stream()
            .filter(name -> name.toLowerCase().contains("japan") || 
                           name.toLowerCase().contains("kimono"))
            .findFirst()
            .orElse("Brush Script MT");
        
        System.out.println("TitleText utilise la police: " + japanKimonoName);
        
        // 2. Utilise le nom exact avec guillemets simples
        String fontFamily = "'" + japanKimonoName + "', 'Brush Script MT', cursive";
        
        // 3. Applique le style
        title.setStyle("-fx-font-family: " + fontFamily + "; " +
                      "-fx-font-size: 24px; " +
                      "-fx-font-weight: bold; " +
                      "-fx-text-fill: #333;");
        
        // 4. Effet d'ombre
        title.setEffect(new DropShadow(5, Color.GRAY));
    }

    @Override
    protected void setupEventHandlers() {
        // Pas d'événements pour un titre simple
    }

    @Override
    protected void initialize() {
        title.setText("Za nga no tsy ahay design eo :/ ");
    }
}