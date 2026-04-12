import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class LectureGrilleFichier {

    public static void charger(int[][] grille, String nomFichier)
            throws FileNotFoundException, IOException, IllegalArgumentException {

        try (BufferedReader br = new BufferedReader(new FileReader(nomFichier))) {

            for (int i = 0; i < 9; i++) {
                String ligne = br.readLine();

                // Pas assez de lignes
                if (ligne == null) {
                    throw new IllegalArgumentException(
                        "Erreur : fichier incomplet. "
                        + "Attendu 9 lignes, seulement " + i + " trouvée(s)."
                    );
                }

                String[] valeurs;
                if (ligne.contains(" ")) {
                     valeurs = ligne.trim().split("\\s+");
                } else {
                     valeurs = new String[ligne.trim().length()];
                for (int k = 0; k < ligne.trim().length(); k++) {
                     valeurs[k] = String.valueOf(ligne.trim().charAt(k));
                          }
                    }
                // Pas assez de colonnes
                if (valeurs.length < 9) {
                    throw new IllegalArgumentException(
                        "Erreur ligne " + (i + 1) + " : colonne(s) manquante(s). "
                        + "Attendu 9 valeurs, trouvé " + valeurs.length + "."
                    );
                }

                // Trop de colonnes
                if (valeurs.length > 9) {
                    throw new IllegalArgumentException(
                        "Erreur ligne " + (i + 1) + " : trop de valeurs. "
                        + "Attendu 9, trouvé " + valeurs.length + "."
                    );
                }

                for (int j = 0; j < 9; j++) {
                    try {
                        int val = Integer.parseInt(valeurs[j]);

                        // Valeur hors [0-9]
                        if (val < 0 || val > 9) {
                            throw new IllegalArgumentException(
                                "Erreur ligne " + (i + 1) + ", colonne " + (j + 1)
                                + " : valeur \"" + val + "\" invalide. "
                                + "Les valeurs doivent être strictement comprises entre 0 et 9."
                            );
                        }

                        grille[i][j] = val;

                    } catch (NumberFormatException e) {
                        throw new IllegalArgumentException(
                            "Erreur ligne " + (i + 1) + ", colonne " + (j + 1)
                            + " : \"" + valeurs[j] + "\" n'est pas un entier valide."
                        );
                    }
                }
            }

            // Trop de lignes
            String ligneSupp = br.readLine();
            if (ligneSupp != null && !ligneSupp.trim().isEmpty()) {
                throw new IllegalArgumentException(
                    "Erreur : le fichier contient trop de lignes. "
                    + "La grille doit comporter exactement 9 lignes."
                );
            }
        }

        // Validation des règles Sudoku sur la grille initiale
        validerGrilleInitiale(grille);
        System.out.println("Fichier chargé et validé avec succès.");
    }

    /**
     * Vérifie qu'aucun chiffre n'est répété dans une ligne, colonne ou bloc 3x3.
     *
     * @param grille tableau 9x9 à valider
     * @throws IllegalArgumentException si une répétition est détectée
     */
    public static void validerGrilleInitiale(int[][] grille) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                int val = grille[i][j];
                if (val != 0) {
                    // Vérification ligne
                    for (int k = 0; k < 9; k++) {
                        if (k != j && grille[i][k] == val) {
                            throw new IllegalArgumentException(
                                "Erreur : répétition du chiffre " + val
                                + " à la ligne " + (i + 1) + "."
                            );
                        }
                    }
                    // Vérification colonne
                    for (int k = 0; k < 9; k++) {
                        if (k != i && grille[k][j] == val) {
                            throw new IllegalArgumentException(
                                "Erreur : répétition du chiffre " + val
                                + " à la colonne " + (j + 1) + "."
                            );
                        }
                    }
                    // Vérification bloc 3x3
                    int dl = (i / 3) * 3;
                    int dc = (j / 3) * 3;
                    for (int x = 0; x < 3; x++) {
                        for (int y = 0; y < 3; y++) {
                            int li = dl + x, co = dc + y;
                            if ((li != i || co != j) && grille[li][co] == val) {
                                throw new IllegalArgumentException(
                                    "Erreur : répétition du chiffre " + val
                                    + " dans un bloc 3x3."
                                );
                            }
                        }
                    }
                }
            }
        }
    }
}
