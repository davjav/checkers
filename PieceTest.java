import static org.junit.Assert.*;

import org.junit.Test;

/** Perform tests of the Piece class. */

public class PieceTest {

	/** Tests the constructor of Piece. */
	@Test
	public void testConstructor() {
		Board b = new Board(true);
		Piece p = new Piece(true, b, 0, 0, "pawn");
		assertEquals(true, p.isFire());
		assertEquals(0, p.side());
		assertEquals(false, p.isBomb());
		assertEquals(false, p.isShield());
		assertEquals(false, p.hasCaptured());
	}

	/** Tests the isFire method. */
	@Test
	public void testIsFire() {
		Board b = new Board(true);
		Piece p0 = new Piece(true, b, 0, 2, "pawn");
		Piece p1 = new Piece(false, b, 1, 7, "pawn");
		assertEquals(true, p0.isFire());
		assertEquals(false, p1.isFire());
	}

	/** Tests the side method. */
	@Test
	public void testSide() {
		Board b = new Board(true);
		Piece p0 = new Piece(true, b, 0, 2, "pawn");
		Piece p1 = new Piece(false, b, 1, 7, "pawn");
		assertEquals(0, p0.side());
		assertEquals(1, p1.side());
	}

	/** Tests the isKing method */
	@Test
	public void testIsKing() {
		Board b = new Board(true);
		Piece p0 = new Piece(true, b, 0, 6, "pawn");
		Piece p1 = new Piece(false, b, 5, 1, "pawn");
		b.place(p0, 0, 6);
		b.place(p1, 5, 1);
		assertEquals(false, p0.isKing());
		assertEquals(false, p1.isKing());
		p0.move(1, 7);
		assertEquals(true, p0.isKing());
		p1.move(4, 0);
		assertEquals(true, p1.isKing());
	}

	/** Tests the isBomb method. */
	@Test
	public void testIsBomb() {
		Board b = new Board(true);
		Piece p0 = new Piece(true, b, 0, 2, "bomb");
		Piece p1 = new Piece(false, b, 1, 7, "pawn");
		assertEquals(true, p0.isBomb());
		assertEquals(false, p1.isBomb());
	}

	/** Tests the isShield method. */
	@Test
	public void testIsShield() {
		Board b = new Board(true);
		Piece p0 = new Piece(true, b, 1, 1, "shield");
		Piece p1 = new Piece(false, b, 1, 7, "pawn");
		assertEquals(true, p0.isShield());
		assertEquals(false, p1.isShield());
	}

	/** Tests the hasCaptured method. */
	@Test
	public void testHasCaptured() {
		Board b = new Board(true);
		Piece p0 = new Piece(true, b, 1, 1, "bomb");
		assertEquals(false, p0.hasCaptured());
	}

	/** Tests the doneCapturing method() */
	@Test
	public void testDoneCaptured() {
		Board b = new Board(true);
		Piece p0 = new Piece(true, b, 1, 1, "bomb");
		p0.doneCapturing();
		assertEquals(false, p0.hasCaptured());
	}	

	public static void main(String[] args) {
		jh61b.junit.textui.runClasses(PieceTest.class);
	}
}
