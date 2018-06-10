package modele.triomino;

import java.util.ArrayList;
import java.util.Collections;

import modele.Point;
import modele.domino.JoueurDomino;

public class ModeleTriomino {
	private PieceTriomino table[][];
	private ArrayList<PieceTriomino> deck;
	private ArrayList<JoueurTriomino> joueurs;
	private ArrayList<Point> extremite;

	
	public ArrayList<JoueurTriomino> getJoueurs() {
		return joueurs;
	}

	public void setJoueurs(ArrayList<JoueurTriomino> joueurs) {
		this.joueurs = joueurs;
	}

	public ArrayList<Point> getExtremite() {
		return extremite;
	}

	public void setExtremite(ArrayList<Point> extremite) {
		this.extremite = extremite;
	}

	public ModeleTriomino(int jeu, ArrayList<JoueurDomino> joueurs) { // dom=0,tri=1
		
			this.setTable(new PieceTriomino[113][113]);
			this.deck = new ArrayList<PieceTriomino>();
			this.joueurs=new ArrayList<JoueurTriomino>();
			for(JoueurDomino j:joueurs){
				this.joueurs.add(new JoueurTriomino(1, j.getNom(), j.isCpu()));
			}
			//this.joueurs = joueurs;
			this.extremite = new ArrayList<Point>();
			this.initDeck(jeu);
		
	}
	public static int getDirection(int i, int j){ //retourn 1 si le sommet est en haut 0 si en bas
		if(i%2 == j%2){
			return 0;
		}else 
			return 1;
	}
	public boolean coupValide(int jeu, PieceTriomino piece, Point p) {
		if(!this.getExtremite().contains(p)){ // si le point joué n'est pas dans le tableau d'extremites alors coup invalide ( non permis )
			return false;
		}
		int direction = ModeleTriomino.getDirection(p.getX(), p.getY()); // la variable direction bch t9olek sommet lfou9 wala louta ( 0 maaneha louta )
		int x=p.getX();
		int y=p.getY();
//		System.out.println("directinon ="+direction);
//
//		System.out.println("X ="+piece.getX());
//		System.out.println("Y ="+piece.getY());
//		System.out.println("Z ="+piece.getZ());
//	
		if(direction==0){//sommet en bas
			boolean pieceJoueEnBas=(this.getTable()[x-1][y]!=null && piece.getZ()==this.getTable()[x-1][y].getX() && piece.getX()==this.getTable()[x-1][y].getZ());
			boolean pieceJoueADroite=(this.getTable()[x][y+1]!=null && piece.getX()==this.getTable()[x][y+1].getY() && piece.getY()==this.getTable()[x][y+1].getX()) ;
			boolean pieceJouerAGauche=(this.getTable()[x][y-1]!=null && piece.getZ()==this.getTable()[x][y-1].getY() && piece.getY()==this.getTable()[x][y-1].getZ());
		//	return pieceJoueEnBas || pieceJoueADroite || pieceJouerAGauche ;
			return (pieceJoueADroite && pieceJouerAGauche && this.getTable()[x-1][y]==null ) 
					|| (pieceJoueADroite && this.getTable()[x][y-1]==null && this.getTable()[x-1][y]==null ) 
					|| (pieceJouerAGauche && this.getTable()[x][y+1]==null && this.getTable()[x-1][y]==null)
					|| (pieceJoueADroite && this.getTable()[x][y-1]==null && pieceJoueEnBas ) 
					|| (pieceJouerAGauche && this.getTable()[x][y+1]==null &&pieceJoueEnBas )
					|| ( pieceJoueEnBas && this.getTable()[x][y+1]==null && this.getTable()[x][y-1]==null ) 
					||  ( pieceJoueEnBas && pieceJoueADroite && this.getTable()[x][y-1]==null ) 
					||  ( pieceJoueEnBas && pieceJouerAGauche && this.getTable()[x][y+1]==null ) 
					||  ( pieceJoueEnBas && pieceJouerAGauche && pieceJoueADroite ) ;
			
		}else {//sommet en haut
			boolean pieceJoueEnHaut=(this.getTable()[x+1][y]!=null && piece.getX()==this.getTable()[x+1][y].getZ() && piece.getZ()==this.getTable()[x+1][y].getX());
			boolean pieceJoueADroite=(this.getTable()[x][y+1]!=null && piece.getZ()==this.getTable()[x][y+1].getY() && piece.getY()==this.getTable()[x][y+1].getZ());
			boolean pieceJouerAGauche=(this.getTable()[x][y-1]!=null && piece.getX()==this.getTable()[x][y-1].getY() && piece.getY()==this.getTable()[x][y-1].getX());
			//return pieceJoueEnHaut || pieceJoueADroite || pieceJouerAGauche;
			return (pieceJoueADroite && pieceJouerAGauche && this.getTable()[x+1][y]==null ) 
					|| (pieceJoueADroite && this.getTable()[x][y-1]==null && this.getTable()[x+1][y]==null ) 
					|| (pieceJouerAGauche && this.getTable()[x][y+1]==null && this.getTable()[x+1][y]==null)
					|| (pieceJoueADroite && this.getTable()[x][y-1]==null && pieceJoueEnHaut ) 
					|| (pieceJouerAGauche && this.getTable()[x][y+1]==null && pieceJoueEnHaut )
					|| ( pieceJoueEnHaut && this.getTable()[x][y+1]==null && this.getTable()[x][y-1]==null ) 
					||  ( pieceJoueEnHaut && pieceJoueADroite && this.getTable()[x][y-1]==null ) 
					||  ( pieceJoueEnHaut && pieceJouerAGauche && this.getTable()[x][y+1]==null ) 
					||  ( pieceJoueEnHaut && pieceJouerAGauche && pieceJoueADroite ) ;
		}
	}

