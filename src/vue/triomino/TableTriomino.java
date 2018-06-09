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
import modele.triomino.ModeleTriomino;
import modele.triomino.PieceTriomino;

public class TableTriomino {
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
	public static int i;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TableTriomino window = new TableTriomino();
					
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

	public void dessinerPiecesJoueur(int nbJoueur, ArrayList<PieceTriomino> pieces) {
		int i;
		for (i = 0; i < pieces.size(); i++) {
			zonePiecesJoueurs[nbJoueur][i].setIcon(new ImageIcon(TableTriomino.recuprerCheminPiece(pieces.get(i),1)));

		}
		for (; i <= 6; i++) {
			zonePiecesJoueurs[nbJoueur][i].setIcon(null);
		}

	}

	public void setNomJoueur(int nbJoueur, String nom) {
		nomJoueurs[nbJoueur].setText(nom);
	}

	public void dessinerPiece(PieceTriomino piece, int matX, int matY) { // index dans la matrice
		JLabel p = new JLabel();
		int x=TableTriomino.convertIJtoXY(matX,matY).getX();
		int y=TableTriomino.convertIJtoXY(matX,matY).getY();
		p.setBounds(x, y, 50, 50);
		p.setIcon(new ImageIcon(TableTriomino.recuprerCheminPiece(piece, ModeleTriomino.getDirection(matX,matY ))));
		p.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				pieceChoisie = new Point(matX, matY);

			}
		});
		this.zonePieces.add(p);
		this.zonePieces.repaint();
	}

	public static Point convertIJtoXY(int i, int j) {
		
		return new Point(j*25,i*31);
	}
	public static Point convertXYtoIJ(int x, int y) {
		int i;
		int j;
		i=y%31;
		if(i%2==0){
			int auxX=x%75;
			int auxY=y%31;
			
		}
		
	return null;
}

	public static String recuprerCheminPiece(PieceTriomino pieceTriomino, int direction) {
		String dir=new String("");
		if(direction==1){dir="h";}
		else { dir="b"; }
		String res = new String();
		res = "/Users/s-man/Desktop/Triomino/";
		res += String.valueOf(pieceTriomino.getX()) + String.valueOf(pieceTriomino.getY()) + String.valueOf(pieceTriomino.getZ())+dir;
		res += ".bmp";
		return res;
	}

	/**
	 * @wbp.parser.entryPoint
	 */
	public TableTriomino() {
		zonesJoueurs = new JPanel[4];
		nomJoueurs = new JLabel[4];
		zonePiecesJoueurs = new JLabel[4][30];
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

		JButton j3p0 = new JButton();
		j3p0.setText("Piocher");
		j3p0.setBackground(Color.YELLOW);
		j3p0.setBounds(16, 34, 81, 45);
		panel.add(j3p0);

		JLabel j3p1 = new JLabel();
		j3p1.setBackground(Color.YELLOW);
		j3p1.setBounds(104, 34, 50, 50);
		panel.add(j3p1);
		int y=104;
		for(i=0;i<30;i++){ //labels joueur 3
			zonePiecesJoueurs[2][i] = new JLabel();
			zonePiecesJoueurs[2][i].setBounds(y, 25, 50, 50);
			//zonePiecesJoueurs[2][i].setIcon(new ImageIcon("/Users/s-man/Desktop/images/001.png"));
			panel.add(zonePiecesJoueurs[2][i]);
			y+=60;
			zonePiecesJoueurs[2][i].addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					resetChoix();
					setChoixJoueur(2, i);
				}
			});
		}
		
		
				tokenJ3 = new JLabel("Token");
				tokenJ3.setBounds(94, 0, 49, 26);
				panel.add(tokenJ3);
				
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

		JButton j1p0 = new JButton();
		j1p0.setText("Piocher");
		j1p0.setBackground(Color.YELLOW);
		j1p0.setBounds(16, 27, 81, 48);
		panel_1.add(j1p0);
		y=100;
		
		for(i=0;i<30;i++){ // labels joueur1
			zonePiecesJoueurs[0][i] = new JLabel();
			//zonePiecesJoueurs[0][i].setIcon(new ImageIcon("/Users/s-man/Desktop/images/001.png"));
			zonePiecesJoueurs[0][i].setBounds(y, 27, 50, 50);
		
			zonesJoueurs[0].add(zonePiecesJoueurs[0][i]);
			y+=60;
			zonePiecesJoueurs[0][i].addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					resetChoix();
					setChoixJoueur(0, i);
				}
			});
		}
	
		
		
				tokenJ1 = new JLabel("Token");
				tokenJ1.setBounds(105, 1, 49, 26);
				panel_1.add(tokenJ1);
				
		
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

		JButton j2p0 = new JButton();
		j2p0.setText("Piocher");

		j2p0.setBounds(6, 50, 67, 80);
		panel_2.add(j2p0);

		
		y=150;
		for(i=0;i<30;i++){ // labels joueur2
			zonePiecesJoueurs[1][i] = new JLabel();
			//zonePiecesJoueurs[1][i].setIcon(new ImageIcon("/Users/s-man/Desktop/images/001.png"));
			zonePiecesJoueurs[1][i].setBounds(10, y, 50, 50);
			panel_2.add(zonePiecesJoueurs[1][i]);
			y+=60;
			zonePiecesJoueurs[1][i].addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					resetChoix();
					setChoixJoueur(1, i);
				}
			});
		}
		
				tokenJ2 = new JLabel("Token");
				tokenJ2.setBounds(6, 26, 49, 26);
				panel_2.add(tokenJ2);
				
					
		
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

		JButton j4p0 = new JButton();
		j4p0.setText("Piocher");
		j4p0.setBounds(6, 55, 50, 80);
		panel_3.add(j4p0);

		
		y=150;
		for(i=0;i<30;i++){ // labels joueur 4
			zonePiecesJoueurs[3][i] = new JLabel();
			zonePiecesJoueurs[3][i].setBounds(10, y, 50, 50);
			//zonePiecesJoueurs[3][i].setIcon(new ImageIcon("/Users/s-man/Desktop/images/001.png"));
			panel_3.add(zonePiecesJoueurs[3][i]);
			y+=60;
			zonePiecesJoueurs[3][i].addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					resetChoix();
					setChoixJoueur(3, i);
				}
			});
		}
		
				tokenJ4 = new JLabel("Token");
				tokenJ4.setBounds(6, 28, 49, 26);
				panel_3.add(tokenJ4);
				

		zonePieces = new JPanel();
		
		JScrollPane js = new JScrollPane();

		js.setViewportView(zonePieces);
		
		zonePieces.setPreferredSize(new Dimension(Controller.tailleFenetreTriomino, 3503));
		js.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		js.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		js.setLocation(102, 101);
		js.setSize(598, 572);
		zonePieces.setBackground(Color.BLUE);
		zonePieces.setBounds(99, 49, 553, 530);
		zonePieces.setLayout(null);
		frame.getContentPane().add(js);

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
