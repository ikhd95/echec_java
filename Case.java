import java.util.*;
public class Case {
    private int ligne;
    private int colonne; 
    private Piece piece;

    public Case(int ligne, int colonne) {
        this.ligne = ligne;
        this.colonne = colonne;
        this.piece = null;
    }

    public int getLigne() {
        return ligne;
    }

    public int getColonne() {
        return colonne;
    }

    public Piece getPiece() {
        return piece;
    }

    public void setPiece(Piece piece) {
        this.piece = piece;
    }

    public boolean estOccupee() {
        return piece != null;
    }

    public String toString() {
        if (piece == null) return "--";
        return piece.toString();
    }
}
