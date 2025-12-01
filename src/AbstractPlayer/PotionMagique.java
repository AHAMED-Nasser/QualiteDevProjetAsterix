package src.AbstractPlayer;
import java.util.ArrayList;
import java.util.List;

public class PotionMagique {
    private final List<Food>ingredients = new ArrayList<>();

    public void ajouter(Food ingredient){
        ingredients.add(ingredient);
    }

    public boolean estValide(){
        return contientObligatoires();
    }

    private boolean contientObligatoires(){ // met false au départ , si rien trouvé dans la marmite on laisse
        boolean gui = false;
        boolean carotte = false;
        boolean sel = false;
        boolean TrefleFrais = false;
        boolean poissonFrais = false;
        boolean huileOuBetterave = false;
        boolean miel = false;
        boolean hydromel = false;
        boolean ingredientSecret = false;

        for(Food f : ingredients){ // parcours des aliments ajouté dans la marmite
            switch (f.getName()){ // on met a jour les variables present dans la marmite (énoncé)
                case "Gui" -> gui = true;
                case "Carotte" -> carotte = true;
                case "Sel" -> sel = true;
                case "Trèfle à quatre feuilles frais" -> TrefleFrais = true;
                case "poisson passablement frais" -> poissonFrais = true;
                case "Huile de Roche", "Jus de betterave" ->huileOuBetterave = true;
                case "Miel" -> miel = true;
                case "Hydromel" -> hydromel = true;
                case "Ingrédient secret" -> ingredientSecret = true;
            }

        }
        return gui && carotte && sel  && TrefleFrais && poissonFrais && huileOuBetterave && miel && hydromel
                && ingredientSecret;
    }
    public boolean estNourrissante(){
        for(Food f : ingredients) {
            if (f.getName().equals("Homard") ||
                f.getName().equals(("Fraises"))){
                return true;
            }

        }
        return false;
    }
    private int doses = 5;

    public int utilierDose(){
        if(doses> 0 ){
            doses --;
            return doses;
        }
        return 0;
    }

}
