package src;

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

    public static void main(String[] var0) {
        CharacterFactory var1 = new CharacterFactory();
        Character var2 = var1.createCharacter(Faction.GAULOIS, Occupation.DRUID, "Panoramix");
        Character var3 = var1.createCharacter(Faction.GAULOIS, Occupation.FORGERON, "Cétautomatix");
        Character var4 = var1.createCharacter(Faction.GAULOIS, Occupation.MARCHAND, "Ordralfabétix");
        Character var5 = var1.createCharacter(Faction.GAULOIS, Occupation.AUBERGISTE, "Odalix");
        Character var6 = var1.createCharacter(Faction.ROMAIN, Occupation.LEGIONNAIRE, "Pamplemus");
        Character var7 = var1.createCharacter(Faction.ROMAIN, Occupation.PREFET, "Bonusmalus");
        Character var8 = var1.createCharacter(Faction.ROMAIN, Occupation.GENERAL, "Jules César");
        Character var9 = var1.createCharacter(Faction.CREATURE_FANTASTIQUE, Occupation.LYCANTHROPE, "Prolix");
        FoodFactory var10 = new FoodFactory();
        Food var11 = var10.createFood(FoodType.WILD_BOAR);
        System.out.println("--- Début de la simulation ---");
        ((IWorker)var2).work();
        ((IWorker)var3).work();
        ((IWorker)var4).work();
        ((IWorker)var5).work();
        ((IFighter)var6).fight(var2);
        ((ILeader)var7).lead(var6);
        ((IFighter)var8).fight(var3);
        ((ILeader)var8).lead(var6);
        ((IFighter)var9).fight(var2);
        System.out.println(var11.getName());
    }
}
