package vue.domino;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.border.LineBorder;

import modele.domino.PieceDomino;

public class ChoixRotDomino {
	private int rot;
	private boolean centre;
	private JFrame frame;
	private boolean clicked;

	private int x;
	private int y;

	public boolean isClicked() {
		return clicked;
	}

	public void setClicked(boolean clicked) {
		this.clicked = clicked;
	}

	public ChoixRotDomino(PieceDomino piece) {
		initialize(piece);
	}

	
	private void initialize(PieceDomino piece) {
		JLabel label = new JLabel();
		JLabel label_1 = new JLabel();
		JLabel label_2 = new JLabel();
		PieceDomino pieceOr1 = new PieceDomino(piece.getX(), piece.getY());
		pieceOr1.setRot(0);
		PieceDomino pieceOr2 = new PieceDomino(piece.getX(), piece.getY());
		pieceOr2.setRot(1);
		PieceDomino pieceOr3 = new PieceDomino(piece.getY(), piece.getX());
		pieceOr3.setRot(0);
		PieceDomino pieceOr4 = new PieceDomino(piece.getY(), piece.getX());
		pieceOr4.setRot(1);
		frame = new JFrame();
		frame.setBounds(100, 100, 404, 229);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		JCheckBox chckbxCentre = new JCheckBox("Centrée");
		chckbxCentre.setBounds(19, 156, 80, 23);
		frame.getContentPane().add(chckbxCentre);
		JLabel lblVeuillezChoisirLorientation = new JLabel("Veuillez choisir l'orientation de la piece ");
		lblVeuillezChoisirLorientation.setBounds(19, 23, 325, 29);
		frame.getContentPane().add(lblVeuillezChoisirLorientation);
		PieceDomino p = new PieceDomino(piece.getX(), piece.getY());
		JLabel lblNewLabel = new JLabel();
		lblNewLabel.setBorder(new LineBorder(Color.RED));
		lblNewLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				lblNewLabel.setBorder(new LineBorder(Color.RED));
				label.setBorder(null);
				label_1.setBorder(null);
				label_2.setBorder(null);
				piece.setCentre(chckbxCentre.isSelected());
				piece.setX(pieceOr1.getX());
				piece.setY(pieceOr1.getY());
				piece.setRot(pieceOr1.getRot());
				setRot(0);

			}
		});

		lblNewLabel.setIcon(new ImageIcon(TableDomino.recuprerCheminPiece(pieceOr1)));
		lblNewLabel.setBounds(19, 64, 80, 80);
		frame.getContentPane().add(lblNewLabel);

		label.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				lblNewLabel.setBorder(null);
				label.setBorder(new LineBorder(Color.RED));
				label_1.setBorder(null);
				label_2.setBorder(null);
				piece.setCentre(chckbxCentre.isSelected());
				piece.setX(pieceOr2.getX());
				piece.setY(pieceOr2.getY());
				piece.setRot(pieceOr2.getRot());
				setRot(1);

			}
		});
		p.setRot(1);
		label.setIcon(new ImageIcon(TableDomino.recuprerCheminPiece(pieceOr2)));
		label.setBounds(111, 64, 80, 80);
		frame.getContentPane().add(label);
		if (p.getX() != p.getY()) {
			p.setRot(0);
			p.swipe();
			label_1.setIcon(new ImageIcon(TableDomino.recuprerCheminPiece(pieceOr3)));
			label_1.setBounds(203, 64, 80, 80);
			label_1.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					lblNewLabel.setBorder(null);
					label_1.setBorder(new LineBorder(Color.RED));
					label.setBorder(null);
					label_2.setBorder(null);
					piece.setCentre(chckbxCentre.isSelected());
					piece.setX(pieceOr3.getX());
					piece.setY(pieceOr3.getY());
					piece.setRot(0);
					setRot(0);

				}
			});
			frame.getContentPane().add(label_1);

			p.setRot(1);
			label_2.setIcon(new ImageIcon(TableDomino.recuprerCheminPiece(pieceOr4)));
			label_2.setBounds(302, 64, 80, 80);
			frame.getContentPane().add(label_2);
			label_2.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					lblNewLabel.setBorder(null);
					label_2.setBorder(new LineBorder(Color.RED));
					label_1.setBorder(null);
					label.setBorder(null);
					piece.setCentre(chckbxCentre.isSelected());
					piece.setX(pieceOr4.getX());
					piece.setY(pieceOr4.getY());
					piece.setRot(1);
					setRot(1);

				}
			});
		}

		JButton btnValider = new JButton("Valider");
		btnValider.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clicked = true;
				frame.setVisible(false);
				setX(piece.getX());
				setY(piece.getY());
				piece.setCentre(chckbxCentre.isSelected());
				setCentre(chckbxCentre.isSelected());

			}
		});
		btnValider.setBounds(121, 156, 117, 29);
		frame.getContentPane().add(btnValider);
		frame.setVisible(true);
	}

	public int getRot() {
		return rot;
	}

	public void setRot(int rot) {
		this.rot = rot;
	}

	public boolean isCentre() {
		return centre;
	}

	public void setCentre(boolean centre) {
		this.centre = centre;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

}
