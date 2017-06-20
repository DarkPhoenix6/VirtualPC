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
	
	/**
	 * @Description the current Instruction Location
	 */
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
	/**
	 * @Description The Current Loop Number 
	 */
	private int loopCount;
	/**
	 * @Description The Current If Statement Number 
	 */
	private int ifCount;
	private int ifFailCount;
	private int ifVarCount;
	/**
	 * @Description The Current Math Expression Variable Number 
	 */
	private int mathVarCount;
	

	
	public Translate()
	{
		
		Avail = 99;
		locationCounter = 0;
		loopCount = 0;
		ifCount = 0;
		ifVarCount = 0;
		ifFailCount = 0;
		mathVarCount = 0;
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

	/**
	 * @return the mathVarCount
	 */
	public int getMathVarCount() {
		return mathVarCount;
	}

	/**
	 * @return the ifVarCount
	 */
	public int getIfVarCount() {
		return ifVarCount;
	}

	/**
	 * @param arr
	 * @param string
	 * @return The First Index Of the Selected CharSequence or -1 of not found
	 */
	private int findIndexInArray(String[] arr, String string) throws InvalidIdentifierError{
		// TODO Auto-generated method stub
		int index = -1;
		for ( int i = 0; i < arr.length; i++ )
		{
			if ( arr[i].matches(string) )
			{
				index = i;
				break;
			}
		}
		
		if ( index != -1 )
		{
			return index;
		}
		else
		{
			return index;
		}
		
	}
	
	// Mutators 
	

	/**
	 * @param locationCounter the locationCounter to set
	 */
	public void setLocationCounter(int locationCounter) {
		this.locationCounter = locationCounter;
	}

	/**
	 * @param mathVarCount the mathVarCount to set
	 */
	public void setMathVarCount(int mathVarCount) {
		this.mathVarCount = mathVarCount;
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
	 * 
	 * @param instructions
	 * @param SymbolTable
	 * @return
	 */
	public String[] generateBinary( String[] instructions, SymbolTableList SymbolTable )
	{
		String[] instructionString = new String[ 100 ];
		int i = 0;
		short[] arr = new short[ instructions.length ];
		for ( int j = 0; j < instructions.length; j++ )
		{
			arr[ j ] = Short.valueOf( instructions[ j ] );
		}
		
		for ( ; i < instructions.length; i++ )
		{
			//instructionString[i] = Integer.toBinaryString( 0xFFFF & arr[i] );
			instructionString[i] = Integer.toBinaryString( arr[i] );
			instructionString[i] = "0000000000000000".substring(instructionString[i].length()) + instructionString[i];
		}
		
		for ( ; i <= SymbolTable.getAvail(); i++ )
		{
			instructionString[i] = "0000000000000000";
		}
		String temp = SymbolTable.getDataControls();
		
		if ( temp != null )
		{
			String[] S = temp.split("\\s\n");
			for ( String T : S )
			{
				String[] tem = T.split("\\s");
				int[] arr2 = new int[2];
				arr2[0] = Integer.valueOf( Short.valueOf( tem[0] ) );
				arr2[1] = Integer.valueOf( Short.valueOf( tem[1] ) );
				instructionString[arr2[0]] = Integer.toBinaryString( 0xFFFF & arr2[1] );
				instructionString[arr2[0]] = "0000000000000000".substring(instructionString[arr2[0]].length()) + instructionString[arr2[0]];
			}
		}
		
		
		return instructionString;
	}
	
	/**
	 * 
	 * @param instructions
	 * @param SymbolTable
	 * @return
	 */
	public String[] generateExecutableString( String[] instructions, SymbolTableList SymbolTable )
	{
		String[] instructionString = new String[ 100 ];
		int i = 0;
		
		for ( ; i < instructions.length; i++ )
		{

			instructionString[i] =  instructions[i];	
		}
		
		for ( ; i <= SymbolTable.getAvail(); i++ )
		{
			instructionString[i] = "0";
		}
		String temp = SymbolTable.getDataControls();
		
		if ( temp != null )
		{
			String[] S = temp.split("\\s\n");
			for ( String T : S )
			{
				String[] tem = T.split("\\s");
				int[] arr2 = new int[2];
				arr2[0] = Integer.valueOf( Short.valueOf( tem[0] ) );
				arr2[1] = Integer.valueOf( Short.valueOf( tem[1] ) );
				instructionString[arr2[0]] = Integer.toString( arr2[1] );

			}
		}
		
		
		return instructionString;
	}
	
	/**
	 * 
	 * @param instructions
	 * @return
	 */
	public short[] generateExecutableShort( String[] instructions )
	{
		short[] instructionShort = new short[ 100 ];
		int i = 0;

		for ( ; i < instructions.length; i++ )
		{

			instructionShort[i] =  Short.valueOf( instructions[i] );	
		}
		return instructionShort;
	}
	
	/**
	 * @Description Decrement Avail
	 */
	protected void decAvail()
	{
		setAvail(getAvail() - 1);
	}
	
	/**
	 * @Description Increment the LocationCounter
	 */
	protected void incLocationCounter()
	{
		this.setLocationCounter(this.getLocationCounter() + 1);
	}
	
	/**
	 * @Description Increment the MathVarCounter
	 */
	protected void incMathVarCounter()
	{
		this.setMathVarCount( this.getMathVarCount() + 1);
	}
	
	/**
	 * @Description Increment the IfVarCounter
	 */
	protected void incIfVarCounter()
	{
		this.setIfVarCount( this.getIfVarCount() + 1 );
	}
	/**
	 * 
	 * @param instructionsList
	 * @param SymbolTable
	 * @return The Assembly Code String[]
	 * @throws InvalidCommandException 
	 */
	public String[] translateToAssemblyCode( String[] instructionsList, SymbolTableList SymbolTable ) throws InvalidCommandException
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
		String[] returnString = new String[ instructionsList.length ];
		boolean addTwo = true;
		int a = 0;
		for ( int i = 0; i < instructions.length; )
		{
			addTwo = true;
	
	
			
			if ( instructions[i].contains("STOP") )
			{
				returnString[a] = new String( String.valueOf( STOP ) );
				addTwo = false;
				i++;
				a++;
			}	
			else if ( instructions[i].contains("LOAD") || instructions[i].contains("LD") )
			{
				
				
				returnString[a] = new String( String.valueOf(LOAD + SymbolTable.getLocation(instructions[i + 1] )) );
				
			}
			else if (instructions[i].contains("STORE") || 
					instructions[i].contains("STOR") || instructions[i].contains("STO") )
			{
				returnString[a] = new String( String.valueOf(
						STORE + SymbolTable.getLocation(instructions[i + 1] )) );
			}
			else if ( instructions[i].contains("ADD") )
			{
				returnString[a] = new String( String.valueOf(
						ADD + SymbolTable.getLocation(instructions[i + 1] )) );
			}		
		
			else if ( instructions[i].contains("SUB") )
			{
				returnString[a] = new String( String.valueOf(
						SUBTRACT + SymbolTable.getLocation(instructions[i + 1] )) );
			}
		
		
			else if ( instructions[i].contains("MULT") )
			{
				returnString[a] = new String( String.valueOf(
						MULTIPLY + SymbolTable.getLocation(instructions[i + 1] )) );
			}
		
		
		
		
			else if ( instructions[i].contains("DIV") )
			{
				returnString[a] = new String( String.valueOf(
						DIVIDE + SymbolTable.getLocation(instructions[i + 1] )) );
			}
		
		
		
			else if ( instructions[i].contains("IN") )
			{
				returnString[a] = new String( String.valueOf(
						INPUT + SymbolTable.getLocation(instructions[i + 1] )) );
			}
		
					
		
			else if ( instructions[i].contains("OUT") )
			{
				returnString[a] = new String( String.valueOf(
						OUTPUT + SymbolTable.getLocation(instructions[i + 1] )) );
			}
		
		
		
			else if ( instructions[i].contains("BR") || instructions[i].matches("BR")  )
			{
				returnString[a] = new String( String.valueOf(
						BRANCH + SymbolTable.getLocation(instructions[i + 1] )) );
			}
		
		
	
			else if ( instructions[i].contains("BRIF0") || instructions[i].contains("BZ") )
			{
				returnString[a] = new String( String.valueOf(
						BRANCHif0 + SymbolTable.getLocation(instructions[i + 1] )) );
			}
		
		
	
			else if ( instructions[i].contains("BRGTR") || instructions[i].contains("BGTR"))
			{
				returnString[a] = new String( String.valueOf(
						BRANCHifGTR0 + SymbolTable.getLocation(instructions[i + 1] )) );
			}
		
			else if ( instructions[i].contains("DC") )
			{
				returnString[a] = instructions[i+1];
			}
			else if( isInt(instructions[i]) )
			{
				returnString[a] = instructions[i];
				addTwo = false;
				i++;
				a++;
			}
			else
			{
				InvalidCommandException ICE = new InvalidCommandException();
				throw ICE;
			}
			
			if ( addTwo == true )
			{
				i += 2;
				a++;
			}
		}
		
		return returnString;
	}

	/**
	 * 
	 * @param instructions
	 * @return
	 */
	public String[] translate( String[] instructions )
	{
		//String[] temp = translateLoops( instructions );
		String[] temp = getMath( instructions );
		//return temp;
		//temp = getIfStatments( temp );
		//temp = getAssignments( temp );
		return getAssignments( temp );
		
		
	}

		
		


	public String[] translateWhileLoop( String[] instructions, int whileSpot, int elihwSpot )
	{
		String loopLable = new String("LOOP" + loopCount );
		String loopReturn = new String("LOOPRETURN" + loopCount );
		String[] returnString = null;
		
		String[] postWhile = null;
		/**
		 * @Description Contains the Array of instructions Before the WHILE Loop
		 */
		String[] preWhile = null;
		String[] whileString = new String[ elihwSpot - whileSpot - 2];
		String[] ifString = translateLogicSymbols( instructions[ whileSpot ].substring( 
				instructions[ whileSpot ].indexOf(":") + 2),  loopLable,  loopReturn );
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
				if ( i == 0 )
				{
					temp += loopReturn + ": " + postWhile[ i ] + " \n";
				}
				else
				{
					temp += postWhile[ i ] + " \n";
				}
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
	


	@SuppressWarnings("unused")
	public String[] translateForLoop ( String[] instructions, int forSpot, int rofSpot )
	{
		String[] returnString = null;
		String[] temp = new String[ rofSpot - forSpot ];
		String loopLable = new String("LOOP" + loopCount );
		String loopReturn = new String("LOOPRETURN" + loopCount );
		incLoopCount();
		return returnString;

	}
	
	public String[] translateLogicSymbols( String instruction, String loopContinue, String loopReturn )
	{
		//TODO
		String[] returnString = null;
		return returnString;
	}

	/**
	 * @Description Translates Mathematical Symbols Recursively with regards to BEDMAS ( Order of Operations )
	 * @param instruction
	 * @param isChecked
	 * @return
	 */
	public String translateMathSymbols( String instruction, boolean isChecked )
	{
		
		String STO = new String( "STO " );
		String LD = new String( "LD " );
		String ADD = new String( "ADD " );
		String SUB = new String( "SUB " );
		String MULT = new String( "MULT " );
		String DIV = new String( "DIV " );
		String[] temp = null;
		if ( instruction.contains( "+=" ) )
		{
			temp =  instruction.split("\\s\\+=\\s");
			return translateMathSymbols( temp[0] + " = " + temp[0] + " + " + temp[1], true );
		}
		else if ( instruction.contains("-=") )
		{
			temp =  instruction.split("\\s\\-=\\s");
			return translateMathSymbols( temp[0] + " = " + temp[0] + " - " + temp[1], true );
		}
		else if ( instruction.contains("*=") )
		{
			temp =  instruction.split("\\s\\*=\\s");
			return translateMathSymbols( temp[0] + " = " + temp[0] + " * " + temp[1], true );
		}
		
		else if ( instruction.contains("/=") )
		{
			temp =  instruction.split("\\s/=\\s");
			return translateMathSymbols( temp[0] + " = " + temp[0] + " / " + temp[1], true );
		}
		
		else if ( instruction.contains(" = ") && isChecked )
		{
			temp =  instruction.split("\\s=\\s");
			return translateMathSymbols( temp[1], true) + STO + translateMathSymbols( temp[0], true);
		}
		
		/*else if ( instruction.contains(" ** ") || instruction.contains(" POW ") ) // Check for Exponents
		{
			if ( instruction.contains(" ** ") )
			{
				temp =  instruction.split("\\s\\*\\*\\s", 2);
			}
			else
			{
				temp =  instruction.split("\\sPOW\\s", 2);
			}
			int j = 0;
			while ( j <= )
			return 
		}*/
		
		else if ( instruction.contains(" * ") || instruction.contains(" / ") ) // Check for Multiplication and Division
		{
			if ( ! instruction.contains(" = ") )
			{
				// Check to see which symbol is leftmost 
				if ( instruction.indexOf("*") < instruction.indexOf("/") && instruction.indexOf("*") > -1 || instruction.indexOf("/") == -1 )
					// If Multiplication
				{
					temp =  instruction.split("\\s\\*\\s", 2);
					return LD + translateMathSymbols( temp[0], true ) + MULT + translateMathSymbols( temp[1], true );
				}
				else
					// Else Division
				{
					temp =  instruction.split("\\s/\\s", 2);
					return LD + translateMathSymbols( temp[0], true ) + DIV + translateMathSymbols( temp[1], true );
				}
			}
			else
			{
					temp =  instruction.split("\\s=\\s", 2);
					return translateMathSymbols( temp[1], true ) + STO + translateMathSymbols( temp[0], true );	
			}
		}
		else if ( instruction.contains(" + ") || instruction.contains(" - ") ) // Check for Addition and Subtraction
		{
			if ( ! instruction.contains(" = ") )
			{
				// Check to see which symbol is leftmost 
				if ( instruction.indexOf("+") < instruction.indexOf("-") && instruction.indexOf("+") > -1 || instruction.indexOf("-") == -1 )
					// If Addition
				{
					temp =  instruction.split("\\s\\+\\s", 2);
					return LD + translateMathSymbols( temp[0], true ) + ADD + translateMathSymbols( temp[1], true );
				}
				else
					// Else Subtraction
				{
					temp =  instruction.split("\\s\\-\\s", 2);
					return LD + translateMathSymbols( temp[0], true ) + SUB + translateMathSymbols( temp[1], true );
				}
			}
			else
			{
				temp =  instruction.split("\\s=\\s", 2);
				return translateMathSymbols( temp[1], true ) + STO + translateMathSymbols( temp[0], true );	
			}
		}

		else
		{
			return instruction + " \n";
		}
	}

	/**
	 * @Description Simplifies Mathematical Equations Recursively with regards to BEDMAS ( Order of Operations )
	 * @param instruction
	 * @param isChecked
	 * @return The Simplified Equation
	 */
	public String SimplifyMathEquations( String instruction, boolean isChecked, String MathVarString )
	{
		
		
		String MathVar = null;
		String[] temp = null;
		String returnString = null;
		String mathVarLable = "Math" + this.getMathVarCount();
		int index = -1;
		if ( ! isChecked )
		{
			if ( instruction.contains( "+=" ) )
			{
				temp =  instruction.split("\\s\\+=\\s");
				return this.SimplifyMathEquations( temp[0] + " = " + temp[0] + " + " + temp[1], true, MathVarString );
			}
			else if ( instruction.contains("-=") )
			{
				temp =  instruction.split("\\s\\-=\\s");
				return this.SimplifyMathEquations( temp[0] + " = " + temp[0] + " - " + temp[1], true, MathVarString );
			}
			else if ( instruction.contains("*=") )
			{
				temp =  instruction.split("\\s\\*=\\s");
				return this.SimplifyMathEquations( temp[0] + " = " + temp[0] + " * " + temp[1], true, MathVarString );
			}
			
			else if ( instruction.contains("/=") )
			{
				temp =  instruction.split("\\s/=\\s");
				return this.SimplifyMathEquations( temp[0] + " = " + temp[0] + " / " + temp[1], true, MathVarString );
			}	
		}
		temp =  instruction.split("\\s");
/*		if ( instruction.contains(" ** ") || instruction.contains(" POW ") ) // Check for Exponents
		{
			if ( instruction.contains(" ** ") )
			{
				index = findIndexInArray( temp, "\\*\\*" );
			}
			else
			{
				index = findIndexInArray( temp, "POW" );
			}
			MathVar = simpleMathString(temp, mathVarLable, MathVarString, index);			
			returnString = generateSimplerMathString(temp, returnString, mathVarLable, index);
			this.incMathVarCounter();
			return SimplifyMathEquations( returnString, true, MathVar ) ;
			
		}
		else if ( instruction.contains(" * ") || instruction.contains(" / ") ) // Check for Multiplication and Division*/ 
		if ( instruction.contains(" * ") || instruction.contains(" / ") ) // Check for Multiplication and Division
		{
			// Check to see which symbol is leftmost 
			if ( instruction.indexOf("*") < instruction.indexOf("/") && instruction.indexOf("*") > -1 || instruction.indexOf("/") == -1 ) 
				// If Multiplication
			{
				index = findIndexInArray( temp, "\\*" );	
			}
			else
				// Else Division
			{
				index = findIndexInArray( temp, "/" );
			}
			
			MathVar = simpleMathString(temp, mathVarLable, MathVarString, index);			
			returnString = generateSimplerMathString(temp, returnString, mathVarLable, index);
			this.incMathVarCounter();
			return SimplifyMathEquations( returnString, true, MathVar ) ;
		}
		
		else if ( instruction.contains(" + ") || instruction.contains(" - ") ) // Check for Addition and Subtraction
		{
			// Check to see which symbol is leftmost 
			if ( instruction.indexOf("+") < instruction.indexOf("-") && instruction.indexOf("+") > -1 || instruction.indexOf("-") == -1 )
				// If Addition
			{
				index = findIndexInArray( temp, "\\+" );
			}
			else
				// Else Subtraction
			{
				index = findIndexInArray( temp, "\\-" );
			}
			MathVar = simpleMathString(temp, mathVarLable, MathVarString, index);			
			returnString = generateSimplerMathString(temp, returnString, mathVarLable, index);
			this.incMathVarCounter();
			return SimplifyMathEquations( returnString, true, MathVar ) ;
		}


		
		else
		{
			if (  MathVarString != null )
			{
				return MathVarString + instruction;
			}
			else
			{
				return instruction;
			}
		}
	}




	/**
	 * @param temp
	 * @param returnString
	 * @param mathVarLable
	 * @param index
	 * @return The Generated Simplified Equation Variables String
	 */
	private String generateSimplerMathString(String[] temp, String returnString, String mathVarLable, int index) {
		for ( int i = 0; i < temp.length; i++ )
		{
			if ( i == index - 1 )
			{
				if ( returnString == null )
				{
					returnString = mathVarLable;
				}
				else
				{
					returnString += " " + mathVarLable;
				}
			}
			else if ( i != index && i != index + 1 )
			{
				if ( returnString == null )
				{
					returnString = temp[i];
				}
				else
				{
					returnString += " " + temp[i];
				}
			}
		}
		return returnString;
	}

	/**
	 * 
	 * @param temp
	 * @param mathVarLable
	 * @param MathVarString
	 * @param index
	 * @return
	 */
	private String simpleMathString(String[] temp, String mathVarLable, String MathVarString, int index) {
		String tempString;
		tempString = new String( mathVarLable + " = " + temp[ index - 1 ] + " " + 
				temp[ index ] + " " + temp [ index + 1 ] + " \n" );
		if ( MathVarString == null )
		{
			MathVarString = tempString;
		}
		else
		{
			MathVarString += tempString;
		}
		return MathVarString;
	}
	
	/**
	 * 
	 * @param instruction
	 * @return
	 */
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

	/**
	 * 
	 * @param instruction
	 * @param branchLable
	 * @param branchReturn
	 * @param ANDFlag
	 * @param ORFlag
	 * @return
	 */
	public String translateIfs( String instruction, String branchLable, String branchReturn, boolean ANDFlag, boolean ORFlag  )
	{
		//TODO
		String[] S = null;
		String ifString = null;
		
		if ( instruction.contains(" AND ") || instruction.contains(" && ") ) // Contains conditional "AND"
		{
			if ( instruction.contains(" AND ") )
			{
				S = instruction.split( "\\sAND\\s", 2 );
			}
			else if ( instruction.contains(" && ") )
			{
				S = instruction.split( "\\s&&\\s", 2 );
			}
			return translateIfs( S[0], "IF" + getIfCount() + ": ", branchReturn, true, ORFlag ) + " \n" + 
				translateIfs( S[0], "IF" + getIfCount() + ": ", branchReturn, ANDFlag, ORFlag) ;
		}
		else if ( instruction.contains(" OR ") || instruction.contains(" || ") ) // Contains conditional "OR" 
		{
			if ( instruction.contains(" OR ") )
			{
				S = instruction.split( "\\sOR\\s", 2 );
			}
			else if ( instruction.contains(" || ") )
			{
				S = instruction.split( "\\s\\|\\|\\s", 2 );
			}
			return translateIfs( S[0], branchLable, branchReturn, ANDFlag, true ) + " \n" + branchLable +
					": " + translateIfs( S[0], "IF" + getIfCount() + ": ", branchReturn, ANDFlag, ORFlag);
		}
		else if ( instruction.contains(" CONDITIONAL ") || instruction.contains(" ?: ") )
		{
			
		}
		else if ( instruction.contains(" <= ") )
		{
			S = instruction.split( "\\s<=\\s", 2 );
			if( ! ORFlag )
			{
				ifString = new String( "LD " + translateIfs( S[0], "IF" + getIfCount() + ": ", branchReturn, ANDFlag, ORFlag) + " \nSUB " + 
						translateIfs( S[0], "IF" + getIfCount() + ": ", branchReturn, ANDFlag, ORFlag) + " \nBGTR " + branchReturn );
				return ifString;
			}
			else if( !ANDFlag && ORFlag )
			{
				ifString = new String( "LD " + translateIfs( S[1], "IF" + getIfCount() + ": ", branchReturn, ANDFlag, ORFlag) + " \nSUB " + 
						translateIfs( S[0], "IF" + getIfCount() + ": ", branchReturn, ANDFlag, ORFlag) + " \nBGTR " + branchLable + " \nBZ "
						+ branchLable);
				return ifString;
			}
			else if( ANDFlag && ORFlag )
			{
				ifString = new String( "LD " + translateIfs( S[1], "IF" + getIfCount() + ": ", branchReturn, ANDFlag, ORFlag) + " \nSUB " + 
						translateIfs( S[0], "IF" + getIfCount() + ": ", branchReturn, ANDFlag, ORFlag) + " \nBGTR " + branchLable + " \nBZ "
						+ branchLable);
				return ifString;
			}
		}
		else if ( instruction.contains(" < ") )
		{
			S = instruction.split( "\\s<\\s", 2 );
			if( ! ORFlag )
			{
				ifString = new String( "LD " + translateIfs( S[0], "IF" + getIfCount() + ": ", branchReturn, ANDFlag, ORFlag) + " \nSUB " + 
						translateIfs( S[0], "IF" + getIfCount() + ": ", branchReturn, ANDFlag, ORFlag) + " \nBGTR " + branchReturn + " \nBZ "
						+ branchReturn );
				return ifString;
			}
			else if( !ANDFlag && ORFlag )
			{
				ifString = new String( "LD " + translateIfs( S[1], "IF" + getIfCount() + ": ", branchReturn, ANDFlag, ORFlag) + " \nSUB " + 
						translateIfs( S[0], "IF" + getIfCount() + ": ", branchReturn, ANDFlag, ORFlag) + " \nBGTR " + branchLable);
				return ifString;
			}
			else if( ANDFlag && ORFlag )
			{
				ifString = new String( "LD " + translateIfs( S[1], "IF" + getIfCount() + ": ", branchReturn, ANDFlag, ORFlag) + " \nSUB " + 
						translateIfs( S[0], "IF" + getIfCount() + ": ", branchReturn, ANDFlag, ORFlag) + " \nBGTR " + branchLable);
				return ifString;
			}
		}
		else if ( instruction.contains(" >= ") )
		{
			S = instruction.split( "\\s>=\\s", 2 );
			if( ! ORFlag )
			{
				ifString = new String( "LD " + " \nSUB " + translateIfs( S[0], "IF" + getIfCount() + ": ", branchReturn, ANDFlag, ORFlag) + 
						translateIfs( S[0], "IF" + getIfCount() + ": ", branchReturn, ANDFlag, ORFlag) + " \nBGTR " + branchReturn );
				return ifString;
			}
			else if( !ANDFlag && ORFlag )
			{
				ifString = new String( "LD " + " \nSUB " + translateIfs( S[1], "IF" + getIfCount() + ": ", branchReturn, ANDFlag, ORFlag) + 
						translateIfs( S[0], "IF" + getIfCount() + ": ", branchReturn, ANDFlag, ORFlag) + " \nBGTR " + branchLable + " \nBZ "
						+ branchLable);
				return ifString;
			}
			else if( ANDFlag && ORFlag )
			{
				ifString = new String( "LD " + " \nSUB " + translateIfs( S[1], "IF" + getIfCount() + ": ", branchReturn, ANDFlag, ORFlag) + 
						translateIfs( S[0], "IF" + getIfCount() + ": ", branchReturn, ANDFlag, ORFlag) + " \nBGTR " + branchLable + " \nBZ "
						+ branchLable);
				return ifString;
			}
		}
		else if ( instruction.contains(" > ") )
		{
			S = instruction.split( "\\s>\\s", 2 );
			if( ! ORFlag )
			{
				ifString = new String( "LD " + " \nSUB " + translateIfs( S[0], "IF" + getIfCount() + ": ", branchReturn, ANDFlag, ORFlag) + 
						translateIfs( S[0], "IF" + getIfCount() + ": ", branchReturn, ANDFlag, ORFlag) + " \nBGTR " + branchReturn + " \nBZ "
						+ branchReturn );
				return ifString;
			}
			else if( !ANDFlag && ORFlag )
			{
				ifString = new String( "LD " + " \nSUB " + translateIfs( S[1], "IF" + getIfCount() + ": ", branchReturn, ANDFlag, ORFlag) + 
						translateIfs( S[0], "IF" + getIfCount() + ": ", branchReturn, ANDFlag, ORFlag) + " \nBGTR " + branchLable);
				return ifString;
			}
			else if( ANDFlag && ORFlag )
			{
				ifString = new String( "LD " + " \nSUB " + translateIfs( S[1], "IF" + getIfCount() + ": ", branchReturn, ANDFlag, ORFlag) + 
						translateIfs( S[0], "IF" + getIfCount() + ": ", branchReturn, ANDFlag, ORFlag) + " \nBGTR " + branchLable);
				return ifString;
			}
		}
		
		
		return instruction;
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
	 * @param ifVarCount the ifVarCount to set
	 */
	public void setIfVarCount(int ifVarCount) {
		this.ifVarCount = ifVarCount;
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

	/**
	 * 
	 * @param instructions
	 * @param count
	 * @param didCheck
	 * @return
	 */
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
				String[] temp = instructions[i].split("\\s");
				if ( this.findIndexInArray(temp, "WHILE:") != -1 )
				{
					count++;
					whileCount++;
					if ( count == 1 )
					{
						whileSpot = i;
					}
				}
				
				if ( this.findIndexInArray(temp, "ELIHW:") != -1 )
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
	
	/**
	 * 
	 * @param instructions
	 * @return
	 */
	public String[] getMath( String[] instructions )
	{
		String temp = null;
		String[] returnString = null;
		for ( String S : instructions )
		{
			String[] Temp = this.SimplifyMathEquations(S, false, null).split("\\s\n");
			this.setMathVarCount(0);
			for ( String I : Temp)
			{
				if ( temp == null )
				{
					temp = this.translateMathSymbols( I, false );
				}
				else
				{
					temp += this.translateMathSymbols( I, false );
				}
			}
		}
		
		returnString = temp.split("\\s\n");
		
		return returnString;
		
	}
	
	/**
	 * 
	 * @param instructions
	 * @return
	 */
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
	
	/**
	 * @param returnString
	 * @param i
	 * @param b
	 * @return returns the instruction Set with logic expressions translate
	 */
	@SuppressWarnings("unused")
	private String[] getConditionals(String[] instructions) {

		String temp = null;
		int ifCounter = 0;
		int elseIfCounter = 0;
		int elseCounter = 0;
		String[] returnString = null;
		for ( String S : instructions )
		{
			
			String[] Temp = this.SimplifyConditionals(S, null).split("\\s\n");
			if( this.findIndexInArray( Temp, "IF:" ) != -1 || this.findIndexInArray( Temp, "ELSEIF:" ) != -1 ||
					this.findIndexInArray( Temp, "ELSE:" ) != -1)
			{
				
				
			}
			else
			{
				if ( temp == null )
				{
					temp = this.translateConditionals( S, false );
				}
				else
				{
					temp += this.translateConditionals( S, false );
				}
			}
		}
		
		
		returnString = temp.split("\\s\n");
		
		return returnString;

	}
	
	/**
	 * 
	 * @param instructions
	 * @param lable
	 * @return
	 */
	private String getConditionals(String[] instructions, String lable ) {

		String temp = null;
		String returnString = null;
		for ( int i = 0; i < instructions.length; i++ )
		{
			
			String[] Temp = this.SimplifyConditionals(instructions[i], null).split("\\s\n");
			if( this.findIndexInArray( Temp, "IF:" ) != -1 || this.findIndexInArray( Temp, "ELSEIF:" ) != -1 )
			{
				this.setIfVarCount(0);
				for ( String I : Temp)
				{
					if ( temp == null )
					{
						temp = this.translateConditionals( I, false );
					}
					else
					{
						temp += this.translateConditionals( I, false );
					}
				}
				temp += "BZ IFRETURN" + this.getIfCount();
				this.incIfCount();
				
			}
			else
			{
				if ( temp == null )
				{
					temp = this.translateConditionals( instructions[i], false );
				}
				else
				{
					temp += this.translateConditionals( instructions[i], false );
				}
			}
		}
		
		return returnString;

	}
	
	/**
	 * @param instructions	The Instruction Set
	 * @param ifSpot 		The First Index of the If Statement
	 * @param lastIndex 	The Last Index of the If Statement
	 * @return
	 */
	private String[] getConditionals(String[] instructions, int ifSpot, int lastIndex) {
		// TODO Auto-generated method stub
		// preIF + translatedIF + IFBlock + IFRETURN + GetIfCount() + ": " + postIF
		String temp = null;
		String[] returnString = null;
		int index = -1;
		String ifReturn = null;
		for ( int i = 0; i < instructions.length; i++ )
		{
			if( i == ifSpot )
			{
				String[] Temp = instructions[i].split("\\s");
				String[] Temp2 = null;
				if( this.findIndexInArray( Temp, "IF:" ) != -1 )
				{					
					index = this.findIndexInArray( Temp, "IF:" );
					if ( index == 0 )
					{
						Temp2 = this.SimplifyConditionals( instructions[i].split("IF:\\s")[0], null).split("\\s\n");
					}
					else
					{
						Temp2 = this.SimplifyConditionals( instructions[i].split("IF:\\s")[1], null).split("\\s\n");
						if ( temp == null )
						{
							temp = instructions[i].split("IF:\\s")[0] +" ";
						}
						else
						{
							temp += instructions[i].split("IF:\\s")[0] +" ";
						}
					}
					
					ifReturn = new String( "IFRETURN" + this.getIfCount() );
					
				}
				else if( this.findIndexInArray( Temp, "ELSEIF:" ) != -1 )
				{
					index = this.findIndexInArray( Temp, "ELSEIF:" );
					Temp2 = this.SimplifyConditionals( instructions[i].split("ELSEIF:\\s")[1], null).split("\\s\n");
					ifReturn = Temp[1];
					
				}
				else if ( this.findIndexInArray( Temp, "ELSE:" ) != -1 )
				{
					index = this.findIndexInArray( Temp, "ELSE:" );
					Temp2 = this.SimplifyConditionals( instructions[i].split("ELSE:\\s")[1], null).split("\\s\n");
					ifReturn = Temp[1];
				}
				
				if ( Temp2 != null ) // If Temp2 Is NOT NULL
				{
					for ( String S : Temp2 )
						// Translate the Array of Conditionals
					{
						if ( temp == null )
						{
							temp = this.translateConditionals( S, false) + " \n";
						}
						else
						{
							temp += this.translateConditionals( S, false ) + " \n";
						}
					}
					
					temp += "BZ " + ifReturn + " \n";
				}
			}
			else if ( i == lastIndex )
			{
				if ( temp == null )
				{
					temp = "IFFAIL" + this.getIfCount() + ": " + ifReturn + ": ";
				}
				else
				{
					temp += "IFFAIL" + this.getIfCount() + ": " + ifReturn + ": ";
				}
			}
			else
			{
				if ( temp == null )
				{
					temp = instructions[i] + " \n";
				}
				else
				{
					temp += instructions[i] + " \n";
				}
			}
			
		}
		
		
		this.incIfCount();
		returnString = temp.split("\\s\n");
		
		return returnString;
		
	}

	/**
	 * 
	 * @param instructions
	 * @param countIf
	 * @param countIfElse
	 * @param countElse
	 * @param didCheck
	 * @return
	 */
	public String[] getIfStatment( String[] instructions, int countIf,
			int countIfElse, int countElse, boolean didCheck ) {
		// TODO Auto-generated method stub
		
		if ( countIf > 0 || countIfElse > 0 || countElse > 0 || didCheck == false)
		{
			int ifCounter = 0;
			int elseIfCounter = 0;
			int elseCounter = 0;
			int fiCounter = 0;
			int fiEsleCounter = 0;
			int esleCounter = 0;
			countIf = 0;
			int ifSpot = 0;
			int elseIfSpot = 0;
			int elseSpot = 0;
			for ( int i = 0; i < instructions.length; i++ )
			{
				String[] temp = instructions[i].split("\\s");
				if ( this.findIndexInArray(temp, "IF:") != -1 )
				{
					countIf++;
					ifCounter++;
					if ( countIf == 1 )
					{
						ifSpot = i;
					}
				}
				
				if ( this.findIndexInArray(temp, "FI:") != -1 )
				{
					fiCounter++;
				}
				
				if ( ifCounter == fiCounter && fiCounter > 0 )
				{
					String[] returnString = getConditionals( instructions, ifSpot, i );
					return getIfStatment( returnString, countIf, elseIfSpot, elseSpot, true );
				}
				
				if ( this.findIndexInArray(temp, "ELSEIF:") != -1 )
				{
					countIfElse++;
					elseIfCounter++;
					if ( countIfElse == 1 )
					{
						elseIfSpot = i;
					}
				}
				
				if ( this.findIndexInArray(temp, "FIESLE:") != -1 )
				{
					fiEsleCounter++;
				}
				
				if ( elseIfCounter == fiEsleCounter && fiEsleCounter > 0 )
				{
					String[] returnString = getConditionals( instructions, elseIfSpot, i );
					return getIfStatment( returnString, countIf, elseIfSpot, elseSpot, true );
				}

				if ( this.findIndexInArray(temp, "ELSE:") != -1 )
				{
					countElse++;
					elseCounter++;
					if ( countElse == 1 )
					{
						elseIfSpot = i;
					}
				}
				
				if ( this.findIndexInArray(temp, "FIESLE:") != -1 )
				{
					fiEsleCounter++;
				}
				
				if ( elseCounter == esleCounter && esleCounter > 0 )
				{
					String[] returnString = getConditionals( instructions, elseSpot, i );
					return getIfStatment( returnString, countIf, elseIfSpot, elseSpot, true );
				}
			}
			
			return getIfStatment( instructions, countIf, elseIfSpot, elseSpot, true ); // This sets Base Case
			
		}
		else
		{
			return instructions;
		}
		
		
	}
	
	/**
	 * @param s
	 * @param b
	 * @param object
	 * @return
	 */
	private String SimplifyConditionals(String instruction, String IfVarString) {
		// TODO Auto-generated method stub
		String IfVar = null;
		String[] temp = null;
		String returnString = null;
		String ifVarLable = "IF" + this.getIfVarCount();
		int index = -1;
		temp =  instruction.split("\\s");

		if ( instruction.contains(" < ") || instruction.contains(" <= ") ) // Check for Less-Than(-Equal-To)
		{
			// Check to see which symbol is leftmost 
			if ( findIndexInArray( temp, "<" ) < findIndexInArray( temp, "<=" ) && 
					findIndexInArray( temp, "<" ) > -1 || findIndexInArray( temp, "<=" ) == -1 ) 
				// If Less-Than
			{
				index = findIndexInArray( temp, "<" );	
			}
			else
				// Else Less-Than
			{
				index = findIndexInArray( temp, "<=" );
			}
			
			IfVar = simpleIfString(temp, ifVarLable, IfVarString, index);			
			returnString = generateSimplerIfString(temp, returnString, ifVarLable, index);
			this.incIfVarCounter();
			return SimplifyConditionals( returnString, IfVar ) ;
		}
		
		else if ( instruction.contains(" > ") || instruction.contains(" >= ") ) // Check for Greater-Than(-Equal-To)
		{
			// Check to see which symbol is leftmost 
			if ( findIndexInArray( temp, ">" ) < findIndexInArray( temp, ">=" ) && 
					findIndexInArray( temp, ">" ) > -1 || findIndexInArray( temp, ">=" ) == -1 ) 
				// If Greater-Than
			{
				index = findIndexInArray( temp, ">" );
			}
			else
				// Else Greater-Than-Equal-To
			{
				index = findIndexInArray( temp, ">=" );
			}
			IfVar = simpleIfString(temp, ifVarLable, IfVarString, index);			
			returnString = generateSimplerIfString(temp, returnString, ifVarLable, index);
			this.incIfVarCounter();
			return SimplifyConditionals( returnString, IfVar ) ;
		}
		
		else if ( instruction.contains(" == ") || instruction.contains(" != ") ) // Check for (Not-)Equal-To
		{
			// Check to see which symbol is leftmost 
			if ( findIndexInArray( temp, "==" ) < findIndexInArray( temp, "!=" ) && 
					findIndexInArray( temp, "==" ) > -1 || findIndexInArray( temp, "!=" ) == -1 ) 
				// If Equal-To
			{
				index = findIndexInArray( temp, "==" );
			}
			else
				// Else Not-Equal-To
			{
				index = findIndexInArray( temp, "!=" );
			}
			IfVar = simpleIfString(temp, ifVarLable, IfVarString, index);			
			returnString = generateSimplerIfString(temp, returnString, ifVarLable, index);
			this.incIfVarCounter();
			return SimplifyConditionals( returnString, IfVar ) ;
		}
		
		else if ( instruction.contains(" && ")  ) // Check for Conditional AND
		{
			
			index = findIndexInArray( temp, "&&" );
			IfVar = simpleIfString(temp, ifVarLable, IfVarString, index);			
			returnString = generateSimplerIfString(temp, returnString, ifVarLable, index);
			this.incIfVarCounter();
			return SimplifyConditionals( returnString, IfVar ) ;
		}

		else if ( instruction.contains(" || ")  ) // Check for Conditional OR
		{
			
			index = findIndexInArray( temp, "\\|\\|" );
			IfVar = simpleIfString(temp, ifVarLable, IfVarString, index);			
			returnString = generateSimplerIfString(temp, returnString, ifVarLable, index);
			this.incIfVarCounter();
			return SimplifyConditionals( returnString, IfVar ) ;
		}

		else if ( instruction.contains(" CONDITIONAL ") || instruction.contains(" ?: ") ) // Check for Conditionals
		{
			// Check to see which symbol is leftmost 
			if ( findIndexInArray( temp, "CONDITIONAL" ) < findIndexInArray( temp, "\\?:" ) && 
					findIndexInArray( temp, "CONDITIONAL" ) > -1 || findIndexInArray( temp, "\\?:" ) == -1 ) 
				// If CONDITIONAL
			{
				index = findIndexInArray( temp, "CONDITIONAL" );
			}
			else
				// Else ?:
			{
				index = findIndexInArray( temp, "\\?:" );
			}
			IfVar = simpleIfString(temp, ifVarLable, IfVarString, index);			
			returnString = generateSimplerIfString(temp, returnString, ifVarLable, index);
			this.incIfVarCounter();
			return SimplifyConditionals( returnString, IfVar ) ;
		}
		
		else
		{
			if (  IfVarString != null )
			{
				return IfVarString + instruction;
			}
			else
			{
				return instruction;
			}
		}
	}

	/**
	 * @param temp
	 * @param ifVarLable
	 * @param ifVarString
	 * @param index
	 * @return
	 */
	private String simpleIfString(String[] temp, String ifVarLable, String ifVarString, int index) {
		// TODO Auto-generated method stub
		String tempString;
		tempString = new String( ifVarLable + " = " + temp[ index - 1 ] + " " + 
				temp[ index ] + " " + temp [ index + 1 ] + " \n" );
		if ( ifVarString == null )
		{
			ifVarString = tempString;
		}
		else
		{
			ifVarString += tempString;
		}
		return ifVarString;
	}

	/**
	 * 
	 * @param temp
	 * @param returnString
	 * @param ifVarLable
	 * @param index
	 * @return
	 */
	private String generateSimplerIfString(String[] temp, String returnString, String ifVarLable, int index) {
		for ( int i = 0; i < temp.length; i++ )
		{
			if ( i == index - 1 )
			{
				if ( returnString == null )
				{
					returnString = ifVarLable;
				}
				else
				{
					returnString += " " + ifVarLable;
				}
			}
			else if ( i != index && i != index + 1 )
			{
				if ( returnString == null )
				{
					returnString = temp[i];
				}
				else
				{
					returnString += " " + temp[i];
				}
			}
		}
		return returnString;
	}

	/**
	 * 
	 * @param instruction
	 * @param isChecked
	 * @return
	 */
	public String translateConditionals( String instruction, boolean isChecked )
	{
		return null;
	}
}
