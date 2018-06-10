package vue.triomino;

import modele.triomino.ModeleTriomino;

public class testD {
public static void main(String[] args) {
	for (int i = 0; i < 100; i++) {
		for (int j = 0; j < 100; j++) {
			System.out.println("i= "+i+"j= "+j+" direction= "+ModeleTriomino.getDirection(i, j));
		}
	}
}
}
