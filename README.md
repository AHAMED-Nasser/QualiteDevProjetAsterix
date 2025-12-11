## Prompt

1 -> Dans une classe abstraite on peut initialiser des attribut ou bien c'est que pour les méthodes ?
<br>
2 -> Donne moi une implémentation de la classe Abstraite du Player sans pour autant me donner la définition des méthodes, juste les attribut et le constructeur.
<br>
3 ->  Je veux que tu me créé assez de personnage pour pouvoir faire une simulation. J'ai pas envie de le faire à la main, un à un.

## Autre sources

* Base de notre projet grandement inspiré du projet github de FUSELIER Jule: https://github.com/julesfuselier


# Qualité de Développement - Projet : Simulation de l'Envahissement de l'Armorique

## 1. Contexte du Projet

Ce projet est une application de simulation en Java de l'envahissement de l'Armorique par l'Empire Romain en 50 avant Jésus-Christ, tel que spécifié dans les documents de travaux dirigés (TD3 et TD4).

L'application est architecturée autour des concepts de la Programmation Orientée Objet (POO) pour modéliser les personnages (Gaulois, Romains, Créatures Fantastiques), les lieux (Villages, Camps, Champs de Bataille) et la gestion temporelle de la simulation.

## 2. Fonctionnalités Implémentées (Spécification Minimale)

* **Personnages** : Gestion des statistiques (Santé, Faim, Force, Potion Magique).
* **Actions** : Combat entre belligérants, Soin, Manger, Boire de la Potion Magique.
* **Métiers** : Implémentation des interfaces `IWorker`, `IFighter`, `ILeader` pour les Forgerons, Druides, Légionnaires, etc..
* **Lieux** : `SafePlace` et `BattleField` avec gestion des contraintes d'accès (qui peut entrer où).
* **Potion Magique** : Implémentation complète de la recette (ingrédients requis et optionnels) et des effets (invincibilité, surdosage en statue de granit).
* **Moteur de Simulation** : Gestion du temps, pourrissement de la nourriture et progression de la faim.

## 3. Architecture et Concepts Java Avancés

Le projet est structuré en utilisant des packages modulaires (`characters`, `food`, `place`, `Enum`, `items`).

Les concepts Java suivants, exigés par les consignes du projet, sont utilisés :

* **Héritage et Classes Abstraites** : Classe mère `Character` et `Place`.
* **Interfaces** : `IFighter`, `IWorker`, `ILeader`, `IPlace`, etc.
* **Modèles de Conception** : Utilisation intensive du **Factory Pattern** (`CharacterFactory`, `FoodFactory`, `PlaceFactory`) pour l'instanciation des objets.
* **Multithreading** : Le `InvasionTheater` utilise un **Thread dédié** pour le moteur du temps, permettant à la simulation d'avancer en parallèle de l'interface utilisateur (écoute des commandes du Chef de Clan).
* **Collections** : Utilisation de `ArrayList` et des Stream API (Java 8+) pour le traitement et le filtrage des listes de personnages/aliments (voir `InvasionTheater.java` et `Place.java`).
* **Tests Unitaires** : Dossier `src/test/java` contenant les tests JUnit pour valider la logique métier (Statistiques, Combat, Nourriture).
* **Exceptions** : Utilisation d'`IllegalArgumentException` pour les erreurs de création ou de logique.

## 4. Lancement et Exécution

### Prérequis

* Java Development Kit (JDK) version 17 ou supérieure.
* Un système de gestion de projet (Maven ou Gradle) est recommandé pour gérer les dépendances (JUnit).

### Instructions de Lancement (Interface Console)

1.  Cloner le dépôt :
    ```bash
    git clone git@github.com:AHAMED-Nasser/QualiteDevProjetAsterix.git
    ```
2.  Compiler et lancer l'application :
    * Le point d'entrée de la simulation est la méthode `main(String[] args)` dans la classe `Main.java`.
    * La logique d'initialisation (création des lieux et de la population) se trouve dans la classe `Game.java`.

Une fois lancé, le théâtre d'envahissement démarre automatiquement. Le moteur du temps avance toutes les 5 secondes. **Tapez `Entrée` à tout moment** pour mettre la simulation en pause et accéder au menu interactif du Chef de Clan.

## 5. Instructions de Livraison (Critères du PDF)

Pour que la livraison soit conforme aux exigences du §2 et §5 du document `Consigne-Projet.pdf`, les points suivants ont été respectés :

| Critère | Détail                                                                   |
| :--- |:-------------------------------------------------------------------------|
| **Nom du Livrable** | `AHAMED-Nasser_CANTOR-Romain.zip`.                                       |
| **Documentation Javadoc** | La documentation complète est générée dans le dossier `javadoc/`.        |
| **Contenu du Livrable** | Inclut le code source, les tests unitaires et les documents demandés.    |
