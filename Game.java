import src.Characters.Character;
import src.Characters.CharacterFactory;
import src.Enum.Faction;
import src.Enum.Occupation;
import src.Food.Food;
import src.Food.FoodFactory;
import src.Food.FoodType;
import src.Interfaces.IFighter;
import src.Interfaces.ILeader;
import src.Interfaces.IWorker;

public class Game {
    public static void main(String[] args) {

        // =============== CHARACTER ================
        CharacterFactory characterFactory = new CharacterFactory();

        Character druide = characterFactory.createCharacter(Faction.GAULOIS, Occupation.DRUID, "Panoramix");
        Character forgeron = characterFactory.createCharacter(Faction.GAULOIS, Occupation.FORGERON, "Cétautomatix");
        Character marchand = characterFactory.createCharacter(Faction.GAULOIS, Occupation.MARCHAND, "Ordralfabétix");
        Character aubergiste = characterFactory.createCharacter(Faction.GAULOIS, Occupation.AUBERGISTE, "Odalix");

        Character legionnaire = characterFactory.createCharacter(Faction.ROMAIN, Occupation.LEGIONNAIRE, "Pamplemus");
        Character prefet = characterFactory.createCharacter(Faction.ROMAIN, Occupation.PREFET, "Bonusmalus");
        Character general = characterFactory.createCharacter(Faction.ROMAIN, Occupation.GENERAL, "Jules César");

        Character lycanthrop = characterFactory.createCharacter(Faction.CREATURE_FANTASTIQUE, Occupation.LYCANTHROPE, "Prolix");

        // =============== FOOD =================
        FoodFactory foodFactory = new FoodFactory();

        Food sanglier = foodFactory.createFood(FoodType.WILD_BOAR);


        System.out.println("--- Début de la simulation ---");

        // CHARACTER
        ((IWorker) druide).work();
        ((IWorker) forgeron).work();
        ((IWorker) marchand).work();
        ((IWorker) aubergiste).work();

        ((IFighter) legionnaire).fight(druide);

        ((ILeader) prefet).lead(legionnaire);
        ((IFighter) general).fight(forgeron);
        ((ILeader) general).lead(legionnaire);

        ((IFighter) lycanthrop).fight(druide);

        // FOOD
        System.out.println(sanglier.getName());
    }
}
