/**
 * Machine
 * compiler
 * Translate.java
 */
package compiler;

/**
 * @class	Translate
 * @author 	Chris Fedun
 * @date	Jun 7, 2017
 *
 */
public class Translate {
	
	private int locationCounter;
	/**
	 * @Description the next available Data Memory Location
	 */
	private int Avail;
	private final int STOP = 0;
	private final int LOAD = 100;
	private final int STORE = 200;
	private final int ADD = 300;
	private final int SUBTRACT = 400;
	private final int MULTIPLY = 500;
	private final int DIVIDE = 600;
	private final int INPUT = 700;
	private final int OUTPUT = 800;
	private final int BRANCH= 900;
	private final int BRANCHif0 = 1000;
	private final int BRANCHifGTR0 = 1100;
	private int loopCount;
	private int ifCount;
	
	private String[] instructionSet;
	
	public Translate()
	{
		
		Avail = 99;
		locationCounter = 0;
		loopCount = 0;
		ifCount = 0;
	}
	
	
	public String[] translateToAssemblyCode( String[] instructionsList, SymbolTableList SymbolTable )
	{
		System.out.println(SymbolTable.toString());
		String Str = null;
		for ( String S: instructionsList)
		{
			if (Str == null)
			{
				Str = S;
			}
			else
			{
				Str += " " + S;
			}
		}
		String[] instructions = Str.split("\\s");		
		String[] returnString = new String[ 1 + (instructions.length / 2) ];
		boolean addTwo = true;
		int a = 0;
		for ( int i = 0; i < instructions.length - 1; )
		{
			addTwo = true;

			if ( instructions[i].contains("STOP") )
			{
				returnString[a] = new String( String.valueOf( STOP ) );
				addTwo = false;
				i++;
				a++;
			}
			
			if ( instructions[i].contains("LOAD") || instructions[i].contains("LD") )
			{
				
				
				returnString[a] = new String( String.valueOf(LOAD + SymbolTable.getLocation(instructions[i + 1] )) );
				
			}
		
		
		
			if (instructions[i].contains("STORE") || instructions[i].contains("STOR") || instructions[i].contains("STO") )
			{
				returnString[a] = new String( String.valueOf(STORE + SymbolTable.getLocation(instructions[i + 1] )) );
			}
		
		

			if ( instructions[i].contains("ADD") )
			{
				returnString[a] = new String( String.valueOf(ADD + SymbolTable.getLocation(instructions[i + 1] )) );
			}
		
					
		
			if ( instructions[i].contains("SUB") )
			{
				returnString[a] = new String( String.valueOf(SUBTRACT + SymbolTable.getLocation(instructions[i + 1] )) );
			}
		
		
			if ( instructions[i].contains("MULT") )
			{
				returnString[a] = new String( String.valueOf(MULTIPLY + SymbolTable.getLocation(instructions[i + 1] )) );
			}
		
		
		
		
			if ( instructions[i].contains("DIV") )
			{
				returnString[a] = new String( String.valueOf(DIVIDE + SymbolTable.getLocation(instructions[i + 1] )) );
			}
		
		
		
			if ( instructions[i].contains("IN") )
			{
				returnString[a] = new String( String.valueOf(INPUT + SymbolTable.getLocation(instructions[i + 1] )) );
			}
		
					
		
			if ( instructions[i].contains("OUT") )
			{
				returnString[a] = new String( String.valueOf(OUTPUT + SymbolTable.getLocation(instructions[i + 1] )) );
			}
		
		
		
			if ( instructions[i].contains("BR") )
			{
				returnString[a] = new String( String.valueOf(BRANCH + SymbolTable.getLocation(instructions[i + 1] )) );
			}
		
		

			if ( instructions[i].contains("BRIF0") || instructions[i].contains("BZ") )
			{
				returnString[a] = new String( String.valueOf(BRANCHif0 + SymbolTable.getLocation(instructions[i + 1] )) );
			}
		
		
	
			if ( instructions[i].contains("BRGTR") || instructions[i].contains("BGTR"))
			{
				returnString[a] = new String( String.valueOf(BRANCHifGTR0 + SymbolTable.getLocation(instructions[i + 1] )) );
			}
		
					

		
			if ( instructions[i].contains("DC") )
			{
				returnString[a] = instructions[i+1];
			}
			
			
			if ( addTwo == true )
			{
				i += 2;
				a++;
			}
		}
		
		return returnString;
	}
	
	
	// Accessors

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
	 * @return the locationCounter
	 */
	public int getLocationCounter() {
		return locationCounter;
	}

