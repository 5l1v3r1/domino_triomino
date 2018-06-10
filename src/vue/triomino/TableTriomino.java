package vue.triomino;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

import controller.Controller;
import modele.Point;
import modele.triomino.JoueurTriomino;
import modele.triomino.ModeleTriomino;
import modele.triomino.PieceTriomino;

public class TableTriomino {
	private JPanel[] zonesJoueurs;
	private JLabel[] nomJoueurs;
	private JFrame frame;
	
	private JPanel zonePieces;
	private VuePieceTriomino zonePiecesJoueurs[][]; // zone des main de jr
	private int choixJoueur[];
	private JLabel tokenJ3;
	private JLabel tokenJ2;
	private JLabel tokenJ4;
	private JLabel tokenJ1;
	private Point pieceChoisie;
	private VuePieceTriomino table[][];

	private JButton j4p0;
	private JButton j1p0;
	private JButton j2p0;
	private JButton j3p0;
	private JLabel scoreJ3;
	private JLabel scoreJ2;
	private JLabel scoreJ4;
	private JLabel scoreJ1;

	
	
	public JFrame getFrame() {
		return frame;
	}

	public void setFrame(JFrame frame) {
		this.frame = frame;
	}
	public void setScoreJoueur(int numJoueur,int score){
		switch (numJoueur) {
		case 0:
			scoreJ1.setText(String.valueOf(score));
			break;
		case 1:
			scoreJ2.setText(String.valueOf(score));
			break;
		case 2:
			scoreJ3.setText(String.valueOf(score));
			break;
		case 3:
			scoreJ4.setText(String.valueOf(score));
			break;
		default:
			break;
		}
	}
	public JButton getBoutonPiocher(int indexJoueur) {
		switch (indexJoueur) {
		case 0:
			return j1p0;

		case 1:
			return j2p0;

		case 2:
			return j3p0;

		case 3:
			return j4p0;

		default:
			return null;

		}
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TableTriomino window = new TableTriomino();
					window.getTable();
					window.getFrame().setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public void setQuiPeutPiocher(int indexJoueur, JoueurTriomino joueur, ArrayList<PieceTriomino> deck) { // 5
																											// pour
																											// tout
																											// desactiver
		j1p0.setEnabled(false);
		j2p0.setEnabled(false);
		j3p0.setEnabled(false);
		j4p0.setEnabled(false);
		if (!joueur.isCpu() && !(deck.size() == 0)) {
			switch (indexJoueur) {
			case 0:
				j1p0.setEnabled(true);
				break;
			case 1:
				j2p0.setEnabled(true);
				break;
			case 2:
				j3p0.setEnabled(true);
				break;
			case 3:
				j4p0.setEnabled(true);
				break;
			default:
				break;
			}
		}

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

	public void dessinerPiecesJoueur(int nbJoueur, ArrayList<PieceTriomino> pieces) {
		int i;
		for (i = 0; i < pieces.size(); i++) {
			zonePiecesJoueurs[nbJoueur][i].getPiece().changerPiece(pieces.get(i));

		}
		for (; i <= 29; i++) {
			// jcp aleh hatitha 6 mch 29 alowl ken amlet bug taw nsala7ha
			zonePiecesJoueurs[nbJoueur][i].getPiece().changerPiece(new PieceTriomino(7, 0, 0));
		}

	}

	public void setNomJoueur(int nbJoueur, String nom) {
		nomJoueurs[nbJoueur].setText(nom);
	}

	public void dessinerPiece(PieceTriomino piece, int matX, int matY) {
		// index dans la matrice

		int x = TableTriomino.convertIJtoXY(matX, matY).getX();
		int y = TableTriomino.convertIJtoXY(matX, matY).getY();
		VuePieceTriomino p = new VuePieceTriomino(piece, x, y, ModeleTriomino.getDirection(matX, matY));

		this.zonePieces.add(p);
		this.zonePieces.repaint();
	}

//	public void dessinerPlacesExtremites(ArrayList<Point> ext) {
//
//		for (Point p : ext) {
//			this.getTable()[p.getX()][p.getY()].getPiece().changerPiece(new PieceTriomino(7, 0, 0));
//				
//			}
//		}
//	

	public static Point convertIJtoXY(int i, int j) {

		return new Point(j * 25, i * 31);
	}

	/**
	 * @wbp.parser.entryPoint
	 */
	public TableTriomino() {
		zonesJoueurs = new JPanel[4];
		nomJoueurs = new JLabel[4];
		zonePiecesJoueurs = new VuePieceTriomino[4][30];
		table = new VuePieceTriomino[113][113];
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

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(0, 0, 700, 104);
		frame.getContentPane().add(scrollPane);
		JPanel panel = new JPanel();
		panel.setPreferredSize(new Dimension(1000, 104));

		scrollPane.setViewportView(panel);
		zonesJoueurs[2] = panel;
		panel.setBackground(Color.GRAY);
		panel.setLayout(null);

		JLabel lblNomJoueur = new JLabel("Nom Joueur 3");
		nomJoueurs[2] = lblNomJoueur;
		lblNomJoueur.setBounds(6, 6, 100, 16);
		panel.add(lblNomJoueur);

		j3p0 = new JButton();
		j3p0.setText("Piocher");
		j3p0.setBackground(Color.YELLOW);
		j3p0.setBounds(16, 34, 81, 45);
		panel.add(j3p0);

		JLabel j3p1 = new JLabel();
		j3p1.setBackground(Color.YELLOW);
		j3p1.setBounds(104, 34, 50, 50);
		panel.add(j3p1);

		for (int i = 0; i < 30; i++) {
			dessinerLabelPieceMainH(2, i, panel, 104);
		}

		tokenJ3 = new JLabel("Token");
		tokenJ3.setBounds(94, 0, 49, 26);
		panel.add(tokenJ3);
		
		scoreJ3 = new JLabel("0");
		scoreJ3.setBounds(145, 6, 61, 16);
		panel.add(scoreJ3);

		j3p1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				resetChoix();

				setChoixJoueur(2, 1);
			}
		});

