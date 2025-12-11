package src.Characters;

import src.Enum.Character.Faction;
import src.Enum.Character.Occupation;

import java.util.*;


public class CreatePopulation {

    private final CharacterFactory factory = new CharacterFactory();
    private final Random random = new Random();

    // Listes de noms pour les factions
    private final List<String> GAUL_NAMES = Arrays.asList(
            "Asterix", "Obelix", "Abraracourcix", "Assurancetourix", "Cétautomatix",
            "Ordralfabétix", "Panoramix", "Bonemine", "Idefix", "Odalix", "Poulalix", "Agecanonix"
    );

    private final List<String> ROMAN_NAMES = Arrays.asList(
            "Caius Bonus", "Amnesix", "Caius Obtus", "Gracchus Flunux", "Milecus",
            "Brutus", "Nomenklatura", "Caius Pupus", "Claudius", "Quintus", "Marcus"
    );

    private final List<String> FANTASY_NAMES = Arrays.asList(
            "Lupus", "Fenris", "Lycaon", "Moondancer", "Shadowfang", "Grispoil"
    );

    /**
     * Crée un personnage aléatoire à partir de la liste de noms et des occupations définies.
     * @param faction La Faction à créer.
     * @param allowedOccupations Les Occupations possibles pour cette Faction.
     * @param nameList La liste de noms disponibles pour cette Faction.
     * @return Un objet Character créé.
     */
    private Character createRandomCharacter(Faction faction, List<Occupation> allowedOccupations, List<String> nameList) {
        // Choisit une occupation aléatoire de la liste autorisée
        Occupation occupation = allowedOccupations.get(random.nextInt(allowedOccupations.size()));

        // Choisit un nom aléatoire (et le retire pour éviter les doublons si possible)
        String name;
        if (!nameList.isEmpty()) {
            name = nameList.remove(random.nextInt(nameList.size()));
        } else {
            // Si la liste est vide, génère un nom générique
            name = "Inconnu" + random.nextInt(100);
        }

        return factory.createCharacter(faction, occupation, name);
    }

    /**
     * Génère une liste de personnages pour la simulation.
     * @param gaulCount Nombre de Gaulois à créer.
     * @param romanCount Nombre de Romains à créer.
     * @param fantasyCount Nombre de Créatures Fantastiques à créer.
     * @return La liste complète des personnages.
     */
    public List<Character> generateSimulationPopulation(int gaulCount, int romanCount, int fantasyCount) {
        List<Character> population = new ArrayList<>();

        // --- 1. Gaulois (Village, Métiers civils) ---
        List<Occupation> gaulOccupations = Arrays.asList(
                Occupation.DRUID, Occupation.FORGERON, Occupation.MARCHAND, Occupation.AUBERGISTE
        );
        List<String> remainingGaulNames = new ArrayList<>(GAUL_NAMES);
        for (int i = 0; i < gaulCount; i++) {
            population.add(createRandomCharacter(Faction.GAULS, gaulOccupations, remainingGaulNames));
        }

        // --- 2. Romains (Armée et Administration) ---
        List<Occupation> romanOccupations = Arrays.asList(
                Occupation.LEGIONNAIRE, Occupation.PREFET, Occupation.GENERAL, Occupation.LEGIONNAIRE, Occupation.LEGIONNAIRE
        ); // Légionnaires plus fréquents
        List<String> remainingRomanNames = new ArrayList<>(ROMAN_NAMES);
        for (int i = 0; i < romanCount; i++) {
            population.add(createRandomCharacter(Faction.ROMAN, romanOccupations, remainingRomanNames));
        }

        // --- 3. Créatures Fantastiques ---
        List<Occupation> fantasyOccupations = Collections.singletonList(Occupation.LYCANTHROPE);
        List<String> remainingFantasyNames = new ArrayList<>(FANTASY_NAMES);
        for (int i = 0; i < fantasyCount; i++) {
            population.add(createRandomCharacter(Faction.FANTASTIC_CREATURE, fantasyOccupations, remainingFantasyNames));
        }

        return population;
    }
}
