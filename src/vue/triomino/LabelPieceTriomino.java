package vue.triomino;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.Shape;

import javax.swing.JLabel;

import modele.triomino.PieceTriomino;

public class LabelPieceTriomino extends JLabel {
	private int direction;
	private int px;
	private int py;
	private int pz;
	private int posX;
	private int posY;
	private static final long serialVersionUID = 1L;
	private Shape forme;

	public void changerPiece(PieceTriomino piece) {
		this.px = piece.getX();
		this.py = piece.getY();
		this.pz = piece.getZ();
		this.repaint();
	}

	public LabelPieceTriomino(int direction, int posX, int posY, PieceTriomino piece) {
		// posX
		// et
		// posY
		// sont le
		// centre du
		// triangle
		// (
		// bas
		// ou haut
		// )
		// 1
		// si le
		// sommet
		// est
		// en
		// haut 0
		// si
		// en
		// bas
		super();
		this.posX = posX;
		this.posY = posY;
		this.direction = direction;
		this.px = piece.getX();
		this.py = piece.getY();
		this.pz = piece.getZ();
		Polygon forme = new Polygon();
		if (this.direction == 1) {
			forme.addPoint(0 + posX, 31 + posY);
			forme.addPoint(25 + posX, 0 + posY); // sommet
			forme.addPoint(50 + posX, 31 + posY);
		} else {
			forme.addPoint(0 + posX, 0 + posY);
			forme.addPoint(25 + posX, 31 + posY); // sommet
			forme.addPoint(50 + posX, 0 + posY);
		}
		this.forme = forme;
		this.setVisible(true);

	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		if (this.px == 7) {// piece vide
			((Graphics2D) g).setColor(Color.WHITE);
			((Graphics2D) g).draw(forme);
		} else if (this.px == 8) {
			// piece retournée
			((Graphics2D) g).setColor(Color.WHITE);
			((Graphics2D) g).fill(forme);
			((Graphics2D) g).draw(forme);
		} else if (this.px == 9) {
			// piece inexistante
			((Graphics2D) g).setColor(Color.BLUE);
			((Graphics2D) g).draw(forme);
		} else {
			((Graphics2D) g).fill(forme);
			((Graphics2D) g).setColor(Color.WHITE);
			((Graphics2D) g).draw(forme);
			((Graphics2D) g).setFont(new Font("TimesRoman", Font.BOLD, 13));
			if (direction == 1) {
				((Graphics2D) g).drawString(String.valueOf(py), 23 + posX, 13 + posY);
				((Graphics2D) g).drawString(String.valueOf(px), 10 + posX, 30 + posY);
				((Graphics2D) g).drawString(String.valueOf(pz), 35 + posX, 30 + posY);
			} else {
				((Graphics2D) g).drawString(String.valueOf(py), 23 + posX, 28 + posY);
				((Graphics2D) g).drawString(String.valueOf(pz), 10 + posX, 10 + posY);
				((Graphics2D) g).drawString(String.valueOf(px), 35 + posX, 10 + posY);
			}
		}
	}

	@Override
	public Dimension getPreferredSize() {
		return new Dimension(51, 32);
	}

	@Override
	public boolean contains(int x, int y) {
		return forme.contains(x, y);
	}

	@Override
	public void paintBorder(Graphics g) {
		super.paintBorder(g);
		((Graphics2D) g).draw(forme);
	}

}
