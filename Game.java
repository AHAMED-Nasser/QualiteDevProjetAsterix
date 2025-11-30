import src.Characters.Blacksmith;
import src.Characters.Character;
import src.Characters.CharacterFactory;
import src.Characters.Druid;
import src.Enum.Faction;
import src.Enum.Occupation;

public class Game {
    public static void main(String[] args) {
        CharacterFactory factory = new CharacterFactory();

        Character druide = factory.createCharacter(Faction.GAULOIS, Occupation.DRUID, "Panoramix");
        Character forgeron = factory.createCharacter(Faction.GAULOIS, Occupation.FORGERON, "Cétautomatix");

        System.out.println("--- Début de la simulation ---");

        System.out.println(druide.getName());
        System.out.println(forgeron.getName());

        forgeron.takeDamage(5); // Pas d'affichage
    }
}
