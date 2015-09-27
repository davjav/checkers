import static org.junit.Assert.*;

import org.junit.Test;

/** Perform tests of the Board class. */

public class BoardTest {

	/** Tests the pieceAt method by using the place method
	  * to place a piece on a board. */
	@Test
	public void testPieceAt() {
		Board b = new Board(true);
		Piece p = new Piece(true, b, 0, 0, "pawn");
		b.place(p, 0, 0);
		assertEquals(p, b.pieceAt(0, 0));
		assertEquals(null, b.pieceAt(1, 0));
	}

	/** Tests the winner method. */
	@Test
	public void testWinner() {
		Board b = new Board(true);
		Piece p1 = new Piece(true, b, 0, 0, "pawn");
		Piece p2 = new Piece(false, b, 1, 1, "pawn");
		assertEquals("No one", b.winner());
		b.place(p1, 0, 0);
		assertEquals("Fire", b.winner());
		b.place(p2, 1, 1);
		assertEquals(null, b.winner());
		b.remove(0, 0);
		assertEquals("Water", b.winner());
	}

	/** Tests the remove method. */
	@Test
	public void testRemove() {
		Board b = new Board(true);
		Piece p1 = new Piece(true, b, 0, 0, "pawn");
		Piece p2 = new Piece(false, b, 1, 1, "pawn");
		b.place(p1, 0, 0);
		b.place(p2, 1, 1);
		assertEquals(p1, b.remove(0, 0));
		assertEquals(p2, b.remove(1, 1));
	}

	/** Tests the canSelect and select methods. */
	@Test
	public void testCanSelect() {
		Board b = new Board(true);
		Piece p1 = new Piece(true, b, 0, 0, "pawn");
		Piece p2 = new Piece(true, b, 1, 1, "pawn");
		Piece p3 = new Piece(false, b, 2, 2, "pawn");
		Piece p4 = new Piece(false, b, 4, 4, "pawn");
		b.place(p1, 0, 0);
		b.place(p2, 1, 1);
		b.place(p3, 2, 2);
		b.place(p4, 4, 4);
		assertFalse(b.canSelect(2, 2));
		assertFalse(b.canSelect(3, 3));
		assertTrue(b.canSelect(0, 0));
		b.select(0, 0);
		assertFalse(b.canSelect(0, 1));
		assertFalse(b.canSelect(3, 3));
		assertTrue(b.canSelect(1, 1));
		b.select(1, 1);
		assertFalse(b.canSelect(2, 2));
		assertTrue(b.canSelect(0, 2));
		assertTrue(b.canSelect(3, 3));
		
		// Capture Piece.
		b.select(3, 3);
		assertFalse(b.canSelect(2, 2));
		assertFalse(b.canSelect(2, 4));
		assertTrue(b.canSelect(5, 5));

		// Multicapture.
		b.select(5, 5);
		assertFalse(b.canSelect(6, 6));
		assertFalse(b.canSelect(4, 4));
	}

	/* Tests the canEndTurn and endTurn methods. */
	@Test
	public void testCanEndTurn() {
		Board b = new Board(true);
		Piece p1 = new Piece(true, b, 0, 0, "pawn");
		Piece p2 = new Piece(true, b, 1, 1, "pawn");
		Piece p3 = new Piece(false, b, 2, 2, "pawn");
		Piece p4 = new Piece(false, b, 4, 4, "pawn");
		Piece p5 = new Piece(false, b, 6, 6, "pawn");
		b.place(p1, 0, 0);
		b.place(p2, 1, 1);
		b.place(p3, 2, 2);
		b.place(p4, 4, 4);
		b.place(p5, 6, 6);
		b.select(1, 1);
		assertFalse(b.canEndTurn());
		b.select(3, 3);
		assertTrue(b.canEndTurn());
		b.select(5, 5);
		assertTrue(b.canEndTurn());
		b.endTurn();
		b.select(6, 6);
		assertFalse(b.canEndTurn());
		b.select(4, 4);
		assertTrue(b.canEndTurn());
		b.endTurn();
	}

	public static void main(String[] args) {
		jh61b.junit.textui.runClasses(BoardTest.class);
	}
}