import java.util.*;
public class Roi extends Piece {
    public Roi(char couleur, Case position, Echiquier echiquier) {
        super(couleur, position, echiquier);
    }

    public ArrayList<Case> getDeplacementsPossibles() {
        ArrayList<Case> deplacements = new ArrayList<>();
        int ligne = position.getLigne();
        int col = position.getColonne();

        int[] directionsLigne = {1, 1, 1, 0, 0, -1, -1, -1};
        int[] directionsCol = {1, 0, -1, 1, -1, 1, 0, -1};

        for (int d = 0; d < 8; d++) {
            int l = ligne + directionsLigne[d];
            int c = col + directionsCol[d];

            Case current = echiquier.getCase(l, c);
            if (current != null) {
                if (!current.estOccupee() || current.getPiece().getCouleur() != couleur) {
                    deplacements.add(current);
                }
            }
        }

        return deplacements;
    }

    public boolean deplacement(Case destination) {
        int dx = Math.abs(destination.getLigne() - position.getLigne());
        int dy = Math.abs(destination.getColonne() - position.getColonne());

        if (dx <= 1 && dy <= 1) {
            Piece p = destination.getPiece();
            return p == null || p.getCouleur() != couleur;
        }

        return false;
    }

    public boolean menace(Case c) {
        return deplacement(c);
    }

}
