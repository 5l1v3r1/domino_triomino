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

	public Table(int jeu,ArrayList<Joueur>joueurs) { // dom=0,tri=1
		if (jeu == 0) {
			this.setTable(new PieceDomino[55][55]);
			this.deck = new ArrayList<PieceDomino>();
			this.joueurs =joueurs;
			this.extremite = new ArrayList<Point>();
			this.initDeck(jeu);
		} else if (jeu == 1) {
			// TODO triomino}
		}
	}

	public boolean coupValide(int jeu, PieceDomino piece, Point p) { //pb ici 
		
		if (jeu == 0) {
			table=this.getTable();
			int x=p.getX();
			int y=p.getY();
			if(table[x][y-1] != null){ 
				if(table[x][y-1].getX()==table[x][y-1].getY() && table[x][y-1].isCentre() && table[x][y-1].getRot()==1){
					if(table[x][y-1].getX()==piece.getX()){
						return true;
					}
				}
				else if(table[x][y-1].getRot()==1){
					if(piece.getRot()==0 && (piece.getY()==table[x][y-1].getX() || piece.getY()==table[x][y-1].getY()))
						return true;
				}
				else if(table[x][y-1].getRot()==0){
					if(piece.getRot()==0 && piece.getX()== table[x][y-1].getY()){
						return true;
					}
					else if(piece.getRot()==1 && piece.isCentre() && piece.getX()==piece.getY() && piece.getX()==table[x][y-1].getY()){
						return true;
					}
				}
				
			}
			else if(table[x][y+1] != null){
				if(table[x][y+1].isCentre() && table[x][y+1].getX()==table[x][y+1].getY() && table[x][y+1].getRot()==1){
					if(table[x][y+1].getX()==piece.getY()){
						return true;
					}
				}
				else if(table[x][y+1].getRot()==1){
					if(piece.getRot()==0 && (piece.getX()==table[x][y-1].getX() || piece.getX()==table[x][y-1].getY()))
						return true;
				}
				else if(table[x][y+1].getRot()==0){
					if(piece.getRot()==0 && piece.getY()==table[x][y+1].getX())
						return true;
					else if(piece.getRot()==1 && piece.isCentre() && piece.getX()==piece.getY() && piece.getX()==table[x][y+1].getX())
						return true;
				}
			}
			else if(table[x-1][y] != null){
				
				if(table[x-1][y].isCentre() && table[x-1][y].getX()==table[x-1][y].getY() &&  table[x-1][y].getRot()==0){
					if(table[x-1][y].getX()==piece.getY())
						return true;
					}
				else if( table[x-1][y].getRot()==1){
					if(piece.getRot()==1 && piece.getY()== table[x-1][y].getX())
						return true;
					else if(piece.getRot()==0 && piece.isCentre() && piece.getX()==piece.getY() && piece.getX()==table[x-1][y].getY())
						return true;
				}
				else if(table[x-1][y].getRot()==0){
					if(piece.getRot()==1 && (piece.getY()==table[x-1][y].getX() || piece.getY()==table[x-1][y].getY()))
						return true;
				}
				
			}
			else if(table[x+1][y] != null){
				if(table[x+1][y].isCentre() && table[x+1][y].getX()==table[x+1][y].getY() &&  table[x+1][y].getRot()==0){
					if(table[x+1][y].getX()==piece.getX())
						return true;
					}
				else if(table[x+1][y].getRot()==1){
					if(piece.getRot()==1 && piece.getX()==table[x+1][y].getY())
						return true;
					else if(piece.getRot()==0 && piece.isCentre() && piece.getX()==piece.getY() && piece.getX()==table[x+1][y].getY())
						return true;
				}
				else if(table[x+1][y].getRot()==0){
					if(piece.getRot()==1 && (piece.getX()==table[x+1][y].getX() || table[x+1][y].getX()==piece.getY()))
						return true;
				}
				
			}
			
			
			
//			if (this.getTable()[p.getX()][p.getY() - 1] != null
//					&& (piece.getX() == this.getTable()[p.getX()][p.getY() - 1].getY())  //valeurs correspondent
//					&& ((piece.getRot()==this.getTable()[p.getX()][p.getY() - 1].getRot() && piece.getRot()==0)
//											|| ((piece.getRot()!=this.getTable()[p.getX()][p.getY() - 1].getRot()) 
//												&& piece.isCentre() ^ this.getTable()[p.getX()][p.getY() - 1].isCentre())) //ou pas meme rotation mais une des pieces est centr
//					) {
//				return true;
//			} else if (this.getTable()[p.getX()][p.getY() + 1] != null
//					&& (piece.getY() == this.getTable()[p.getX()][p.getY() + 1].getX())  //valeurs correspondent
//					&& ((piece.getRot()==this.getTable()[p.getX()][p.getY() + 1].getRot()  && piece.getRot()==0)
//											|| ((piece.getRot()!=this.getTable()[p.getX()][p.getY() + 1].getRot()) 
//												&& piece.isCentre() ^ this.getTable()[p.getX()][p.getY() + 1].isCentre())) //ou pas meme rotation mais une des pieces est centre
//					) {
//				return true;
//			}else if (this.getTable()[p.getX()+1][p.getY() ] != null
//					&& (piece.getX() == this.getTable()[p.getX()+1][p.getY() ].getY())  //valeurs correspondent
//					&& ((piece.getRot()==this.getTable()[p.getX()+1][p.getY()].getRot()  && piece.getRot()==1)
//											|| ((piece.getRot()!=this.getTable()[p.getX()+1][p.getY() ].getRot()) 
//												&& piece.isCentre() ^ this.getTable()[p.getX()+1][p.getY() ].isCentre())) 
//					) {
//				return true;
//			} else if (this.getTable()[p.getX()-1][p.getY() ] != null
//					&& (piece.getX() == this.getTable()[p.getX()-1][p.getY() ].getY())  //valeurs correspondent
//					&& ((piece.getRot()==this.getTable()[p.getX()-1][p.getY()].getRot()  && piece.getRot()==1)
//											|| ((piece.getRot()!=this.getTable()[p.getX()-1][p.getY() ].getRot()) 
//												&& piece.isCentre() ^ this.getTable()[p.getX()-1][p.getY() ].isCentre())) 
//					) {
//				return true;
//			}
		} else {
			
			return false;
			// TODO trio
		}
return false;
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
					return i;
				} else {
					if (joueurs.get(i).getMain().size() < min) {
						min = joueurs.get(i).getMain().size();
						minId = i;
						fin = fin && joueurs.get(i).nePeutPasJouer(jeu, this);
					}
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
