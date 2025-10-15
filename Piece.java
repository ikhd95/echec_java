import java.util.*;
public abstract class Piece {
    protected char couleur;
    protected Case position;
    protected Echiquier echiquier;

    public Piece(char couleur, Case position, Echiquier echiquier) {
        this.couleur = couleur;
        this.position = position;
        this.echiquier = echiquier;
    }

    public char getCouleur() {
        return couleur;
    }

    public Case getPosition() {
        return position;
    }

    public void setPosition(Case position) {
        this.position = position;
    }
    public void setCouleur(char c){
        this.couleur = c;
    }

    public abstract boolean deplacement(Case destination);

    public abstract boolean menace (Case c);

    public abstract ArrayList<Case> getDeplacementsPossibles();

    public String toString() {
        return this.getClass().getSimpleName().charAt(0) + ("" + couleur);
    }
}