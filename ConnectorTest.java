import junit.framework.TestCase;


public class ConnectorTest extends TestCase {
    public void testToConnector() {
    	assertEquals(Connector.toConnector("12"), new Connector(1,2));
    	Connector.toConnector("21");
    	assertEquals(Connector.toConnector("12"), new Connector(1,2));
    	Connector.toConnector(" 12");
    	assertEquals(Connector.toConnector("12"), new Connector(1,2));
    	Connector.toConnector("12 ");
    	assertEquals(Connector.toConnector("12"), new Connector(1,2));
    	Connector.toConnector(" 12     ");
    	assertEquals(Connector.toConnector("12"), new Connector(1,2));
    	Connector.toConnector("      12");
    	assertEquals(Connector.toConnector("12"), new Connector(1,2));
    	try{
    	Connector.toConnector("1s");
    	}
    	catch (IllegalFormatException e) {
    		System.out.println(e);
    	}
    	try {
    	Connector.toConnector("s2");
    	}
    	catch (IllegalFormatException e) {
    		System.out.println(e);
    	}
    	try {
    	Connector.toConnector("123");
    	}
    	catch (IllegalFormatException e) {
    		System.out.println(e);
    	}
    	try {
    	Connector.toConnector("47");
    	}
    	catch (IllegalFormatException e) {
    		System.out.println(e);
    	}
    	try {
    	Connector.toConnector("74");
    	}
    	catch (IllegalFormatException e) {
    		System.out.println(e);
    	}
    	try {
    	Connector.toConnector("");
    	}
    	catch (IllegalFormatException e) {
    		System.out.println(e);
    	}
    	try {
    	Connector.toConnector("  ");
    	}
    	catch (IllegalFormatException e) {
    		System.out.println(e);
    	}
    	try {
    	Connector.toConnector("1");
    	}
    	catch (IllegalFormatException e) {
    		System.out.println(e);
    	}
     	try {
        	Connector.toConnector(null);
        	}
        	catch (IllegalFormatException e) {
        		System.out.println(e);
        		}
     	try {
        	Connector.toConnector("11");
        	}
        	catch (IllegalFormatException e) {
        		System.out.println(e);
        		}
       	try {
        	Connector.toConnector("1 2");
        	}
        	catch (IllegalFormatException e) {
        		System.out.println(e);
        		}
    }
}
