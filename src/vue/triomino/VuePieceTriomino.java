package vue.triomino;
import java.awt.GridLayout;

import javax.swing.JPanel;

import modele.triomino.PieceTriomino;

public class VuePieceTriomino extends JPanel {

	private static final long serialVersionUID = 1L;
	LabelPieceTriomino piece;

	public LabelPieceTriomino getPiece() {
		return piece;
	}

	public void setPiece(LabelPieceTriomino piece) {
		this.piece = piece;
	}

	public VuePieceTriomino(PieceTriomino piece, int posX, int posY, int direction) {
		super();
		this.setOpaque(false);
		this.setLayout(new GridLayout(1, 1));
		this.piece = new LabelPieceTriomino(direction, 0, 0, piece);
		this.setBounds(posX, posY, 50, 32);
		this.add(this.piece);
	}
}
