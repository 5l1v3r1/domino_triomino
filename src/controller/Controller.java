package controller;

import java.util.Scanner;

import modele.Joueur;
import modele.PieceDomino;
import modele.Point;
import modele.Table;
import vue.ChoixJeu;
import vue.ChoixJoueurs;
import vue.ChoixRot;
import vue.TableDomino;

public class Controller {

	public static void main(String[] args) {
		Point oldPoint;
		int nbJoueurs = 0;
		int jeu;
		int joueurCourant;
		ChoixJeu choixJeu = new ChoixJeu(); // choixJeu (tri/dom)
		System.err.println("choisir le jeu");
		do {
			jeu = choixJeu.getChoix();
			System.err.print(""); // choix jeu
		} while (jeu == -1);
		choixJeu.exit();
		choixJeu = null; // pour le garbage collector
		ChoixJoueurs choixJoueurs = new ChoixJoueurs(jeu); // config joueurs (
		System.err.print("config");													// noms , cpu ... )
		while (choixJoueurs.getJoueurs().size() == 0) {
			System.err.print(""); // setup
		}

		Table modele = new Table(jeu, choixJoueurs.getJoueurs()); // init mains
																	// et tout
		nbJoueurs = modele.getJoueurs().size();
		for (Joueur j : modele.getJoueurs()) {
			j.initMain(modele.getDeck(), 7);
		}
		choixJoueurs = null; // pour le garbage collector
		TableDomino tableDeJeu = new TableDomino(); // init table de jeu (
													// pieces et noms )
		int i;
		for (i = 0; i < nbJoueurs; i++) {
			tableDeJeu.setNomJoueur(i, modele.getJoueurs().get(i).getNom()); // init
																				// noms
			tableDeJeu.dessinerPiecesJoueur(i, modele.getJoueurs().get(i).getMain()); // init
																						// main
		}
		for (; i < 4; i++) {
			tableDeJeu.setNomJoueur(i, ""); // init labels des noms vides
		}
		joueurCourant = modele.joueurQuiCommance(); // definir le joueur ki
													// commance
		System.err.println(joueurCourant);
		Joueur jCourant = modele.getJoueurs().get(joueurCourant);
		System.out.println("debug>>le joueur qui commance est " + joueurCourant);
		tableDeJeu.setToken(joueurCourant);
		int premierePieceAjouer = modele.getJoueurs().get(joueurCourant).indexDuPlusGrandDouble();
		tableDeJeu.dessinerPiece(jCourant.getMain().get(premierePieceAjouer), 500, 500, 28, 28);
		modele.getTable()[28][28] = jCourant.getMain().get(premierePieceAjouer);
		tableDeJeu.getTable()[28][28] = new Point(500, 500);
		jCourant.getMain().remove(premierePieceAjouer);
		tableDeJeu.dessinerPiecesJoueur(joueurCourant, jCourant.getMain());
		modele.getExtremite().add(new Point(28, 29));
		modele.getExtremite().add(new Point(28, 27));
		joueurCourant = (joueurCourant + 1) % nbJoueurs;
		jCourant = modele.getJoueurs().get(joueurCourant);
		tableDeJeu.setToken(joueurCourant);
		while (modele.finPartie(jeu) == 5) {

			System.out.println("partie non terminée c'est le tour de" + joueurCourant);
			// jeu
			Point point;
			if (jCourant.nePeutPasJouer(jeu, modele)) { // si le joueur ne peut
														// pas jouer il passe
														// son tour
														// automatiquement
				System.err.println("joueur passe son tour");
			} else {
				if (jCourant.isCpu()) { // joueur ordinateur
					System.out.println("coup cpu");
					Point oldP = new Point(0, 0);
					point = jCourant.coup(jeu, null, 0, modele, 0, 0, 0, false, oldP);
					System.out.println("coordonnes");
					int x = oldP.getX();
					int y = oldP.getY();
					PieceDomino oldPiece = modele.getTable()[x][y];
					tableDeJeu.getTable()[point.getX()][point.getY()] = Controller.getOffset(tableDeJeu.getTable(),
							modele.getTable()[point.getX()][point.getY()], oldPiece, x, y, point.getX(), point.getY());
					// tableDeJeu.getTable()[point.getX()][point.getY()] =
					// Controller
					// .getOffset(modele.getTable()[point.getX()][point.getY()]);
					Point coord = Controller.getCoord(tableDeJeu.getTable(), point.getX(), point.getY());
					System.out.println("le point piece pose est" + point.getX() + "," + point.getY());
					tableDeJeu.dessinerPiece(modele.getTable()[point.getX()][point.getY()], coord.getX(), coord.getY(),
							point.getX(), point.getY());
					System.out.println("piece" + modele.getTable()[point.getX()][point.getY()]
							+ " desiné dans les coordonées" + coord.getX() + coord.getY());

				} else { // joueur humain
					ChoixRot choixRot;
					Point pointHumain;
					do {

						System.out.println("c'est le tour d'un humain");
						//
						System.err.println("choisir la piece dans la main"); 
						while (tableDeJeu.getChoixJoueur(joueurCourant) == -1
								|| tableDeJeu.getChoixJoueur(joueurCourant) >= jCourant.getMain().size()) {
							System.err.print(""); // choix piece main

						}
						System.out.println(tableDeJeu.getChoixJoueur(joueurCourant));
						choixRot = new ChoixRot(jCourant.getMain().get(tableDeJeu.getChoixJoueur(joueurCourant)));
						System.err.println("choisir la rotation"); 
						while (choixRot.isClicked() == false) {
							System.out.print(""); // choix rot

						}
						System.err.println("choisir ou jouer la piece"); 
						while (tableDeJeu.getPieceChoisie().getX() == -1) {
							System.out.print(""); // choix table

						}
					
						pointHumain = jCourant.coup(jeu,
								jCourant.getMain().get(tableDeJeu.getChoixJoueur(joueurCourant)),
								tableDeJeu.getChoixJoueur(joueurCourant), modele, tableDeJeu.getPieceChoisie().getX(),
								tableDeJeu.getPieceChoisie().getY(), choixRot.getRot(), choixRot.isCentre(), null);
						oldPoint = tableDeJeu.getPieceChoisie();

						//
						choixRot.setClicked(false);
						tableDeJeu.resetChoix();
						tableDeJeu.resetPieceChoisie();
						System.out.println("le point que l'humain va jouer est"+pointHumain);
					} while (pointHumain == null);
					
					tableDeJeu.getTable()[pointHumain.getX()][pointHumain.getY()] = Controller.getOffset(
							tableDeJeu.getTable(), modele.getTable()[pointHumain.getX()][pointHumain.getY()],
							modele.getTable()[oldPoint.getX()][oldPoint.getY()], oldPoint.getX(), oldPoint.getY(),
							pointHumain.getX(), pointHumain.getY());
					// tableDeJeu.getTable()[pointHumain.getX()][pointHumain.getY()]
					// = Controller
					// .getOffset(modele.getTable()[pointHumain.getX()][pointHumain.getY()]);

					Point coord = Controller.getCoord(tableDeJeu.getTable(), pointHumain.getX(), pointHumain.getY());
					tableDeJeu.dessinerPiece(modele.getTable()[pointHumain.getX()][pointHumain.getY()], coord.getX(),
							coord.getY(), pointHumain.getX(), pointHumain.getY());
					System.out.println("piece" + modele.getTable()[pointHumain.getX()][pointHumain.getY()]
							+ " desiné dans les coordonées" + coord.getX() + coord.getY());

				}
			}
			System.out.println("dessin main de j de nv");
			tableDeJeu.dessinerPiecesJoueur(joueurCourant, jCourant.getMain());
			System.out.println("on passe au joueur courant");
			joueurCourant = (joueurCourant + 1) % nbJoueurs;
			jCourant = modele.getJoueurs().get(joueurCourant);
			tableDeJeu.setToken(joueurCourant);
			System.out.println("si 5 alors fin " + modele.finPartie(jeu));

		}

	}

