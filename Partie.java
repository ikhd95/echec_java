import java.util.*;

public class Partie {
    private Joueur blanc, noir;
    private Echiquier echiquier;
    private char trait;
    private ArrayList<String> coupsJoues;
    private Scanner sc;

    public Partie() {
        sc = new Scanner(System.in);
        System.out.print("Nom du joueur blanc : ");
        blanc = new Joueur(sc.nextLine(), 'B');
        System.out.print("Nom du joueur noir  : ");
        noir = new Joueur(sc.nextLine(), 'N');
        echiquier = new Echiquier();
        trait = 'B';
        coupsJoues = new ArrayList<>();
    }

    public void jouer() {
        while (true) {
            echiquier.modeliser();
            System.out.println("\n Coups joués : " + coupsJoues);
            System.out.println((trait == 'B' ? blanc.getNom() : noir.getNom()) + " joue (ex : e2 e4): ");
            
            String depart = sc.next();
            String arrivee = sc.next();

            Case c1 = echiquier.getCase(depart);
            Case c2 = echiquier.getCase(arrivee);

            Piece piece = c1.getPiece();

            
            if (piece == null || piece.getCouleur() != trait || !piece.deplacement(c2)) {
                System.out.println("\n \n \t  Coup invalide. Recommencez. \n \n \t");
                continue;
            }

            
            Piece pieceCapturee = c2.getPiece();
            c2.setPiece(piece);
            c1.setPiece(null);
            piece.setPosition(c2);

            
            if (echiquier.estEnEchec(trait)) {
                System.out.println("\n \n \t Coup met votre roi en échec. Recommencez. \n ");
                c1.setPiece(piece);
                c2.setPiece(pieceCapturee);
                piece.setPosition(c1);
                continue;
                
            }

            coupsJoues.add(depart + "-" + arrivee);
            
            
            trait = (trait == 'B') ? 'N' : 'B';
            if (echiquier.estEnEchec(trait)) {
                System.out.println("\n Échec pour les " + trait + "\n");
            }
            if (echiquier.estEchecEtMat(trait)) {System.out.println("\n \n \t ECHEC ET MATH ---- > LES " + trait + " ONT PERDU.\n"); break;}
            if (echiquier.estPat(trait)) {System.out.println("\n \n \t PATH : MATCH NULL\n"); break;}
        }
    }

    public void fermerScanner() {
        if (sc != null) {
            sc.close();
        }
    }

    public static void main(String[] args) {
        Partie p = new Partie();
        p.jouer();
        p.fermerScanner(); 
    }
}