import src.Characters.Character;
import src.Characters.CharacterFactory;
import src.Characters.Gaulois.Druid;
import src.Enum.Faction;
import src.Enum.Occupation;
import src.Interfaces.IFighter;
import src.Interfaces.ILeader;
import src.Interfaces.IWorker;

public class Game {
    public static void main(String[] args) {
        CharacterFactory factory = new CharacterFactory();

        Character druide = factory.createCharacter(Faction.GAULOIS, Occupation.DRUID, "Panoramix");
        Character forgeron = factory.createCharacter(Faction.GAULOIS, Occupation.FORGERON, "Cétautomatix");
        Character marchand = factory.createCharacter(Faction.GAULOIS, Occupation.MARCHAND, "Ordralfabétix");
        Character aubergiste = factory.createCharacter(Faction.GAULOIS, Occupation.AUBERGISTE, "Odalix");

        Character legionnaire = factory.createCharacter(Faction.ROMAIN, Occupation.LEGIONNAIRE, "Pamplemus");
        Character prefet = factory.createCharacter(Faction.ROMAIN, Occupation.PREFET, "Bonusmalus");
        Character general = factory.createCharacter(Faction.ROMAIN, Occupation.GENERAL, "Jules César");

        Character lycanthrop = factory.createCharacter(Faction.CREATURE_FANTASTIQUE, Occupation.LYCANTHROPE, "Prolix");

        System.out.println("--- Début de la simulation ---");

//        ((IWorker) druide).work();
//        ((IWorker) forgeron).work();
//        ((IWorker) marchand).work();
//        ((IWorker) aubergiste).work();
//
//        ((IFighter) legionnaire).fight(druide);
//
//        ((ILeader) prefet).lead(legionnaire);
//        ((IFighter) general).fight(forgeron);
//        ((ILeader) general).lead(legionnaire);
//
//        ((IFighter) lycanthrop).fight(druide);

        ((Druid) druide).createMagicPotion();
        System.out.println(((Druid) druide).hasMagicPotionBottle());
        System.out.println(((Druid) druide).hasMagicPotionCauldron());
    }

}