	private static Point getCoord(Point[][] table, int x, int y) {
		return table[x][y];
		// Point point = new Point(0, 0);
		// if (x > 28) {
		// for (int i = 28; i < x; i++) {
		// point.setY(point.getY() + table[i][28].getY());
		// }
		// } else {
		// for (int i = 27; i > x; i--) {
		// point.setY(point.getY() - table[i][28].getY());
		// }
		// }
		//
		// if (y > 28) {
		// for (int i = 28; i < y; i++) {
		// System.out.println("i="+i);
		// System.out.println("x="+x);
		// point.setX(point.getX() + table[x][i].getX());
		// }
		// } else {
		// for (int i = 28; i > y; i--) {
		// System.out.println("i="+i);
		// System.out.println("x="+x);
		// point.setX(point.getX() - table[x][i].getX());
		// }
		// }
		// point.setX(point.getX() + 500);
		// point.setY(point.getY() + 500);
		// return point;

	}

	private static Point getOffset(Point[][] table, PieceDomino piece, PieceDomino oldPiece, int x, int y, int newX,
			int newY) {
		Point oldPoint = table[x][y];
		System.out.println("oldpoint offser" + oldPoint);

		Point newPoint = new Point(oldPoint.getX(), oldPoint.getY());
		if (piece.getRot() == 0 && oldPiece.getRot() == 0) {
			if (piece.getX() == oldPiece.getY()) {
				newPoint.setX(oldPoint.getX() + 80);
				newPoint.setY(oldPoint.getY());
			} else if (piece.getY() == oldPiece.getX()) {
				newPoint.setX(oldPoint.getX() - 80);
				newPoint.setY(oldPoint.getY());
			}
		} else if (piece.getRot() == 1 && oldPiece.getRot() == 1) {
			if (piece.getX() == oldPiece.getY()) {
				newPoint.setX(oldPoint.getX());
				newPoint.setY(oldPoint.getY() + 80);
			} else if (piece.getY() == oldPiece.getX()) {
				newPoint.setX(oldPoint.getX());
				newPoint.setY(oldPoint.getY() - 80);
			}
		} else if (piece.getRot() == 1 && oldPiece.getRot() == 0) {
			if (oldPiece.isCentre() && oldPiece.getX() == oldPiece.getY() && !piece.isCentre()) {
				if (piece.getX() == oldPiece.getX() && newX == x + 1) {
					newPoint.setX(oldPoint.getX() + 20);
					newPoint.setY(oldPoint.getY() + 40);
				} else if (piece.getY() == oldPiece.getX() && newX == x - 1) {
					newPoint.setX(oldPoint.getX() + 20);
					newPoint.setY(oldPoint.getY() - 80);
				}
			} else if (piece.isCentre() && piece.getX() == piece.getY() && !oldPiece.isCentre()) {
				if (piece.getX() == oldPiece.getX() && newY == y - 1) {
					newPoint.setX(oldPoint.getX() - 40);
					newPoint.setY(oldPoint.getY() - 40);
				} else if (piece.getX() == oldPiece.getY() && newY == y + 1) {
					newPoint.setX(oldPoint.getX() - 40);
					newPoint.setY(oldPoint.getY() + 80);
				}

			} else {
				if (piece.getX() == oldPiece.getY()) {
					newPoint.setX(oldPoint.getX() + 40);
					newPoint.setY(oldPoint.getY() + 40);
				} else if (piece.getX() == oldPiece.getX()) {
					newPoint.setX(oldPoint.getX());
					newPoint.setY(oldPoint.getY() + 40);
				} else if (piece.getY() == oldPiece.getX()) {
					newPoint.setX(oldPoint.getX());
					newPoint.setY(oldPoint.getY() - 80);
				} else if (piece.getY() == oldPiece.getY()) {
					newPoint.setX(oldPoint.getX() + 40);
					newPoint.setY(oldPoint.getY() - 80);
				}
			}
		}

		//

		else if (piece.getRot() == 0 && oldPiece.getRot() == 1) {
			if (oldPiece.isCentre() && oldPiece.getX() == oldPiece.getY() && !piece.isCentre()) {
				if (piece.getX() == oldPiece.getX() && newY == y + 1) {
					newPoint.setX(oldPoint.getX() + 40);
					newPoint.setY(oldPoint.getY() + 20);
				} else if (piece.getY() == oldPiece.getX() && newY == y - 1) {
					newPoint.setX(oldPoint.getX() - 80);
					newPoint.setY(oldPoint.getY() + 20);
				}
			} else if (piece.isCentre() && piece.getX() == piece.getY() && !oldPiece.isCentre()) {
				if (piece.getX() == oldPiece.getX() && newX == x - 1) {
					newPoint.setX(oldPoint.getX() - 20);
					newPoint.setY(oldPoint.getY() - 40);
				} else if (piece.getX() == oldPiece.getY() && newX == x + 1) {
					newPoint.setX(oldPoint.getX() - 20);
					newPoint.setY(oldPoint.getY() + 80);
				} //

			} else {
				if (piece.getX() == oldPiece.getY()) {
					newPoint.setX(oldPoint.getX() + 40);
					newPoint.setY(oldPoint.getY() + 40);
				} else if (piece.getX() == oldPiece.getX()) {
					newPoint.setX(oldPoint.getX() + 40);
					newPoint.setY(oldPoint.getY());
				} else if (piece.getY() == oldPiece.getX()) {
					newPoint.setX(oldPoint.getX() - 80);
					newPoint.setY(oldPoint.getY());
				} else if (piece.getY() == oldPiece.getY()) {
					newPoint.setX(oldPoint.getX() - 80);
					newPoint.setY(oldPoint.getY() + 40);
				}
			}
		}

		return newPoint;

	}
	// if (piece.isCentre()) {
	// if (piece.getRot() == 0) {
	// return new Point(20, 40);
	// } else {
	// return new Point(40, 20);
	// }
	// } else {
	// if (piece.getRot() == 0) {
	// return new Point(80, 40);
	// } else {
	// return new Point(40, 80);
	// }
	// }
	// }
}
