import java.io.IOException;

public class SudokuMain {
    public static void main(String[] args) {
        SudokuResolu solver = new SudokuResolu();
        
        if (args.length > 0) {
            try {
                solver.chargerGrilleFichier(args[0]);
            } catch (IOException | IllegalArgumentException e) {
                System.out.println("Erreur: " + e.getMessage());
                return;
            }
        } else {
            solver.chargerGrilleManuelle();
        }
        
        solver.afficherGrille("Grille initiale");
        
        if (solver.resoudre()) {
            solver.afficherGrille("Grille résolue");
        } else {
            System.out.println("Aucune solution possible pour cette grille.");
        }
    }
}
