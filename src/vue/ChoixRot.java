package vue;

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

import modele.PieceDomino;

public class ChoixRot {
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

	public ChoixRot(PieceDomino piece) {
		initialize(piece);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(PieceDomino piece) {
		JLabel label = new JLabel();
		JLabel label_1 = new JLabel();
		JLabel label_2 = new JLabel();
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
				piece.setX(p.getX());
				piece.setX(p.getY());
				piece.setRot(0);
				setRot(0);
				
			}
		});

		lblNewLabel.setIcon(new ImageIcon(TableDomino.recuprerCheminPiece(p)));
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
				piece.setX(p.getX());
				piece.setX(p.getY());
				piece.setRot(1);
				setRot(1);

			}
		});
		p.setRot(1);
		label.setIcon(new ImageIcon(TableDomino.recuprerCheminPiece(p)));
		label.setBounds(111, 64, 80, 80);
		frame.getContentPane().add(label);
		if (p.getX() != p.getY()) {
			p.setRot(0);
			p.swipe();
			label_1.setIcon(new ImageIcon(TableDomino.recuprerCheminPiece(p)));
			label_1.setBounds(203, 64, 80, 80);
			label_1.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					lblNewLabel.setBorder(null);
					label_1.setBorder(new LineBorder(Color.RED));
					label.setBorder(null);
					label_2.setBorder(null);
					piece.setCentre(chckbxCentre.isSelected());
					piece.setX(p.getX());
					piece.setY(p.getY());
					piece.setRot(0);
					setRot(0);

				}
			});
			frame.getContentPane().add(label_1);

			p.setRot(1);
			label_2.setIcon(new ImageIcon(TableDomino.recuprerCheminPiece(p)));
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
					piece.setX(p.getX());
					piece.setX(p.getY());
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
