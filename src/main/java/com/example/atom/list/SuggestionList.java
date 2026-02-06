package com.example.atom.list;

import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import com.example.atom.BaseComponent;
import com.example.dao.WordDAO;
import com.example.model.Word;
import com.example.model.WordClickListener;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;

public class SuggestionList extends BaseComponent {

    private ListView<Object> listView;
    private VBox vbox;
    private WordDAO wordDAO;
    private ObservableList<String> allWords; // Stocke tous les mots pour le filtrage
    private WordClickListener wordClickListener;

    public void setOnWordClicked(WordClickListener listener) {
    this.wordClickListener = listener;
}

    @Override
    protected void createComponents() {
        listView = new ListView<>();
        vbox = new VBox();
        wordDAO = new WordDAO();
        allWords = FXCollections.observableArrayList();
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
        // Affiche un mot selectionné lony 
        listView.setOnMouseClicked(e -> {
            String choosen = (listView.getSelectionModel().getSelectedItem()).toString();
            // System.out.println("Le mot selectionné est : " + choosen);
            if (choosen != null && wordClickListener != null) {
                wordClickListener.onWordClicked(choosen);

            }
        }); 
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
                    allWords.clear();

                    // Limite à 100 éléments max pour la performance
                    int limit = Math.min(100, words.size());
                    for (int i = 0; i < limit; i++) {
                        String wordStr = words.get(i).toString();
                        listView.getItems().add(wordStr);
                        allWords.add(wordStr);
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

        // Crée le pattern regex (insensible à la casse)
        String regex = ".*" + Pattern.quote(filterText) + ".*";
        Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);

        // Filtre les mots
        List<String> filteredWords = allWords.stream()
                .filter(word -> pattern.matcher(word).matches())
                .collect(Collectors.toList());

        // Met à jour la ListView
        listView.getItems().setAll(filteredWords);

        // Affiche seulement s'il y a des résultats
        this.setVisible(!filteredWords.isEmpty());
    }

}
