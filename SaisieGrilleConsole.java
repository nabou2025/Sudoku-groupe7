import java.util.Scanner;
public class SaisieGrilleConsole {

    public static void saisir(int[][] grille) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== Saisie manuelle de la grille ===");
        System.out.println("Entrez 9 lignes de 9 chiffres (0-9) séparés par des esp>
        System.out.println("Le chiffre 0 représente une case vide.\n");

        for (int i = 0; i < 9; i++) {
            boolean ligneValide = false;

            // On redemande la ligne tant qu'elle est incorrecte
            while (!ligneValide) {
                System.out.print("Ligne " + (i + 1) + " : ");
                String ligne = scanner.nextLine().trim();

                // Ligne vide
                if (ligne.isEmpty()) {
                    System.out.println("  → Ligne vide, veuillez ressaisir.");
                    continue;
                }

                String[] valeurs = ligne.split("\\s+");

                // Pas assez de colonnes
                if (valeurs.length < 9) {
                    System.out.println("  → Colonne(s) manquante(s) : attendu 9 val>
                        + "trouvé " + valeurs.length + ". Veuillez ressaisir.");
                    continue;
                }

                // Trop de colonnes
                if (valeurs.length > 9) {
                    System.out.println("  → Trop de valeurs : attendu 9, "
                        + "trouvé " + valeurs.length + ". Veuillez ressaisir.");
                    continue;
                }

                // Lecture valeur par valeur
                                
