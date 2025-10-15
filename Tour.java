import java.util.*;
public class Tour extends Piece {
    public Tour(char couleur, Case position, Echiquier echiquier) {
        super(couleur, position, echiquier);
    }

    public ArrayList<Case> getDeplacementsPossibles() {
        ArrayList<Case> deplacements = new ArrayList<Case>();
        int ligne = position.getLigne();
        int col = position.getColonne();

        int[] directionsLigne = {1, -1, 0, 0};
        int[] directionsCol = {0, 0, 1, -1};

        for (int d = 0; d < 4; d++) {
            int dl = directionsLigne[d];
            int dc = directionsCol[d];
            int l = ligne + dl;
            int c = col + dc;

            while (true) {
                Case current = echiquier.getCase(l, c);
                if (current == null) {break;} 

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
        if (destination.getLigne() != position.getLigne() && destination.getColonne() != position.getColonne()) {
            return false;
        }

        int x1 = position.getLigne();
        int y1 = position.getColonne();
        int x2 = destination.getLigne();
        int y2 = destination.getColonne();

        int dx = Integer.compare(x2, x1);
        int dy = Integer.compare(y2, y1);

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