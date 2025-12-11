# Spécification Fonctionnelle Technique (SFT)

## Partie 1 : Étude de Conception et Architecture

Le projet est une application de simulation en Java construite autour des principes de la **Programmation Orientée Objet (POO)** pour garantir la modularité, la robustesse et l'évolutivité.

### 1.1 Modélisation des Entités et Héritage

| Entité | Rôle | Architecture POO |
| :--- | :--- | :--- |
| **`Character`** | Classe abstraite mère pour tous les personnages (Gaulois, Romains, Créatures). | Gère les statistiques de base (`Statistics` pour PV, Faim, Potion), les préférences alimentaires et les actions communes (`eat`, `heal`, `takeDamage`). |
| **`Place`** | Classe abstraite mère pour tous les lieux (Village, Camp, Champ de Bataille). | Gère la liste générique des `Character` et `Food`, ainsi que les affichages. |
| **Héritage** | Les classes concrètes (ex: `Druid`, `Legionnaire`, `SafePlace`, `BattleField`) héritent des comportements de base et implémentent la logique métier spécifique. | Spécialisation des comportements et des données. |

### 1.2 Interfaces et Comportements

L'utilisation d'interfaces permet de définir des contrats clairs pour les comportements des entités :

| Interface | Rôle | Exemples de Classes |
| :--- | :--- | :--- |
| `IFighter` | Définit la capacité de se battre (nécessaire pour le combat). | `Legionnaire`, `General`, `Druid`, `Lycanthrope`. |
| `IWorker` | Définit la capacité de travailler (produire des ressources). | `Blacksmith`, `Merchant`, `Innkeeper`, `Druid`. |
| `ILeader` | Définit la capacité de donner des ordres. | `General`, `Prefect`, `Druid`. |
| `IPlace` / `ISafePlace` | Contrats pour les actions spécifiques aux lieux (ajout/retrait de personnages, soins, nourriture). | `Place`, `SafePlace`. |

### 1.3 Modèles de Conception (Design Patterns)

* **Factory Pattern** : Utilisé de manière intensive pour centraliser et encapsuler la logique de création des objets complexes.
    * `CharacterFactory` : Crée les personnages en appliquant les bonus de statistiques selon leur métier (`Occupation`).
    * `FoodFactory` : Crée les aliments avec leurs valeurs de nutrition prédéfinies.
    * `PlaceFactory` : Crée les lieux en initialisant les populations et les propriétés.
* **Multithreading** : La classe `InvasionTheater` utilise un **Thread dédié** pour le moteur du temps. Cela permet à la simulation d'avancer (combats, faim) en parallèle du thread principal qui gère les entrées de l'utilisateur (menu du Chef de Clan), rendant l'application réactive.

### 1.4 Algorithmes et Techniques

* **Clamping** : La classe `Statistics` implémente une logique de **Clamping** (bornage) pour garantir que les statistiques (PV, Faim, Potion) ne dépassent jamais leurs limites maximales ou minimales.
* **Logique de Potion Magique** : `Druid::brewPotion` implémente une routine de vérification stricte des **9 ingrédients** nécessaires dans le stock du lieu, incluant la gestion des ingrédients spéciaux (Licorne, Poils d'Idéfix).

***

## Partie 2 : Clôture du Travail (Bilan Technique)

### 2.1 Réalisations Techniques Clés

* **Implémentation Complète de la Potion Magique** : La logique de la potion est intégrée, de la vérification des ingrédients à la gestion des effets complexes dans `Character::drinkMagicPotion` (Invincibilité, Surdosage en statue de granit).
* **Tests Unitaires (JUnit)** : Un ensemble de tests unitaires couvrant les classes critiques (`CharacterTest`, `StatisticsTest`, `PlaceTest`, `FactoryTest`) a été développé pour valider la logique métier complexe.
* **Logique d'Interaction Réaliste** : Les personnages puisent la nourriture dans le stock du lieu et la méthode `Character::eat` gère l'ensemble des règles du TP (malus pour poisson pas frais, malus pour légumes consécutifs, et refus de faction).

### 2.2 Problèmes et Solutions Techniques

| Problème Rencontré | Solution Implémentée |
| :--- | :--- |
| **Décalage d'Index lors du Transfert de Troupes** | Correction de la méthode `SafePlace::transferCharacter` pour toujours retirer l'élément à l'index `0` (`remove(0)`), garantissant que le personnage suivant prend l'index `0` sans erreur. |
| **Calcul des Dégâts par Nourriture Négative** | Utilisation de `Math.abs()` lors du calcul des dégâts dans `Character::eat` pour garantir que la valeur de nutrition négative du poisson pas frais inflige correctement des dégâts (valeur positive). |
| **Refus de Manger les Légumes** | Réorganisation de la logique dans `Character::eat` pour que les légumes (FoodCategory.VEGETABLE) soient toujours acceptés, même s'ils ne font pas partie des préférences de faction, tout en conservant le malus en cas de consommation consécutive. |

### 2.3 Écarts avec les Prévisions et Mesures d'Amélioration

* **Écarts** : Les effets spéciaux optionnels de la potion magique (Dédoublement et Métamorphose) se limitent actuellement à un affichage en console et à une modification des statistiques. L'implémentation complète des mécaniques de jeu associées (nouvelle classe de personnage `Lycanthrope` dynamique) n'est pas finalisée.
* **Améliorations** :
    1.  Implémenter des **Exceptions Personnalisées** (ex: `PlaceConstraintException`) pour mieux gérer les contraintes métier (ex: interdire l'entrée d'un Romain dans un Village Gaulois).
    2.  Compléter la documentation Javadoc pour les classes et méthodes restantes.
