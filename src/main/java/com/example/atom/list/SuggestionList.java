package com.example.atom.list;

import com.example.atom.BaseComponent;
import com.example.dao.WordDAO;

import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;

public class SuggestionList extends BaseComponent {

    private ListView<Object> listView;
    private VBox vbox;
    private WordDAO wordDAO;

    @Override
    protected void createComponents() {
        listView = new ListView<>();
        vbox = new VBox();
        wordDAO = new WordDAO();
    }

    @Override
    protected void setupLayout() {
        vbox.getChildren().add(listView);
        this.getChildren().add(vbox);
    }

    @Override
    protected void applyDefaultStyles() {
        listView.setStyle("-fx-border-color: #ccc; -fx-border-width: 1;");
        vbox.setStyle("-fx-padding: 10;");
    }

    @Override
    protected void setupEventHandlers() {
        // Pas d'événements pour l'instant 
    }

    @Override
    protected void initialize() {
        try {
            listView.getItems().addAll(wordDAO.getAllWords());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
