import java.io.FileNotFoundException;
import java.io.IOException;

public class SudokuMain {

    public static void main(String[] args) {
        SudokuResolu solver = new SudokuResolu();

        // ── 1. Chargement ──────────────────────────────────────────────
        if (args.length > 0) {
            try {
                solver.chargerGrilleFichier(args[0]);
            } catch (FileNotFoundException e) {
                System.out.println("Erreur : fichier introuvable → " + args[0]);
                return;
            } catch (IOException e) {
                System.out.println("Erreur de lecture : " + e.getMessage());
                return;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
                return;
            }
        } else {
            try {
                solver.chargerGrilleManuelle();
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
                return;
            }
        }

        // ── 2. Grille initiale ─────────────────────────────────────────
        solver.afficherGrille("Grille initiale");

        // ── 3. Résolution ──────────────────────────────────────────────
        if (solver.resoudre()) {
            solver.afficherGrille("Grille résolue");
        } else {
            System.out.println("\nAucune solution possible pour cette grille.");
        }
    }
}
