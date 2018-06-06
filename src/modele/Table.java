package modele;

import java.util.ArrayList;

public class Table {
	private PieceDomino table[][];
	private ArrayList<PieceDomino> deck;
	private ArrayList<Joueur> joueurs;
	private ArrayList<Point> extremite;

	public int joueurQuiCommance() {
		int res = 0;
		int val = 0;
		int i = 0;
		for (Joueur j : joueurs) {
			if (j.valeurDuPlusGrandDouble() > val) {
				val = j.valeurDuPlusGrandDouble();
				res = i;
			}
			i++;
		}
		return res;
	}

	public ArrayList<Joueur> getJoueurs() {
		return joueurs;
	}

	public void setJoueurs(ArrayList<Joueur> joueurs) {
		this.joueurs = joueurs;
	}

	public ArrayList<Point> getExtremite() {
		return extremite;
	}

	public void setExtremite(ArrayList<Point> extremite) {
		this.extremite = extremite;
	}

	public Table(int jeu, ArrayList<Joueur> joueurs) { // dom=0,tri=1
		if (jeu == 0) {
			this.setTable(new PieceDomino[55][55]);
			this.deck = new ArrayList<PieceDomino>();
			this.joueurs = joueurs;
			this.extremite = new ArrayList<Point>();
			this.initDeck(jeu);
		} else if (jeu == 1) {
			// TODO triomino}
		}
	}

