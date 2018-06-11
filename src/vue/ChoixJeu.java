package vue;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class ChoixJeu {

	private JFrame frame;
	private JButton btnDomino;
	private JButton btnTriomino;
	private int choix;
	
	public ChoixJeu() {
		initialize();
		setChoix(-1);
	}

	public void exit(){
		this.frame.dispose();
	}
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblVeuillezChoisirLe = new JLabel("Veuillez choisir le jeu");
		lblVeuillezChoisirLe.setBounds(125, 55, 222, 16);
		frame.getContentPane().add(lblVeuillezChoisirLe);
		
		btnDomino = new JButton("Domino");
		btnDomino.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				choix=0;
			}
		});
		btnDomino.setBounds(119, 83, 183, 48);
		frame.getContentPane().add(btnDomino);
		
		btnTriomino = new JButton("Triomino");
		btnTriomino.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				choix=1;
			}
		});
		btnTriomino.setBounds(119, 143, 183, 56);
		frame.getContentPane().add(btnTriomino);
		frame.setVisible(true);
	}

	public int getChoix() {
		return choix;
	}

	public void setChoix(int choix) {
		this.choix = choix;
	}

}
