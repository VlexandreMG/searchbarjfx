package com.example;

import com.example.atom.text.TitleText;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
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
        root.setCenter(title);

        // 3. Affiche
        Scene scene = new Scene(root, 640, 480);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}