		JScrollPane scrollPane_3 = new JScrollPane();
		scrollPane_3.setBounds(102, 673, 700, 99);
		frame.getContentPane().add(scrollPane_3);

		JPanel panel_1 = new JPanel();//
		panel_1.setPreferredSize(new Dimension(1000, 80));
		scrollPane_3.setViewportView(panel_1);
		zonesJoueurs[0] = panel_1;
		panel_1.setBackground(Color.GRAY);
		panel_1.setLayout(null);
		JLabel lblNomJoueur_1 = new JLabel("Nom Joueur 1");
		nomJoueurs[0] = lblNomJoueur_1;
		lblNomJoueur_1.setBounds(6, 6, 91, 16);
		panel_1.add(lblNomJoueur_1);

		j1p0 = new JButton();
		j1p0.setText("Piocher");
		j1p0.setBackground(Color.YELLOW);
		j1p0.setBounds(16, 27, 81, 48);
		panel_1.add(j1p0);

		for (int i = 0; i < 30; i++) {
			dessinerLabelPieceMainH(0, i, zonesJoueurs[0], 100);
		}

		tokenJ1 = new JLabel("Token");
		tokenJ1.setBounds(105, 1, 49, 26);
		panel_1.add(tokenJ1);
		
		scoreJ1 = new JLabel("0");
		scoreJ1.setBounds(154, 6, 61, 16);
		panel_1.add(scoreJ1);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane_1.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane_1.setBounds(0, 102, 102, 670);
		frame.getContentPane().add(scrollPane_1);

		JPanel panel_2 = new JPanel();
		scrollPane_1.setViewportView(panel_2);
		panel_2.setPreferredSize(new Dimension(102, 1000));
		zonesJoueurs[1] = panel_2;
		panel_2.setBackground(Color.DARK_GRAY);
		panel_2.setLayout(null);
		JLabel lblNomJoueur_2 = new JLabel("Nom Joueur 2");
		nomJoueurs[1] = lblNomJoueur_2;
		lblNomJoueur_2.setBounds(6, 6, 88, 16);
		panel_2.add(lblNomJoueur_2);

		j2p0 = new JButton();
		j2p0.setText("Piocher");

		j2p0.setBounds(6, 64, 67, 80);
		panel_2.add(j2p0);

