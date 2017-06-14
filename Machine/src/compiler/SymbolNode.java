/**
 * Machine
 * compiler
 * SymbolNode.java
 */
package compiler;

/**
 * @class	SymbolNode
 * @author 	Chris Fedun
 * @date	Jun 2, 2017
 *
 */
public class SymbolNode {

	private SymbolNode next;
	private short location;
	private short value;
	private String name;
	
	public SymbolNode( String Name)
	{
		this( Name, (short) 0, (short) 0, null);
	}

	
	/**
	 * 
	 * @param Name
	 * @param Location
	 * @param Value
	 * @param Next
	 */
	public SymbolNode( String Name, short Location ) {
		this( Name, Location, (short) 0, null);
	}
	
	/**
	 * 
	 * @param Name
	 * @param Location
	 * @param Value
	 * @param Next
	 */
	public SymbolNode( String Name, short Location, short Value, SymbolNode Next) {
		this.name = Name;
		this.location = Location;
		this.value = Value;
		this.next = Next;
	}
	
	

	/**
	 * 
	 * @param Name
	 * @param Location
	 * @param Value
	 */
	public SymbolNode( String Name, short Location, short Value ) {
		this.name = Name;
		this.location = Location;
		this.value = Value;
		this.next = null;
	}
	
	/**
	 * 
	 * @param Name
	 * @param Location
	 * @param Value
	 */
	public SymbolNode( String Name, short Location, int Value ) {
		this.name = Name;
		this.location = Location;
		this.value = (short) Value;
		this.next = null;
	}

	/**
	 * @return the next
	 */
	public SymbolNode getNext() {
		return next;
	}


	/**
	 * @return the location
	 */
	public short getLocation() {
		return location;
	}


	/**
	 * @return the value
	 */
	public short getValue() {
		return value;
	}


	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}


	/**
	 * @param next the next to set
	 */
	public void setNext(SymbolNode next) {
		this.next = next;
	}


	/**
	 * @param location the location to set
	 */
	public void setLocation(short location) {
		this.location = location;
	}


	/**
	 * @param value the value to set
	 */
	public void setValue(short value) {
		this.value = value;
	}


	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}


	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return String.format("Node: %s \n"
				+ "Location: %s \n"
				+ "Initial Value: %s \n"
				+ " \n", name, getLocation(), getValue());
	}
	
	
}
