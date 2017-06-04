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
	
	public void insertAtEnd( String name, short location )
	{
		last.setNext( new SymbolNode( name, location ) );
		iterateLast();
	}
	
	public void insertAtEnd( String name, short location, short Value)
	{
		last.setNext( new SymbolNode( name, location, Value ) );
		iterateLast();
	}
	
	public String[] generateSymbolTable( String[] Str )
	{
		//TODO
		String[] postDCString = addDataControl( Str );
		int i = 0;
		int arrPlace = 0;
		int[] arr = new int[postDCString.length];
		//TODO check for DCs And inject Stores at top of list
		for ( String S : postDCString)
		{
			if ( S.contains(":") )
			{
				generateNode( S, i );
				arr[arrPlace] = i;
				arrPlace++;
				i++;
			}
			else
			{
				i++;
			}
			
		}
		return postDCString;
		
	}

	/**
	 * @param str
	 * @return
	 */
	private String[] addDataControl(String[] preDC ) {
		// TODO Auto-generated method stub
		
		int count = 0;
		int tempPlace = 0;
		String BR = new String("BR DATACONTROL");
		String[] temp = new String[preDC.length];
		int place = 0;
		
		int[] arr = new int[preDC.length]; 
		for ( String instruction: preDC )
		{
			
			//String substring = instruction[0].substring(0, instruction[].length() - 1);
			if ( instruction.contains(": DC") || instruction.contains(": dc"))
			{
				String substring = instruction.substring(0, instruction.indexOf(":"));
				temp[tempPlace] = new String( "LD " + place );
				tempPlace++;
				temp[tempPlace] = new String( "STORE " + substring );
				arr[count] = place;
				count++;
				place++;
				
			}
			else 
			{
				place++;
			}
		}
		
		if ( count > 0 )
		{	
		
			String[] postDCString = new String[preDC.length + 2 + tempPlace ];
	
			postDCString[0] = BR;
			int postDCPlace = 1;
			for ( String instruction: preDC )
			{
				postDCString[postDCPlace] = instruction;
				postDCPlace++;
			}
			
			for ( int i = 0; i < tempPlace; i++ )
			{
				postDCString[postDCPlace] = temp[i];
				postDCPlace++;
			}
			
			return postDCString;
		}
		else
		{
			return preDC;
		}
	}

	/**
	 * @param s
	 * @param i
	 */
	private void generateNode(String lable, int location) {
		// TODO Auto-generated method stub
		String[] instruction = lable.split("\\s");
		String substring = instruction[0].substring(0, instruction[0].length() - 1);
		if ( lable.contains(": DC") || lable.contains(": dc"))
		{
			insertAtEnd( substring, (short) location, Short.valueOf(instruction[2]) );
		}
		else 
		{
			insertAtEnd( substring, (short) location );
		}
	}

	/**
	 * @param name
	 * @return
	 */
	public int getLocation( String name) {
		// TODO Auto-generated method stub
		SymbolNode saveSpot = last;
		setLast(getSTLHead());
		int loc = -1;
			
		while ( getLast() != saveSpot && name != getLast().getName() )
		{
			iterateLast();
			if ( name == getLast().getName())
			{
				loc = getLast().getLocation();
			}
		}
		
		setLast( saveSpot );
		if ( loc != -1 )
		{	
			return loc;
		}
		else
		{
			InvalidIdentifierError e = new InvalidIdentifierError( name );
			throw e;
		}
		

	}
}