	/**
	 * @return the loopCount
	 */
	public int getLoopCount() {
		return loopCount;
	}


	/**
	 * @return the ifCount
	 */
	public int getIfCount() {
		return ifCount;
	}


	/**
	 * @return the avail
	 */
	public int getAvail() {
		return Avail;
	}

	// Mutators 
	


	/**
	 * @param locationCounter the locationCounter to set
	 */
	public void setLocationCounter(int locationCounter) {
		this.locationCounter = locationCounter;
	}

	/**
	 * @param avail the avail to set
	 */
	public void setAvail(int avail) {
		Avail = avail;
	}
	
	/**
	 * @param loopCount the loopCount to set
	 */
	public void setLoopCount(int loopCount) {
		this.loopCount = loopCount;
	}


	/**
	 * @param ifCount the ifCount to set
	 */
	public void setIfCount(int ifCount) {
		this.ifCount = ifCount;
	}


	/**
	 * @Description Decrement Avail
	 */
	protected void decAvail()
	{
		setAvail(getAvail() - 1);
	}
	
	/**
	 * @Description Increment LocationCounter
	 */
	protected void incLocationCounter()
	{
		setLocationCounter(getLocationCounter() + 1);
	}
	
	public String[] translate( String[] instructions )
	{
		String[] temp = translateLoops( instructions );
		temp = getMath( temp );
		//return temp;
		//temp = getIfStatments( temp );
		return getAssignments( temp );
	}

	public String[] translateWhileLoop( String[] instructions, int whileSpot, int elihwSpot )
	{
		String loopLable = new String("LOOP" + loopCount );
		String loopReturn = new String("LOOPRETURN" + loopCount );
		String[] returnString = null;
		String[] postWhile = null;
		String[] preWhile = null;
		String[] whileString = new String[ elihwSpot - whileSpot - 2];
		String[] ifString = translateLogicSymbols( instructions[ whileSpot ].substring(4), "BZ " + loopLable, "BR " + loopReturn );
		String temp = null;

		if ( whileSpot > 0 )
		{
			preWhile = new String[ whileSpot ];
			
			int i = 0;
			while ( i < whileSpot )
			{
				preWhile[ i ] = new String( instructions[ i ] );
			}
			
		}
		
		if ( elihwSpot < instructions.length - 1 )
		{
			postWhile = new String[ instructions.length - elihwSpot ];
			int i = elihwSpot + 1;
			while ( i < instructions.length )
			{
				postWhile[ i ] = new String( instructions[ i ] );
			}
			
		}
		int j = 0;
		for ( int i = whileSpot + 1; i < elihwSpot; i++ )
		{
			whileString[ j ] = new String( instructions[ i ] );
			j++;
		}
		
	
		if ( preWhile != null )
		{
			for ( String S : preWhile )
			{
				if ( temp == null )
				{
					temp = new String( S + " \n" );
				}
				else
				{
					temp += S + " \n";
				}
			}
		}
		
		for ( String S : whileString )
		{
			if ( temp == null )
			{
				temp = new String( S + " \n" );
			}
			else
			{
				temp += S + " \n";
			}
		}
		
		for ( String S : ifString )
		{
			temp += S + " \n";
		}
		
		if ( postWhile != null )
		{
			for ( int i = 0; i < postWhile.length - 1; i++ )
			{
				temp += postWhile[ i ] + " \n";
			}
			
			temp += postWhile[postWhile.length - 1];
		}
		
		returnString = temp.split("\\s\n");

		incLoopCount();
		return returnString;
	}

	
	public String[] translateLoops(String[] instructions)
	{
		String[] returnString = getForLoop( instructions, 0, false );
		returnString = getWhileLoop( returnString, 0, false );
		return returnString;
	}
	


