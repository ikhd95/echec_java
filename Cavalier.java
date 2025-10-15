import java.util.*;
public class Cavalier extends Piece {
    public Cavalier(char couleur, Case position, Echiquier echiquier) {
        super(couleur, position, echiquier);
    }

    public ArrayList<Case> getDeplacementsPossibles() {
        ArrayList<Case> deplacements = new ArrayList<>();
        int ligne = position.getLigne();
        int col = position.getColonne();

        int[][] mouvements = {
            {2, 1}, {2, -1}, {-2, 1}, {-2, -1},
            {1, 2}, {1, -2}, {-1, 2}, {-1, -2}
        };

        for (int[] m : mouvements) {
            int l = ligne + m[0];
            int c = col + m[1];

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
        return (dx == 2 && dy == 1) || (dx == 1 && dy == 2);
    }

    public boolean menace(Case destination) {
        return deplacement(destination);
    }
}