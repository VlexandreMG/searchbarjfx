package com.example.atom.input;

import com.example.atom.BaseComponent;

import javafx.scene.control.TextField;

public class Textfield extends BaseComponent {

    private TextField textField;
    private TextChangeHandler listener;

    public interface TextChangeHandler {

        void onTextChanged(String newText);
    }

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
        textField.setStyle("-fx-font-size: 14px; "
                + "-fx-padding: 8px; "
                + "-fx-border-color: #ccc; "
                + "-fx-border-radius: 4px; "
                + "-fx-background-radius: 4px;");
        textField.setPrefWidth(400);
    }

    @Override
    protected void setupEventHandlers() {
        textField.textProperty().addListener((obs, oldVal, newVal) -> {
            //System.out.println("Le texte a changé en :" + newVal);

            if (listener != null) {
                listener.onTextChanged(newVal);
            }
        });
    }

    @Override
    protected void initialize() {
        textField.setPromptText("Recherche....");
    }

    public void setOnTextChanged(TextChangeHandler listener) {
        this.listener = listener;
    }

    public TextField getTextField() {
        return textField;
    }

}