	public String[] translateForLoop ( String[] instructions, int forSpot, int rofSpot )
	{
		String[] returnString = null;
		String[] temp = new String[ rofSpot - forSpot ];
		String loopLable = new String("LOOP" + loopCount );
		String loopReturn = new String("LOOPRETURN" + loopCount );
		incLoopCount();
		return returnString;

	}
	
	/**
	 * 
	 */
	private void incLoopCount() {
		// TODO Auto-generated method stub
		setLoopCount( getLoopCount() + 1 );
	}

	
	/**
	 * 
	 */
	private void incIfCount() {
		// TODO Auto-generated method stub
		setIfCount( getIfCount() + 1 );
	}
	/**
	 * @param instructions
	 * @return
	 */
	private String[] getForLoop(String[] instructions, int count, boolean didCheck) {
		// TODO Auto-generated method stub
		
		if ( count > 0 || didCheck == false)
		{
			int forCount = 0;
			int rofCount = 0;
			count = 0;
			int forSpot = 0;
			for ( int i = 0; i < instructions.length; i++ )
			{
				if ( instructions[i].regionMatches( 0, "FOR:", 0, 4 ) )
				{
					count++;
					forCount++;
					if ( count == 1 )
					{
						forSpot = i;
					}
				}
				
				if ( instructions[i].regionMatches( 0, "ROF:", 0, 4 ) )
				{
					rofCount++;
				}
				
				if ( forCount == rofCount && rofCount > 0 )
				{
					String[] returnString = translateForLoop( instructions, forSpot, i );
					return getForLoop( returnString, count, true );
				}

				
			}
			
			return getForLoop( instructions, count, true );
			
		}
		else
		{
			return instructions;
		}
		
		
	}


	public String[] getWhileLoop( String[] instructions, int count, boolean didCheck ) {
		// TODO Auto-generated method stub
		
		if ( count > 0 || didCheck == false)
		{
			int whileCount = 0;
			int elihwCount = 0;
			count = 0;
			int whileSpot = 0;
			for ( int i = 0; i < instructions.length; i++ )
			{
				if ( instructions[i].regionMatches( 0, "WHILE:", 0, 4 ) )
				{
					count++;
					whileCount++;
					if ( count == 1 )
					{
						whileSpot = i;
					}
				}
				
				if ( instructions[i].regionMatches( 0, "ELIHW:", 0, 4 ) )
				{
					elihwCount++;
				}
				
				if ( whileCount == elihwCount && elihwCount > 0 )
				{
					String[] returnString = translateWhileLoop( instructions, whileSpot, i );
					return getWhileLoop( returnString, count, true );
				}

				
			}
			
			return getWhileLoop( instructions, count, true );
			
		}
		else
		{
			return instructions;
		}
		
		
	}
	
	public String[] getMath( String[] instructions )
	{
		String temp = null;
		String[] returnString = null;
		for ( String S : instructions )
		{
			if ( temp == null )
			{
				temp = translateMathSymbols( S, false );
			}
			else
			{
				temp += translateMathSymbols( S, false );
			}
		}
		
		returnString = temp.split("\\s\n");
		
		return returnString;
		
	}
	
