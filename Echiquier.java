import java.util.*;
public class Echiquier {
    private Case[][] cases;

    public Echiquier() {
        cases = new Case[8][8];
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                cases[i][j] = new Case(i, j);
            }
        }
        initialiser();
    }

    public void initialiser() {
        for (int i = 0; i < 8; i++) {
            cases[1][i].setPiece(new Pion('B', cases[1][i], this));
            cases[6][i].setPiece(new Pion('N', cases[6][i], this));
        }

        cases[0][0].setPiece(new Tour('B', cases[0][0], this));
        cases[0][7].setPiece(new Tour('B', cases[0][7], this));
        cases[7][0].setPiece(new Tour('N', cases[7][0], this));
        cases[7][7].setPiece(new Tour('N', cases[7][7], this));

        cases[0][1].setPiece(new Cavalier('B', cases[0][1], this));    

        cases[0][6].setPiece(new Cavalier('B', cases[0][6], this));
        cases[7][1].setPiece(new Cavalier('N', cases[7][1], this));
        cases[7][6].setPiece(new Cavalier('N', cases[7][6], this));

        cases[0][2].setPiece(new Fou('B', cases[0][2], this));
        cases[0][5].setPiece(new Fou('B', cases[0][5], this));
        cases[7][2].setPiece(new Fou('N', cases[7][2], this));
        cases[7][5].setPiece(new Fou('N', cases[7][5], this));

        cases[0][3].setPiece(new Dame('B', cases[0][3], this));
        cases[7][3].setPiece(new Dame('N', cases[7][3], this));

        cases[0][4].setPiece(new Roi('B', cases[0][4], this));
        cases[7][4].setPiece(new Roi('N', cases[7][4], this));
    }

    public void modeliser() {
        for (int i = 7; i >= 0; i--) {
            System.out.print((i + 1) + " ");
            for (int j = 0; j < 8; j++) {
                System.out.print(cases[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println("   a  b  c  d  e  f  g  h");
    }

    public Case getCase(String notation) {
        int col = notation.charAt(0) - 'a';
        int row = Character.getNumericValue(notation.charAt(1)) - 1;
        return cases[row][col];
    }

    public Case getCase(int ligne, int colonne) {
        if (ligne >= 0 && ligne < 8 && colonne >= 0 && colonne < 8) {
            return cases[ligne][colonne];
        }
        return null;
    }

    public Case trouverRoi(char couleur) {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Piece p = cases[i][j].getPiece();
                if (p instanceof Roi && p.getCouleur() == couleur) {
                    return cases[i][j];
                }
            }
        }
        return null;
    }

    public boolean estEnEchec(char couleur) {
        Case roi = trouverRoi(couleur);
        if (roi == null) {return false;}

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Piece p = cases[i][j].getPiece();    

                if (p != null && p.getCouleur() != couleur) {
                    if (p.menace(roi)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public boolean estEchecEtMat(char couleur) {
    if (!estEnEchec(couleur)) return false;

    for (int i = 0; i < 8; i++) {
        for (int j = 0; j < 8; j++) {
            Piece p = cases[i][j].getPiece();
            if (p != null && p.getCouleur() == couleur) {
                ArrayList<Case> deplacements = p.getDeplacementsPossibles();
                for (Case destination : deplacements) {
                    Case source = p.getPosition();
                    Piece pieceCapturee = destination.getPiece();

                    source.setPiece(null);
                    destination.setPiece(p);
                    p.setPosition(destination);

                    boolean toujoursEnEchec = estEnEchec(couleur);

                    destination.setPiece(pieceCapturee);
                    source.setPiece(p);
                    p.setPosition(source);

                    if (!toujoursEnEchec) {
                        return false;
                    }
                }
            }
        }
    }

    return true;
}
    public boolean estPat(char couleur) {
    if (estEnEchec(couleur)) {return false;}

    for (int i = 0; i < 8; i++) {
        for (int j = 0; j < 8; j++) {
            Piece p = cases[i][j].getPiece();
            if (p != null && p.getCouleur() == couleur) {
                ArrayList<Case> deplacements = p.getDeplacementsPossibles();
                for (Case destination : deplacements) {
                    Case source = p.getPosition();
                    Piece pieceCapturee = destination.getPiece();

                    source.setPiece(null);
                    destination.setPiece(p);
                    p.setPosition(destination);

                    boolean roiToujoursHorsEchec = !estEnEchec(couleur);

                    destination.setPiece(pieceCapturee);
                    source.setPiece(p);
                    p.setPosition(source);

                    if (roiToujoursHorsEchec) {
                        return false; 
                    }
                }
            }
        }
    }
    return true;
}
}