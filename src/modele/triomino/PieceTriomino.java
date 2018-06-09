package modele.triomino;

import java.util.ArrayList;

import modele.Piece;

public class PieceTriomino extends Piece {

	private int z;

	public int getZ() {
		return z;
	}

	public void setZ(int z) {
		this.z = z;
	}

	public PieceTriomino(int x, int y, int z) {
		super(x, y);
		this.z = z;

	}

	@Override
	public void swipe() {
		int aux = this.z;
		this.setZ(this.getY());
		this.setY(this.getX());
		this.setX(aux);
	}

	@Override
	public int valeur() {
		return this.getX() + this.getY() + this.getZ();
	}

	public ArrayList<PieceTriomino> getAllSwipes() {
		ArrayList<PieceTriomino> resultat = new ArrayList<PieceTriomino>();
		resultat.add(new PieceTriomino(this.getX(), this.getY(), this.getZ()));
		resultat.add(new PieceTriomino(this.getZ(), this.getX(), this.getY()));
		resultat.add(new PieceTriomino(this.getY(), this.getZ(), this.getX()));
		return resultat;
	}
}
