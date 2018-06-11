package vue;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import modele.domino.JoueurDomino;

public class ChoixJoueurs {

	private JFrame frame;
	private JTextField txtPlayer1;
	private JTextField txtPlayer2;
	private JTextField txtPlayer3;
	private JTextField txtPlayer4;
	private JButton btnValiderChoix;
	private JCheckBox chckbxHumainP4;
	private JCheckBox chckbxHumainP3;
	private JCheckBox chckbxHumainP2;
	private JCheckBox chckbxHumainP1;
	private ArrayList<JoueurDomino> joueurs;

	public ChoixJoueurs(int jeu) {
		joueurs = new ArrayList<JoueurDomino>();
		initialize(jeu);
	}

	public ArrayList<JoueurDomino> getJoueurs() {
		return joueurs;
	}

	public void setJoueurs(ArrayList<JoueurDomino> joueurs) {
		this.joueurs = joueurs;
	}

	public void exit() {
		this.frame.dispose();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(int jeu) {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JLabel lblJoueur = new JLabel("Nom joueur 1 :");
		lblJoueur.setBounds(18, 38, 94, 16);
		frame.getContentPane().add(lblJoueur);

		txtPlayer1 = new JTextField();
		txtPlayer1.setText("Player 1");
		txtPlayer1.setToolTipText("");
		txtPlayer1.setBounds(124, 33, 130, 26);
		frame.getContentPane().add(txtPlayer1);
		txtPlayer1.setColumns(10);

		chckbxHumainP1 = new JCheckBox("Humain");
		chckbxHumainP1.setEnabled(false);
		chckbxHumainP1.setSelected(true);
		chckbxHumainP1.setBounds(259, 34, 81, 23);
		frame.getContentPane().add(chckbxHumainP1);

		JLabel lblNomJoueur = new JLabel("Nom joueur 2 :");
		lblNomJoueur.setBounds(18, 76, 94, 16);
		frame.getContentPane().add(lblNomJoueur);

		txtPlayer2 = new JTextField();
		txtPlayer2.setToolTipText("");
		txtPlayer2.setText("Player 2");
		txtPlayer2.setColumns(10);
		txtPlayer2.setBounds(124, 71, 130, 26);
		frame.getContentPane().add(txtPlayer2);

		chckbxHumainP2 = new JCheckBox("Humain");
		chckbxHumainP2.setBounds(259, 72, 81, 23);
		frame.getContentPane().add(chckbxHumainP2);

		JLabel lblNomJoueur_1 = new JLabel("Nom joueur 3 :");
		lblNomJoueur_1.setBounds(18, 109, 94, 16);
		frame.getContentPane().add(lblNomJoueur_1);

		txtPlayer3 = new JTextField();
		txtPlayer3.setToolTipText("");
		txtPlayer3.setText("Player 3");
		txtPlayer3.setColumns(10);
		txtPlayer3.setBounds(124, 104, 130, 26);
		frame.getContentPane().add(txtPlayer3);

		chckbxHumainP3 = new JCheckBox("Humain");
		chckbxHumainP3.setBounds(259, 105, 81, 23);
		frame.getContentPane().add(chckbxHumainP3);

		JLabel lblNomJoueur_2 = new JLabel("Nom joueur 4 :");
		lblNomJoueur_2.setBounds(18, 146, 94, 16);
		frame.getContentPane().add(lblNomJoueur_2);

		txtPlayer4 = new JTextField();
		txtPlayer4.setToolTipText("");
		txtPlayer4.setText("Player 4");
		txtPlayer4.setColumns(10);
		txtPlayer4.setBounds(124, 141, 130, 26);
		frame.getContentPane().add(txtPlayer4);

		chckbxHumainP4 = new JCheckBox("Humain");
		chckbxHumainP4.setBounds(259, 142, 81, 23);
		frame.getContentPane().add(chckbxHumainP4);

		btnValiderChoix = new JButton("Valider choix");

		btnValiderChoix.setBounds(148, 191, 117, 29);
		frame.getContentPane().add(btnValiderChoix);

		JLabel lblNewLabel = new JLabel("Choisir le nombre de joueurs et les joueurs humains :");
		lblNewLabel.setBounds(18, 6, 392, 16);
		frame.getContentPane().add(lblNewLabel);

		JCheckBox chckbxDesactiverP3 = new JCheckBox("Desactiver");
		chckbxDesactiverP3.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (chckbxDesactiverP3.isSelected()) {
					txtPlayer3.setText("");
					txtPlayer3.setEnabled(false);
					chckbxHumainP3.setEnabled(false);
				} else {
					txtPlayer3.setText("Player 3");
					txtPlayer3.setEnabled(true);
					chckbxHumainP3.setEnabled(true);
				}
			}
		});
		chckbxDesactiverP3.setBounds(342, 105, 102, 23);
		frame.getContentPane().add(chckbxDesactiverP3);

		JCheckBox chckbxDesactiverP4 = new JCheckBox("Desactiver");
		chckbxDesactiverP4.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (chckbxDesactiverP4.isSelected()) {
					txtPlayer4.setText("");
					txtPlayer4.setEnabled(false);
					chckbxHumainP4.setEnabled(false);
				} else {
					txtPlayer4.setText("Player 4");
					txtPlayer4.setEnabled(true);
					chckbxHumainP4.setEnabled(true);
				}
			}
		});
		chckbxDesactiverP4.setBounds(342, 142, 102, 23);
		frame.getContentPane().add(chckbxDesactiverP4);
		btnValiderChoix.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				joueurs.add(new JoueurDomino(jeu, txtPlayer1.getText(), false));
				joueurs.add(new JoueurDomino(jeu, txtPlayer2.getText(), !chckbxHumainP2.isSelected()));
				if (!chckbxDesactiverP3.isSelected()) {
					joueurs.add(new JoueurDomino(jeu, txtPlayer3.getText(), !chckbxHumainP3.isSelected()));
				}
				if (!chckbxDesactiverP4.isSelected()) {
					joueurs.add(new JoueurDomino(jeu, txtPlayer4.getText(), !chckbxHumainP4.isSelected()));
				}
				frame.dispose();
			}
		});
		frame.setVisible(true);
	}
}
