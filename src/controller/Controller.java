package controller;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import dao.config.Dao;
import modele.Point;
import modele.domino.JoueurDomino;
import modele.domino.ModeleDomino;
import modele.domino.PieceDomino;
import modele.triomino.JoueurTriomino;
import modele.triomino.ModeleTriomino;
import modele.triomino.PieceTriomino;
import vue.ChoixJeu;
import vue.ChoixJoueurs;
import vue.VueGagnant;
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
				tableDeJeu.dessinerPiecesJoueur(i, modele.getJoueurs().get(i).getMain(),modele.getJoueurs().get(i).isCpu());
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
			tableDeJeu.dessinerPiecesJoueur(joueurCourant, jCourant.getMain(),jCourant.isCpu());
			modele.getExtremite().add(new Point(28, 29));
			modele.getExtremite().add(new Point(28, 27));
			modele.getExtremite().add(new Point(27, 28));
			modele.getExtremite().add(new Point(29, 28));
			// Ainsi on a pose ce double au milieu et ajoute les extremites au
			// tableau d'extremite
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

						Point coord = Controller.getCoord(tableDeJeu.getTable(), point.getX(), point.getY());
						System.out.println("Le joueur a poser la piece " + modele.getTable()[point.getX()][point.getY()]
								+ " dans : x=" + point.getX() + ", y=" + point.getY());
						tableDeJeu.dessinerPiece(modele.getTable()[point.getX()][point.getY()], coord.getX(),
								coord.getY(), point.getX(), point.getY());
						// Ici on dessine la piece jouée par le cpu
						System.out.println("La piece" + modele.getTable()[point.getX()][point.getY()]
								+ "est desinée dans les coordonées" + coord.getX() + coord.getY());

					} else {
						// C'est un joueur humain qui est le joueur courant
						ChoixRotDomino choixRot;
						Point pointHumain;
						do {

							System.out.println("c'est le tour de l'humain " + joueurCourant);

							System.out.println("Attente du choix de la piece a jouer");
							while (tableDeJeu.getChoixJoueur(joueurCourant) == -1
									|| tableDeJeu.getChoixJoueur(joueurCourant) >= jCourant.getMain().size()) {
								System.err.print(""); // choix piece main

							}
							// On attend tant que le joueur n'as pas choisi de
							// piece dans sa propre main
							System.out.println("Le joueur a choisi la piece dont l'indice est "
									+ tableDeJeu.getChoixJoueur(joueurCourant));

							choixRot = new ChoixRotDomino(
									jCourant.getMain().get(tableDeJeu.getChoixJoueur(joueurCourant)));
							System.out.println("Attente du choix de la rotation de la piece");
							while (choixRot.isClicked() == false) {
								System.out.print("");

							}
							// On attent jusqu'a ce que le joueur choissise la
							// rotation de la piece et si elle est centrée
							System.out.println("choix de la rotation de la piece effectué");
							System.err.println("Attente choix de l'emplacement de la piece choisie");
							while (tableDeJeu.getPieceChoisie().getX() == -1) {
								System.out.print("");

							}
							// Le joueur doit choisir ou jouer la piece , on
							// attend jusqu'a ce qu'il choissise , il doit
							// cliquer sur une piece compatible dans la table de
							// jeu
							System.err.println("Choix de l'emplacement de la piece choisie effectué");
							jCourant.getMain().get(tableDeJeu.getChoixJoueur(joueurCourant))
									.setCentre(choixRot.isCentre());
							// Mise a jouer de la piece si elle est centrée
							pointHumain = jCourant.coup(jeu,
									jCourant.getMain().get(tableDeJeu.getChoixJoueur(joueurCourant)),
									tableDeJeu.getChoixJoueur(joueurCourant), modele,
									tableDeJeu.getPieceChoisie().getX(), tableDeJeu.getPieceChoisie().getY(),
									choixRot.getRot(), choixRot.isCentre(), null);
							// La variable pointHumain peut avoir la valeur du
							// nouveau point jouée , ou nulle si le coup n'est
							// pas valide
							oldPoint = tableDeJeu.getPieceChoisie();
							System.err.println("fin Tentative de coup");
							choixRot.setClicked(false);
							choixRot.setCentre(false);
							tableDeJeu.resetChoix();
							tableDeJeu.resetPieceChoisie();
							// Ici on remet a zero les variables qui servent
							// pour l'attente et la synchronisation
							System.out.println("le point que l'humain va jouer est" + pointHumain);
						} while (pointHumain == null);
						// Si c'est un coup invalide alors que le joueur peut
						// jouer on reboucle sur le meme joueur jusqu'a ce qu'il
						// joue une piece valide
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
				tableDeJeu.dessinerPiecesJoueur(joueurCourant, jCourant.getMain(),jCourant.isCpu());
				// Apres avoir jouer une piece valide on la dessine puis on
				// passe au joueur suivant
				System.out.println("On passe au joueur suivant " + joueurCourant + "+1");
				joueurCourant = (joueurCourant + 1) % nbJoueurs;
				jCourant = modele.getJoueurs().get(joueurCourant);
				tableDeJeu.setToken(joueurCourant);
				System.out.println("Passage terminé");
				System.out.println("si 5 alors non fin " + modele.finPartie(jeu));
				// Fin domino , l'ajout dans la base de données et l'affichage
			}
			// du gagnant va etre appelé ici
			Dao dao = new Dao("database.xml");
			tableDeJeu.exit();
			// Fermer l'interface de jeu domino
			dao.ajouterPartieDomino(modele.getJoueurs(), modele.finPartie(jeu));
			// Ajouter les données de la partie a la base
			VueGagnant vueGangnant = new VueGagnant(jeu, modele.getJoueurs().get(modele.finPartie(jeu)));
			vueGangnant.getFrame().setVisible(true);
			// Affichage du gagnant

		} else if (jeu == 1) {
			// Si la variable jeu contient 1 alors le jeu choisi est triomino
			JoueurTriomino jCourant;
			ModeleTriomino modeleTriomino = new ModeleTriomino(jeu, choixJoueurs.getJoueurs());
			modeleTriomino.initDeck(jeu);
			nbJoueurs = modeleTriomino.getJoueurs().size();
			TableTriomino tableTriomino = new TableTriomino();
			tableTriomino.getFrame().setVisible(true);
			// On creer une instance du modele est de la vue , on initialise la
			// pioche et les joueurs en premier lieu
			tableTriomino.setQuiPeutPiocher(5, new JoueurTriomino(jeu, "", true), modeleTriomino.getDeck());
			// Avant de commancer on ne sait pas qui est le joueur qui commance
			// , on desactive alors tout les boutons piocher
			for (int i = 0; i < nbJoueurs; i++) {
				// Dans cette boucle nous alons initialiser quelques fonctions
				// et parametres de joueurs dans la vue
				tableTriomino.setNomJoueur(i, modeleTriomino.getJoueurs().get(i).getNom());
				tableTriomino.dessinerPiecesJoueur(i, new ArrayList<PieceTriomino>(),false);
				// On va mettre a jour les labels de noms de joueurs et on va
				// creer les main des joueurs dans la vue avec des pieces vides

				if (!modeleTriomino.getJoueurs().get(i).isCpu()) {
					// Si le joueur dont on va initialiser la vue est un cpu on
					// ne va pas ajoutez de listner a son bouton piocher car la
					// pioche va etre automatique s'il ne peut pas choisir

					switch (i) {
					// Je sais bien qu'au lieu d'un switch on peut faire une
					// fonction qu'on appele a chaque tour de boucle , mais il
					// faut regler les variables statiques dans ce cas et je
					// trouve plus prioritaire le fait de debbuger tout le jeu
					// avant , modification probable apres (optimisation)
					case 0:
						// Pour l'instant ou si on na pas modifier le code , je
						// vais commanter qu'un seul case car les autres on le
						// meme comportement
						tableTriomino.getBoutonPiocher(0).addMouseListener(new MouseAdapter() {
							// Dans la classe de vue de triomino , on a creer un
							// tableau de boutons indexé comme l'ArrayList des
							// joueurs piocher pour pouvoir y acceder par
							// l'indice du joueur , on ajoute alors au bouton
							// d'indice i ( qui correspond au joueur i) le
							// listner suivant
							@Override
							public void mouseClicked(MouseEvent e) {
								if (!partieCommance) {
									// Si partieCommance est faux ca veut dire
									// que nous sommes dans le cas ou les
									// joueurs doivent piocher pour determiner
									// qui est le joueur qui commance
									modeleTriomino.getJoueurs().get(0).piocher(modeleTriomino.getDeck(), 1);
									piocheOk = true;
									// Cette variable va nous servir a
									// controller la boucle d'attente de la
									// pioche
								} else if (tableTriomino.getBoutonPiocher(0).isEnabled()) {
									// Si la partie a commancer le comportement
									// du bouton pioche va changer , il va
									// prendre en compte le fait que le joueur
									// ne peut pas piocher plus que 3 fois dans
									// le meme tour et qu'il y'a des changement
									// de score a chaque pioche
									modeleTriomino.getJoueurs().get(0).piocher(modeleTriomino.getDeck(), 1);
									// Le jouer d'incide i (O dans ce cas) va
									// appeler sa methode piocher du deck
									// principal )
									modeleTriomino.getJoueurs().get(0)
											.setScore(modeleTriomino.getJoueurs().get(0).getScore() - 5);
									// On lui retire 5 points de son score
									modeleTriomino.getJoueurs().get(0).setNombreDePioches(
											modeleTriomino.getJoueurs().get(0).getNombreDePioches() + 1);
									// On incremente le nombre de pioches

									if (modeleTriomino.getJoueurs().get(0).getNombreDePioches() >= 3) {
										// On retire 10 du score du joueur apres
										// la 3 eme pioche et on desactive le
										// bouton qui sera reactivé dans le
										// prochain
										tableTriomino.getBoutonPiocher(0).setEnabled(false);
										modeleTriomino.getJoueurs().get(0)
												.setScore(modeleTriomino.getJoueurs().get(0).getScore() - 10);
									}
									tableTriomino.dessinerPiecesJoueur(0, modeleTriomino.getJoueurs().get(0).getMain(),modeleTriomino.getJoueurs().get(0).isCpu());
									tableTriomino.setScoreJoueur(0, modeleTriomino.getJoueurs().get(0).getScore());
									// Apres avoir piocher le score du joueur
									// dans la vue va etre mis a jouer et la
									// piece va etre affichée
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
									if (modeleTriomino.getJoueurs().get(1).getNombreDePioches() >= 3) {
										tableTriomino.getBoutonPiocher(1).setEnabled(false);
										modeleTriomino.getJoueurs().get(1)
												.setScore(modeleTriomino.getJoueurs().get(1).getScore() - 10);
									}
									tableTriomino.dessinerPiecesJoueur(1, modeleTriomino.getJoueurs().get(1).getMain(),modeleTriomino.getJoueurs().get(1).isCpu());
									tableTriomino.setScoreJoueur(1, modeleTriomino.getJoueurs().get(1).getScore());
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
									tableTriomino.dessinerPiecesJoueur(2, modeleTriomino.getJoueurs().get(2).getMain(),modeleTriomino.getJoueurs().get(2).isCpu());
									tableTriomino.setScoreJoueur(2, modeleTriomino.getJoueurs().get(2).getScore());
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
									if (modeleTriomino.getJoueurs().get(3).getNombreDePioches() >= 3) {
										tableTriomino.getBoutonPiocher(3).setEnabled(false);
										modeleTriomino.getJoueurs().get(3)
												.setScore(modeleTriomino.getJoueurs().get(3).getScore() - 10);
									}
									tableTriomino.dessinerPiecesJoueur(3, modeleTriomino.getJoueurs().get(3).getMain(),modeleTriomino.getJoueurs().get(3).isCpu());
									tableTriomino.setScoreJoueur(3, modeleTriomino.getJoueurs().get(3).getScore());
								}

							}
						});
						break;
					default:
						break;
					}
				}

			}
			// On arrive ici a la fin de l'initalisaton des nom , boutons ,
			// scores et mains vides

			joueurCourant = 0;
			jCourant = modeleTriomino.getJoueurs().get(joueurCourant);
			tableTriomino.setToken(joueurCourant);
			tableTriomino.setQuiPeutPiocher(joueurCourant, jCourant, modeleTriomino.getDeck());
			int tentative = -1;
			// A present chaque joueur va commancer a piocher , avec le bouton
			// si c'est un humain ou automatiquement si c'est un cpu , jusqu'a
			// ce qu'un joueur pioche une piece dont la valeur et plus grande
			// que les autres ( s'il y a egalité on recommance )
			do {
				for (int i = 0; i < nbJoueurs; i++) {
					// C'est la boucle ou les nbJoueurs pioches

					if (!jCourant.isCpu()) {
						// Si le joueur est humain on fait se traitement
						while (!piocheOk) {
							System.out.print("");
						}
						// Comme indiqué precedement on attend tant que la
						// variable piocheOk est fausse et on la remet a faut
						// apres pour le joueur suivant
						piocheOk = false;
					} else {
						// Sinon si le joueur est cpu on fait se traitement qui
						// consiste a piocher automatiquement sans cliquer sur
						// le bouton
						modeleTriomino.getJoueurs().get(i).piocher(modeleTriomino.getDeck(), 1);

					}

					joueurCourant = (joueurCourant + 1) % nbJoueurs;
					jCourant = modeleTriomino.getJoueurs().get(joueurCourant);
					tableTriomino.setToken(joueurCourant);
					tableTriomino.setQuiPeutPiocher(joueurCourant, jCourant, modeleTriomino.getDeck());
					// Pour passer d'un joueur au suivant on incremente la
					// variable joueurCourant modulo le nombre de joueurs puis
					// on enregistre la reference du joueur pour ne pas
					// compliquer le code , apres on lui donne le jeton dans la
					// vue pour differencier qui est le joueur courant,
					// finalement on lui donne l'acces a son bouton pioche et on
					// deactive les autres , nous allons utiliser cette logique
					// dans tout le code donc je vais la commanter une seule
					// fois
					tableTriomino.dessinerPiecesJoueur(i, modeleTriomino.getJoueurs().get(i).getMain(),false);
					// chaque fois qu'un joueur pioche il faut dessiner la piece
					// qu'il a piocher

				}
				tentative++;
			} while (modeleTriomino.getJoueurQuiCommance(tentative) == 4);
			// On continue de piocher tant qu'on a pas trouver de piece maximum
			// unique
			partieCommance = true;
			// partieCommance change le comportement du bouton pioche comme
			// indiqué tout a l'heure
			joueurCourant = modeleTriomino.getJoueurQuiCommance(tentative);
			System.out.println("C'est le joueur " + joueurCourant + " qui commance");
			jCourant = modeleTriomino.getJoueurs().get(joueurCourant);
			tableTriomino.setToken(joueurCourant);
			tableTriomino.setQuiPeutPiocher(joueurCourant, jCourant, modeleTriomino.getDeck());
			// Le joueur qui commance a la main maintenant

			modeleTriomino.initDeck(jeu);
			// Des qu'on a determiner le premier a jouer , on doit remettre les
			// pieces pioches dans le deck , et vider les main des joueurs , la
			// methode la plus facile est de rappeler la methode initdeck et de
			// mettre des arraylist vides dans les main des joueurs
			int tailleMain = 7;
			if (nbJoueurs == 2) {
				// Si il n'ya que 2 joueurs on va piocher 9 pieces chaqun au
				// lieu de 7
				tailleMain = 9;
			}
			for (int i = 0; i < nbJoueurs; i++) {
				// Dans cette boucle on vide les main des joueurs , pioche les
				// tailleMain pieces et met a jour l'affichage
				modeleTriomino.getJoueurs().get(i).setMain(new ArrayList<PieceTriomino>());
				modeleTriomino.getJoueurs().get(i).initMain(modeleTriomino.getDeck(), tailleMain);
				tableTriomino.dessinerPiecesJoueur(i, modeleTriomino.getJoueurs().get(i).getMain(),modeleTriomino.getJoueurs().get(i).isCpu());

			}

			// Toute les initalisations on été faites , on a a present les
			// joueurs avec leur mains remplies , le premier qui va commancer ,
			// la vue mise a jour , on peut a present commancer le jeu

			System.out.println("debut de jeu");

			Point pointJoue = new Point(-1, -1);

			// On va faire le premier coup , il est a part car il ne neccesite
			// aucun controle , juste le choix d'une piece qui va etre posée au
			// milieu
			if (jCourant.isCpu()) {
				// Si le joueur qui commance est un cpu , on a choisi qu'il pose
				// la premiere piece de sa main
				modeleTriomino.getTable()[57][57] = jCourant.getMain().get(0);
				jCourant.setScore(jCourant.getScore() + jCourant.getMain().get(0).valeur());
				jCourant.getMain().remove(0);
				tableTriomino.setScoreJoueur(joueurCourant, jCourant.getScore());
			} else {
				// Si c'est un humain , il va choisir une piece de sa main ainsi
				// que sa rotation ( je veux dire par rotation position , le
				// sommet est forcement en bas dans le milieu )
				tableTriomino.resetChoix();
				tableTriomino.resetPieceChoisie();
				System.out.println("Attente du choix de la piece a jouer");
				while (tableTriomino.getChoixJoueur(joueurCourant) == -1
						|| tableTriomino.getChoixJoueur(joueurCourant) >= jCourant.getMain().size()) {
					System.err.print("");

				}
				// Boucle d'attente
				System.err.println("Le joueur a choisi la piece "
						+ jCourant.getMain().get(tableTriomino.getChoixJoueur(joueurCourant)));

				ChoixRotTriomino choixRotTriomino = new ChoixRotTriomino(
						jCourant.getMain().get(tableTriomino.getChoixJoueur(joueurCourant)));
				System.out.println("Attente du choix de la rotation de la piece");
				while (choixRotTriomino.isClicked() == false) {
					System.out.print("");
				}
				// Boucle d'attente
				System.out.println("choix de la rotation de la piece effectué");
				modeleTriomino.getTable()[57][57] = jCourant.getMain().get(tableTriomino.getChoixJoueur(joueurCourant));
				jCourant.setScore(jCourant.getScore()
						+ jCourant.getMain().get(tableTriomino.getChoixJoueur(joueurCourant)).valeur());
				tableTriomino.setScoreJoueur(joueurCourant, jCourant.getScore());
				jCourant.getMain().remove(tableTriomino.getChoixJoueur(joueurCourant));
			}
			jCourant.ajouterExtremites(1, modeleTriomino, 57, 57);
			// Apres avoir jouer une piece on ajoute les extremites possibles
			// dans l'arrayList d'extermites et on dessine la piece dans le
			// terrain et on met a jour la vue de la main du joueur
			tableTriomino.dessinerPiecesJoueur(joueurCourant, modeleTriomino.getJoueurs().get(joueurCourant).getMain(),modeleTriomino.getJoueurs().get(joueurCourant).isCpu());
			tableTriomino.getTable()[57][57].getPiece().changerPiece(modeleTriomino.getTable()[57][57]);
			// Passage au joueur suivant
			joueurCourant = (joueurCourant + 1) % nbJoueurs;
			jCourant = modeleTriomino.getJoueurs().get(joueurCourant);
			tableTriomino.setToken(joueurCourant);
			tableTriomino.setQuiPeutPiocher(joueurCourant, jCourant, modeleTriomino.getDeck());
			tableTriomino.resetChoix();
			tableTriomino.resetPieceChoisie();
			// Fin Premier coup
			/* wsolt lil houni bil com mriglin w ken fil classe hethi */
			do {
				// Debut boucle de jeu
				if (jCourant.isCpu()) {
					// Ici on va traiter le cas du joueur cpu
					if (jCourant.nePeutPasJouer(1, modeleTriomino)) {
						// Si le joueur ne peut pas jouer
						// il va piocher automatiquement jusqu'a ce qu'il trouve
						// une piece qu'il peut jouer
						// tout en prenant compte qu'il ne peut piocher que 3
						// fois ici c'est le code de la premiere pioche
						System.out.println("joueur cpu " + joueurCourant + " ne peut pas jouer");
						jCourant.piocher(modeleTriomino.getDeck(), 1);
						jCourant.setScore(jCourant.getScore() - 5);
						tableTriomino.dessinerPiecesJoueur(joueurCourant,
								modeleTriomino.getJoueurs().get(joueurCourant).getMain(),modeleTriomino.getJoueurs().get(joueurCourant).isCpu());
						tableTriomino.setScoreJoueur(joueurCourant, jCourant.getScore());
					}
					if (jCourant.nePeutPasJouer(1, modeleTriomino)) {
						// Si le joueur cpu n'as pas pioché une piece jouable il
						// pioche de nouveau une deuxiemme fois
						System.out.println("joueur cpu " + joueurCourant + " ne peut pas jouer");
						jCourant.piocher(modeleTriomino.getDeck(), 1);
						jCourant.setScore(jCourant.getScore() - 5);
						tableTriomino.dessinerPiecesJoueur(joueurCourant,
								modeleTriomino.getJoueurs().get(joueurCourant).getMain(),modeleTriomino.getJoueurs().get(joueurCourant).isCpu());
						tableTriomino.setScoreJoueur(joueurCourant, jCourant.getScore());

					}
					if (jCourant.nePeutPasJouer(1, modeleTriomino)) {
						// S'il n'as pas encore piocher une bonne piece il
						// pioche une derniere fois
						// et 5 points de penalités sont retirés de son score

						System.out.println("joueur cpu " + joueurCourant + " ne peut pas jouer");
						jCourant.piocher(modeleTriomino.getDeck(), 1);
						jCourant.setScore(jCourant.getScore() - 5);
						tableTriomino.dessinerPiecesJoueur(joueurCourant,
								modeleTriomino.getJoueurs().get(joueurCourant).getMain(),modeleTriomino.getJoueurs().get(joueurCourant).isCpu());
						tableTriomino.setScoreJoueur(joueurCourant, jCourant.getScore());

					}
					if (jCourant.nePeutPasJouer(1, modeleTriomino)) {
						// Si le joueur ne peut pas jouer apres avoir piocher 3
						// fois il passe sont tour
						System.out.println("joueur cpu " + joueurCourant + " ne peut pas jouer");
						jCourant.setScore(jCourant.getScore() - 10);
						tableTriomino.dessinerPiecesJoueur(joueurCourant,
								modeleTriomino.getJoueurs().get(joueurCourant).getMain(),modeleTriomino.getJoueurs().get(joueurCourant).isCpu());

					} else {
						// Si le joueur peut jouer la methode coup de la classe
						// joueur choisi automatiquement la piece a jouer
						// d'apres la strategie choisie puisque c'est un cpu ,
						// on n'attend aucune action d'utilisateur
						pointJoue = jCourant.coup(0, null, 0, modeleTriomino, 0, 0);
						tableTriomino.getTable()[pointJoue.getX()][pointJoue.getY()].getPiece()
								.changerPiece(modeleTriomino.getTable()[pointJoue.getX()][pointJoue.getY()]);
						// On dessine apres la piece jouée dans la table

						tableTriomino.dessinerPiecesJoueur(joueurCourant,
								modeleTriomino.getJoueurs().get(joueurCourant).getMain(),modeleTriomino.getJoueurs().get(joueurCourant).isCpu());
						// On dessine les pieces dans la main restantes apres le
						// coup
						tableTriomino.setScoreJoueur(joueurCourant, jCourant.getScore());
						// FInalement on met a jouer le score dans la vue
					}
				} else {
					// Si le joueur est humain on va commancer par reinitialiser
					// le nombre de fois qu'il a piocher
					jCourant.setNombreDePioches(0);

					do {
						// On va attendre que le joueur pose une piece valide
						tableTriomino.resetChoix();
						tableTriomino.resetPieceChoisie();
						if (jCourant.nePeutPasJouer(jeu, modeleTriomino) && jCourant.getNombreDePioches() >= 3) {
							// S'il ne peut pa jouer et qu'il a activer le
							// listner du bouton piocher 3 fois on va sortir de
							// la boucle et passer a l'incrementation du joueur
							break;
						} else {
							// Si on arrive ici c'est que le joueur peut jouer
							System.out.println("c'est le tour de l'humain " + joueurCourant);
							// On va donc attendre qu'il choisisse une piece
							// dans sa main
							System.out.println("Attente du choix de la piece a jouer");
							while (tableTriomino.getChoixJoueur(joueurCourant) == -1
									|| tableTriomino.getChoixJoueur(joueurCourant) >= jCourant.getMain().size()
											&& (!jCourant.nePeutPasJouer(jeu, modeleTriomino)
													|| jCourant.getNombreDePioches() < 3)) {
								System.err.print("");
								tableTriomino.resetPieceChoisie();
								// S'il na pas jouer un coup valide on va
								// remettre a zero la piece choisie dans la
								// table pour ne pas le forcer a utiliser la
								// meme position dans ce meme tour
							}
							System.out.println("Le joueur a choisi la piece dont l'indice est "
									+ tableTriomino.getChoixJoueur(joueurCourant));

							ChoixRotTriomino choixRotTriomino = new ChoixRotTriomino(
									jCourant.getMain().get(tableTriomino.getChoixJoueur(joueurCourant)));
							System.out.println("Attente du choix de la rotation de la piece");
							// Une piece a 3 rotations possibles et pour chaque
							// rotation 2 directions
							// le joueur doit choisir une rotation , pour la
							// direction il est libre de la choisir ou pas car
							// s'il ne le fait pas l'application va le faire a
							// sa place
							while (choixRotTriomino.isClicked() == false
									&& (!jCourant.nePeutPasJouer(jeu, modeleTriomino)
											|| jCourant.getNombreDePioches() < 3)) {
								System.out.print("");
							}
							tableTriomino.resetPieceChoisie();
							System.out.println("choix de la rotation de la piece effectué");
							System.err.println("Attente choix de l'emplacement de la piece choisie");
							// Le joueur doit choisir la place de la piece dans
							// la table
							while (tableTriomino.getPieceChoisie().getX() == -1
									&& (!jCourant.nePeutPasJouer(jeu, modeleTriomino)
											|| jCourant.getNombreDePioches() < 3)) {
								System.out.print("");
							}
							Point point = tableTriomino.getPieceChoisie();
							pointJoue = jCourant.coup(jeu,
									jCourant.getMain().get(tableTriomino.getChoixJoueur(joueurCourant)),
									tableTriomino.getChoixJoueur(joueurCourant), modeleTriomino, point.getX(),
									point.getY());
							// La methode coup dans ce cas va prendre en compte
							// les parametres puisque le joueur est humain
						}

					} while (pointJoue.getX() == -1);
					if (pointJoue.getX() == -1) {
						// Si le joueur a passer son tour car il ne peut pas
						// jouer on ne fait rien
					} else {
						// Sionon on dessine la piece dans la table , on met a
						// jour la main dans la vue et le score
						System.out.println(tableTriomino.getTable()[pointJoue.getX()][pointJoue.getY()]);
						tableTriomino.getTable()[pointJoue.getX()][pointJoue.getY()].getPiece()
								.changerPiece(modeleTriomino.getTable()[pointJoue.getX()][pointJoue.getY()]);
						tableTriomino.setScoreJoueur(joueurCourant, jCourant.getScore());
						tableTriomino.dessinerPiecesJoueur(joueurCourant,
								modeleTriomino.getJoueurs().get(joueurCourant).getMain(),modeleTriomino.getJoueurs().get(joueurCourant).isCpu());

					}
					tableTriomino.resetChoix();
					tableTriomino.resetPieceChoisie();
					// Reinitialisation des variables de controle des choix pour
					// le prochain tour de boucle

				}
				// Le joueur humain a fini son tour ici

				// On passe au joueur suivant
				joueurCourant = (joueurCourant + 1) % nbJoueurs;
				jCourant = modeleTriomino.getJoueurs().get(joueurCourant);
				tableTriomino.setToken(joueurCourant);
				tableTriomino.setQuiPeutPiocher(joueurCourant, jCourant, modeleTriomino.getDeck());
				System.out.println("il reste dans le deck" + modeleTriomino.getDeck().size());
			} while (modeleTriomino.finPartie(1) == 5);
			System.out.println(modeleTriomino.finPartie(1) + "a fini");
			partieCommance = false;

			tableTriomino.exit();
			// Fermer l'interface de jeu triomino
			Dao dao = new Dao("database.xml");
			// On creer une instance de la classe de persistance de donnes
			dao.ajouterPartieTriomino(modeleTriomino.getJoueurs(), modeleTriomino.finPartie(jeu));
			// Ajouter les données de la partie a la base
			VueGagnant vueGangnant = new VueGagnant(jeu,
					modeleTriomino.getJoueurs().get(modeleTriomino.finPartie(jeu)));
			vueGangnant.getFrame().setVisible(true);
			// Affichage du gagnant
		}
		System.out.println("fin Controller.main");
		// Fin
	}

}
