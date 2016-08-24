public class Connector {
	
	// Implement an immutable connector that connects two points on the game board.
	// Invariant: 1 <= myPoint1 < myPoint2 <= 6.
	
	private int myPoint1, myPoint2;
	
	public Connector (int p1, int p2) {
		if (p1 < p2) {
			myPoint1 = p1;
			myPoint2 = p2;
		} else {
			myPoint1 = p2;
			myPoint2 = p1;
		}
	}
	
	public int endPt1 ( ) {
		return myPoint1;
	}
	
	public int endPt2 ( ) {
		return myPoint2;
	}
	
	public boolean equals (Object obj) {
		Connector e = (Connector) obj;
		return (e.myPoint1 == myPoint1 && e.myPoint2 == myPoint2);
	}
	
	public String toString ( ) {
		return "" + myPoint1 + myPoint2;
	}
	
	// Format of a connector is endPoint1 + endPoint2 (+ means string concatenation),
	// possibly surrounded by white space. Each endpoint is a digit between
	// 1 and 6, inclusive; moreover, the endpoints aren't identical.
	// If the contents of the given string is correctly formatted,
	// return the corresponding connector.  Otherwise, throw IllegalFormatException.
	public static Connector toConnector (String s) throws IllegalFormatException {
		int endPt1 = 0;
		int endPt2 = 0;
		int a = 0;
		int b = 0;
		if (s==null) 
			throw new IllegalFormatException("cannot input null");
		if (s.length()<2)
			throw new IllegalFormatException("not enough chars");
		for (int i=0;i<s.length()-1;i++) {
			while(! Character.isWhitespace(s.charAt(i)))  {
				try{
				a = Integer.parseInt(s.substring(i,i+1));
				}
				catch (NumberFormatException e) {
					throw new IllegalFormatException("first char must be a number");
				}
				if (a<1 ||a>6) 
					throw new IllegalFormatException ("fisrt char must be between 1 and 6");
				if (a >=1 && a<=6) {
					if (Character.isWhitespace(s.charAt(i+1)))
						throw new IllegalFormatException ("cannot have whitespace between endpts");
					try{			
						b = Integer.parseInt(s.substring(i+1,i+2));	
					}
					catch (NumberFormatException e) {
						throw new IllegalFormatException("second char must be a number");
					}
					if (b<1 ||b>6)
						throw new IllegalFormatException ("second char must be between 1 and 6");
					if (b>=1 && b<=6) {
						endPt1=a;
						endPt2 =b;
						if (b==a)
							throw new IllegalFormatException("endpoints must be different");
						if (i+1 !=s.length()-1 && !Character.isWhitespace(s.charAt(i+2)))
							throw new IllegalFormatException("too many characters");
						break;
					}
				}
			}
			if (endPt1!=0 && endPt2!=0)
				break;
		}
		if(endPt1==0 || endPt2==0) 
			throw new IllegalFormatException("not a legal input");

	Connector k = new Connector(endPt1,endPt2);
	return k;
	}
}

		