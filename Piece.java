public class Piece {
	private boolean isFire;
	private Board b;
	private int x;
	private int y;
	private String type;
	private boolean crowned;
	private int N = 8;

	/** Keeps track of if a piece has capture another piece this turn. */
	private boolean captured;

	public Piece(boolean isFire, Board b, int x, int y, String type) {
		this.isFire = isFire;
		this.b = b;
		this.x = x;
		this.y = y;
		this.type = type;
		this.crowned = false;
		this.captured = false;
	}

	/** Returns whether or not the piece is a fire piece. */
	public boolean isFire() {
		return isFire;
	}

	/** Returns 0 if the piece is a fire piece,
	  * or 1 if the piece is a water piece. */
	public int side() {
		if (isFire) {
			return 0;
		}
		return 1;
	}

	/** Returns whether or not the piece has been crowned. */
	public boolean isKing() {
		return crowned;
	}

	/** Returns whether or not the piece is a Bomb Piece. */
	public boolean isBomb() {
		return type.equals("bomb");
	}

	/** Returns whether or not the piece is a Shield Piece. */
	public boolean isShield() {
		return type.equals("shield");
	}

	/** Moves the piece to (x, y),
	  * capturing any intermediate piece if applicable. */
	public void move(int x, int y) {
		b.remove(this.x, this.y);
		b.place(this, x, y);
		if (Math.abs(x - this.x) == 2) {
			captured = true;
			b.remove(this.x + ((x - this.x) / 2), this.y + ((y - this.y) / 2));
		}
		this.x = x;
		this.y = y;
		if ((isFire && this.y == N - 1) || (!isFire && this.y == 0)) {
			this.crowned = true;
		}
		if(this.isBomb() && this.captured) {
			for (int i = x - 1; i <= x + 1; i++) {
				for (int j = y - 1; j <= y + 1; j++) {
					Piece toRemove = b.pieceAt(i, j);
					if (toRemove != null && !toRemove.isShield()) {
						b.remove(i, j);
					}
				}
			}
		}
	}

	/** Returns whether or not this Piece
	  * has captured another piece this turn. */
	public boolean hasCaptured() {
		return captured;
	}

	/** Makes sure the piece's hasCaptured() value
	  * returns to false at the end of each turn. */
	public void doneCapturing() {
		captured = false;
	}
}
