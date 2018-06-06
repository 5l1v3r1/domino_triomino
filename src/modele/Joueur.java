package modele;

import java.util.ArrayList;
import java.util.Collections;

public class Joueur {
	private String nom;
	private int score;
	private boolean cpu;
	private ArrayList<PieceDomino> main;

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

	public ArrayList<PieceDomino> getMain() {
		return main;
	}

	public void setMain(ArrayList<PieceDomino> main) {
		this.main = main;
	}

	public Joueur(int jeu, String nom, boolean cpu) {
		super();
		this.nom = nom;
		this.cpu = cpu;
		this.score = 0;
		if (jeu == 0)
			this.main = new ArrayList<PieceDomino>();
	}

	public ArrayList<PieceDomino> getAllPiecesSwipes() {
		ArrayList<PieceDomino> resultat = new ArrayList<PieceDomino>();
		for (PieceDomino p : main) {
			resultat.addAll(p.getAllSwipes());
		}
		return resultat;
	}

	public void initMain(ArrayList<PieceDomino> pieces, int tailleMain) { // initialiser
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

	public boolean piocher(ArrayList<PieceDomino> pieces, int jeu) { // jeu=0
																		// domino
																		// ,
		// jeu=1
		// triomino
		if (pieces.size() == 0) {
			return false;
		}
		if (jeu == 0) {
			Collections.shuffle(pieces);
			this.main.add(pieces.get(0));
			pieces.remove(0);
			return true;
		} else if (jeu == 1) {
			// TODO triomino ( score )
			return false;
		}
		return false;
	}

	public boolean mainVide() {
		return (this.main.size() == 0);
	}

	public Point coup(int jeu, PieceDomino piece, int indexDePieceDansLaMain, Table table, int posX, int posY,
			int rotation, boolean centre, Point oldP) {
		int x=1, y=1;
		if (jeu == 0) {
			this.getMain().get(indexDePieceDansLaMain).setCentre(centre);
			this.getMain().get(indexDePieceDansLaMain).setRot(rotation);
			if (!this.isCpu()) {
				Point pp=this.getPointJoueurHumain(table, piece, posX, posY);
				if(pp!=null){
				x = pp.getX();
				y = pp.getY();

				System.out.println("x=" + x + "y= " + y);
				}
				if (table.coupValide(jeu, piece, new Point(x, y), new Point(posX, posY))) {
					System.out.println("Joueur:coup valide en" + posX + posY + "avec la piece" + piece);
					Point ptx = this.getPointJoueurHumain(table, piece, posX, posY);
					table.getTable()[ptx.getX()][ptx.getY()] = piece;

					this.getMain().remove(indexDePieceDansLaMain);
					for (int i = 0; i < table.getExtremite().size(); i++) {
						if (table.getExtremite().get(i).getX() == x && table.getExtremite().get(i).getY() == y) {
							table.getExtremite().remove(i);
						}
						if (!table.getTable()[posX][posY].isCentre()) {
							table.getExtremite().remove(new Point(posX, posY));
							table.getExtremite().remove(new Point(posX - 1, posY));
							table.getExtremite().remove(new Point(posX + 1, posY));
							table.getExtremite().remove(new Point(posX, posY + 1));
							table.getExtremite().remove(new Point(posX, posY - 1));
						}
					}
					if (centre) {
						if (table.getTable()[x - 1][y] == null)
							table.getExtremite().add(new Point(x - 1, y));
						if (table.getTable()[x + 1][y] == null)
							table.getExtremite().add(new Point(x + 1, y));
						if (table.getTable()[x][y + 1] == null)
							table.getExtremite().add(new Point(x, y + 1));
						if (table.getTable()[x][y - 1] == null)
							table.getExtremite().add(new Point(x, y - 1));
					} else {
						if (rotation != 0) {
							if (table.getTable()[x - 1][y] == null)
								table.getExtremite().add(new Point(x - 1, y));
							if (table.getTable()[x + 1][y] == null)
								table.getExtremite().add(new Point(x + 1, y));
						} else {
							if (table.getTable()[x][y + 1] == null)
								table.getExtremite().add(new Point(x, y + 1));
							if (table.getTable()[x][y - 1] == null)
								table.getExtremite().add(new Point(x, y - 1));
						}

					}

					return new Point(x, y);
				} else {
					System.out.println("Classe joueur dit : coup invalide en " + x + " " + y);
					return null;
				}

			} else {

				for (Point p : table.getExtremite()) {
					for (int i = 0; i < this.getMain().size(); i++) {
						for (PieceDomino pi : this.getMain().get(i).getAllSwipes()) {
							if (table.coupValide(0, pi, p, oldP)) {
								this.getMain().remove(i);
								table.getTable()[p.getX()][p.getY()] = pi;
								x = p.getX();
								y = p.getY();
								posX = x;
								posY = y;
								table.getTable()[p.getX()][p.getY()].setRot(0);

								if (pi.getRot() != 0) {
									if (table.getTable()[posX - 1][posY] == null)
										table.getExtremite().add(new Point(posX - 1, posY));
									if (table.getTable()[posX + 1][posY] == null)
										table.getExtremite().add(new Point(posX + 1, posY));
								} else {
									if (table.getTable()[posX][posY + 1] == null)
										table.getExtremite().add(new Point(posX, posY + 1));
									if (table.getTable()[posX][posY - 1] == null)
										table.getExtremite().add(new Point(posX, posY - 1));
								}

								table.getExtremite().remove(p);

								/*
								 * 
								 * 
								 * if (pi.getRot() == 0) { if
								 * (table.getTable()[posX - 1][posY] == null)
								 * table.getExtremite().add(new Point(posX - 1,
								 * posY)); if (table.getTable()[posX + 1][posY]
								 * == null) table.getExtremite().add(new
								 * Point(posX + 1, posY)); } else { if
								 * (table.getTable()[posX][posY + 1] == null)
								 * table.getExtremite().add(new Point(posX, posY
								 * + 1)); if (table.getTable()[posX][posY - 1]
								 * == null) table.getExtremite().add(new
								 * Point(posX, posY - 1)); }
								 * 
								 */
								System.out.println(table.getExtremite());
								return new Point(x, y);
							}
						}
					}
				}
				return null;
			}
		} else {
			// TODO triomino
			return null;
		}
	}

	public Point getPointJoueurHumain(Table table, PieceDomino piece, int posX, int posY) {
		//
		// int x = posX;
		// int y = posY;
		// System.out.println("getPointJoueurHumain");
		// System.out.println("posX=" + posX);
		// System.out.println("posY=" + posY);
		// PieceDomino pieceTable = table.getTable()[posX][posY];
		// System.out.println("piece " + piece.getRot() + "piece table" +
		// pieceTable.getRot());
		// System.out.println("piece x=" + piece.getX() + " y =
		// "+piece.getY()+piece);
		// System.out.println("piece table x=" + pieceTable.getX()+" y= " +
		// pieceTable.getY()+pieceTable);
		//
		// if ((piece.getRot() == 1 && pieceTable.getRot() == 1 && piece.getX()
		// == pieceTable.getY())
		// || (piece.getRot() == 1 && pieceTable.getRot() == 0 && piece.getX()
		// == pieceTable.getX())
		// || (piece.getRot() == 1 && pieceTable.getRot() == 0 && piece.getX()
		// == pieceTable.getY())
		// || (piece.getRot() == 0 && pieceTable.getY() == 1 && piece.getX() ==
		// piece.getY()
		// && piece.getX() == pieceTable.getY())) {
		// return new Point(x + 1, y);
		// } else if ((piece.getRot() == 1 && pieceTable.getRot() == 1 &&
		// piece.getY() == pieceTable.getX())
		// || (piece.getRot() == 1 && pieceTable.getRot() == 0 && piece.getY()
		// == pieceTable.getY())
		// || (piece.getRot() == 1 && pieceTable.getRot() == 0 && piece.getY()
		// == pieceTable.getX())
		// || (piece.getRot() == 0 && pieceTable.getY() == 1 && piece.getX() ==
		// piece.getY()
		// && piece.getX() == pieceTable.getY())) {
		// return new Point(x - 1, y);
		// } else if ((piece.getRot() == 0 && pieceTable.getRot() == 0 &&
		// piece.getX() == pieceTable.getY())
		// || (piece.getRot() == 0 && pieceTable.getRot() == 1 &&
		// pieceTable.getX() == pieceTable.getY()
		// && piece.getX() == pieceTable.getY())
		// || (piece.getRot() == 1 && pieceTable.getRot() == 0 && piece.getX()
		// == piece.getY()
		// && piece.getX() == pieceTable.getY())) {
		// return new Point(x, y + 1);
		// } else if ((piece.getRot() == 0 && pieceTable.getRot() == 0 &&
		// piece.getY() == pieceTable.getX())
		// || (piece.getRot() == 0 && pieceTable.getRot() == 1 &&
		// pieceTable.getX() == pieceTable.getY()
		// && piece.getY() == pieceTable.getX())
		// || (piece.getRot() == 1 && pieceTable.getRot() == 0 && piece.getX()
		// == piece.getY()
		// && piece.getX() == pieceTable.getX())) {
		// return new Point(x, y - 1);
		// } else {
		// return new Point(0, 0);
		// }

		if (table.coupValide(0, piece, new Point(posX - 1, posY), new Point(posX, posY)))
			return new Point(posX - 1, posY);
		else if (table.coupValide(0, piece, new Point(posX + 1, posY), new Point(posX, posY)))
			return new Point(posX + 1, posY);
		else if (table.coupValide(0, piece, new Point(posX, posY + 1), new Point(posX, posY)))
			return new Point(posX, posY + 1);
		else if (table.coupValide(0, piece, new Point(posX, posY - 1), new Point(posX, posY)))
			return new Point(posX, posY - 1);
		return null;

		// System.out.println("x manuel");
		// int x=new Scanner(System.in).nextInt();
		// System.out.println("y manuel");
		// int y=new Scanner(System.in).nextInt();
		// return new Point(x,y);
	}

	public boolean nePeutPasJouer(int jeu, Table table) {
		if (jeu == 0) {
			for (Point p : table.getExtremite()) {
				for (PieceDomino piece : this.getAllPiecesSwipes()) {
					if (table.coupValide(jeu, piece, p, new Point(0, 0))) {
						return false;
					}
					// if (table.getTable()[p.getX()][p.getY() - 1] != null
					// && piece.getX() == table.getTable()[p.getX()][p.getY() -
					// 1].getY()) {
					// return false;
					// } else if (table.getTable()[p.getX()][p.getY() + 1] !=
					// null
					// && piece.getY() == table.getTable()[p.getX()][p.getY() +
					// 1].getX()) {
					// return false;
					// } else if (table.getTable()[p.getX() + 1][p.getY()] !=
					// null
					// && piece.getX() == table.getTable()[p.getX() +
					// 1][p.getY()].getY()) {
					// return false;
					// } else if (table.getTable()[p.getX() - 1][p.getY()] !=
					// null
					// && piece.getY() == table.getTable()[p.getX() -
					// 1][p.getY()].getX()) {
					// return false;
					// }
				}
			}
			System.err.println("*** "+this.getNom()+" ne peu pas jouer");
			return true;
		} else {
			// TODO trio
			return false;
		}
	}

	public int indexDuPlusGrandDouble() { // retourne -1 si il n'ya pas de
											// double
		int val = 0;
		int res = 0;
		for (int i = 0; i < this.getMain().size(); i++) {
			if (this.getMain().get(i).getX() == this.getMain().get(i).getX()) {
				if (this.getMain().get(i).valeur() > val) {
					val = this.getMain().get(i).valeur();
					res = i;
				}
			}
		}
		return res;

	}

	public int valeurDuPlusGrandDouble() {
		int res = 0;
		for (PieceDomino piece : main) {
			if (piece.getX() == piece.getY()) {
				if (piece.valeur() > res)
					res = piece.valeur();
			}
		}
		return res;

	}
}
