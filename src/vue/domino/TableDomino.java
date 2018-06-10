package vue.domino;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

import controller.Controller;
import modele.Point;
import modele.domino.PieceDomino;

public class TableDomino {
	private JPanel[] zonesJoueurs;
	private JLabel[] nomJoueurs;
	private JFrame frame;
	private JPanel zonePieces;
	private JLabel zonePiecesJoueurs[][]; // zone des main de jr
	private int choixJoueur[];
	private JLabel tokenJ3;
	private JLabel tokenJ2;
	private JLabel tokenJ4;
	private JLabel tokenJ1;
	private Point pieceChoisie;
	private Point table[][];

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					// TableDomino window = new TableDomino();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public JPanel getZonePieces() {
		return zonePieces;
	}

	public void setZonePieces(JPanel zonePieces) {
		this.zonePieces = zonePieces;
	}

	/**
	 * Create the application.
	 */
	public void setToken(int i) {
		switch (i) {
		case 0:
			tokenJ1.setIcon(new ImageIcon("/Users/s-man/Desktop/images/jeton.png"));
			tokenJ2.setIcon(null);
			tokenJ3.setIcon(null);
			tokenJ4.setIcon(null);
			break;
		case 1:
			tokenJ2.setIcon(new ImageIcon("/Users/s-man/Desktop/images/jeton.png"));
			tokenJ1.setIcon(null);
			tokenJ3.setIcon(null);
			tokenJ4.setIcon(null);
			break;
		case 2:
			tokenJ3.setIcon(new ImageIcon("/Users/s-man/Desktop/images/jeton.png"));
			tokenJ2.setIcon(null);
			tokenJ1.setIcon(null);
			tokenJ4.setIcon(null);
			break;
		case 3:
			tokenJ4.setIcon(new ImageIcon("/Users/s-man/Desktop/images/jeton.png"));
			tokenJ2.setIcon(null);
			tokenJ3.setIcon(null);
			tokenJ1.setIcon(null);
			break;
		default:

			tokenJ1.setIcon(null);
			tokenJ2.setIcon(null);
			tokenJ3.setIcon(null);
			tokenJ4.setIcon(null);
			break;

		}
	}

	public void dessinerPiecesJoueur(int nbJoueur, ArrayList<PieceDomino> pieces) {
		int i;
		for (i = 0; i < pieces.size(); i++) {
			zonePiecesJoueurs[nbJoueur][i].setIcon(new ImageIcon(TableDomino.recuprerCheminPiece(pieces.get(i))));

		}
		for (; i <= 6; i++) {
			zonePiecesJoueurs[nbJoueur][i].setIcon(null);
		}

	}

	public void setNomJoueur(int nbJoueur, String nom) {
		nomJoueurs[nbJoueur].setText(nom);
	}

	public void dessinerPiece(PieceDomino piece, int x, int y, int matX, int matY) {
		JLabel p = new JLabel();
		int wx = 80;
		int wy = 80;
		if (piece.getRot() == 0)
			wy = 40;
		else
			wx = 40;
		p.setBounds(x, y, wx, wy);
		p.setIcon(new ImageIcon(recuprerCheminPiece(piece)));
		p.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				pieceChoisie = new Point(matX, matY);

			}
		});
		this.zonePieces.add(p);
		this.zonePieces.repaint();
	}

	public static String recuprerCheminPiece(PieceDomino piece) {
		String res = new String();
		res = "/Users/s-man/Desktop/images/";
		res += String.valueOf(piece.getRot()) + String.valueOf(piece.getX()) + String.valueOf(piece.getY());
		res += ".png";
		return res;
	}

	/**
	 * @wbp.parser.entryPoint
	 */
	public TableDomino() {
		zonesJoueurs = new JPanel[4];
		nomJoueurs = new JLabel[4];
		zonePiecesJoueurs = new JLabel[4][7];
		table = new Point[55][55];
		choixJoueur = new int[4];
		this.resetChoix();
		initialize();
		resetPieceChoisie();

	}

	public void resetPieceChoisie() {
		pieceChoisie = new Point(-1, -1);
	}

	public int getChoixJoueur(int nbJoueur) {
		return choixJoueur[nbJoueur];
	}

	public void setChoixJoueur(int nbJoueur, int choix) {
		choixJoueur[nbJoueur] = choix;
	}

	public void resetChoix() {
		for (int i = 0; i < 4; i++) {
			choixJoueur[i] = -1;
		}
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 805, 831);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		JPanel panel = new JPanel();
		zonesJoueurs[2] = panel;
		panel.setBackground(Color.GRAY);
		panel.setBounds(0, 0, 665, 137);
		frame.getContentPane().add(panel);
		panel.setLayout(null);

		JLabel lblNomJoueur = new JLabel("Nom Joueur 3");
		nomJoueurs[2] = lblNomJoueur;
		lblNomJoueur.setBounds(6, 6, 100, 16);
		panel.add(lblNomJoueur);

		JLabel j3p0 = new JLabel();
		j3p0.setBackground(Color.YELLOW);
		j3p0.setBounds(16, 34, 81, 80);
		panel.add(j3p0);

		JLabel j3p1 = new JLabel();
		j3p1.setBackground(Color.YELLOW);
		j3p1.setBounds(104, 34, 81, 80);
		panel.add(j3p1);

		JLabel j3p2 = new JLabel();
		j3p2.setBackground(Color.YELLOW);
		j3p2.setBounds(190, 34, 81, 80);
		panel.add(j3p2);

		JLabel j3p3 = new JLabel();
		j3p3.setBackground(Color.YELLOW);
		j3p3.setBounds(276, 34, 81, 80);
		panel.add(j3p3);

		JLabel j3p4 = new JLabel();
		j3p4.setBackground(Color.YELLOW);
		j3p4.setBounds(364, 34, 81, 80);
		panel.add(j3p4);

		JLabel j3p5 = new JLabel();
		j3p5.setBackground(Color.YELLOW);
		j3p5.setBounds(453, 34, 81, 80);
		panel.add(j3p5);

		JLabel j3p6 = new JLabel();
		j3p6.setBackground(Color.YELLOW);
		j3p6.setBounds(546, 34, 81, 80);
		panel.add(j3p6);

		JPanel panel_1 = new JPanel();
		zonesJoueurs[0] = panel_1;
		panel_1.setBackground(Color.GRAY);
		panel_1.setBounds(138, 649, 664, 137);
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		JLabel lblNomJoueur_1 = new JLabel("Nom Joueur 1");
		nomJoueurs[0] = lblNomJoueur_1;
		lblNomJoueur_1.setBounds(6, 6, 91, 16);
		panel_1.add(lblNomJoueur_1);

		JLabel j1p0 = new JLabel();
		j1p0.setBackground(Color.YELLOW);
		j1p0.setBounds(16, 27, 81, 80);
		panel_1.add(j1p0);

		JLabel j1p1 = new JLabel();
		j1p1.setBackground(Color.YELLOW);
		j1p1.setBounds(104, 27, 81, 80);
		panel_1.add(j1p1);

		JLabel j1p2 = new JLabel();
		j1p2.setBackground(Color.YELLOW);
		j1p2.setBounds(190, 27, 81, 80);
		panel_1.add(j1p2);

		JLabel j1p3 = new JLabel();
		j1p3.setBackground(Color.YELLOW);
		j1p3.setBounds(276, 27, 81, 80);
		panel_1.add(j1p3);

		JLabel j1p4 = new JLabel();
		j1p4.setBackground(Color.YELLOW);
		j1p4.setBounds(364, 27, 81, 80);
		panel_1.add(j1p4);

		JLabel j1p5 = new JLabel();
		j1p5.setBackground(Color.YELLOW);
		j1p5.setBounds(453, 27, 81, 80);
		panel_1.add(j1p5);

		JLabel j1p6 = new JLabel();
		j1p6.setBackground(Color.YELLOW);
		j1p6.setBounds(546, 27, 81, 80);
		panel_1.add(j1p6);

		JPanel panel_2 = new JPanel();
		zonesJoueurs[1] = panel_2;
		panel_2.setBackground(Color.DARK_GRAY);
		panel_2.setBounds(0, 137, 137, 649);
		frame.getContentPane().add(panel_2);
		panel_2.setLayout(null);
		JLabel lblNomJoueur_2 = new JLabel("Nom Joueur 2");
		nomJoueurs[1] = lblNomJoueur_2;
		lblNomJoueur_2.setBounds(6, 6, 88, 16);
		panel_2.add(lblNomJoueur_2);

		JLabel j2p0 = new JLabel();

		j2p0.setBounds(6, 31, 81, 80);
		panel_2.add(j2p0);

		JLabel j2p1 = new JLabel();

		j2p1.setBounds(6, 111, 81, 80);
		panel_2.add(j2p1);

		JLabel j2p2 = new JLabel();
		j2p2.setBackground(Color.YELLOW);
		j2p2.setBounds(6, 191, 81, 80);
		panel_2.add(j2p2);

		JLabel j2p3 = new JLabel();
		j2p3.setBackground(Color.YELLOW);
		j2p3.setBounds(6, 271, 81, 80);
		panel_2.add(j2p3);

		JLabel j2p4 = new JLabel();
		j2p4.setBackground(Color.YELLOW);
		j2p4.setBounds(6, 351, 81, 80);
		panel_2.add(j2p4);

		JLabel j2p5 = new JLabel();
		j2p5.setBounds(6, 431, 81, 80);
		panel_2.add(j2p5);

		JLabel j2p6 = new JLabel();
		j2p6.setBounds(6, 513, 81, 80);
		panel_2.add(j2p6);

		JPanel panel_3 = new JPanel();
		zonesJoueurs[3] = panel_3;
		panel_3.setBackground(Color.DARK_GRAY);
		panel_3.setBounds(665, 0, 137, 650);
		frame.getContentPane().add(panel_3);
		panel_3.setLayout(null);

		JLabel lblNomJoueur_3 = new JLabel("Nom Joueur4");
		nomJoueurs[3] = lblNomJoueur_3;
		lblNomJoueur_3.setBounds(6, 6, 88, 16);
		panel_3.add(lblNomJoueur_3);

		JLabel j4p0 = new JLabel();
		j4p0.setBounds(6, 33, 81, 80);
		panel_3.add(j4p0);

		JLabel j4p1 = new JLabel();

		j4p1.setBounds(6, 113, 81, 80);
		panel_3.add(j4p1);

		JLabel j4p2 = new JLabel();
		j4p2.setBackground(Color.YELLOW);
		j4p2.setBounds(6, 193, 81, 80);
		panel_3.add(j4p2);

		JLabel j4p3 = new JLabel();
		j4p3.setBackground(Color.YELLOW);
		j4p3.setBounds(6, 273, 81, 80);
		panel_3.add(j4p3);

		JLabel j4p4 = new JLabel();
		j4p4.setBackground(Color.YELLOW);
		j4p4.setBounds(6, 353, 81, 80);
		panel_3.add(j4p4);

		JLabel j4p5 = new JLabel();
		j4p5.setBackground(Color.YELLOW);
		j4p5.setBounds(6, 433, 81, 80);
		panel_3.add(j4p5);

		JLabel j4p6 = new JLabel();
		j4p6.setBackground(Color.YELLOW);
		j4p6.setBounds(6, 515, 81, 80);
		panel_3.add(j4p6);

		zonePieces = new JPanel();
		
		JScrollPane js = new JScrollPane();

		js.setViewportView(zonePieces);
		
		zonePieces.setPreferredSize(new Dimension(Controller.tailleFenetre, Controller.tailleFenetre));
		js.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		js.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		js.setLocation(134, 137);
		js.setSize(531, 514);
		zonePieces.setBackground(Color.BLUE);
		zonePieces.setBounds(99, 49, 553, 530);
		zonePieces.setLayout(null);
		frame.getContentPane().add(js);
		zonePiecesJoueurs[0][0] = j1p0;
		zonePiecesJoueurs[0][1] = j1p1;
		zonePiecesJoueurs[0][2] = j1p2;
		zonePiecesJoueurs[0][3] = j1p3;
		zonePiecesJoueurs[0][4] = j1p4;
		zonePiecesJoueurs[0][5] = j1p5;
		zonePiecesJoueurs[0][6] = j1p6;

		tokenJ1 = new JLabel("Token");
		tokenJ1.setBounds(105, 1, 49, 26);
		panel_1.add(tokenJ1);
		zonePiecesJoueurs[1][0] = j2p0;
		zonePiecesJoueurs[1][1] = j2p1;
		zonePiecesJoueurs[1][2] = j2p2;
		zonePiecesJoueurs[1][3] = j2p3;
		zonePiecesJoueurs[1][4] = j2p4;
		zonePiecesJoueurs[1][5] = j2p5;
		zonePiecesJoueurs[1][6] = j2p6;

		tokenJ2 = new JLabel("Token");
		tokenJ2.setBounds(88, 0, 49, 26);
		panel_2.add(tokenJ2);
		zonePiecesJoueurs[2][0] = j3p0;
		zonePiecesJoueurs[2][1] = j3p1;
		zonePiecesJoueurs[2][2] = j3p2;
		zonePiecesJoueurs[2][3] = j3p3;
		zonePiecesJoueurs[2][4] = j3p4;
		zonePiecesJoueurs[2][5] = j3p5;
		zonePiecesJoueurs[2][6] = j3p6;

		tokenJ3 = new JLabel("Token");
		tokenJ3.setBounds(94, 0, 49, 26);
		panel.add(tokenJ3);
		zonePiecesJoueurs[3][0] = j4p0;
		zonePiecesJoueurs[3][1] = j4p1;
		zonePiecesJoueurs[3][2] = j4p2;
		zonePiecesJoueurs[3][3] = j4p3;
		zonePiecesJoueurs[3][4] = j4p4;
		zonePiecesJoueurs[3][5] = j4p5;
		zonePiecesJoueurs[3][6] = j4p6;

		tokenJ4 = new JLabel("Token");
		tokenJ4.setBounds(88, 0, 49, 26);
		panel_3.add(tokenJ4);
		j1p0.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				resetChoix();
				setChoixJoueur(0, 0);
			}
		});
		j1p1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				resetChoix();
				setChoixJoueur(0, 1);
			}
		});
		j1p2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				resetChoix();
				setChoixJoueur(0, 2);
			}
		});
		j1p3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				resetChoix();
				setChoixJoueur(0, 3);
			}
		});
		j1p4.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				resetChoix();
				setChoixJoueur(0, 4);
			}
		});
		j1p5.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				resetChoix();
				setChoixJoueur(0, 5);
			}
		});
		j1p6.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				resetChoix();
				setChoixJoueur(0, 6);
			}
		});
		j2p0.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				resetChoix();
				setChoixJoueur(1, 0);
			}
		});

		j2p1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				resetChoix();
				setChoixJoueur(1, 1);
			}
		});

		j2p2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				resetChoix();
				setChoixJoueur(1, 2);
			}
		});

		j2p3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				resetChoix();
				setChoixJoueur(1, 3);
			}
		});

		j2p4.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				resetChoix();
				setChoixJoueur(1, 4);
			}
		});

		j2p5.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				resetChoix();
				setChoixJoueur(1, 5);
			}
		});

		j2p6.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				resetChoix();
				setChoixJoueur(1, 6);
			}
		});

		j3p0.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				resetChoix();
				setChoixJoueur(2, 0);
			}
		});

		j3p1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				resetChoix();
				setChoixJoueur(2, 1);
			}
		});
		j3p2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				resetChoix();
				setChoixJoueur(2, 2);
			}
		});
		j3p3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				resetChoix();
				setChoixJoueur(2, 3);
			}
		});
		j3p4.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				resetChoix();
				setChoixJoueur(2, 4);
			}
		});
		j3p5.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				resetChoix();
				setChoixJoueur(2, 5);
			}
		});
		j3p6.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				resetChoix();
				setChoixJoueur(2, 6);
			}
		});
		j4p0.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				resetChoix();
				setChoixJoueur(3, 0);
			}
		});
		j4p1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				resetChoix();
				setChoixJoueur(3, 1);
			}
		});

		j4p2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				resetChoix();
				setChoixJoueur(3, 2);
			}
		});

		j4p3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				resetChoix();
				setChoixJoueur(3, 3);
			}
		});

		j4p4.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				resetChoix();
				setChoixJoueur(3, 4);
			}
		});

		j4p5.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				resetChoix();
				setChoixJoueur(3, 5);
			}
		});

		j4p6.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				resetChoix();
				setChoixJoueur(3, 6);
			}
		});

		frame.setVisible(true);
		// dessinerPiece(new PieceDomino(1,2), 0, 0);
		// dessinerPiece(new PieceDomino(2,3), 80, 0);
		// dessinerPiecesJoueur(0, new ArrayList<PieceDomino>());
	}

	public Point getPieceChoisie() {
		return pieceChoisie;
	}

	public void setPieceChoisie(Point pieceChoisie) {
		this.pieceChoisie = pieceChoisie;
	}

	public Point[][] getTable() {
		return table;
	}

	public void setTable(Point table[][]) {
		this.table = table;
	}

	 

}
