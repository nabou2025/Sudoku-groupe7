public class SudokuSolver {
    private int[][] grille = new int[9][9];
    
    public void chargerGrilleFichier(String nomFichier) throws IOException, IllegalArgumentException {
        GrilleDepuisFichier.charger(this.grille, nomFichier);
    }
    
    public void chargerGrilleManuelle() {
        GrilleDepuisSaisiesManuelles.saisir(this.grille);
    }
    
    public void afficherGrille(String titre) {
        System.out.println("\n=== " + titre + " ===");
        System.out.print("  ");
        for (int i = 0; i < 9; i++) {
            System.out.print((i % 3 == 0 ? "┌───" : "───"));
        }
        System.out.println("┐");
        
        for (int i = 0; i < 9; i++) {
            System.out.print((i % 3 == 0 ? "│ " : "  "));
            for (int j = 0; j < 9; j++) {
                int val = grille[i][j];
                System.out.print((val == 0 ? "  " : val + " ") + (j % 3 == 2 ? "│" : ""));
            }
            System.out.println();
            
            if (i % 3 == 2) {
                System.out.print("  ");
                for (int j = 0; j < 9; j++) {
                    System.out.print("───" + (j % 3 == 2 ? "┤" : ""));
                }
                System.out.println("┘");
            }
        }
    }
    
    public boolean resoudre() {
        return backtracking(0, 0);
    }
    
    private boolean backtracking(int ligne, int col) {
        if (ligne == 9) {
            return true; // Grille complète
        }
        
        if (col == 9) {
            return backtracking(ligne + 1, 0);
        }
        
        if (grille[ligne][col] != 0) {
            return backtracking(ligne, col + 1);
        }
        
        for (int num = 1; num <= 9; num++) {
            if (estValide(ligne, col, num)) {
                grille[ligne][col] = num;
                if (backtracking(ligne, col + 1)) {
                    return true;
                }
                grille[ligne][col] = 0; // Backtrack
            }
        }
        return false;
    }
    
    private boolean estValide(int ligne, int col, int num) {
        // Ligne
        for (int j = 0; j < 9; j++) {
            if (grille[ligne][j] == num) return false;
        }
        // Colonne
        for (int i = 0; i < 9; i++) {
            if (grille[i][col] == num) return false;
        }
        // Bloc 3x3
        int debutLigne = (ligne / 3) * 3;
        int debutCol = (col / 3) * 3;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (grille[debutLigne + i][debutCol + j] == num) return false;
            }
        }
        return true;
    }
    
    // Accesseurs pour validation
    public int[][] getGrille() { return grille; }
    public void setGrille(int[][] g) { this.grille = g; }
}
