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
	private int Avail;
	int count;
	
	public SymbolTableList()
	{
		STLHead = new SymbolNode("HEADNODE");
		last = getSTLHead();
		Avail = 99;
		count = 0;

	}

	/**
	 * @param sTLHead
	 */
	public SymbolTableList(SymbolNode Head ) {
		STLHead = Head;
		this.last = getSTLHead();
		Avail = 99;
		count = 0;
	}
	
	// Accessors
	/**
	 * @return the last
	 */
	public SymbolNode getLast() {
		return last;
	}

	

	/**
	 * @return the avail
	 */
	public int getAvail() {
		return Avail;
	}

	/**
	 * @return the count
	 */
	public int getCount() {
		return count;
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
	
	/**
	 * @param avail the avail to set
	 */
	public void setAvail(int avail) {
		Avail = avail;
	}

	public void insertAtEnd( String name, short location )
	{
		last.setNext( new SymbolNode( name, location ) );
		iterateLast();
	}
	
	/**
	 * @Description Decrement Avail
	 */
	protected void decAvail()
	{
		setAvail(getAvail() - 1);
	}
	
	public void insertAtEnd( String name, short location, short Value)
	{
		if ( ! symbolExists( name ) )
		{
			last.setNext( new SymbolNode( name, (short) getAvail(), Value ) );
			decAvail();
			iterateLast();
		}
	}
	
	/**
	 * @param count the count to set
	 */
	public void setCount(int count) {
		this.count = count;
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
		
		String temp = null;
		
		for ( String ST : postDCString )
		{
			for ( String S : ST.split("\\s") )
			{
				if ( ! S.contains(":") )
				{
					if ( temp == null )
					{
						temp = S;
					}
					else
					{
						temp += " " + S;
					}
				}
			}
		}
		String[] tempString = temp.split("\\s");
		String[] preReturnString = new String[ (tempString.length / 2) + 1];
		int a = 0;
		int returnCount = 0;
		while ( a < tempString.length -2  )
		{
			

			
				
			if (  tempString[a].contains("STOP") )
			{
				preReturnString[ returnCount++ ] = tempString[a++];
			}
			else
			{
				preReturnString[ returnCount++ ] = tempString[a++] + " " + tempString[a++];
			}
		
			
			
		}
		
		
		preReturnString[returnCount] = new String("BR TOP");
		generateVariables(preReturnString);
		String[] returnString = generateConstants(preReturnString);
		return preReturnString;
		
	}

	/**
	 * @param preReturnString
	 */
	private void generateVariables(String[] string) {
		// TODO Auto-generated method stub

		int i = -1;
		for ( String S : string )
		{
			i++;
			if ( S.split("\\s").length > 1 )
			{
				if (S.regionMatches(0, "DC", 0, 2))
				{
					continue;
				}
				else if ( isInt(S.split("\\s")[1] ) )
				{
					continue;
				}
				else if ( ! symbolExists(S.split("\\s")[1]))
				{
					generateNode( S.split("\\s")[1] + ":", getAvail());
					this.decAvail();
				}
				
			}
			
			else if ( isInt( S ) )
			{
				generateNode( S + ": " + S, i );
			}
		
			
			
		}
	}

	/**
	 * @param str
	 * @return
	 */
	private String[] addDataControl(String[] preDC ) {
		// TODO Auto-generated method stub
		
		
		int tempPlace = 0;
		String BR = new String("BR DATACONTROL");
		String TOP = new String("BR TOP");
		String[] temp = new String[preDC.length];
		int place = 0;
		
		int[] arr = new int[preDC.length]; 
		for ( String instruction: preDC )
		{
			//TODO: use constants
			
			//String substring = instruction[0].substring(0, instruction[].length() - 1);
			if ( instruction.contains(": DC") || instruction.contains(": dc"))
			{
				String substring = instruction.substring(0, instruction.indexOf(":"));
				if ( tempPlace == 0 )
				{
					temp[tempPlace] = new String( "DATACONTROL: LD " + (place + 1) );
				}
				else
				{
					temp[tempPlace] = new String( "LD " + (place + 1) );
				}
				
				tempPlace++;
				temp[tempPlace] = new String( "STORE " + substring );
				tempPlace++;
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
				if ( postDCPlace == 1 )
				{
					postDCString[postDCPlace] = "TOP: " + instruction;
				}
				else
				{
					postDCString[postDCPlace] = instruction;
				}
				
				postDCPlace++;
			}
			
			for ( int i = 0; i < tempPlace; i++ )
			{
				postDCString[postDCPlace] = temp[i];
				postDCPlace++;
			}
			
			postDCString[postDCString.length - 1] = TOP;
			//System.out.println(postDCString.length);
			generateConstants( postDCString );
			return postDCString;
		}
		else
		{
			generateConstants( preDC );
			return preDC;
		}
	}

	/**
	 * @param postDCString
	 * @return 
	 */
	private String[] generateConstants(String[] instructions) {
		// TODO insert constants
		return null
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
			generateNode( instruction[2] + ": " + instruction[2], location );
		}
		else if ( isInt(substring ) )
		{
			insertAtEnd( substring, (short) location, Short.valueOf(instruction[2]) );
		}
		else 
		{
			insertAtEnd( substring, (short) location );
		}
		
	}

	/**
	 * @param string
	 * @return
	 */
	@SuppressWarnings("unused")
	private boolean isInt(String string) {
		// TODO Auto-generated method stub
		try
		{
			int i = Integer.valueOf(string);
			return true;
		}
		catch (NumberFormatException NFE)
		{
			return false;
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
			
		while ( getLast() != saveSpot && ! getLast().getName().contains(name) )
		{
			iterateLast();
			if ( getLast().getName().contains(name))
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

	boolean symbolExists(String name)
	{
		SymbolNode saveSpot = getLast();
		setLast(this.getSTLHead());
		boolean exists = false;
		while ( getLast() != null )
		{
		
			if ( getLast().getName().equals(name))
			{
				exists = true;
				
				break;
			}
			this.iterateLast();
		}
		
		setLast( saveSpot );
		return exists;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return String.format("SymbolTable \n"
				+ "Avail= %s \n"
				+ "count= %s \n"
				+ " \n"
				+ "%s", Avail, count, printTable());
	}

	/**
	 * @return
	 */
	private String printTable() {
		// TODO Auto-generated method stub
		SymbolNode saveSpot = getLast();
		setLast(this.getSTLHead());
		
		String returnString = null;
		while ( getLast() != null )
		{
			if ( returnString == null )
			{
				returnString = getLast().toString();
			}
			else 
			{
				returnString += getLast().toString();
			}
			this.iterateLast();
		}
		setLast( saveSpot );
		return returnString;
	}
	
	
}