	public boolean coupValide(int jeu, PieceDomino piece, Point p, Point oldPoint) { // pb ici
		int x = p.getX();
		int y = p.getY();
		if(piece.getRot()==0){ //piece jouée horizentale
			if(piece.isCentre() && piece.getX()==piece.getY()){// piece jouée horizentale et centrée
				if(table[x-1][y]!=null && table[x-1][y+1]==null && table[x-1][y-1]==null && piece.getX()==table[x-1][y].getY()){ // piece jouée horizentale et centrée en bas de la pieceTable
					oldPoint.setX(x-1);
					oldPoint.setY(y);
					return true;
				}
				else if(table[x+1][y]!=null  && table[x+1][y+1]==null && table[x+1][y-1]==null && piece.getX()==table[x+1][y].getX()){ // piece jouée horizentale et centrée en haut de la pieceTable
					oldPoint.setX(x+1);
					oldPoint.setY(y);
					return true;
				}
				else { //piece jouée horizentale mais pas valide
					return false;
				}
			} 
			else{ // piece jouée horizentale et non centrée
				if(table[x][y-1]!=null && table[x-1][y-1]==null && table[x+1][y-1]==null && table[x][y-1].getRot()==1 && table[x][y-1].isCentre() && piece.getX()==table[x][y-1].getY()){// piece jouée horizentale et non centrée et a droite de la pieceTable( qui est centrée) et pas meme rot
					oldPoint.setX(x);
					oldPoint.setY(y-1);
					return true;
				}
				else if(table[x][y+1]!=null && table[x-1][y+1]==null && table[x+1][y+1]==null && table[x][y+1].getRot()==1 && table[x][y+1].isCentre() && piece.getY()==table[x][y+1].getX()){// piece jouée horizentale et non centrée et a gauche de la pieceTable( qui est centrée) et pas meme rot
					oldPoint.setX(x);
					oldPoint.setY(y+1);
					return true;
				}
				else if(table[x][y-1]!=null && table[x-1][y-1]==null && table[x+1][y-1]==null && table[x][y-1].getRot()==0 && piece.getX()==table[x][y-1].getY()){ // piece jouée horizentale et non centrée et a droite de la pieceTable et meme rot
					oldPoint.setX(x);
					oldPoint.setY(y-1);
					return true;
				}
				else if(table[x][y+1]!=null && table[x-1][y+1]==null && table[x+1][y+1]==null && table[x][y+1].getRot()==0 && piece.getY()==table[x][y+1].getX()){ // piece jouée horizentale et non centrée et a gauche de la pieceTable et meme rot
					oldPoint.setX(x);
					oldPoint.setY(y+1);
					return true;
				}
				else if(table[x][y-1]!=null && table[x-1][y-1]==null && table[x][y-2]==null && table[x][y-1].getRot()==1 && piece.getX()==table[x][y-1].getX()){ // piece jouée horizentale et non centrée et a droite de la pieceTable et pas meme rot
					oldPoint.setX(x);
					oldPoint.setY(y-1);
					return true;
				}
				else if(table[x][y+1]!=null && table[x-1][y+1]==null && table[x][y+2]==null&& table[x][y+1].getRot()==1 && piece.getX()==table[x][y+1].getX()){ // piece jouée horizentale et non centrée et a gauche de la pieceTable et pas meme rot
					oldPoint.setX(x);
					oldPoint.setY(y+1);
					return true;
				}
				else if(table[x][y-1]!=null && table[x+1][y-1]==null && table[x][y-2]==null && table[x][y-1].getRot()==1 && piece.getX()==table[x][y-1].getY()){ // piece jouée horizentale et non centrée et a droite de la pieceTable et pas meme rot
					oldPoint.setX(x);
					oldPoint.setY(y-1);
					return true;
				}
				else if(table[x][y+1]!=null && table[x+1][y+1]==null  && table[x][y+2]==null && table[x][y+1].getRot()==1 && piece.getY()==table[x][y+1].getY()){ // piece jouée horizentale et non centrée et a gauche de la pieceTable et pas meme rot
					oldPoint.setX(x);
					oldPoint.setY(y+1);
					return true;
				}
			}
			
		}
		else{ //piece jouée verticale
			if(piece.isCentre() && piece.getX()==piece.getY()){// piece jouée verticale et centrée
				if(table[x][y-1]!=null && table[x+1][y-1]==null && table[x-1][y-1]==null && piece.getX()==table[x][y-1].getY()){ // piece jouée verticale et centrée a droite de la pieceTable
					oldPoint.setX(x);
					oldPoint.setY(y-1);
					return true;
				}
				else if(table[x][y+1]!=null  && table[x-1][y+1]==null && table[x+1][y+1]==null && piece.getX()==table[x][y+1].getX()){ // piece jouée verticale et centrée a gauche de la pieceTable
					oldPoint.setX(x);
					oldPoint.setY(y+1);
					return true;
				}
				else { //piece jouée centre verticale mais pas valide
					return false;
				}
			} 
			else{ // piece jouée verticale et non centrée
				if(table[x+1][y]!=null && table[x+1][y-1]==null && table[x+1][y+1]==null && table[x+1][y].getRot()==0 && table[x+1][y].isCentre() && piece.getY()==table[x+1][y].getX()){// piece jouée vertcale et non centrée et en haut de la pieceTable( qui est centrée) et pas meme rot
					oldPoint.setX(x+1);
					oldPoint.setY(y);
					return true;
				}
				else if(table[x-1][y]!=null && table[x-1][y+1]==null && table[x+1][y-1]==null && table[x-1][y].getRot()==0 && table[x-1][y].isCentre() && piece.getX()==table[x-1][y].getX()){// piece jouée vertcale et non centrée et en bas de la pieceTable( qui est centrée) et pas meme rot
					oldPoint.setX(x-1);
					oldPoint.setY(y);
					return true;
				}
				else if(table[x+1][y]!=null && table[x+1][y-1]==null && table[x+1][y+1]==null && table[x+1][y].getRot()==1 && piece.getY()==table[x+1][y].getX()){ // piece jouée vertcale et non centrée et a droite de la pieceTable et meme rot
					oldPoint.setX(x+1);
					oldPoint.setY(y);
					return true;
				}
				else if(table[x-1][y]!=null && table[x-1][y-1]==null && table[x-1][y+1]==null && table[x-1][y].getRot()==1 && piece.getX()==table[x-1][y].getX()){ // piece jouée vertcale et non centrée et a gauche de la pieceTable et meme rot
					oldPoint.setX(x-1);
					oldPoint.setY(y);
					return true;
				}
				else if(table[x+1][y]!=null && table[x+1][y-1]==null  && table[x+2][y]==null  && table[x+1][y].getRot()==0 && piece.getY()==table[x+1][y].getX()){ // piece jouée vertcale et non centrée et en haut de la pieceTable et pas meme rot
					oldPoint.setX(x+1);
					oldPoint.setY(y);
					return true;
				}
				else if(table[x-1][y]!=null && table[x-1][y-1]==null && table[x-2][y]==null  && table[x-1][y].getRot()==0 && piece.getX()==table[x-1][y].getX()){ // piece jouée vertcale et non centrée et en bas de la pieceTable et pas meme rot
					oldPoint.setX(x-1);
					oldPoint.setY(y);
					return true;
				}
				else if(table[x+1][y]!=null && table[x+1][y+1]==null  && table[x+2][y]==null  && table[x+1][y].getRot()==0 && piece.getY()==table[x+1][y].getY()){ // piece jouée vertcale et non centrée et en haut de la pieceTable et pas meme rot
					oldPoint.setX(x+1);
					oldPoint.setY(y);
					return true;
				}
				else if(table[x-1][y]!=null && table[x-1][y+1]==null && table[x-2][y]==null  && table[x-1][y].getRot()==0 && piece.getX()==table[x-1][y].getY()){ // piece jouée vertcale et non centrée et en bas de la pieceTable et pas meme rot
					oldPoint.setX(x-1);
					oldPoint.setY(y);
					return true;
				}
			}
			
		}
		return false;
//		if (jeu == 0) {
//			table = this.getTable();
//			int x = p.getX();
//			int y = p.getY();
//		
//			if (table[x][y - 1] != null) {
//				if (table[x][y - 1].getX() == table[x][y - 1].getY() && table[x][y - 1].isCentre()
//						&& table[x][y - 1].getRot() == 1) {
//					if (table[x][y - 1].getX() == piece.getX()) {
//						oldPoint.setX(x);
//						oldPoint.setY(y - 1);
//						return true;
//					}
//				} 
//				if (table[x][y - 1].getRot() == 1) {
//					if (piece.getRot() == 0
//							&& ((piece.getY() == table[x][y - 1].getX() && table[x-1][y - 1]==null)|| 
//						(	piece.getY() == table[x][y - 1].getY() && table[x+1][y - 1]==null))) {
//						oldPoint.setX(x);
//						oldPoint.setY(y - 1);
//						return true;
//					}
//				} 
//				if (table[x][y - 1].getRot() == 0) {
//					if (piece.getRot() == 1 && piece.isCentre() && piece.getX() == piece.getY()
//							&& piece.getX() == table[x][y - 1].getY()) {
//						oldPoint.setX(x);
//						oldPoint.setY(y - 1);
//						return true;
//					} else if (piece.getRot() == 0 && piece.getX() == table[x][y - 1].getY()) {
//						oldPoint.setX(x);
//						oldPoint.setY(y - 1);
//						return true;
//					}
//				}
//
//			} else if (table[x][y + 1] != null) {
//				if (table[x][y + 1].isCentre() && table[x][y + 1].getX() == table[x][y + 1].getY()
//						&& table[x][y + 1].getRot() == 1) {
//					if (table[x][y + 1].getX() == piece.getY()) {
//						oldPoint.setX(x);
//						oldPoint.setY(y + 1);
//						return true;
//					}
//				}
//				if (table[x][y + 1].getRot() == 1) {
//					if (piece.getRot() == 0 && ((piece.getX() == table[x][y - 1].getX() && table[x - 1][y + 1] == null))
//							|| (piece.getX() == table[x][y + 1].getY() && table[x + 1][y + 1] == null)) {
//						oldPoint.setX(x);
//						oldPoint.setY(y + 1);
//						return true;
//					}
//				} 
//				if (table[x][y + 1].getRot() == 0) {
//					if (piece.getRot() == 0 && piece.getY() == table[x][y + 1].getX()) {
//						oldPoint.setX(x);
//						oldPoint.setY(y + 1);
//						return true;
//					} else if (piece.getRot() == 1 && piece.isCentre() && piece.getX() == piece.getY()
//							&& piece.getX() == table[x][y + 1].getX()) {
//						oldPoint.setX(x);
//						oldPoint.setY(y + 1);
//						return true;
//					}
//				}
//			} else if (table[x - 1][y] != null) {
//
//				if (table[x - 1][y].isCentre() && table[x - 1][y].getX() == table[x - 1][y].getY()
//						&& table[x - 1][y].getRot() == 0) {
//					if (table[x - 1][y].getX() == piece.getY()) {
//						oldPoint.setX(x - 1);
//						oldPoint.setY(y);
//						return true;
//					}
//				} 
//				if (table[x - 1][y].getRot() == 1) {
//					if (piece.getRot() == 1 && piece.getX() == table[x - 1][y].getY())
//
//					{
//						oldPoint.setX(x - 1);
//						oldPoint.setY(y);
//						return true;
//					} else if (piece.getRot() == 0 && piece.isCentre() && piece.getX() == piece.getY()
//							&& piece.getY() == table[x - 1][y].getX()) {
//						oldPoint.setX(x - 1);
//						oldPoint.setY(y);
//						return true;
//					}
//				} 
//				if (table[x - 1][y].getRot() == 0) { 
//					if (piece.getRot() == 1 && ((piece.getX() == table[x - 1][y].getX() && table[x - 1][y - 1] == null)
//							|| (piece.getX() == table[x - 1][y].getY() && table[x - 1][y + 1] == null))) {
//						oldPoint.setX(x - 1);
//						oldPoint.setY(y);
//						return true;
//					}
//				}
//
//			} else if (table[x + 1][y] != null) {
//				if (table[x + 1][y].isCentre() && table[x + 1][y].getX() == table[x + 1][y].getY()
//						&& table[x + 1][y].getRot() == 0) {
//					if (table[x + 1][y].getX() == piece.getX()) {
//						oldPoint.setX(x + 1);
//						oldPoint.setY(y);
//						return true;
//					}
//				} 
//				if (table[x + 1][y].getRot() == 1) {
//					if (piece.getRot() == 1 && piece.getY() == table[x + 1][y].getX()) {
//						oldPoint.setX(x + 1);
//						oldPoint.setY(y);
//						return true;
//					} else if (piece.getRot() == 0 && piece.isCentre() && piece.getX() == piece.getY()
//							&& piece.getX() == table[x + 1][y].getY()) {
//						oldPoint.setX(x + 1);
//						oldPoint.setY(y);
//						return true;
//					}
//				} 
//				if (table[x + 1][y].getRot() == 0) {
//					if (piece.getRot() == 1 && ((piece.getX() == table[x + 1][y].getX() && table[x + 1][y - 1] == null))
//							|| (table[x + 1][y].getY() == piece.getY() && table[x + 1][y + 1] == null)) {
//						oldPoint.setX(x + 1);
//						oldPoint.setY(y);
//						return true;
//					}
//				}
//
//			}
//		} else {
//
//			return false;
//			// TODO trio
//		}
//		
//		return false;
	}

