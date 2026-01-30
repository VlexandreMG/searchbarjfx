#!/bin/bash
echo "⚡ Démarrage rapide..."

# Vérifie si clean est demandé
if [ "$1" == "--clean" ]; then
    echo "🧹 Nettoyage en cours..."
    mvn clean
fi

# Compile et lance
echo "🔨 Compilation..."
mvn compile

echo "🚀 Lancement de l'application..."
mvn javafx:run