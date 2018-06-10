package vue.triomino;
import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import modele.triomino.PieceTriomino;

public class tes {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					tes window = new tes();
					window.frame.setVisible(true);
				
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public tes() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		VuePieceTriomino piece=new VuePieceTriomino(new PieceTriomino(1, 1, 1), 0, 0, 1);
		VuePieceTriomino piece1=new VuePieceTriomino(new PieceTriomino(2, 2, 2), 25, 0, 0);
		VuePieceTriomino piece2=new VuePieceTriomino(new PieceTriomino(2, 2, 2), 50, 0, 1);
		piece.getPiece().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.out.println("3asba1");
			}
		});
		piece1.getPiece().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.out.println("3asba2");
			}
		});
		piece2.getPiece().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.out.println("3asba3");
			}
		});
		frame.getContentPane().setLayout(null);
		frame.getContentPane().add(piece);

		frame.getContentPane().add(piece1);

		frame.getContentPane().add(piece2);
	}
}
