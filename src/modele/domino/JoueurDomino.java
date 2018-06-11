package modele.domino;

import java.util.ArrayList;
import java.util.Collections;

import modele.Joueur;
import modele.Point;

public class JoueurDomino extends Joueur {

	private ArrayList<PieceDomino> main;

	public ArrayList<PieceDomino> getMain() {
		return main;
	}

	public void setMain(ArrayList<PieceDomino> main) {
		this.main = main;
	}

	public JoueurDomino(int jeu, String nom, boolean cpu) {
		super(nom, 0, cpu);

		this.main = new ArrayList<PieceDomino>();
	}

	public ArrayList<PieceDomino> getAllPiecesSwipes() {
		// Cette methode retourne un ArrayList qui contient toutes les rotations
		// possibles de toutes les pieces de la main du joueur , elle va etre
		// utilse pour voir si le joueur peut jouer
		ArrayList<PieceDomino> resultat = new ArrayList<PieceDomino>();
		for (PieceDomino p : main) {
			resultat.addAll(p.getAllSwipes());
		}
		return resultat;
	}

	public void initMain(ArrayList<PieceDomino> pieces, int tailleMain) {
		// Pour initaliser la main du joueur , on a un deck (pieces)
		// on fait un melange a chaque fois et on prend la premiere piece puis
		// on la supprime
		for (int i = 0; i < tailleMain; i++) {
			Collections.shuffle(pieces);
			this.main.add(pieces.get(0));
			pieces.remove(0);
		}
	}

	public boolean piocher(ArrayList<PieceDomino> pieces, int jeu) {
		// Si le deck est vide on ne peut pas piocher
		if (pieces.size() == 0) {
			return false;
		}

		// Meme principe que initMain , on melange et on prend la premiere puis
		// on la supprime
		Collections.shuffle(pieces);
		this.main.add(pieces.get(0));
		pieces.remove(0);
		return true;

	}

	public boolean mainVide() {
		return (this.main.size() == 0);
	}

	public void ajouterExtremites(int jeu, ModeleDomino table, int newX, int newY) {
		// Pour chaque coté de la piece joué qui a les coordonees newX et newY
		// on ajoute le point qui correspond au coté s'il est vide
		if (table.getTable()[newX + 1][newY] == null) {
			table.getExtremite().add(new Point(newX + 1, newY));
		}
		if (table.getTable()[newX - 1][newY] == null) {
			table.getExtremite().add(new Point(newX - 1, newY));
		}
		if (table.getTable()[newX][newY + 1] == null) {
			table.getExtremite().add(new Point(newX, newY + 1));
		}
		if (table.getTable()[newX][newY - 1] == null) {
			table.getExtremite().add(new Point(newX, newY - 1));
		}
	}

