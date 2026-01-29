package com.example.atom.list;

import java.util.List;

import com.example.atom.BaseComponent;
import com.example.dao.WordDAO;

import javafx.application.Platform;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;
import com.example.model.Word;

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
        listView.setPrefWidth(400);
    }

    @Override
    protected void setupEventHandlers() {
        // Pas d'événements pour l'instant 
    }

    @Override
    protected void initialize() {
    // 1. Affiche IMMÉDIATEMENT l'interface (vide)
    listView.getItems().add("Chargement...");
            this.setVisible(false);

            // 2. Charge les données en arrière-plan
            new Thread(() -> {
                try {
                    List<Word> words = wordDAO.getAllWords();

                    // 3. Met à jour l'UI quand c'est prêt
                    Platform.runLater(() -> {
                        listView.getItems().clear();

                        // Limite à 100 éléments max pour la performance
                        int limit = Math.min(100, words.size());
                        for (int i = 0; i < limit; i++) {
                            listView.getItems().add(words.get(i).toString());
                        }

                        System.out.println("✅ " + words.size() + " mots chargés");
                    });
                } catch (Exception e) {
                    Platform.runLater(() -> {
                        listView.getItems().clear();
                        listView.getItems().add("Erreur de chargement");
                    });
                }
            }).start();
        }
    public void filterSuggestions(String filterText) {
    if (filterText == null || filterText.trim().isEmpty()) {
        this.setVisible(false);
        return;
    }
    
    this.setVisible(true);
    // Le filtrage réel se fera quand tu auras les données
    listView.getItems().setAll("Recherche en cours...");
        }

}

   