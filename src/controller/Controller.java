package controller;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import modele.Point;
import modele.domino.JoueurDomino;
import modele.domino.ModeleDomino;
import modele.domino.PieceDomino;
import modele.triomino.JoueurTriomino;
import modele.triomino.ModeleTriomino;
import modele.triomino.PieceTriomino;
import vue.ChoixJeu;
import vue.ChoixJoueurs;
import vue.domino.ChoixRotDomino;
import vue.domino.TableDomino;
import vue.triomino.ChoixRotTriomino;
import vue.triomino.TableTriomino;

public class Controller {
	public static int tailleFenetre = 4400;
	public static int tailleFenetreTriomino = 5650;
	// Cette variable m'as servie pour la phase de test , je vais voir si je
	// vais la supprimer
	public static int i;
	// ces deux variable vont probablement etre deplacées dans une classe de Vue
	// pour triomino
	public static boolean piocheOk = false;
	// piocheOk permet de sortir de la boucle d'attente quand l'utilisateur
	// pioche ( dans le debut pour determiner qui commance)
	private static boolean partieCommance = false;

	// le Listner des boutons pioches (triomino) doit avoir 2 fonctions , soit
	// il est utilisé au debut pour determiner le joueur qui commance soit au
	// cours du jeu la variable partieCommance permet de determiner quel
	// reaction doit avoir le bouton ( voir les lisnters pour bien comprendre )
	private static Point getCoord(Point[][] table, int x, int y) {
		// Cette methode est inutile je vais sans doute la remplacer par une
		// affectation dans la partie domino dans ce controller

		return table[x][y];
	}

	private static Point getOffset(Point[][] table, PieceDomino piece, PieceDomino oldPiece, int x, int y, int newX,
			int newY) {
		// Cette methode permet de calculer la position de la piece domino ,
		// elle
		// sera deplacée dans la vue du domino apres l'avoir bien testé ( elle
		// marche bien a priori )

		Point oldPoint = table[x][y];
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
					newPoint.setY(oldPoint.getY() - 20);
				} else if (piece.getX() == oldPiece.getY() && newY == y + 1) {
					newPoint.setX(oldPoint.getX() + 80);
					newPoint.setY(oldPoint.getY() - 20);
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
				}

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

