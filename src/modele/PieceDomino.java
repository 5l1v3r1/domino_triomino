package modele;
import java.util.ArrayList;

public class PieceDomino extends Piece {
	private int rot; // 1 pour verticale 0 pour horizentale
	private boolean centre;

	

	@Override
	public String toString() {
		return "PieceDomino [rot=" + rot + ", centre=" + centre + "]";
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

	public PieceDomino(int x, int y) {
		super(x,y);
	}
	public PieceDomino() {
		super(-1,-1);
		rot=0;
		centre=false;
	
	}
	public ArrayList<PieceDomino> getAllSwipes(){ // cette fonction fait des swipe de la piece pour retourner toutes les comb possibles pour tester apres s'il ya coup possible
		ArrayList<PieceDomino> resultat=new ArrayList<PieceDomino>();
		resultat.add(new PieceDomino(this.getX(), this.getY()));
		resultat.add(new PieceDomino(this.getY(), this.getX()));
		return resultat;
	}

	@Override
	public void swipe() {
		int y=this.getX();
		this.setX(this.getY());
		this.setY(y);
	}

	@Override
	public int valeur() {
		return this.getX()+this.getY();
	}
}
