package com.example.molecule;

import com.example.atom.BaseComponent;
import com.example.atom.button.ClassicButton;
import com.example.atom.text.SimpleText;
import com.example.atom.text.TitleText;
import javafx.scene.layout.VBox;

public class AlertMolecule extends BaseComponent {

    private ClassicButton closeButton;
    private SimpleText alertText;
    private TitleText titleText;
    private VBox vbox;

    @Override
    protected void createComponents() {
        closeButton = new ClassicButton();
        alertText = new SimpleText();
        titleText = new TitleText();
        vbox = new VBox();
    }

    @Override
    protected void setupLayout() {
        vbox.getChildren().addAll(titleText, alertText, closeButton);
        this.getChildren().add(vbox);
        
    }

    @Override
    protected void applyDefaultStyles() {
        // TODO Auto-generated method stub
        
    }

    @Override
    protected void setupEventHandlers() {
        // TODO Auto-generated method stub
        
    }

    @Override
    protected void initialize() {};
}
