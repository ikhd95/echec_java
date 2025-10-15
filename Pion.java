import java.util.*;
public class Pion extends Piece {
    public Pion(char couleur, Case position, Echiquier echiquier) {
        super(couleur, position, echiquier);
    }

    public ArrayList<Case> getDeplacementsPossibles() {
        ArrayList<Case> deplacements = new ArrayList<Case>();

        int direction = (couleur == 'B') ? 1 : -1;
        int ligne = position.getLigne();
        int col = position.getColonne();

        Case caseDevant = echiquier.getCase(ligne + direction, col);
        if (caseDevant != null && !caseDevant.estOccupee()) {
            deplacements.add(caseDevant);

            if ((couleur == 'B' && ligne == 1) || (couleur == 'N' && ligne == 6)) {
                Case caseDeuxDevant = echiquier.getCase(ligne + 2 * direction, col);
                if (caseDeuxDevant != null && !caseDeuxDevant.estOccupee()) {
                    deplacements.add(caseDeuxDevant);
                }
            }
        }

        Case diagGauche = echiquier.getCase(ligne + direction, col - 1);
        if (diagGauche != null && diagGauche.estOccupee() && diagGauche.getPiece().getCouleur() != couleur) {
            deplacements.add(diagGauche);
        }

        Case diagDroite = echiquier.getCase(ligne + direction, col + 1);
        if (diagDroite != null && diagDroite.estOccupee() && diagDroite.getPiece().getCouleur() != couleur) {
            deplacements.add(diagDroite);
        }

        return deplacements;
    }

    public boolean deplacement(Case destination) {
        int direction = (couleur == 'B') ? 1 : -1;
        int dx = destination.getLigne() - position.getLigne();
        int dy = Math.abs(destination.getColonne() - position.getColonne());

        if (dy == 0) {
            if ((couleur == 'B' && position.getLigne() == 1 && dx == 2) ||
                (couleur == 'N' && position.getLigne() == 6 && dx == -2)) {return true;}
            return dx == direction;
        }
        return dy == 1 && dx == direction;
    }
    public boolean menace(Case destination) {
        return deplacement(destination); 
    }
}