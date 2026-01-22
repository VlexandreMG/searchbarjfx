package com.example;

import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import com.example.atom.button.SearchButton;
import com.example.atom.input.Textfield;
import com.example.atom.text.TitleText;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class App extends Application {

    @Override 
    public void start(Stage primaryStage) {
        // 1. CHARGE LA POLICE (seulement cette ligne)
        javafx.scene.text.Font.loadFont(
            getClass().getResourceAsStream("/fonts/JapanKimono.ttf"),
            14
        );
        
        // 2. Crée l'interface
        BorderPane root = new BorderPane();
        TitleText title = new TitleText();
        Textfield textfield = new Textfield();
        SearchButton searchButton = new SearchButton();

        //Alignement des elements 
        HBox hbox = new HBox();
        hbox.setSpacing(10);
        hbox.getChildren().addAll(textfield, searchButton);
        hbox.setAlignment(Pos.CENTER);

        VBox vbox = new VBox();
        vbox.setSpacing(20);
        vbox.getChildren().addAll(title, hbox);
        vbox.setAlignment(Pos.CENTER);

        root.setCenter(vbox);

        // 3. Affiche
        Scene scene = new Scene(root, 640, 480);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}