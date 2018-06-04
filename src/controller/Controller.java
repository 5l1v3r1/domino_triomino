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

		int nbJoueurs = 0;
		int jeu;
		int joueurCourant;
		ChoixJeu choixJeu = new ChoixJeu(); // choixJeu (tri/dom)
		do {
			jeu = choixJeu.getChoix();
			System.err.print("a"); // choix jeu
		} while (jeu == -1);
		choixJeu.exit();
		choixJeu = null; // pour le garbage collector
		ChoixJoueurs choixJoueurs = new ChoixJoueurs(jeu); // config joueurs (
															// noms , cpu ... )
		while (choixJoueurs.getJoueurs().size() == 0) {
			System.out.print("b"); // setup
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
		tableDeJeu.getTable()[28][28] = new Point(80, 40);
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
					System.err.println("debug>>joueur cpu");
					System.out.println(" coup cpu");
					point = jCourant.coup(jeu, null, 0, modele, 0, 0, 0, false);

					tableDeJeu.getTable()[point.getX()][point.getY()] = Controller
							.getOffset(modele.getTable()[point.getX()][point.getY()]);
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
//new Scanner(System.in).nextLine();
						while (tableDeJeu.getChoixJoueur(joueurCourant) == -1
								|| tableDeJeu.getChoixJoueur(joueurCourant) >= jCourant.getMain().size()) {
							System.err.print("x"); // choix piece main

						}
						System.out.println(tableDeJeu.getChoixJoueur(joueurCourant));
						choixRot = new ChoixRot(jCourant.getMain().get(tableDeJeu.getChoixJoueur(joueurCourant)));
						
						while (choixRot.isClicked() == false) {
							System.out.print("y"); // choix rot

						}
						while (tableDeJeu.getPieceChoisie().getX() == -1) {
							System.out.print("z"); // choix table

						}

						pointHumain = jCourant.coup(jeu,
								jCourant.getMain().get(tableDeJeu.getChoixJoueur(joueurCourant)),
								tableDeJeu.getChoixJoueur(joueurCourant), modele, tableDeJeu.getPieceChoisie().getX(),
								tableDeJeu.getPieceChoisie().getY(), choixRot.getRot(), choixRot.isCentre());


//new Scanner(System.in).nextLine();
						choixRot.setClicked(false);
						tableDeJeu.resetChoix();
						tableDeJeu.resetPieceChoisie();
						System.out.println(pointHumain);
					} while (pointHumain == null);
					System.out.println(modele.getTable()[pointHumain.getX()][pointHumain.getY()]);
					tableDeJeu.getTable()[pointHumain.getX()][pointHumain.getY()] = Controller
							.getOffset(modele.getTable()[pointHumain.getX()][pointHumain.getY()]);

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
		Point point = new Point(0, 0);
		if (x > 28) {
			for (int i = 28; i < x; i++) {
				point.setY(point.getY() + table[i][28].getY());
			}
		} else {
			for (int i = 27; i > x; i--) {
				point.setY(point.getY() - table[i][28].getY());
			}
		}

		if (y > 28) {
			for (int i = 28; i < y; i++) {
				System.out.println("i="+i);
				System.out.println("x="+x);
				point.setX(point.getX() + table[x][i].getX());
			}
		} else {
			for (int i = 28; i > y; i--) {
				System.out.println("i="+i);
				System.out.println("x="+x);
				point.setX(point.getX() - table[x][i].getX());
			}
		}
		point.setX(point.getX() + 500);
		point.setY(point.getY() + 500);
		return point;
		
	}

	private static Point getOffset(Point [][]table,PieceDomino piece,PieceDomino oldPiece,int x,int y) {
		Point oldPoint=table[x][y];
		
		
		
		
		
		
	}
//		if (piece.isCentre()) {
//			if (piece.getRot() == 0) {
//				return new Point(20, 40);
//			} else {
//				return new Point(40, 20);
//			}
//		} else {
//			if (piece.getRot() == 0) {
//				return new Point(80, 40);
//			} else {
//				return new Point(40, 80);
//			}
//		}
//	}
}