	public static void main(String[] args) {

		Point oldPoint;
		int nbJoueurs = 0;
		int jeu;
		int joueurCourant;
		ChoixJeu choixJeu = new ChoixJeu();
		// choixJeu est une interface graphique qui va demander a l'utilsateur
		// de choisir le jeu ( dom/tri)
		System.err.println("Attente choix du jeu");
		do {
			jeu = choixJeu.getChoix();
			System.err.print(""); // choix jeu
		} while (jeu == -1);
		// Tant que l'utilisateur n'as pas choisi le jeu ( valeur par defaut de
		// choixJeu.getChoix() ==-1) on attend
		System.err.println("Choix jeu ok");
		// Apres etre sorti de la boucle le choix du jeu a ete forcement fait
		choixJeu.exit();
		// On quitte la fenetre choixJeu
		choixJeu = null;
		// pour le garbage collector
		ChoixJoueurs choixJoueurs = new ChoixJoueurs(jeu);
		// Cette Fenetre ser a configurer les joueurs , choisir les nom ,
		// determiner qui serons les CPU et les Humains ,et choisir le nombre de
		// joueurs
		System.err.println("Attente configuration joueurs");
		while (choixJoueurs.getJoueurs().size() == 0) {
			System.err.print(""); // setup
		}
		System.err.println("Configuration joueurs ok");
		// Ici les joueurs on ete bien configurés

		if (jeu == 0) {
			ModeleDomino modele = new ModeleDomino(jeu, choixJoueurs.getJoueurs());
			// Creation d'une instance du modele du jeu domino
			nbJoueurs = modele.getJoueurs().size();
			// Cette variable contients le nombe de joueurs pour les
			// initialisations
			for (JoueurDomino j : modele.getJoueurs()) {
				j.initMain(modele.getDeck(), 7);
				// Ici pour chaque joueurs on pioche 7 piece de domino au hasard
				// et on les enleve de la pioche
			}
			choixJoueurs = null;
			// Pour le garbage collector
			TableDomino tableDeJeu = new TableDomino();
			// TableDomino est la classe qui contiens la vue principale du jeu
			// domino
			int i;
			for (i = 0; i < nbJoueurs; i++) {
				tableDeJeu.setNomJoueur(i, modele.getJoueurs().get(i).getNom());
				// Pour chaque iteration on fait une mise a jour du JLabel
				// destiné au nom de joueur
				tableDeJeu.dessinerPiecesJoueur(i, modele.getJoueurs().get(i).getMain());
				// Pour chaque joueur on affiche la main initalisée plus tot
			}
			for (; i < 4; i++) {
				tableDeJeu.setNomJoueur(i, "");
				// Si on joue a moin de 4 joueurs , les labels des nom des
				// joueurs desactivés doivent etre vides
			}
			joueurCourant = modele.joueurQuiCommance();
			// Parmi les methodes du modele il ya joueurQuiCommance() qui permet
			// de determiner le joueur qui a le double avec la valeur maximum la
			// methode renvoie l'index du joueur dans la liste qui est un
			// attibut du modele
			JoueurDomino jCourant = modele.getJoueurs().get(joueurCourant);
			// On stocke a chaque fois une reference du joueur courant pour
			// acceder facilement a ces methodes et ne pas compliquer le code
			System.out.println("Le joueur qui commance est " + joueurCourant);
			tableDeJeu.setToken(joueurCourant);
			// Quand le tour d'un joueur arrive on lui donne le jeton pour
			// differencier qui est le joueur courant dans la vue
			int premierePieceAjouer = modele.getJoueurs().get(joueurCourant).indexDuPlusGrandDouble();
			tableDeJeu.dessinerPiece(jCourant.getMain().get(premierePieceAjouer), tailleFenetre / 2, tailleFenetre / 2,
					28, 28);
			// premierePieceAjouer contient l'index du plus grand double du
			// joueur qui commance
			modele.getTable()[28][28] = jCourant.getMain().get(premierePieceAjouer);
			modele.getTable()[28][28].setCentre(true);
			tableDeJeu.getTable()[28][28] = new Point(tailleFenetre / 2, tailleFenetre / 2);
			jCourant.getMain().remove(premierePieceAjouer);
			tableDeJeu.dessinerPiecesJoueur(joueurCourant, jCourant.getMain());
			modele.getExtremite().add(new Point(28, 29));
			modele.getExtremite().add(new Point(28, 27));
			modele.getExtremite().add(new Point(27, 28));
			modele.getExtremite().add(new Point(29, 28));
			// Ainsi on a poser ce double au milieu
			joueurCourant = (joueurCourant + 1) % nbJoueurs;
			jCourant = modele.getJoueurs().get(joueurCourant);
			tableDeJeu.setToken(joueurCourant);
			// Apres avoir fait le premier tour on va entrer dans la boucle du
			// jeux
			// Debut de la partie
			while (modele.finPartie(jeu) == 5) {
				// La methode finPartie renvoie le numero de joueur gangant si
				// la partie et finie et renvoie la valeur 5 sinon ( c'est un
				// choix 5 c'est pas une deduction )

				System.out.println("Partie non terminée c'est le tour du joueur " + joueurCourant);

				Point point;
				if (jCourant.nePeutPasJouer(jeu, modele)) {
					// Si le joueur courant ne peut pas jouer ( variante sans
					// pioche ) on va sauter le else et passer directement au
					// joueur suivant
					System.err.println("Le joueur " + joueurCourant + " passe son tour");
				} else {
					if (jCourant.isCpu()) {
						// Dans ce bloc c'est un joueur CPU qui est le joueur
						// courant

						System.out.println("C'est le tour du joueur cpu " + joueurCourant);
						Point oldP = new Point(0, 0);
						// oldP est un objet de Type Point qui va contenir les
						// coordonees de la piece que le joueur a utilisé pour
						// coller sa piece
						point = jCourant.coup(jeu, null, 0, modele, 0, 0, 0, false, oldP);
						// Le joueur courant cpu va faire son coup ( la plupart
						// des valeurs son a 0 ou nulles car il n'as pas besoin
						// de parametres pour jouer puisqu'il n'est pas humain )

						int x = oldP.getX();
						int y = oldP.getY();
						PieceDomino oldPiece = modele.getTable()[x][y];
						tableDeJeu.getTable()[point.getX()][point.getY()] = Controller.getOffset(tableDeJeu.getTable(),
								modele.getTable()[point.getX()][point.getY()], oldPiece, x, y, point.getX(),
								point.getY());
						// L'attribut table de tableDeJeu ( la vue ) est une
						// matrice parallele a celle du modele mais qui contient
						// les coordonees de la piece dans le terrain de jeu
/* wsolt lil houni bil com mriglin w ken fil classe hethi */
						Point coord = Controller.getCoord(tableDeJeu.getTable(), point.getX(), point.getY());
						System.out.println("Le joueur a poser la piece " + modele.getTable()[point.getX()][point.getY()]
								+ " dans : x=" + point.getX() + ", y=" + point.getY());
						tableDeJeu.dessinerPiece(modele.getTable()[point.getX()][point.getY()], coord.getX(),
								coord.getY(), point.getX(), point.getY());
						// Ici on dessine la piece jouée par le cpu
						System.out.println("La piece" + modele.getTable()[point.getX()][point.getY()]
								+ "est desinée dans les coordonées" + coord.getX() + coord.getY());

					} else { // joueur humain
						ChoixRotDomino choixRot;
						Point pointHumain;
						do {

							System.out.println("c'est le tour de l'humain " + joueurCourant);

							System.out.println("Attente du choix de la piece a jouer");
							while (tableDeJeu.getChoixJoueur(joueurCourant) == -1
									|| tableDeJeu.getChoixJoueur(joueurCourant) >= jCourant.getMain().size()) {
								System.err.print(""); // choix piece main

							}
							System.out.println("Le joueur a choisi la piece dont l'indice est "
									+ tableDeJeu.getChoixJoueur(joueurCourant));

							choixRot = new ChoixRotDomino(
									jCourant.getMain().get(tableDeJeu.getChoixJoueur(joueurCourant)));
							System.out.println("Attente du choix de la rotation de la piece");
							while (choixRot.isClicked() == false) {
								System.out.print(""); // choix rot

							}

							System.out.println("choix de la rotation de la piece effectué");
							System.err.println("Attente choix de l'emplacement de la piece choisie");
							while (tableDeJeu.getPieceChoisie().getX() == -1) {
								System.out.print(""); // choix table

							}
							System.err.println("Choix de l'emplacement de la piece choisie effectué");
							jCourant.getMain().get(tableDeJeu.getChoixJoueur(joueurCourant))
									.setCentre(choixRot.isCentre());

							pointHumain = jCourant.coup(jeu,
									jCourant.getMain().get(tableDeJeu.getChoixJoueur(joueurCourant)),
									tableDeJeu.getChoixJoueur(joueurCourant), modele,
									tableDeJeu.getPieceChoisie().getX(), tableDeJeu.getPieceChoisie().getY(),
									choixRot.getRot(), choixRot.isCentre(), null);
							oldPoint = tableDeJeu.getPieceChoisie();
							System.err.println("fin Tentative de coup");
							choixRot.setClicked(false);
							choixRot.setCentre(false);
							tableDeJeu.resetChoix();
							tableDeJeu.resetPieceChoisie();
							System.out.println("le point que l'humain va jouer est" + pointHumain);
							// yomkon nrajaaha if(pointHumain==null){
							// jCourant.getMain().get(tableDeJeu.getChoixJoueur(joueurCourant)).setCentre(false);
							// }
						} while (pointHumain == null);

						tableDeJeu.getTable()[pointHumain.getX()][pointHumain.getY()] = Controller.getOffset(
								tableDeJeu.getTable(), modele.getTable()[pointHumain.getX()][pointHumain.getY()],
								modele.getTable()[oldPoint.getX()][oldPoint.getY()], oldPoint.getX(), oldPoint.getY(),
								pointHumain.getX(), pointHumain.getY());

						Point coord = Controller.getCoord(tableDeJeu.getTable(), pointHumain.getX(),
								pointHumain.getY());
						tableDeJeu.dessinerPiece(modele.getTable()[pointHumain.getX()][pointHumain.getY()],
								coord.getX(), coord.getY(), pointHumain.getX(), pointHumain.getY());
						System.out.println("la piece" + modele.getTable()[pointHumain.getX()][pointHumain.getY()]
								+ " desinée dans les coordonées x=" + coord.getX() + " y = " + coord.getY());

					}
				}
				tableDeJeu.dessinerPiecesJoueur(joueurCourant, jCourant.getMain());
				System.out.println("On passe au joueur suivant " + joueurCourant + "+1");
				joueurCourant = (joueurCourant + 1) % nbJoueurs;
				jCourant = modele.getJoueurs().get(joueurCourant);
				tableDeJeu.setToken(joueurCourant);
				System.out.println("Passage terminé");
				System.out.println("si 5 alors non fin " + modele.finPartie(jeu));

			}
		} else if (jeu == 1) {// triomin
			JoueurTriomino jCourant;

			ModeleTriomino modeleTriomino = new ModeleTriomino(jeu, choixJoueurs.getJoueurs());
			modeleTriomino.initDeck(jeu);
			nbJoueurs = modeleTriomino.getJoueurs().size();
			TableTriomino tableTriomino = new TableTriomino();
			tableTriomino.getFrame().setVisible(true);

			tableTriomino.setQuiPeutPiocher(5, new JoueurTriomino(jeu, "", true), modeleTriomino.getDeck());
			// desactiver// toutes// les// pioches
			for (int i = 0; i < nbJoueurs; i++) { // init noms

				tableTriomino.setNomJoueur(i, modeleTriomino.getJoueurs().get(i).getNom());
				tableTriomino.dessinerPiecesJoueur(i, new ArrayList<PieceTriomino>());
				// init// pieces// vides // (// car// il// faut // piocer// et//
				// determiner// le// joueur// qui// commance// avant// )
				if (!modeleTriomino.getJoueurs().get(i).isCpu()) {
					// si le// joueur n'est pascpu il fautactiver le bouton //
					// pioche etlui faire/ un// listner

					switch (i) {
					case 0:
						tableTriomino.getBoutonPiocher(0).addMouseListener(new MouseAdapter() {
							@Override
							public void mouseClicked(MouseEvent e) {
								if (!partieCommance) {
									modeleTriomino.getJoueurs().get(0).piocher(modeleTriomino.getDeck(), 1);
									piocheOk = true;
								} else if (tableTriomino.getBoutonPiocher(0).isEnabled()) {
									modeleTriomino.getJoueurs().get(0).piocher(modeleTriomino.getDeck(), 1);
									modeleTriomino.getJoueurs().get(0)
											.setScore(modeleTriomino.getJoueurs().get(0).getScore() - 5);
									modeleTriomino.getJoueurs().get(0).setNombreDePioches(
											modeleTriomino.getJoueurs().get(0).getNombreDePioches() + 1);
									// System.out.println(modeleTriomino.getJoueurs().get(0).getNombreDePioches()
									// );
									if (modeleTriomino.getJoueurs().get(0).getNombreDePioches() >= 3) {
										tableTriomino.getBoutonPiocher(0).setEnabled(false);
										modeleTriomino.getJoueurs().get(0)
												.setScore(modeleTriomino.getJoueurs().get(0).getScore() - 10);
									}
									tableTriomino.dessinerPiecesJoueur(0, modeleTriomino.getJoueurs().get(0).getMain());

								}

							}
						});
						break;
					case 1:
						tableTriomino.getBoutonPiocher(1).addMouseListener(new MouseAdapter() {
							@Override
							public void mouseClicked(MouseEvent e) {
								if (!partieCommance) {
									modeleTriomino.getJoueurs().get(1).piocher(modeleTriomino.getDeck(), 1);
									piocheOk = true;
								} else if (tableTriomino.getBoutonPiocher(1).isEnabled()) {
									modeleTriomino.getJoueurs().get(1).piocher(modeleTriomino.getDeck(), 1);
									modeleTriomino.getJoueurs().get(1)
											.setScore(modeleTriomino.getJoueurs().get(1).getScore() - 5);
									modeleTriomino.getJoueurs().get(1).setNombreDePioches(
											modeleTriomino.getJoueurs().get(1).getNombreDePioches() + 1);
									// System.out.println(modeleTriomino.getJoueurs().get(1).getNombreDePioches()
									// );
									if (modeleTriomino.getJoueurs().get(1).getNombreDePioches() >= 3) {
										tableTriomino.getBoutonPiocher(1).setEnabled(false);
										modeleTriomino.getJoueurs().get(1)
												.setScore(modeleTriomino.getJoueurs().get(1).getScore() - 10);
									}
									tableTriomino.dessinerPiecesJoueur(1, modeleTriomino.getJoueurs().get(0).getMain());

								}

							}
						});
						break;
					case 2:
						tableTriomino.getBoutonPiocher(2).addMouseListener(new MouseAdapter() {
							@Override
							public void mouseClicked(MouseEvent e) {
								if (!partieCommance) {
									modeleTriomino.getJoueurs().get(2).piocher(modeleTriomino.getDeck(), 1);
									piocheOk = true;
								} else if (tableTriomino.getBoutonPiocher(2).isEnabled()) {
									modeleTriomino.getJoueurs().get(2).piocher(modeleTriomino.getDeck(), 1);
									modeleTriomino.getJoueurs().get(2)
											.setScore(modeleTriomino.getJoueurs().get(2).getScore() - 5);
									modeleTriomino.getJoueurs().get(2).setNombreDePioches(
											modeleTriomino.getJoueurs().get(2).getNombreDePioches() + 1);

									if (modeleTriomino.getJoueurs().get(2).getNombreDePioches() >= 3) {
										tableTriomino.getBoutonPiocher(2).setEnabled(false);
										modeleTriomino.getJoueurs().get(2)
												.setScore(modeleTriomino.getJoueurs().get(2).getScore() - 10);
									}
									tableTriomino.dessinerPiecesJoueur(2, modeleTriomino.getJoueurs().get(2).getMain());

								}

							}
						});
						break;
					case 3:
						tableTriomino.getBoutonPiocher(3).addMouseListener(new MouseAdapter() {
							@Override
							public void mouseClicked(MouseEvent e) {
								if (!partieCommance) {
									modeleTriomino.getJoueurs().get(3).piocher(modeleTriomino.getDeck(), 1);
									piocheOk = true;
								} else if (tableTriomino.getBoutonPiocher(3).isEnabled()) {
									modeleTriomino.getJoueurs().get(3).piocher(modeleTriomino.getDeck(), 1);
									modeleTriomino.getJoueurs().get(3)
											.setScore(modeleTriomino.getJoueurs().get(3).getScore() - 5);
									modeleTriomino.getJoueurs().get(3).setNombreDePioches(
											modeleTriomino.getJoueurs().get(3).getNombreDePioches() + 1);
									// System.out.println(modeleTriomino.getJoueurs().get(3).getNombreDePioches()
									// );
									if (modeleTriomino.getJoueurs().get(3).getNombreDePioches() >= 3) {
										tableTriomino.getBoutonPiocher(3).setEnabled(false);
										modeleTriomino.getJoueurs().get(3)
												.setScore(modeleTriomino.getJoueurs().get(3).getScore() - 10);
									}
									tableTriomino.dessinerPiecesJoueur(3, modeleTriomino.getJoueurs().get(3).getMain());

								}

							}
						});
						break;
					default:
						break;
					}
				}

			}
			// System.out.println("fin init noms");
			// fin init noms et boutons et main vides
			// on vas determiner le jouer qui commance ( nebdew mel joueur 1
			// w yebdew wahna mechin kenou cpu yepiochi wahdou sinon pioche
			// automatique
			joueurCourant = 0;
			jCourant = modeleTriomino.getJoueurs().get(joueurCourant);
			tableTriomino.setToken(joueurCourant);
			tableTriomino.setQuiPeutPiocher(joueurCourant, jCourant, modeleTriomino.getDeck());
			int tentative = -1;
			do {
				for (int i = 0; i < nbJoueurs; i++) {
					// dans cette boucle les nbJoueurs doivent piocher

					if (!jCourant.isCpu()) {
						while (!piocheOk) {
							System.out.print("");
						}
						piocheOk = false;
					} else {
						modeleTriomino.getJoueurs().get(i).piocher(modeleTriomino.getDeck(), 1);

					}

					joueurCourant = (joueurCourant + 1) % nbJoueurs;
					jCourant = modeleTriomino.getJoueurs().get(joueurCourant);
					tableTriomino.setToken(joueurCourant);
					tableTriomino.setQuiPeutPiocher(joueurCourant, jCourant, modeleTriomino.getDeck());
					// dessiner la pieces pioché a chaque fois
					tableTriomino.dessinerPiecesJoueur(i, modeleTriomino.getJoueurs().get(i).getMain());
					// System.out.println("dessin des pieces du joueur " +
					// joueurCourant);
				}
				tentative++;
			} while (modeleTriomino.getJoueurQuiCommance(tentative) == 4);
			partieCommance = true;
			joueurCourant = modeleTriomino.getJoueurQuiCommance(tentative);
			System.out.println("C'est le joueur " + joueurCourant + " qui commance");
			jCourant = modeleTriomino.getJoueurs().get(joueurCourant);
			tableTriomino.setToken(joueurCourant);
			tableTriomino.setQuiPeutPiocher(joueurCourant, jCourant, modeleTriomino.getDeck());
			// alert dialog icis (pour fin choix du j qui commance
			// joueur qui commance determiner , maintenant on vas remettre les
			// pieces tirés dans le deck et vider les main
			modeleTriomino.initDeck(jeu);
			// reinit deck ( 3awed 3abih melowl )
			int tailleMain = 7;
			if (nbJoueurs == 2) {
				tailleMain = 9;
			}
			for (int i = 0; i < nbJoueurs; i++) {
				modeleTriomino.getJoueurs().get(i).setMain(new ArrayList<PieceTriomino>());
				modeleTriomino.getJoueurs().get(i).initMain(modeleTriomino.getDeck(), tailleMain);
				tableTriomino.dessinerPiecesJoueur(i, modeleTriomino.getJoueurs().get(i).getMain());
				// vider la main
			}

			// le debut du jeu va commancer ici
			System.out.println("debut de jeu");
			ArrayList<Point> extre = new ArrayList<Point>();
			Point pointJoue = new Point(-1, -1);

			// premier coup
			if (jCourant.isCpu()) { // cpu
				modeleTriomino.getTable()[57][57] = jCourant.getMain().get(0);
				jCourant.getMain().remove(0);

			} else { // humain
				tableTriomino.resetChoix();
				tableTriomino.resetPieceChoisie();
				System.out.println("Attente du choix de la piece a jouer");
				while (tableTriomino.getChoixJoueur(joueurCourant) == -1
						|| tableTriomino.getChoixJoueur(joueurCourant) >= jCourant.getMain().size()) {
					System.err.print(""); // choix piece main

				}

				System.err.println("Le joueur a choisi la piece "
						+ jCourant.getMain().get(tableTriomino.getChoixJoueur(joueurCourant)));

				ChoixRotTriomino choixRotTriomino = new ChoixRotTriomino(
						jCourant.getMain().get(tableTriomino.getChoixJoueur(joueurCourant)));
				System.out.println("Attente du choix de la rotation de la piece");
				while (choixRotTriomino.isClicked() == false) {
					System.out.print(""); // choix rot
				}
				System.err.println("La piece choisie apres rotation "
						+ jCourant.getMain().get(tableTriomino.getChoixJoueur(joueurCourant)));

				System.out.println("choix de la rotation de la piece effectué");
				modeleTriomino.getTable()[57][57] = jCourant.getMain().get(tableTriomino.getChoixJoueur(joueurCourant));
				jCourant.getMain().remove(tableTriomino.getChoixJoueur(joueurCourant));
			}
			jCourant.ajouterExtremites(1, modeleTriomino, 57, 57);
			///////////
			// tableTriomino.dessinerPlacesExtremites(modeleTriomino.getExtremite());
			///////////
			tableTriomino.dessinerPiecesJoueur(joueurCourant, modeleTriomino.getJoueurs().get(joueurCourant).getMain());
			// tableTriomino.dessinerPiece(modeleTriomino.getTable()[57][57],
			// 57, 57);
			tableTriomino.getTable()[57][57].getPiece().changerPiece(modeleTriomino.getTable()[57][57]);
			joueurCourant = (joueurCourant + 1) % nbJoueurs;
			jCourant = modeleTriomino.getJoueurs().get(joueurCourant);
			tableTriomino.setToken(joueurCourant);
			tableTriomino.setQuiPeutPiocher(joueurCourant, jCourant, modeleTriomino.getDeck());
			tableTriomino.resetChoix();
			tableTriomino.resetPieceChoisie();
			// fin premier coup

			do {

				if (jCourant.isCpu()) {// joueur cpu
					if (jCourant.nePeutPasJouer(1, modeleTriomino)) { // pioche
																		// 1
						System.out.println("joueur cpu " + joueurCourant + " ne peut pas jouer");
						jCourant.piocher(modeleTriomino.getDeck(), 1);
						jCourant.setScore(jCourant.getScore() - 5);
						tableTriomino.dessinerPiecesJoueur(joueurCourant,
								modeleTriomino.getJoueurs().get(joueurCourant).getMain());
					}
					if (jCourant.nePeutPasJouer(1, modeleTriomino)) { // pioche
																		// 2
						System.out.println("joueur cpu " + joueurCourant + " ne peut pas jouer");
						jCourant.piocher(modeleTriomino.getDeck(), 1);
						jCourant.setScore(jCourant.getScore() - 5);
						tableTriomino.dessinerPiecesJoueur(joueurCourant,
								modeleTriomino.getJoueurs().get(joueurCourant).getMain());

					}
					if (jCourant.nePeutPasJouer(1, modeleTriomino)) {
						// 3eme pioche

						System.out.println("joueur cpu " + joueurCourant + " ne peut pas jouer");
						jCourant.piocher(modeleTriomino.getDeck(), 1);
						jCourant.setScore(jCourant.getScore() - 5);
						tableTriomino.dessinerPiecesJoueur(joueurCourant,
								modeleTriomino.getJoueurs().get(joueurCourant).getMain());

					}
					if (jCourant.nePeutPasJouer(1, modeleTriomino)) {
						// ne peut pas jouer apres 3 pioches
						System.out.println("joueur cpu " + joueurCourant + " ne peut pas jouer");
						jCourant.setScore(jCourant.getScore() - 10);
						tableTriomino.dessinerPiecesJoueur(joueurCourant,
								modeleTriomino.getJoueurs().get(joueurCourant).getMain());
						// le joueur passe son tour
					} else { // le joueur peut jouer
						pointJoue = jCourant.coup(0, null, 0, modeleTriomino, 0, 0, extre);

						tableTriomino.getTable()[pointJoue.getX()][pointJoue.getY()].getPiece()
								.changerPiece(modeleTriomino.getTable()[pointJoue.getX()][pointJoue.getY()]);
						/////////
						// tableTriomino.dessinerPlacesExtremites(modeleTriomino.getExtremite());

						tableTriomino.dessinerPiecesJoueur(joueurCourant,
								modeleTriomino.getJoueurs().get(joueurCourant).getMain());

						// coup cpu
					}
				} else {// joueur humain

					jCourant.setNombreDePioches(0);
					// reinit nombre de pioches pour
					// calculer s'il a piocher 3
					// fois
					do {
						tableTriomino.resetChoix();
						tableTriomino.resetPieceChoisie();
						// System.out.println(tableTriomino.getPieceChoisie());
						if (jCourant.nePeutPasJouer(jeu, modeleTriomino) && jCourant.getNombreDePioches() >= 3) {
							// joueur passe
							break;
						} else {

							System.out.println("c'est le tour de l'humain " + joueurCourant);

							System.out.println("Attente du choix de la piece a jouer");
							while (tableTriomino.getChoixJoueur(joueurCourant) == -1
									|| tableTriomino.getChoixJoueur(joueurCourant) >= jCourant.getMain().size()) {
								System.err.print(""); // choix piece main

							}
							System.out.println("Le joueur a choisi la piece dont l'indice est "
									+ tableTriomino.getChoixJoueur(joueurCourant));

							ChoixRotTriomino choixRotTriomino = new ChoixRotTriomino(
									jCourant.getMain().get(tableTriomino.getChoixJoueur(joueurCourant)));
							System.out.println("Attente du choix de la rotation de la piece");
							while (choixRotTriomino.isClicked() == false) {
								System.out.print(""); // choix rot
							}

							System.out.println("choix de la rotation de la piece effectué");
							System.err.println("Attente choix de l'emplacement de la piece choisie");
							while (tableTriomino.getPieceChoisie().getX() == -1) {
								System.out.print(""); // choix table
							}
							Point point = tableTriomino.getPieceChoisie();
							pointJoue = jCourant.coup(jeu,
									jCourant.getMain().get(tableTriomino.getChoixJoueur(joueurCourant)),
									tableTriomino.getChoixJoueur(joueurCourant), modeleTriomino, point.getX(),
									point.getY(), extre);

						}

					} while (pointJoue.getX() == -1);
					if (pointJoue.getX() == -1) {
						// le joueur a passé son tour
					} else {

						System.out.println(tableTriomino.getTable()[pointJoue.getX()][pointJoue.getY()]);
						tableTriomino.getTable()[pointJoue.getX()][pointJoue.getY()].getPiece()
								.changerPiece(modeleTriomino.getTable()[pointJoue.getX()][pointJoue.getY()]);
						////////
						// tableTriomino.dessinerPlacesExtremites(modeleTriomino.getExtremite());

						tableTriomino.dessinerPiecesJoueur(joueurCourant,
								modeleTriomino.getJoueurs().get(joueurCourant).getMain());

					}
					tableTriomino.resetChoix();
					tableTriomino.resetPieceChoisie();

				} // fin humain

				// tableTriomino.dessinerPiece(modeleTriomino.getTable()[pointJoue.getX()][pointJoue.getY()],
				// pointJoue.getX(), pointJoue.getY());

				joueurCourant = (joueurCourant + 1) % nbJoueurs;
				jCourant = modeleTriomino.getJoueurs().get(joueurCourant);
				tableTriomino.setToken(joueurCourant);
				tableTriomino.setQuiPeutPiocher(joueurCourant, jCourant, modeleTriomino.getDeck());

			} while (modeleTriomino.finPartie(1) == 5);
			partieCommance = false;
		}
		System.out.println("fin Controller.main");
	}
}
