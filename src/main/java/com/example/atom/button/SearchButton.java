package com.example.atom.button;

import com.example.atom.BaseComponent;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class SearchButton extends BaseComponent {
    private Button button;
    private Image image;
    private ImageView icon;

    @Override
    protected void createComponents() {
        button = new Button();
        image = new Image(getClass().getResourceAsStream("/images/search-icon.png"));

        icon = new ImageView(image);
        icon.setFitHeight(20);
        icon.setFitWidth(20);
        icon.setPreserveRatio(true);
    }

    @Override
    protected void setupLayout() {
        button.setGraphic(icon);
        this.getChildren().add(button);
    }

    @Override
    protected void applyDefaultStyles() {
        button.setStyle("-fx-padding: 8px; " +  // Moins de padding car pas de texte
                        "-fx-background-radius: 4px;");
        
        //Style pour que l'image soit bien centrée
        button.setContentDisplay(javafx.scene.control.ContentDisplay.GRAPHIC_ONLY);
    }

    @Override
    protected void setupEventHandlers() {
        button.setOnAction(event -> {
            // Action à effectuer lors du clic sur le bouton
            System.out.println("Recherche lancée !");
        });
        
    }

    @Override
    protected void initialize() {
        //button.setText("Search");
    }
}