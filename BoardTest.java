import java.awt.Color;
import java.util.Iterator;

import junit.framework.TestCase;

public class BoardTest extends TestCase {
	
	// Check empty board.
	public void testEmptyBoard ( ) {
		Board b = new Board ( );
		assertTrue (b.isOK ( ));
		checkCollection (b, 0, 0); // applies more tests
		assertTrue (!b.formsTriangle (new Connector (1, 2), Color.RED));
	}
	
	// Check one-connector board.
	public void test1Connector ( ) {
		Board b = new Board ( );
		b.add (new Connector (1, 2), Color.RED);
		assertTrue (b.isOK ( ));
		checkCollection (b, 1, 0);
		
		Iterator<Connector> iter = b.connectors (Color.RED);
		assertTrue (iter.hasNext ( ));
		Connector cnctr = iter.next ( );
		assertEquals (b.colorOf (cnctr), Color.RED);
		assertEquals (new Connector (1, 2), cnctr);
		assertTrue (!iter.hasNext ( ));
		
		assertTrue (!b.formsTriangle (new Connector(1,3), Color.RED));
		assertTrue (!b.formsTriangle (new Connector(5,6), Color.RED));
		assertTrue (!b.choice ( ).equals (new Connector (1, 2)));
		assertEquals (b.colorOf (b.choice ( )), Color.WHITE);
	}
	
	public void testisOK( ) {
		Board b = new Board ( );
		b.add(new Connector (1,2), Color.RED);
		assertTrue (b.isOK());
		b.add (new Connector (2,3), Color.RED);
		assertFalse (b.isOK());
		
		Board c = new Board ();
		c.add (new Connector (1,2), Color.BLUE);
		assertFalse (c.isOK());
		
		Board d = new Board ( );
		d.add(new Connector (1,2), Color.RED);
		assertTrue (d.isOK());
		d.add (new Connector (2,3), Color.BLUE);
		assertTrue (d.isOK());
		d.add (new Connector (1,6), Color.BLUE);
		assertFalse (d.isOK());
	}
	
	public void testFormsTriangle() {
		Board e = new Board ( );
		e.add(new Connector (1,2), Color.RED);
		assertTrue (e.isOK());
		e.add (new Connector (2,3), Color.BLUE);
		assertTrue (e.isOK());
		e.add (new Connector (1,6), Color.RED);
		assertTrue (e.isOK());
		e.add (new Connector (4,6), Color.BLUE);
		assertTrue (e.isOK());
		e.add (new Connector (2,6), Color.RED);
		assertFalse(e.isOK());
		
		Board f = new Board ( );
		f.add(new Connector (1,4), Color.RED);
		assertTrue (f.isOK());
		f.add(new Connector (1,2), Color.BLUE);
		assertTrue (f.isOK());
		f.add (new Connector (2,3), Color.RED);
		assertTrue (f.isOK());
		f.add (new Connector (1,6), Color.BLUE);
		assertTrue (f.isOK());
		f.add (new Connector (4,6), Color.RED);
		assertTrue (f.isOK());
		f.add (new Connector (2,6), Color.BLUE);
		assertFalse(f.isOK());
		
	}
	public void testDuplicate() {
		// Red and Blue play same connector
		Board f = new Board ( );
		f.add(new Connector (1,4), Color.RED);
		assertTrue (f.isOK());
		f.add(new Connector (1,4), Color.BLUE);
		assertFalse(f.isOK());
		
		// Red plays duplicate connector
		Board b = new Board ( );
		b.add(new Connector (1,4), Color.RED);
		assertTrue (b.isOK());
		b.add(new Connector (1,2), Color.BLUE);
		assertTrue (b.isOK());
		b.add(new Connector (1,4), Color.RED);
		assertFalse (b.isOK());
		
		// Blue plays duplicate connector
		Board c = new Board ( );
		c.add(new Connector (1,3), Color.RED);
		assertTrue (c.isOK());
		c.add(new Connector (1,2), Color.BLUE);
		assertTrue (c.isOK());
		c.add(new Connector (1,4), Color.RED);
		assertTrue(c.isOK());
		c.add(new Connector (1,2), Color.BLUE);
		assertFalse(c.isOK());

	}
	public void testConnectorCount ( ) {
		Board b = new Board ( );
		assertTrue(b.isOK());
		b.add(new Connector (1,4), Color.RED);
		assertTrue (b.isOK());
		b.add(new Connector (1,2), Color.BLUE);
		assertTrue(b.isOK());
	}
	
	// (a useful helper method)
	// Make the following checks on a board that should be legal:
	//	Check red vs. blue counts.
	//	Check for a blue triangle, which shouldn't exist.

	//	Check connector counts (# reds + # blues + # uncolored should be 16.
	//	Check for duplicate connectors.
	
		private void checkCollection (Board b, int redCount, int blueCount) {
		// Fill this in if you'd like to use this method.
	}
}