	public void initDeck(int jeu) {
		deck=new ArrayList<PieceTriomino>();
			for (int i = 0; i < 6; i++) {
				for (int j = i; j < 6; j++) {
					for( int k=j; k<6;k++){
					deck.add(new PieceTriomino(i, j,k));
				
					}
				}
			}
	
	}

	public ArrayList<PieceTriomino> getDeck() {
		return deck;
	}

	public void setDeck(ArrayList<PieceTriomino> deck) {
		this.deck = deck;
	}

	public PieceTriomino[][] getTable() {
		return table;
	}

	public void setTable(PieceTriomino table[][]) {
		this.table = table;
	}
	public boolean piocher(JoueurTriomino joueur){
		if(this.getDeck().size()==0)
			return false;
		Collections.shuffle(this.getDeck());
		this.deck.add(this.getDeck().get(0));
		this.getDeck().remove(0);
		return true;
	}
	public int finPartie(int jeu) { // retourne l'id du joueurs ganant 5 sinon // plutart il faut traiter le cas de l'egalité
		
			int max = joueurs.get(0).getScore();
			int maxId = 0;
			
			boolean fin = true;
			for (int i = 0; i < joueurs.size(); i++) {
				
				if (joueurs.get(i).mainVide()) {
					return i;
				} else {
					if (joueurs.get(i).getScore() > max) {
						max = joueurs.get(i).getMain().size();
						maxId = i;
				
					}
					fin = fin && joueurs.get(i).nePeutPasJouer(jeu, this) && joueurs.get(i).getNombreDePioches() >=3; 
				}
			}
			if (fin == true) {
				return maxId;
			} else {
				return 5;
			}
		
	}
	public boolean verifPont(Point joue,PieceTriomino piece){
		int direction=ModeleTriomino.getDirection(joue.getX(), joue.getY());
		int x=joue.getX();
		int y=joue.getY();
		if(direction==1){ //sommet en haut
			boolean pontPointeGauche=(this.getTable()[x][y-1]==null && this.getTable()[x+1][y]==null && this.getTable()[x][y+1]!=null && this.getTable()[x][y-2]!=null && this.getTable()[x][y-2].getZ()==piece.getX());
			boolean pontPointeDroite=(this.getTable()[x][y+1]==null && this.getTable()[x+1][y]==null && this.getTable()[x][y-1]!=null && this.getTable()[x][y+2]!=null && this.getTable()[x][y-2].getX()==piece.getZ());
			boolean pontPointeHaut=(this.getTable()[x][y+1]==null && this.getTable()[x][y-1]==null && this.getTable()[x+1][y]!=null && this.getTable()[x-2][y]!=null && this.getTable()[x-2][y].getY()==piece.getY());
			return pontPointeGauche ||pontPointeDroite||pontPointeHaut;
		}else { //sommet en bas
			boolean pontPointeGauche=(this.getTable()[x][y-1]==null && this.getTable()[x-1][y]==null && this.getTable()[x][y+1]!=null && this.getTable()[x][y-2]!=null && this.getTable()[x][y-2].getZ()==piece.getX());
			boolean pontPointeDroite=(this.getTable()[x][y+1]==null && this.getTable()[x-1][y]==null && this.getTable()[x][y-1]!=null && this.getTable()[x][y+2]!=null && this.getTable()[x][y-2].getX()==piece.getZ());
			boolean pontPointeBas=(this.getTable()[x][y+1]==null && this.getTable()[x][y-1]==null && this.getTable()[x-1][y]!=null && this.getTable()[x-2][y]!=null && this.getTable()[x-2][y].getY()==piece.getY());
			return pontPointeGauche ||pontPointeDroite||pontPointeBas;
		}
		

	}
	public boolean verifHexagone(Point joue,int direction){ // 0 a droite  1 a gauche 2 haut ou bas
		int sommet=ModeleTriomino.getDirection(joue.getX(), joue.getY());
		int x=joue.getX();
		int y=joue.getY();
		if(sommet==1){
			if(direction==2){
				return (this.getTable()[x][y-1]!=null &&this.getTable()[x][y+1]!=null && this.getTable()[x-1][y]!=null && this.getTable()[x-1][y+1]!=null && this.getTable()[x-1][y-1]!=null );
			}
			else if(direction==0){
				return (this.getTable()[x][y+1]!=null &&this.getTable()[x][y+2]!=null && this.getTable()[x+1][y]!=null && this.getTable()[x+1][y+1]!=null && this.getTable()[x+1][y+2]!=null );
				
			}
			else{
				return (this.getTable()[x][y-1]!=null &&this.getTable()[x][y-2]!=null && this.getTable()[x+1][y]!=null && this.getTable()[x+1][y-1]!=null && this.getTable()[x+1][y-2]!=null );
				
			}
		}
		else{
			if(direction==2){
				return (this.getTable()[x][y-1]!=null &&this.getTable()[x][y+1]!=null && this.getTable()[x+1][y]!=null && this.getTable()[x+1][y+1]!=null && this.getTable()[x+1][y-1]!=null );
			}
			else if(direction==0){
				return (this.getTable()[x][y+1]!=null &&this.getTable()[x][y+2]!=null && this.getTable()[x-1][y]!=null && this.getTable()[x-1][y+1]!=null && this.getTable()[x-1][y+2]!=null );
				
			}
			else{
				return (this.getTable()[x][y-1]!=null &&this.getTable()[x][y-2]!=null && this.getTable()[x-1][y]!=null && this.getTable()[x-1][y-1]!=null && this.getTable()[x-1][y-2]!=null );
				
			}
		}
	}

	public boolean verifDoubleHexagone(Point joue){
		return (this.verifHexagone(joue, 0) && this.verifHexagone(joue, 1)) || (this.verifHexagone(joue, 0) && this.verifHexagone(joue, 2)) || (this.verifHexagone(joue, 2) && this.verifHexagone(joue, 1));
	}

	public boolean verifTripleHexagone(Point joue){
		return (this.verifHexagone(joue, 0) && this.verifHexagone(joue, 1)  && this.verifHexagone(joue, 2)); 
	}

	public  int getJoueurQuiCommance(int tentative) { //retourn 4 si il ya 2 joueurs qui ont le max
		int max=joueurs.get(0).getMain().get(tentative).valeur();
		int resultat=0;
		int nbMax=0;
		for(int i=0;i<joueurs.size();i++){
			if(joueurs.get(i).getMain().get(tentative).valeur()>max){max=joueurs.get(i).getMain().get(tentative).valeur();}
		}
		for(int i=0;i<joueurs.size();i++){
			if(joueurs.get(i).getMain().get(tentative).valeur()==max){
				resultat=i;
				nbMax++;
				}
		}
		if(nbMax==1){
			return resultat;
			
		}else{
		
			return 4;
		}
	}
}
