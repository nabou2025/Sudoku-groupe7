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
