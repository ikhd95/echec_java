import java.util.*;
public class Dame extends Piece {
    public Dame(char couleur, Case position, Echiquier echiquier) {
        super(couleur, position, echiquier);
    }

    public ArrayList<Case> getDeplacementsPossibles() {
        ArrayList<Case> deplacements = new ArrayList<>();
        int ligne = position.getLigne();
        int col = position.getColonne();

        // Toutes les directions (8 directions)
        int[] directionsLigne = {1, 1, 1, 0, 0, -1, -1, -1};
        int[] directionsCol = {1, 0, -1, 1, -1, 1, 0, -1};

        for (int d = 0; d < 8; d++) {
            int dl = directionsLigne[d];
            int dc = directionsCol[d];
            int l = ligne + dl;
            int c = col + dc;

            while (true) {
                Case current = echiquier.getCase(l, c);
                if (current == null) break;

                if (!current.estOccupee()) {
                    deplacements.add(current);
                } else {
                    if (current.getPiece().getCouleur() != couleur) {
                        deplacements.add(current);
                    }
                    break;
                }
                l += dl;
                c += dc;
            }
        }
        return deplacements;
    }

    public boolean deplacement(Case destination) {
        int x1 = position.getLigne();
        int y1 = position.getColonne();
        int x2 = destination.getLigne();
        int y2 = destination.getColonne();

        int dx = Integer.compare(x2, x1);
        int dy = Integer.compare(y2, y1);

        
        boolean estLigne = x1 == x2;
        boolean estColonne = y1 == y2;
        boolean estDiagonale = Math.abs(x2 - x1) == Math.abs(y2 - y1);

        if (!estLigne && !estColonne && !estDiagonale) return false;

        int x = x1 + dx;
        int y = y1 + dy;

        while (x != x2 || y != y2) {
            if (echiquier.getCase(x, y).getPiece() != null) return false;
            x += dx;
            y += dy;
        }

        return destination.getPiece() == null || destination.getPiece().getCouleur() != couleur;
    }

    public boolean menace(Case destination) {
        return deplacement(destination);
    }
}