	public void initDeck(int jeu) { // dom=0,tri=1
		if (jeu == 0) {
			for (int i = 0; i <= 6; i++) {
				for (int j = i; j <= 6; j++) {
					deck.add(new PieceDomino(i, j));
				}
			}
		} else if (jeu == 1) {
			// TODO trio
		}
	}

	public ArrayList<PieceDomino> getDeck() {
		return deck;
	}

	public void setDeck(ArrayList<PieceDomino> deck) {
		this.deck = deck;
	}

	public PieceDomino[][] getTable() {
		return table;
	}

	public void setTable(PieceDomino table[][]) {
		this.table = table;
	}

	public int finPartie(int jeu) { // retourne l'id du joueurs ganant 5 sinon
		if (jeu == 0) {
			int min = 28;
			int minId = 5;
			
			boolean fin = true;
			for (int i = 0; i < joueurs.size(); i++) {
				
				if (joueurs.get(i).mainVide()) {
					System.out.println("main de "+i+" vide");
					return i;
				} else {
					if (joueurs.get(i).getMain().size() < min) {
						min = joueurs.get(i).getMain().size();
						minId = i;
				
					}
					fin = fin && joueurs.get(i).nePeutPasJouer(jeu, this);
				}
			}
			if (fin == true) {
				return minId;
			} else {
				return 5;
			}
		} else {
			// TODO triomino
			return 0;
		}
	}
}
