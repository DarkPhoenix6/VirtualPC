/**
 * Machine
 * compiler
 * MLPIsTranslator.java
 */
package compiler;

/**
 * @class	MLPIsTranslator
 * @author 	Chris 
 * @date	Jun 3, 2017
 *
 */
public class MLPIsTranslator {


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


	String test = new String( "STOP");
	
	public MLPIsTranslator()
	{
		
		Avail = 99;
		locationCounter = 0;
		
	}
	
	
	public String[] translate( String[] instructionsList, SymbolTableList SymbolTable )
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
				if ( isInt( instructions[i + 1] ) )//TODO: if instructions[i + 1] is a integer 	then
					//			returnString[i / 2] = new String( String.valueOf(MULTIPLY + instructions[i + 1] ) );
				{
					returnString[ a ] = new String( String.valueOf(LOAD + Integer.valueOf(instructions[i + 1] ) ) );
				}
				else
				{
					returnString[a] = new String( String.valueOf(LOAD + SymbolTable.getLocation(instructions[i + 1] )) );
				}
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
		
		

			if ( instructions[i].contains("BRIF0") )
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
}
