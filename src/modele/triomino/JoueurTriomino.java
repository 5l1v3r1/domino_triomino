package modele.triomino;

import java.util.ArrayList;
import java.util.Collections;

import modele.Point;

public class JoueurTriomino {
	private String nom;
	private int score;
	private boolean cpu;
	private ArrayList<PieceTriomino> main;

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public boolean isCpu() {
		return cpu;
	}

	public void setCpu(boolean cpu) {
		this.cpu = cpu;
	}

	public ArrayList<PieceTriomino> getMain() {
		return main;
	}

	public void setMain(ArrayList<PieceTriomino> main) {
		this.main = main;
	}

	public JoueurTriomino(int jeu, String nom, boolean cpu) {
		super();
		this.nom = nom;
		this.cpu = cpu;
		this.score = 0;
		if (jeu == 1)
			this.main = new ArrayList<PieceTriomino>();
	}

	public ArrayList<PieceTriomino> getAllPiecesSwipes() {
		ArrayList<PieceTriomino> resultat = new ArrayList<PieceTriomino>();
		for (PieceTriomino p : main) {
			resultat.addAll(p.getAllSwipes());
		}
		return resultat;
	}

	public void initMain(ArrayList<PieceTriomino> pieces, int tailleMain) { // initialiser
		// la main ;
		// tailleMain
		// va varier
		// selon le
		// jeu
		for (int i = 0; i < tailleMain; i++) {
			Collections.shuffle(pieces);
			this.main.add(pieces.get(0));
			pieces.remove(0);
		}
	}

	public boolean piocher(ArrayList<PieceTriomino> pieces, int jeu) { // jeu=0
																		// domino
																		// ,
		// jeu=1
		// triomino
		if (pieces.size() == 0) {
			return false;
		}
		
			Collections.shuffle(pieces);
			this.main.add(pieces.get(0));
			pieces.remove(0);
			return true;
	
	
	}

	public boolean mainVide() {
		return (this.main.size() == 0);
	}
	public void ajouterExtremites(int jeu,ModeleTriomino table, int newX,int newY){
		int direction=ModeleTriomino.getDirection(newY, newY);
		int x=newX;
		int y=newY;
		if(table.getTable()[x][y+1]==null){
			table.getExtremite().add(new Point(x, y+1));
		}
		if(table.getTable()[x][y-1]==null){
			table.getExtremite().add(new Point(x, y-1));
		}
		if(direction==1){
			if(table.getTable()[x+1][y]==null){
				table.getExtremite().add(new Point(x+1, y));
			}
		}
		else{
			if(table.getTable()[x-1][y]==null){
				table.getExtremite().add(new Point(x-1, y));
			}
		}
		}
	public void supprimerExtremites(int jeu,ModeleTriomino table, int newX,int newY){
		table.getExtremite().remove(new Point(newX, newY));
	}
	// le coup dans triomino mch ki il domino ; il joueur yenzl al blasa li yheb yhot fiha lpiece mch al piece li bch yrakeb alih ( hit les coordonnes yethesbo )
	public Point coup(int jeu, PieceTriomino piece, int indexDePieceDansLaMain, ModeleTriomino table, int posX, int posY) {
		if(!this.isCpu()){ // humain
			if(table.coupValide(jeu, piece, new Point(posX, posY))){
				table.getTable()[posX][posY]=piece;
				
			this.supprimerExtremites(jeu, table, posX, posY);
			this.ajouterExtremites(jeu, table, posX, posY);
			this.getMain().remove(indexDePieceDansLaMain);
			return new Point(posX, posY);
			}
			else {
				return new Point(1, 1);
			}
		}
		else{ //coup cpu
			for(int i=0;i<this.getMain().size();i++){
				for(Point ext:table.getExtremite()){
					for(PieceTriomino pieceCpu:this.getMain().get(i).getAllSwipes()){
						if(table.coupValide(jeu, pieceCpu, ext)){
							table.getTable()[ext.getX()][ext.getY()]=pieceCpu;
							this.supprimerExtremites(jeu, table, ext.getX(), ext.getY());
							this.ajouterExtremites(jeu, table, ext.getX(), ext.getY());
							this.getMain().remove(i);
							return new Point(ext.getX(), ext.getY());
						}
					}
				}
			}
			return  new Point(1, 1);
		}
	}
	



	public boolean nePeutPasJouer(int jeu, ModeleTriomino table) {
		
			for (Point p : table.getExtremite()) {
				for (PieceTriomino piece : this.getAllPiecesSwipes()) {
					if (table.coupValide(jeu, piece, p)) {
						return false;
					}
				
				}
			}
		
			return true;
		
	}

	
}
