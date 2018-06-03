package vue;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ChoixJeu {

	private JFrame frame;
	private JButton btnDomino;
	private JButton btnTriomino;
	private int choix;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ChoixJeu window = new ChoixJeu();
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ChoixJeu() {
		initialize();
		setChoix(-1);
	}

	/**
	 * Initialize the contents of the frame.
	 */
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
