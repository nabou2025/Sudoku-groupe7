import java.io.IOException;

public class SudokuResolu {

    public static final int TAILLE = 9;

    public static final int BLOC = 3;

    private int[][] grille = new int[TAILLE][TAILLE];


    public int[][] getGrille() { return grille; }
    public void setGrille(int[][] g) { this.grille = g; }
    public int getValeur(int i, int j) { return grille[i][j]; }
    public void setValeur(int i, int j, int val) { grille[i][j] = val; }


    public void chargerGrilleFichier(String nomFichier)
            throws IOException, IllegalArgumentException {
        LectureGrilleFichier.charger(this.grille, nomFichier);
    }


    public void chargerGrilleManuelle() {
        SaisieGrilleConsole.saisir(this.grille);
    }


    public void afficherGrille(String titre) {
        System.out.println("\n=== " + titre + " ===");
        System.out.println("╔═══════╦═══════╦═══════╦═══════════╗");

        for (int i = 0; i < TAILLE; i++) {
            StringBuilder sb = new StringBuilder();
            sb.append("║");

            for (int j = 0; j < TAILLE; j++) {
                int val = grille[i][j];
                String cellule = (val == 0) ? " " : String.valueOf(val);
                sb.append(" ").append(cellule).append(" ");

                if (j == 2 || j == 5) {
                    sb.append("║");
                } else if (j < 8) {
                    sb.append("│");
                }
            }
            sb.append("║");
            System.out.println(sb.toString());

            if (i == 2 || i == 5) {
                System.out.println("╠═══════╬═══════╬═══════╬═══════════╣");
            } else if (i < 8) {
                System.out.println("├───────┼───────┼───────┼───────────┤");
            }
        }
        System.out.println("╚═══════╩═══════╩═══════╩═══════════╝");
    }

    public boolean estValide(int ligne, int col, int num) {
        // Vérification ligne
        for (int j = 0; j < TAILLE; j++) {
            if (grille[ligne][j] == num) return false;
        }
        // Vérification colonne
        for (int i = 0; i < TAILLE; i++) {
            if (grille[i][col] == num) return false;
        }
        // Vérification bloc 3x3
        int debutLigne = (ligne / BLOC) * BLOC;
        int debutCol   = (col   / BLOC) * BLOC;
        for (int i = 0; i < BLOC; i++) {
            for (int j = 0; j < BLOC; j++) {
                if (grille[debutLigne + i][debutCol + j] == num) return false;
            }
        }
        return true;
    }

    // ─── Résolution ────────────────────────────────────────────────────

    /**
     * Lance la résolution de la grille par backtracking.
     *
     * @return true si une solution a été trouvée, false sinon
     */
    public boolean resoudre() {
        return backtracking(0, 0);
    }

    /**
     * Algorithme de backtracking récursif.
     * Parcourt la grille de gauche à droite, de haut en bas.
     *
     * @param ligne ligne courante (0-8)
     * @param col   colonne courante (0-8)
     * @return true si la grille est résoluble depuis cette position
     */
    private boolean backtracking(int ligne, int col) {
        // Toutes les lignes traitées → solution trouvée
        if (ligne == TAILLE) return true;

        // Fin de ligne → on passe à la suivante
        if (col == TAILLE) return backtracking(ligne + 1, 0);

        // Case déjà remplie → on avance
        if (grille[ligne][col] != 0) return backtracking(ligne, col + 1);

        // On essaie les chiffres 1 à 9
        for (int num = 1; num <= TAILLE; num++) {
            if (estValide(ligne, col, num)) {
                grille[ligne][col] = num;
                if (backtracking(ligne, col + 1)) return true;
                grille[ligne][col] = 0; // backtrack
            }
        }
        return false;
    }
}
