import java.util.Scanner;

public class SaisieGrilleConsole {
    public static void saisir(int[][] grille) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Saisissez la grille (9 lignes, 9 valeurs séparées par espace, 0 pour vide):");
        
        for (int i = 0; i < 9; i++) {
            System.out.print("Ligne " + (i + 1) + ": ");
            String ligne = scanner.nextLine().trim();
            String[] valeurs = ligne.split("\\s+");
            
            if (valeurs.length != 9) {
                throw new IllegalArgumentException("Chaque ligne doit contenir exactement 9 valeurs");
            }
            
            for (int j = 0; j < 9; j++) {
                try {
                    int val = Integer.parseInt(valeurs[j]);
                    if (val < 0 || val > 9) {
                        throw new IllegalArgumentException("Valeurs invalides (0-9 seulement)");
                    }
                    grille[i][j] = val;
                } catch (NumberFormatException e) {
                    throw new IllegalArgumentException("Valeurs non numériques à la position " + (j + 1));
                }
            }
        }
        scanner.close();
        
        LectureGrilleFichier.validerGrilleInitiale(grille);
    }
}
