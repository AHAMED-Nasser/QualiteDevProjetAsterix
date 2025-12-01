package src.AbstractPlayer.Simulation;
import src.AbstractPlayer.Food;
import src.AbstractPlayer.FoodType;
import src.AbstractPlayer.PotionMagique;

public class Simulation {
    public static void main(String[] args){

        // Creation ingredients
        Food gui = new Food(FoodType.GUI);
        Food carotte = new Food(FoodType.CAROTTES);
        Food sel = new Food(FoodType.SEL);
        Food trefle = new Food(FoodType.TREFLE_FRAIS);
        Food poisson = new Food(FoodType.POISSON_FRAIS);
        Food huile = new Food(FoodType.HUILE_DE_ROCHE);
        Food miel = new Food(FoodType.MIEL);
        Food hydromel = new Food(FoodType.HYDROMEL);
        Food secret = new Food(FoodType.INGREDIENT_SECRET);
        Food homard = new Food(FoodType.HOMARD); // option
        Food fraises = new Food(FoodType.FRAISES); // option

        // creation marmite
        PotionMagique marmite = new PotionMagique();

        //Ajout des ingredients dans la marmite
        marmite.ajouter(gui);
        marmite.ajouter(carotte);
        marmite.ajouter(sel);
        marmite.ajouter(trefle);
        marmite.ajouter(poisson);
        marmite.ajouter(huile);
        marmite.ajouter(miel);
        marmite.ajouter(hydromel);
        marmite.ajouter(secret);
        marmite.ajouter(homard);
        marmite.ajouter(fraises);
        //Test
        System.out.println("Potion complete ?"+ marmite.estValide());

    }
}
