import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class LectureGrilleFichier {
    public static void charger(int[][] grille, String nomFichier) throws IOException, IllegalArgumentException {
        try (BufferedReader br = new BufferedReader(new FileReader(nomFichier))) {
            for (int i = 0; i < 9; i++) {
                String ligne = br.readLine();
                if (ligne == null) {
                    throw new IllegalArgumentException("Fichier incomplet: manque des lignes");
                }
                
                String[] valeurs = ligne.trim().split("\\s+");
                if (valeurs.length != 9) {
                    throw new IllegalArgumentException("La grille doit contenir exactement 9 colonnes");
                }
                
                for (int j = 0; j < 9; j++) {
                    int val = Integer.parseInt(valeurs[j]);
                    if (val < 0 || val > 9) {
                        throw new IllegalArgumentException("Valeurs invalides (0-9 seulement)");
                    }
                    grille[i][j] = val;
                }
            }
        }
        
        validerGrilleInitiale(grille);
    }
    
    public static void validerGrilleInitiale(int[][] grille) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (grille[i][j] != 0) {
                    for (int k = 0; k < 9; k++) {
                        if (k != j && grille[i][k] == grille[i][j]) {
                            throw new IllegalArgumentException(
                                "Erreur répétition de chiffre " + grille[i][j] + " dans la ligne " + (i + 1)
                            );
                        }
                    }
                    for (int k = 0; k < 9; k++) {
                        if (k != i && grille[k][j] == grille[i][j]) {
                            throw new IllegalArgumentException(
                                "Erreur répétition de chiffre " + grille[i][j] + " dans la colonne " + (j + 1)
                            );
                        }
                    }
                    int debutLigne = (i / 3) * 3;
                    int debutCol = (j / 3) * 3;
                    for (int x = 0; x < 3; x++) {
                        for (int y = 0; y < 3; y++) {
                            int posLigne = debutLigne + x;
                            int posCol = debutCol + y;
                            if ((posLigne != i || posCol != j) && grille[posLigne][posCol] == grille[i][j]) {
                                throw new IllegalArgumentException(
                                    "Erreur répétition de chiffre " + grille[i][j] + " dans le bloc 3x3"
                                );
                            }
                        }
                    }
                }
            }
        }
    }
}
