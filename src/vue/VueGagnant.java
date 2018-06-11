package vue;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import modele.Joueur;

public class VueGagnant {

	private JFrame frame;
	private JLabel nomJoueur;



	
	public VueGagnant(int jeu,Joueur joueur) {
		initialize(jeu,joueur);
	}

	public JFrame getFrame() {
		return frame;
	}

	public void setFrame(JFrame frame) {
		this.frame = frame;
	}

	
	private void initialize(int jeu,Joueur joueur) {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblLeJoueur = new JLabel("Le joueur ");
		lblLeJoueur.setBounds(52, 83, 62, 16);
		frame.getContentPane().add(lblLeJoueur);
		
		nomJoueur = new JLabel(joueur.getNom());
		nomJoueur.setBounds(120, 83, 82, 16);
		frame.getContentPane().add(nomJoueur);
		JLabel lblAGang= new JLabel();
		if(jeu==0){

			lblAGang = new JLabel("a gangé");
		}else if(jeu==1){

			lblAGang = new JLabel("a gangé avec un score ");
		}
		lblAGang.setBounds(200, 83, 104, 16);
		frame.getContentPane().add(lblAGang);
		
		JButton btnQuittez = new JButton("Quittez");
		btnQuittez.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnQuittez.setBounds(68, 160, 117, 29);
		frame.getContentPane().add(btnQuittez);
		
		if(jeu==1){
			JLabel scoreJ = new JLabel(String.valueOf(joueur.getScore()));
			scoreJ.setBounds(340, 83, 75, 16);
			frame.getContentPane().add(scoreJ);
		}
	}
}
