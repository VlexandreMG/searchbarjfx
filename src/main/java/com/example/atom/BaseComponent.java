package com.example.atom;

import javafx.scene.Parent;

public abstract class BaseComponent extends Parent {
    
    protected BaseComponent() {
        initializeComponent();
    }

    private void initializeComponent() {
        createComponents();

        setupLayout();

        applyDefaultStyles();

        setupEventHandlers();

        initialize();
    }

    protected abstract void createComponents();

    protected abstract void setupLayout();

    protected abstract void applyDefaultStyles();

    protected abstract void setupEventHandlers();

    protected abstract void initialize();
}