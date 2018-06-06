	public boolean coupValide(int jeu, PieceDomino piece, Point p, Point oldP) {

		if (jeu == 0) {
			table = this.getTable();
			int x = p.getX();
			int y = p.getY();
			if (table[x][y - 1] != null) {
							if (table[x][y - 1].getX() == table[x][y - 1].getY() && table[x][y - 1].isCentre() && table[x][y - 1].getRot() == 1) {
										if (table[x][y - 1].getX() == piece.getX()) {
											oldP.setX(x);
											oldP.setY(y - 1);
											return true;
										}
							} 
							else if (table[x][y - 1].getRot() == 1) {
										if (piece.getRot() == 0 && (piece.getY() == table[x][y - 1].getX() || piece.getY() == table[x][y - 1].getY())) {
											oldP.setX(x);
											oldP.setY(y - 1);
											return true;
										}
							} 
							else if (table[x][y - 1].getRot() == 0) {
										if (piece.getRot() == 0 && piece.getX() == table[x][y - 1].getY()) {
											oldP.setX(x);
											oldP.setY(y - 1);
											return true;
										} 
										else if (piece.getRot() == 1 && piece.isCentre() && piece.getX() == piece.getY() && piece.getX() == table[x][y - 1].getY()) {
											oldP.setX(x);
											oldP.setY(y - 1);
											return true;
										}
								}
			} 
			else if (table[x][y + 1] != null){
							if (table[x][y + 1].isCentre() && table[x][y + 1].getX() == table[x][y + 1].getY() && table[x][y + 1].getRot() == 1) {
										if (table[x][y + 1].getX() == piece.getY()) {
											oldP.setX(x);
											oldP.setY(y + 1);
											return true;
										}
							} 
							else if (table[x][y + 1].getRot() == 1) {
								if (piece.getRot() == 0 && (piece.getX() == table[x][y - 1].getX() || piece.getX() == table[x][y - 1].getY())) {
									oldP.setX(x);
									oldP.setY(y + 1);
									return true;
								}
							} 
							else if (table[x][y + 1].getRot() == 0) {
										if (piece.getRot() == 0 && piece.getY() == table[x][y + 1].getX()) {
											oldP.setX(x);
											oldP.setY(y + 1);
											return true;
										} 
										else if (piece.getRot() == 1 && piece.isCentre() && piece.getX() == piece.getY() && piece.getX() == table[x][y + 1].getX()) {
											oldP.setX(x);
											oldP.setY(y + 1);
											return true;
										}
								}
			} 
			else if (table[x - 1][y] != null) {
				
								if (table[x - 1][y].isCentre() && table[x - 1][y].getX() == table[x - 1][y].getY() && table[x - 1][y].getRot() == 0) {
									if (table[x - 1][y].getX() == piece.getY()) {
										oldP.setX(x - 1);
										oldP.setY(y);
										return true;
									}
								} 
								else if (table[x - 1][y].getRot() == 1) {
									if (piece.getRot() == 1 && piece.getY() == table[x - 1][y].getX()) {
										oldP.setX(x - 1);
										oldP.setY(y);
										return true;
									} 
									else if (piece.getRot() == 0 && piece.isCentre() && piece.getX() == piece.getY() && piece.getX() == table[x - 1][y].getY()) {
										oldP.setX(x - 1);
										oldP.setY(y);
										return true;
									}
								} 
								else if (table[x - 1][y].getRot() == 0) {
									if (piece.getRot() == 1 && (piece.getY() == table[x - 1][y].getX() || piece.getY() == table[x - 1][y].getY())) {
										oldP.setX(x - 1);
										oldP.setY(y);
										return true;
									}
								}
				
			} 
			else if (table[x + 1][y] != null) {
								if (table[x + 1][y].isCentre() && table[x + 1][y].getX() == table[x + 1][y].getY() && table[x + 1][y].getRot() == 0) {
									if (table[x + 1][y].getX() == piece.getX()) {
										oldP.setX(x + 1);
										oldP.setY(y);
										return true;
									}
								} 
								else if (table[x + 1][y].getRot() == 1) {
									if (piece.getRot() == 1 && piece.getX() == table[x + 1][y].getY()) {
										oldP.setX(x + 1);
										oldP.setY(y);
										return true;
									} 
									else if (piece.getRot() == 0 && piece.isCentre() && piece.getX() == piece.getY() && piece.getX() == table[x + 1][y].getY()) {
										oldP.setX(x + 1);
										oldP.setY(y);
										return true;
									} 
									else if (table[x + 1][y].getRot() == 0) {
										if (piece.getRot() == 1 && (piece.getX() == table[x + 1][y].getX() || table[x + 1][y].getY() == piece.getX())) {
											oldP.setX(x + 1);
											oldP.setY(y);
											return true;
										}
									}
								}
				
			}

		} else {

			return false;
			// TODO trio
		}
		return false;
	}

	