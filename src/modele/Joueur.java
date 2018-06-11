package modele;

public class Joueur {
	private String nom;
	private int score;
	private boolean cpu;
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	public boolean isCpu() {
		return cpu;
	}
	public void setCpu(boolean cpu) {
		this.cpu = cpu;
	}
	public Joueur(String nom, int score, boolean cpu) {
		super();
		this.nom = nom;
		this.score = score;
		this.cpu = cpu;
	}
	@Override
	public String toString() {
		return "Joueur [nom=" + nom + ", score=" + score + ", cpu=" + cpu + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (cpu ? 1231 : 1237);
		result = prime * result + ((nom == null) ? 0 : nom.hashCode());
		result = prime * result + score;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Joueur other = (Joueur) obj;
		if (cpu != other.cpu)
			return false;
		if (nom == null) {
			if (other.nom != null)
				return false;
		} else if (!nom.equals(other.nom))
			return false;
		if (score != other.score)
			return false;
		return true;
	}
}
