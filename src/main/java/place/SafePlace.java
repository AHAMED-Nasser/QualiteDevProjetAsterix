package src.main.java.place;

import src.main.java.characters.Character;
import src.main.java.characters.CreatePopulation;
import src.main.java.food.Food;
import src.main.java.Enum.place.TypePlace;
import src.main.java.Statistics;

import java.util.List;

public class SafePlace extends Place implements ISafePlace {

    private Character clanChief;
    private int nbGauls;
    private int nbRoman;
    private int nbLycanthrope;

    public SafePlace(String name, int surface, TypePlace typePlace, List<Character> characterList, List<Food> foodList, Character clanChief, int nbGauls, int nbRoman, int nbLycanthrope) {
        super(name, surface, typePlace, characterList, foodList);
        this.clanChief = clanChief;
        this.nbGauls = nbGauls;
        this.nbRoman = nbRoman;
        this.nbLycanthrope = nbLycanthrope;

        CreatePopulation populator = new CreatePopulation();
        List<Character> allCharacters = populator.generateSimulationPopulation(this.nbGauls, this.nbRoman, this.nbLycanthrope);

        for (Character character : allCharacters) {
            characterList.add(character);
        }
    }

    @Override
    public void displayPlaceInfo() {
        System.out.println("<----------- INFORMATION DU LIEU: " + this.getName() + " ------------>");
        System.out.println("Chef du clan: " + this.clanChief.getName());
        System.out.println("Nom: " + this.getName());
        System.out.println("Surface: " + this.getSurface() + "m²");
        System.out.println("Type de village: " + this.getTypePlace());
    }

    @Override
    public void displayCharacterFullInfo() {
        System.out.println();
        System.out.println("<---- Information des personnages ---->");
        for (Character character : this.getCharacterList()) {
            character.displayFullInformation();
        }
    }

    @Override
    public void healAllCharacters() {
        for (Character character : this.getCharacterList()) {
            character.heal(100);
        }
    }

    @Override
    public void healOneCharacters(Character character) {
        character.heal(100);
    }

    @Override
    public void feedAllCharacters() {
        System.out.println("Le chef de clan à nourrit tout le monde.");
        for (Character character : this.getCharacterList()) {
            character.setHunger(new Statistics(100, 0, 100));
        }
    }

    @Override
    public void feedOneCharacters(Character character) {
        System.out.println("Le chef de clan à nourrit " + character.getName());
        character.setHunger(new Statistics(100, 0, 100));
    }

    @Override
    public void transferCharacter(int nbCharacter, BattleField battleField) {
        for (int i = 0; i < nbCharacter; i++) {
            battleField.addCharacter(this.getCharacterList().get(i));
        }
    }

}