	public String translateMathSymbols( String instruction, boolean isChecked )
	{

		String STO = new String( "STO " );
		String LD = new String( "LD " );
		String ADD = new String( "ADD " );
		String SUB = new String( "SUB " );
		String MULT = new String( "MULT " );
		String DIV = new String( "DIV " );
		if ( instruction.contains( "+=" ) )
		{
			String[] temp =  instruction.split("\\s\\+=\\s");
			return translateMathSymbols( temp[0] + " = " + temp[0] + " + " + temp[1], true );
		}
		else if ( instruction.contains("-=") )
		{
			String[] temp =  instruction.split("\\s\\-=\\s");
			return translateMathSymbols( temp[0] + " = " + temp[0] + " - " + temp[1], true );
		}
		else if ( instruction.contains("*=") )
		{
			String[] temp =  instruction.split("\\s\\*=\\s");
			return translateMathSymbols( temp[0] + " = " + temp[0] + " * " + temp[1], true );
		}
		
		else if ( instruction.contains("/=") )
		{
			String[] temp =  instruction.split("\\s/=\\s");
			return translateMathSymbols( temp[0] + " = " + temp[0] + " / " + temp[1], true );
		}
		
		else if ( instruction.contains(" = ") && isChecked )
		{
			String[] temp =  instruction.split("\\s=\\s");
			return translateMathSymbols( temp[1], true) + STO + translateMathSymbols( temp[0], true);
		}
		
		else if ( instruction.contains(" * ") || instruction.contains(" / ") )
		{
			if ( ! instruction.contains(" = ") )
			{
				if ( instruction.indexOf("*") > instruction.indexOf("/") )
				{
					String[] temp =  instruction.split("\\s\\*\\s", 2);
					return LD + translateMathSymbols( temp[0], true ) + MULT + translateMathSymbols( temp[1], true );
				}
				else
				{
					String[] temp =  instruction.split("\\s/\\s", 2);
					return LD + translateMathSymbols( temp[0], true ) + DIV + translateMathSymbols( temp[1], true );
				}
			}
			else
			{
					String[] temp =  instruction.split("\\s=\\s", 2);
					return translateMathSymbols( temp[1], true ) + STO + translateMathSymbols( temp[0], true );	
			}
		}
		else if ( instruction.contains(" + ") || instruction.contains(" + ") )
		{
			if ( ! instruction.contains(" = ") )
			{
				if ( instruction.indexOf("+") > instruction.indexOf("-") )
				{
					String[] temp =  instruction.split("\\s\\+\\s", 2);
					return LD + translateMathSymbols( temp[0], true ) + ADD + translateMathSymbols( temp[1], true );
				}
				else
				{
					String[] temp =  instruction.split("\\s\\-\\s", 2);
					return LD + translateMathSymbols( temp[0], true ) + SUB + translateMathSymbols( temp[1], true );
				}
			}
			else
			{
				String[] temp =  instruction.split("\\s=\\s", 2);
				return translateMathSymbols( temp[1], true ) + STO + translateMathSymbols( temp[0], true );	
			}
		}
		
		else
		{
			return instruction + " \n";
		}
	}
	
	public String[] translateLogicSymbols( String instruction, String loopContinue, String loopReturn )
	{
		//TODO
		String[] returnString = null;
		return returnString;
	}
	
	public String[] getAssignments( String[] instructions){
		String temp = null;
		String[] returnString = null;
		for ( String S : instructions )
		{
			if ( temp == null )
			{
				temp = translateAssignmentOperators( S );
			}
			else
			{
				temp += translateAssignmentOperators( S );
			}
		}
		
		returnString = temp.split("\\s\n");
		
		return returnString;
	}
	public String translateAssignmentOperators ( String instruction )
	{
		
		if ( instruction.contains(" = ") )
		{
			String[] temp =  instruction.split("\\s=\\s");
			String returnString = "LD " + temp[1] + " \n" + "STO " + temp[0] + " \n";
			return returnString;
			
		}
		else
		{
			return instruction + " \n";
		}
				
		
	}
	
	public String translateIfs( String instruction, boolean ANDFlag, boolean ORFlag )
	{
		//TODO
		String ifString = null;
		incIfCount();
		return instruction;
	}
	
	/**
	 * @param returnString
	 * @param i
	 * @param b
	 * @return returns the instruction Set with logic expressions translate
	 */
	private String[] getIfStatments(String[] instructions) {

		String temp = null;
		String[] returnString = null;
		for ( String S : instructions )
		{
			if ( temp == null )
			{
				temp = translateIfs( S, false, false );
			}
			else
			{
				temp += translateIfs( S, false, false );
			}
		}
		
		returnString = temp.split("\\s\n");
		
		return returnString;
	}
}