	public void supprimerExtremites(int jeu, ModeleDomino table, PieceDomino piece, int oldX, int oldY, int newX,
			int newY) {

		PieceDomino pieceTable = table.getTable()[oldX][oldY];
		table.getExtremite().remove(new Point(newX, newY));
		// le point joué est forcement supprimer car on ne peu plus joue dans ce
		// point
		if (pieceTable.isCentre()) {

		} else if (piece.getRot() == pieceTable.getRot()) {
			if (newY == oldY - 1 && table.getTable()[oldX][oldY + 1] != null) {
				table.getExtremite().remove(new Point(oldX + 1, oldY));
				table.getExtremite().remove(new Point(oldX - 1, oldY));
			} else if (newY == oldY + 1 && table.getTable()[oldX][oldY - 1] != null) {
				table.getExtremite().remove(new Point(oldX + 1, oldY));
				table.getExtremite().remove(new Point(oldX - 1, oldY));
			} else if (newX == oldX - 1 && table.getTable()[oldX + 1][oldY] != null) {
				table.getExtremite().remove(new Point(oldX, oldY + 1));
				table.getExtremite().remove(new Point(oldX, oldY - 1));
			} else if (newX == oldX + 1 && table.getTable()[oldX - 1][oldY] != null) {
				table.getExtremite().remove(new Point(oldX, oldY + 1));
				table.getExtremite().remove(new Point(oldX, oldY - 1));
			}
		} else if (pieceTable.getRot() != piece.getRot()) {

			if (newY == oldY + 1 && piece.getX() == pieceTable.getX()) {
				table.getExtremite().remove(new Point(oldX - 1, oldY));
				table.getExtremite().remove(new Point(oldX, oldY - 1));
			} else if (newY == oldY + 1 && piece.getX() == pieceTable.getY()) {
				table.getExtremite().remove(new Point(oldX + 1, oldY));
				table.getExtremite().remove(new Point(oldX, oldY - 1));
			} else if (newY == oldY - 1 && piece.getY() == pieceTable.getX()) {
				table.getExtremite().remove(new Point(oldX - 1, oldY));
				table.getExtremite().remove(new Point(oldX, oldY + 1));
			} else if (newY == oldY - 1 && piece.getY() == pieceTable.getY()) {
				table.getExtremite().remove(new Point(oldX + 1, oldY));
				table.getExtremite().remove(new Point(oldX, oldY + 1));
			}

			if (newX == oldX + 1 && piece.getX() == pieceTable.getX()) {
				table.getExtremite().remove(new Point(oldX - 1, oldY));
				table.getExtremite().remove(new Point(oldX, oldY - 1));
			} else if (newX == oldX + 1 && piece.getX() == pieceTable.getY()) {
				table.getExtremite().remove(new Point(oldX - 1, oldY));
				table.getExtremite().remove(new Point(oldX, oldY + 1));
			} else if (newX == oldX - 1 && piece.getY() == pieceTable.getX()) {
				table.getExtremite().remove(new Point(oldX + 1, oldY));
				table.getExtremite().remove(new Point(oldX, oldY - 1));
			} else if (newX == oldX - 1 && piece.getY() == pieceTable.getY()) {
				table.getExtremite().remove(new Point(oldX + 1, oldY));
				table.getExtremite().remove(new Point(oldX, oldY + 1));
			}
		}
	}

	public Point coup(int jeu, PieceDomino piece, int indexDePieceDansLaMain, ModeleDomino table, int posX, int posY,
			int rotation, boolean centre, Point oldP) {
		int x = 1, y = 1;
		if (jeu == 0) {
			this.getMain().get(indexDePieceDansLaMain).setCentre(centre);
			this.getMain().get(indexDePieceDansLaMain).setRot(rotation);
			if (!this.isCpu()) {
				Point pp = this.getPointJoueurHumain(table, piece, posX, posY);
				if (pp != null) {
					x = pp.getX();
					y = pp.getY();
				}
				if (table.coupValide(jeu, piece, new Point(x, y), new Point(posX, posY))) {
					System.out.println("Joueur:coup valide en" + posX + posY + "avec la piece" + piece);
					Point ptx = this.getPointJoueurHumain(table, piece, posX, posY);
					table.getTable()[ptx.getX()][ptx.getY()] = piece;

					this.getMain().remove(indexDePieceDansLaMain);

					this.supprimerExtremites(jeu, table, piece, posX, posY, x, y);
					this.ajouterExtremites(jeu, table, x, y);
					System.out.println(table.getExtremite());
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

								this.supprimerExtremites(jeu, table, pi, oldP.getX(), oldP.getY(), p.getX(), p.getY());
								this.ajouterExtremites(jeu, table, x, y);

								System.out.println(table.getExtremite());
								return new Point(x, y);
							}
						}
					}
				}
				return null;
			}
		} else {
			return null;
		}
	}

	public Point getPointJoueurHumain(ModeleDomino table, PieceDomino piece, int posX, int posY) {

		if (table.coupValide(0, piece, new Point(posX - 1, posY), new Point(posX, posY)))
			return new Point(posX - 1, posY);
		else if (table.coupValide(0, piece, new Point(posX + 1, posY), new Point(posX, posY)))
			return new Point(posX + 1, posY);
		else if (table.coupValide(0, piece, new Point(posX, posY + 1), new Point(posX, posY)))
			return new Point(posX, posY + 1);
		else if (table.coupValide(0, piece, new Point(posX, posY - 1), new Point(posX, posY)))
			return new Point(posX, posY - 1);
		return null;

	}

	public boolean nePeutPasJouer(int jeu, ModeleDomino table) {
		if (jeu == 0) {
			for (Point p : table.getExtremite()) {
				for (PieceDomino piece : this.getAllPiecesSwipes()) {
					if (table.coupValide(jeu, piece, p, new Point(0, 0))) {
						return false;
					}

				}
			}

			return true;
		} else {

			return false;
		}
	}

	public int indexDuPlusGrandDouble() {
		// retourne -1 si il n'ya pas de double
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