		for (int i = 0; i < 30; i++) {

			dessinerLabelPieceMainV(1, i, panel_2, 150);
		}

		tokenJ2 = new JLabel("Token");
		tokenJ2.setBounds(6, 26, 49, 26);
		panel_2.add(tokenJ2);
		
		scoreJ2 = new JLabel("0");
		scoreJ2.setBounds(6, 47, 61, 16);
		panel_2.add(scoreJ2);

		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane_2.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane_2.setBounds(699, 0, 103, 675);
		frame.getContentPane().add(scrollPane_2);

		JPanel panel_3 = new JPanel();
		panel_3.setPreferredSize(new Dimension(103, 1000));
		scrollPane_2.setViewportView(panel_3);
		zonesJoueurs[3] = panel_3;
		panel_3.setBackground(Color.DARK_GRAY);
		panel_3.setLayout(null);

		JLabel lblNomJoueur_3 = new JLabel("Nom Joueur4");
		nomJoueurs[3] = lblNomJoueur_3;
		lblNomJoueur_3.setBounds(6, 6, 88, 16);
		panel_3.add(lblNomJoueur_3);

		j4p0 = new JButton();
		j4p0.setText("Piocher");
		j4p0.setBounds(6, 69, 67, 80);
		panel_3.add(j4p0);

		for (int i = 0; i < 30; i++) {

			dessinerLabelPieceMainV(3, i, panel_3, 150);
		}

		tokenJ4 = new JLabel("Token");
		tokenJ4.setBounds(6, 28, 49, 26);
		panel_3.add(tokenJ4);
		
		scoreJ4 = new JLabel("0");
		scoreJ4.setBounds(6, 52, 61, 16);
		panel_3.add(scoreJ4);

		zonePieces = new JPanel();

		JScrollPane js = new JScrollPane();

		js.setViewportView(zonePieces);

		zonePieces.setPreferredSize(new Dimension(Controller.tailleFenetreTriomino / 2, 3503));
		js.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		js.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		js.setLocation(102, 101);
		js.setSize(598, 572);
		zonePieces.setBackground(Color.BLUE);
		zonePieces.setBounds(99, 49, 553, 530);
		zonePieces.setLayout(null);
		frame.getContentPane().add(js);

	
		for(int i=0;i<113;i++){
			for(int j=0;j<113;j++){
			
				dessinerPieceTerrain(table,i,j);
			}
		}
		
	}

	private void dessinerPieceTerrain(VuePieceTriomino[][] table, int i, int j) {
		int x=TableTriomino.convertIJtoXY(i, j).getX();
		int y=TableTriomino.convertIJtoXY(i, j).getY();
		table[i][j]=new VuePieceTriomino(new PieceTriomino(9, 0, 0), x, y, ModeleTriomino.getDirection(i, j));
		table[i][j].getPiece().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				setPieceChoisie(new Point(i, j));
			
			}
		});
		
		zonePieces.add(table[i][j]);
		
	}

	public Point getPieceChoisie() {
		return pieceChoisie;
	}

	public void setPieceChoisie(Point pieceChoisie) {
		this.pieceChoisie = pieceChoisie;
	}

	public VuePieceTriomino[][] getTable() {
		return table;
	}

	public void setTable(VuePieceTriomino table[][]) {
		this.table = table;
	}

	public void dessinerLabelPieceMainH(int joueur, int index, JPanel panel, int commancement) {

		int y = commancement + 60 * index;
		zonePiecesJoueurs[joueur][index] = new VuePieceTriomino(new PieceTriomino(7, 0, 0), y, 25, 1);
		panel.add(zonePiecesJoueurs[joueur][index]);
		zonePiecesJoueurs[joueur][index].addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				resetChoix();
				setChoixJoueur(joueur, index);
			}
		});
	}

	public void dessinerLabelPieceMainV(int joueur, int index, JPanel panel, int commancement) {
		int y = commancement + 60 * index;
		zonePiecesJoueurs[joueur][index] = new VuePieceTriomino(new PieceTriomino(7, 0, 0), 10, y, 1);
		panel.add(zonePiecesJoueurs[joueur][index]);
		zonePiecesJoueurs[joueur][index].addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				resetChoix();
				setChoixJoueur(joueur, index);
			}
		});
	}

}
