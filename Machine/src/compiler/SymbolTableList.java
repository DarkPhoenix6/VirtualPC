/**
 * Machine
 * compiler
 * SymbolTableList.java
 */
package compiler;

/**
 * @class	SymbolTableList
 * @author 	Chris Fedun
 * @date	Jun 2, 2017
 *
 */
public class SymbolTableList {

	private final SymbolNode STLHead;
	private SymbolNode last;
	

	public SymbolTableList()
	{
		STLHead = new SymbolNode("HEADNODE");
		last = getSTLHead();
	}

	/**
	 * @param sTLHead
	 */
	public SymbolTableList(SymbolNode Head ) {
		STLHead = Head;
		this.last = getSTLHead();
	}
	
	// Accessors
	/**
	 * @return the last
	 */
	public SymbolNode getLast() {
		return last;
	}

	

	/**
	 * @return the sTLHead
	 */
	public SymbolNode getSTLHead() {
		return STLHead;
	}

	// Mutators
	/**
	 * @param last the last to set
	 */
	public void setLast(SymbolNode last) {
		this.last = last;
	}

	public void iterateLast()
	{
			last = last.getNext();
	}
	
	public void insertAtEnd( String name, short location, short Value)
	{
		last.setNext( new SymbolNode( name, location, Value ) );
		iterateLast();
	}
	
	public String[] generateSymbolTable( String[] Str )
	{
		return Str;
		
	}
}
