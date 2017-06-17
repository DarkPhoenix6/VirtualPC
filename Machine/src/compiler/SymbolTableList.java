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
	private String DCString = null;
	private String varString = null;
	private String lableString = null;
	int locationCount;
	private String constString;
	
	public SymbolTableList()
	{
		STLHead = new SymbolNode("HEADNODE");
		last = getSTLHead();
		Avail = 99;
		locationCount = 0;

	}

	/**
	 * @param sTLHead
	 */
	public SymbolTableList(SymbolNode Head ) {
		STLHead = Head;
		this.last = getSTLHead();
		Avail = 99;
		locationCount = 0;
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
	public int getLocationCount() {
		return locationCount;
	}

	public boolean hasMemoryLeft() throws OutOfMemoryException 
	{
		if ( getLocationCount() != getAvail() )
		{
			return false;
		}
		else
		{
			OutOfMemoryException OOME = new OutOfMemoryException();
			throw OOME;
		}
		
	}
	/**
	 * @return the sTLHead
	 */
	public SymbolNode getSTLHead() {
		return STLHead;
	}

	public String getDataControls()
	{
		String returnString = null;
		SymbolNode saveSpot = getLast();
		setLast(this.getSTLHead());
		

		while ( getLast() != null )
		{
			if ( returnString == null && getLast().getLocation() > getAvail() )
			{

				returnString = new String( Short.toString( getLast().getLocation() ) + " " + getLast().getValue() );
			}
			else if( getLast().getLocation() > getAvail() )
			{
				returnString += " \n" + Short.toString( getLast().getLocation() ) + " " + getLast().getValue();
			}
			this.iterateLast();
		}
		setLast( saveSpot );
		return returnString;
	}
	/**
	 * @return the dCString
	 */
	public String getDCString() {
		return DCString;
	}

	/**
	 * @return the varString
	 */
	public String getVarString() {
		return varString;
	}

	/**
	 * @return the lableString
	 */
	public String getLableString() {
		return lableString;
	}

	/**
	 * @return
	 */
	private String getConstString() {
		// TODO Auto-generated method stub
		return constString;
	}
	
	/**
	 * @param testString
	 * @return
	 */
	private boolean isNull(String testString) {
		// TODO Auto-generated method stub
		if ( testString == null )
		{
			return true;
		}
		else
		{
			return false;
		}
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
		if ( ! symbolExists( name ) )
		{
			last.setNext( new SymbolNode( name, location ) );
			iterateLast();
		}
	}
	
	/**
	 * @throws OutOfMemoryException 
	 * @Description Decrement Avail
	 */
	protected void decAvail() throws OutOfMemoryException
	{
		setAvail(getAvail() - 1);
		@SuppressWarnings("unused")
		boolean check = hasMemoryLeft();
	}
	
	public void insertAtEnd( String name, short location, short Value) throws OutOfMemoryException
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
	public void setLocationCount(int count) {
		this.locationCount = count;
	}
	
	public void incLC() throws OutOfMemoryException
	{
		setLocationCount( 1 + getLocationCount() );
		@SuppressWarnings("unused")
		boolean check = hasMemoryLeft();
	}

	public String symbolTable( String instruction) throws OutOfMemoryException
	{
		System.out.println(getLocationCount());
		if ( instruction.contains(":") )
		{
			String substring = instruction.substring(0, instruction.indexOf(":"));
			
			
			if ( instruction.contains(": DC ") )
			{
				
				setDCString(substring);
				
				
				if( ! symbolExists( substring ) )
				{
					generateNode( instruction, getLocationCount(), getAvail() );
				}

//				else
//				{
//					short value = Short.valueOf( constants[2] );
//					getNode( substring ).setValue(value);
//					getNode( substring ).setLocation( (short) getAvail() );
//					decAvail();
//				}
				
				return null;
			}
			else 
			{
				if ( symbolExists( substring ) )
				{
					getNode( substring ).setLocation( (short) getLocationCount() );
				}
				else
				{
					generateNode( instruction, getLocationCount(), getAvail() );
				}
				setLableString(substring);
				return symbolTable( removeLables( instruction ) );
			}
		}
		else if ( instruction.split("\\s").length > 1 )
		{
			
			
			if ( ! isInt(instruction.split("\\s")[1] ) && ! symbolExists(instruction.split("\\s")[1]) )
			{
				setVarString(instruction);
				return removeLables( instruction );
				//generateNode( instruction.split("\\s")[1] + ":", getLocationCount(), getAvail());
			}
			else if ( isInt(instruction.split("\\s")[1] ) && ! symbolExists(instruction.split("\\s")[1]) )
			{
				String PoM = null;
				String num = null;
				@SuppressWarnings("unused")
				String noString = symbolTable( instruction.split("\\s")[1] );
				if ( Short.valueOf( instruction.split("\\s")[1] ) < 0 )
				{
					PoM = new String("PLUS");
					num = instruction.split("\\s")[1].substring( 1 );
				}
				else
				{
					PoM = new String("PLUS");
					num = instruction.split("\\s")[1];
				}
				return removeLables( instruction.split("\\s")[0] + " " + PoM + num );
			}
			
		}
		
		else if ( isInt( instruction ) )
		{
			if ( Short.valueOf(instruction) < 0 )
			{
				//generateNode( "MINUS" + instruction.substring( 1 ) + ": " + instruction, getLocationCount(), getAvail() );
				if ( constString == null )
				{
					constString = new String("MINUS" + instruction.substring( 1 ) + ": DC " + instruction);
				}
				else
				{
					constString += " \n" + "MINUS" + instruction.substring( 1 ) + ": DC " + instruction;
				}
				
			}
			else
			{
				//generateNode( "PLUS" + instruction + ": " + instruction, getLocationCount(), getAvail() );
				if ( constString == null )
				{
					constString = new String("PLUS" + instruction + ": DC " + instruction);
				}
				else
				{
					constString += " \n" + "PLUS" + instruction + ": DC " + instruction;
				}
			}
			return null;
		}
		else
		{
			if ( instruction.compareTo("EXIT") == 0 )
			{
				return symbolTable( "BR DONE" );
			}
			
			
		}
		return instruction;
	
		
		
		
		
	}

	/**
	 * @param instruction
	 */
	private void setVarString(String instruction) {
		if ( varString == null )
		{
			varString = new String(" " +instruction.split("\\s")[1] + " ");
		}
		else
		{
			varString += " " + instruction.split("\\s")[1] + " ";
		}
	}

	/**
	 * @param substring
	 */
	private void setLableString(String substring) {
		if ( lableString == null )
		{
			lableString = new String( " " + substring + " " );
		}
		else 
		{
			lableString +=  substring + " ";
		}
	}

	/**
	 * @param substring
	 */
	private void setDCString(String substring) {
		if ( DCString == null )
		{
			DCString = new String( substring );
		}
		else
		{
			DCString += " " + substring;
		}
	}
	
	public String[] generateSymbolTable( String[] Str ) throws OutOfMemoryException
	{

		String temp = null;
		//TODO check for DCs And inject Stores at top of list

		for ( String instruction : Str )
		{
			if ( temp == null )
			{
				temp = symbolTable( instruction );
			}
			else
			{
				if ( ! isNull( symbolTable( instruction ) ) )
				{
					temp += " \n" + symbolTable( instruction );
				}
			}
			incLC();
		}
		
		if ( temp.contains("BR DONE") && ! temp.contains(" STOP") )
		{
			temp += " \n" + symbolTable( "DONE: STOP" );
			incLC();
		}
		String[] preReturnString = temp.split("\\s\n");
		generateVariables();
		generateConstants();
//		preReturnString[returnCount] = new String("BR TOP");
		//generateVariables(preReturnString);
		//String[] returnString = generateConstants(preReturnString);
		return preReturnString;
		
	}

	
	


	private String removeLables(String instruction) {

		if ( ! instruction.contains(":") )
		{
			return instruction;
		}
		else
		{	
			return  instruction.substring(instruction.indexOf(":") + 2);	
		}
	}


	/**
	 * @param preReturnString
	 * @throws OutOfMemoryException 
	 */
	private void generateVariables() throws OutOfMemoryException {
		// TODO Auto-generated method stub

		
		if ( getVarString() != null )
		{
			String[] t = getVarString().split("\\s");
			for ( String S : t )
			{
				
				if ( isNull(getDCString()) )
				{
					if ( isNull(getLableString() ) )
					{
						symbolTableDC( S + ": DC 0" );
					}
					else if ( ! isNull(getLableString() )  )
					{
						if ( ! getLableString().contains( " " + S + " " ) )
						{
							symbolTableDC( S + ": DC 0" );
						}
					}
				}
				else
				{
					if ( isNull(getLableString() ) && ! isNull(getDCString()) )
					{
						if ( ! getDCString().contains( S + ": DC 0" ) )
						{
							symbolTableDC( S + ": DC 0" );
						}
					}
				}
				
			}
			
		}
					
	}

	

	/**
	 * @param instruction
	 * @param b
	 * @throws OutOfMemoryException 
	 */
	private void symbolTableDC(String instruction) throws OutOfMemoryException {
		// TODO Auto-generated method stub
		String substring = instruction.substring(0, instruction.indexOf(":"));
		String[] constants = instruction.split("\\s");
		if( ! symbolExists( substring ) )
		{
			generateNode( instruction, getLocationCount(), getAvail() );
		}
		else
		{
			short value = Short.valueOf( constants[2] );
			getNode( substring ).setValue(value);
		}
	}

	private void generateConstants() throws OutOfMemoryException {
		if ( getConstString() != null )
		{
			String[] t = getConstString().split("\\s\n");
			for ( String S : t )
			{
				symbolTableDC( S );
			}
		}
	}


	/**
	 * @param postDCString
	 * @return 
	 * @throws OutOfMemoryException 
	 */
	@SuppressWarnings("unused")
	private String[] generateConstants(String[] instructions) throws OutOfMemoryException {
		// TODO insert constants
		
		int i = -1;
		String instructionString = null;
		String constString = null;
		for ( String S : instructions )
		{
			
			if ( S.split("\\s").length > 1 )
			{
				if ( isInt(S.split("\\s")[1] ) && ! symbolExists(S.split("\\s")[1]) )
				{
					if ( instructionString == null )
					{
						instructionString = new String(S);
					}
					else
					{
						instructionString += " \n" + S ;
					}
					
					if ( constString == null )
					{
						constString = new String(" \n" + S.split("\\s")[1] );
					}
					else
					{
						constString += " \n" + S.split("\\s")[1] ;
					}
					i++;
					generateNode( S.split("\\s")[1] + ":", i + instructions.length , i + instructions.length );
					
				}
				else
				{
					if ( instructionString == null )
					{
						instructionString = new String(S);
					}
					else
					{
						instructionString += " \n" + S ;
					}
				}
				
			}
			else
			{
				if ( instructionString == null )
				{
					
					if ( S.compareTo("EXIT") == 0)
					{
						instructionString = new String( "BR DONE" );
					}
					else
					{
						instructionString = new String(S);
					}
				}
				else
				{
					if ( S.compareTo("EXIT") == 0)
					{
						instructionString += " \nBR DONE";
					}
					else
					{
						instructionString += " \n" + S ;
					}
				}
			}

		
			
			
		}
		String S = new String(instructionString + constString + " \nSTOP" );
		String[] returnString = S.split("\\s\n");
		generateNode( "DONE: STOP", returnString.length - 1, returnString.length - 1 );
		return returnString;
	}

	/**
	 * @param s
	 * @param i
	 * @throws OutOfMemoryException 
	 */
	private void generateNode(String lable, int location, int avail) throws OutOfMemoryException {
		// TODO Auto-generated method stub
		String[] instruction = lable.split("\\s");
		String substring = instruction[0].substring(0, instruction[0].length() - 1);
		if ( lable.contains(": DC") || lable.contains(": dc"))
		{
			insertAtEnd( substring, (short) avail, Short.valueOf(instruction[2]) );
			//generateNode( instruction[2] + ": " + instruction[2], location, avail );
		}
		else if ( ! isInt(substring ) && lable.contains(":") )
		{
			insertAtEnd( substring, (short) location );
		}
		else if ( ! isInt(substring ) )
		{
			insertAtEnd( substring, (short) avail );
			decAvail();
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

		while ( getLast() != saveSpot && ! ( getLast().getName().contains(name) && getLast().getName().equals(name)))
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

	public SymbolNode getNode( String name )
	{
		SymbolNode saveSpot = last;
		SymbolNode returnNode = null;
		setLast(getSTLHead());

		while ( getLast() != saveSpot && ! ( getLast().getName().contains(name) && getLast().getName().equals(name)))
		{
	
			iterateLast();
			if ( getLast().getName().contains(name))
			{
				returnNode = getLast();
			}
		}
		

		setLast( saveSpot );
		if ( returnNode != null )
		{	
			return returnNode;
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
				+ "%s", Avail, locationCount, printTable());
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
