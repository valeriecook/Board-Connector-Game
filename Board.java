import java.awt.Color;
import java.util.*;

public class Board{

	public static boolean iAmDebugging = true;
	ArrayList<Connector> myReds;
	ArrayList<Connector> myBlues;
	ArrayList<Connector> myWhites;

	// Initialize an empty board with no colored edges.
	public Board ( ) {
		myReds= new ArrayList<Connector>();
		myBlues= new ArrayList<Connector>();
		myWhites= new ArrayList<Connector>();
		for (int i=1;i<7;i++) {
			for (int j=i+1;j<7;j++) {
			    myWhites.add(new Connector(i,j));
			}
		}
	}
	
	// Add the given connector with the given color to the board.
	// Unchecked precondition: the given connector is not already chosen 
	// as RED or BLUE.
	public void add (Connector cnctr, Color c) {
		
		//if(myWhites.contains(cnctr)) {
			if(c.equals(Color.red)) {
				myReds.add(cnctr);
			}
			if(c.equals(Color.blue)) {
				myBlues.add(cnctr);
			}
			myWhites.remove(cnctr);
		//}
	}
	
	// Set up an iterator through the connectors of the given color, 
	// which is either RED, BLUE, or WHITE. 
	// If the color is WHITE, iterate through the uncolored connectors.
	// No connector should appear twice in the iteration.  
	public Iterator<Connector> connectors (Color c) {
        if(c.equals(Color.red))
		    return myReds.iterator();
        if(c.equals(Color.blue))
            return myBlues.iterator();
        if(c.equals(Color.white)) {
        	Iterator<Connector> it=myWhites.iterator();
        	while (it.hasNext()) {
        		it.next();
        	}
        }
        return myWhites.iterator();
	}
	
	// Set up an iterator through all the 15 connectors.
	// No connector should appear twice in the iteration.  
	public Iterator<Connector> connectors ( ) {
		ArrayList<Connector> total=new ArrayList<Connector>();
		total.addAll(myReds);
		total.addAll(myBlues);
		total.addAll(myWhites);
		return total.iterator();
	}
	
	// Return the color of the given connector.
	// If the connector is colored, its color will be RED or BLUE;
	// otherwise, its color is WHITE.
	public Color colorOf (Connector e) {
		if (myReds.contains(e)) {
			return Color.RED;
		}
		if (myBlues.contains(e)) {
			return Color.BLUE;
			
		}
		return Color.WHITE;
	}
	
	// Unchecked prerequisite: cnctr is an initialized uncolored connector.
	// Let its endpoints be p1 and p2.
	// Return true exactly when there is a point p3 such that p1 is adjacent
	// to p3 and p2 is adjacent to p3 and those connectors have color c.
	public boolean formsTriangle (Connector cnctr, Color c) {
		int pointOne=cnctr.endPt1();
		int pointTwo=cnctr.endPt2();
		int a =0;
		ArrayList<Connector> myConnectors = myWhites;
		if (c.equals(Color.red))
			myConnectors = myReds;
		if (c.equals(Color.blue))
			myConnectors = myBlues;
		
		for (Connector connector: myConnectors) {
				if (pointOne == connector.endPt1())
					a = connector.endPt2();
				else if (pointOne == connector.endPt2()) 
					a = connector.endPt1();
			
				if (myConnectors.contains(new Connector(a,pointTwo)) 
						|| myConnectors.contains(new Connector(pointTwo,a))) 
					return true;
		}
		return false;
	}
	
	private boolean formsSafeTriangle (Connector cnctr, Color c) {
		int pointOne=cnctr.endPt1();
		int pointTwo=cnctr.endPt2();
		int a =0;
		ArrayList<Connector> myConnectors = null;
		ArrayList<Connector> myOtherConnectors = null;
		if (c.equals(Color.red)) {
			myConnectors = myReds;
			myOtherConnectors = myBlues;
		}
		if (c.equals(Color.blue)) {
			myConnectors = myBlues;
			myOtherConnectors = myReds;
		}
		for (Connector connector: myConnectors) {
				if (pointOne == connector.endPt1())
					a = connector.endPt2();
				else if (pointOne == connector.endPt2()) 
					a = connector.endPt1();
			
				if (myOtherConnectors.contains(new Connector(a,pointTwo)) 
						|| myOtherConnectors.contains(new Connector(pointTwo,a))) 
					return true;
		}
		for (Connector connector: myOtherConnectors) {
			if (pointOne == connector.endPt1())
				a = connector.endPt2();
			else if (pointOne == connector.endPt2()) 
				a = connector.endPt1();
		
			if (myConnectors.contains(new Connector(a,pointTwo)) 
					|| myConnectors.contains(new Connector(pointTwo,a))) 
				return true;
		}
		return false;
	}
	
	private boolean isConnected (Connector c1, Color c) {
		ArrayList<Connector> myConnectors = null;
		if (c.equals(Color.red))
			myConnectors = myReds;
		if (c.equals(Color.blue)) 
			myConnectors = myBlues;
		
		for (Connector connector: myConnectors) {
			if (c1.endPt1() == connector.endPt1() || c1.endPt1() == connector.endPt2()
					|| c1.endPt2() == connector.endPt1() || c1.endPt2() == connector.endPt2())
				return true;
		}
		return false;
	}
		
	
	// The computer (playing BLUE) wants a move to make.
	// The board is assumed to contain an uncolored connector, with no 
	// monochromatic triangles.
	// There must be an uncolored connector, since if all 15 connectors are colored,
	// there must be a monochromatic triangle.
	// Pick the first uncolored connector that doesn't form a BLUE triangle.
	// If each uncolored connector, colored BLUE, would form a BLUE triangle,
	// return any uncolored connector.
	public Connector choice ( ) {
		ArrayList<Connector> choices = new ArrayList<Connector>();
		ArrayList<Connector> betterChoices = new ArrayList<Connector>();
		
		// choose non-losing choices for computer
		for( Connector connector : myWhites) {
			if ( ! formsTriangle(connector, Color.blue))
				choices.add(connector);
		}
		if (choices.isEmpty())
			return myWhites.iterator().next();
		
		// eliminate losing choices for user
		for (Connector connector : choices) {
			if ( ! formsTriangle(connector, Color.red))
				betterChoices.add(connector);
		}
		if ( ! betterChoices.isEmpty())
			choices = betterChoices;
		
		// play connector if closes safe triangle
		for (Connector connector : choices)
			if ( formsSafeTriangle(connector, Color.blue))
				return connector;
		
		// play connector if it isn't connected to other blue lines
		for (Connector connector : choices)	
			if (! isConnected( connector, Color.blue) )
				return connector;
		
		// play connector if it isn't connected to red lines
		for (Connector connector : choices)
			if (! isConnected( connector, Color.red))
				return connector;
		
		return choices.iterator().next();
	}

	// Return true if the instance variables have correct and internally
	// consistent values.  Return false otherwise.
	// Unchecked prerequisites:
	//	Each connector in the board is properly initialized so that 
	// 	1 <= myPoint1 < myPoint2 <= 6.
	public boolean isOK ( ) {
		for (Connector connector : myReds) {
			if (formsTriangle(connector, Color.red))
				return false;
		}
		for (Connector connector : myBlues) {
			if ( formsTriangle( connector, Color.blue))
				return false;
		}
		if(myReds.size()!=myBlues.size() && myReds.size()!=myBlues.size()+1) {
			return false;
		}
		if (myReds.size()+myBlues.size()+myWhites.size()!=15)
			return false;
		
		return true;				
	}
}