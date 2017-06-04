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
/*	private STOPJOB stop;
	private LOADER load;
	private STOREENUM store;
	private ADDITION add;
	private SUBTRACTENUM sub;
	private MULTIPLYENUM mult;
	private DIVIDEENUM DIV;
	private INPUTENUM in;
	private OUTPUTENUM out;
	private BRANCHUNCONDITIONALLY branch;
	private BRANCHONLYIF0 branchIf0;
	private BRANCHIFGTR0 branchIfGTR0;
	private DataControl DC;*/

	String test = new String( "STOP");
	
	public MLPIsTranslator()
	{
		
		Avail = 99;
		locationCounter = 0;
		
	}
	
	public String[] translate( String[] instructionsList, SymbolTableList SymbolTable )
	{
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
		String[] returnString = new String[instructions.length / 2];
		Boolean addTwo = true;
	
		for ( int i = 0; i < instructions.length - 1; )
		{
			addTwo = true;

			if ( instructions[i].contains("STOP") )
			{
				returnString[i / 2] = new String( String.valueOf( STOP ) );
				addTwo = false;
				i++;
			}
			
			if ( instructions[i].contains("LOAD") || instructions[i].contains("LD") )
			{
				if ( isInt( instructions[i + 1] ) )//TODO: if instructions[i + 1] is a integer 	then
					//			returnString[i / 2] = new String( String.valueOf(MULTIPLY + instructions[i + 1] ) );
				{
					returnString[i / 2] = new String( String.valueOf(LOAD + Integer.valueOf(instructions[i + 1] ) ) );
				}
				else
				{
					returnString[i / 2] = new String( String.valueOf(LOAD + SymbolTable.getLocation(instructions[i + 1] )) );
				}
			}
		
		
		
			if (instructions[i].contains("STORE") || instructions[i].contains("STOR") )
			{
				returnString[i / 2] = new String( String.valueOf(STORE + SymbolTable.getLocation(instructions[i + 1] )) );
			}
		
		

			if ( instructions[i].contains("ADD") )
			{
				returnString[i / 2] = new String( String.valueOf(ADD + SymbolTable.getLocation(instructions[i + 1] )) );
			}
		
					
		
			if ( instructions[i].contains("SUB") )
			{
				returnString[i / 2] = new String( String.valueOf(SUBTRACT + SymbolTable.getLocation(instructions[i + 1] )) );
			}
		
		
			if ( instructions[i].contains("MULT") )
			{
				returnString[i / 2] = new String( String.valueOf(MULTIPLY + SymbolTable.getLocation(instructions[i + 1] )) );
			}
		
		
		
		
			if ( instructions[i].contains("DIV") )
			{
				returnString[i / 2] = new String( String.valueOf(DIVIDE + SymbolTable.getLocation(instructions[i + 1] )) );
			}
		
		
		
			if ( instructions[i].contains("IN") )
			{
				returnString[i / 2] = new String( String.valueOf(INPUT + SymbolTable.getLocation(instructions[i + 1] )) );
			}
		
					
		
			if ( instructions[i].contains("OUT") )
			{
				returnString[i / 2] = new String( String.valueOf(OUTPUT + SymbolTable.getLocation(instructions[i + 1] )) );
			}
		
		
		
			if ( instructions[i].contains("BR") )
			{
				returnString[i / 2] = new String( String.valueOf(BRANCH + SymbolTable.getLocation(instructions[i + 1] )) );
			}
		
		

			if ( instructions[i].contains("BRIF0") )
			{
				returnString[i / 2] = new String( String.valueOf(BRANCHif0 + SymbolTable.getLocation(instructions[i + 1] )) );
			}
		
		
	
			if ( instructions[i].contains("BRGTR") || instructions[i].contains("BGTR"))
			{
				returnString[i / 2] = new String( String.valueOf(BRANCHifGTR0 + SymbolTable.getLocation(instructions[i + 1] )) );
			}
		
					

		
			if ( instructions[i].contains("DC") )
			{
				returnString[i / 2] = instructions[i+1];
			}
			
			
			if ( addTwo == true )
			{
				i += 2;
			}
		}
		
		return returnString;
	}
/*	public String[] translate( String[] instructions, SymbolTableList SymbolTable )
	{
		String[] returnString = new String[instructions.length / 2];
		Boolean addTwo = true;
	
		for ( int i = 0; i < instructions.length - 1; )
		{
			addTwo = true;
			for (String B : stop.getNames(true) )
			{
				if ( instructions[i].contains(B) )
				{
					returnString[i / 2] = new String( String.valueOf( STOP ) );
					addTwo = false;
					i++;
				}
				
			}
			
			for (String B : load.getNames() )
			{
				if ( instructions[i].contains(B) )
				{
					if ( isInt( instructions[i + 1] ) )//TODO: if instructions[i + 1] is a integer 	then
						//			returnString[i / 2] = new String( String.valueOf(MULTIPLY + instructions[i + 1] ) );
					{
						returnString[i / 2] = new String( String.valueOf(LOAD + Integer.valueOf(instructions[i + 1] ) ) );
					}
					else
					{
						returnString[i / 2] = new String( String.valueOf(LOAD + SymbolTable.getLocation(instructions[i + 1] )) );
					}
				}
			}
			
			for (String B : store.getNames() )
			{
				if (instructions[i].contains(B) )
				{
					returnString[i / 2] = new String( String.valueOf(STORE + SymbolTable.getLocation(instructions[i + 1] )) );
				}
			}
			
			for (String B : add.getNames() )
			{
				if ( instructions[i].contains(B) )
				{
					returnString[i / 2] = new String( String.valueOf(ADD + SymbolTable.getLocation(instructions[i + 1] )) );
				}
			}
						
			for (String B : sub.getNames() )
			{
				if ( instructions[i].contains(B) )
				{
					returnString[i / 2] = new String( String.valueOf(SUBTRACT + SymbolTable.getLocation(instructions[i + 1] )) );
				}
			} 
			for (String B : mult.getNames() )
			{
				if ( instructions[i].contains(B) )
				{
					returnString[i / 2] = new String( String.valueOf(MULTIPLY + SymbolTable.getLocation(instructions[i + 1] )) );
				}
			}
			
			for (String B : DIV.getNames() )
			{
				if ( instructions[i].contains(B) )
				{
					returnString[i / 2] = new String( String.valueOf(DIVIDE + SymbolTable.getLocation(instructions[i + 1] )) );
				}
			}
			
			for (String B : in.getNames() )
			{
				if ( instructions[i].contains(B) )
				{
					returnString[i / 2] = new String( String.valueOf(INPUT + SymbolTable.getLocation(instructions[i + 1] )) );
				}
			}
						
			for (String B : out.getNames() )
			{
				if ( instructions[i].contains(B) )
				{
					returnString[i / 2] = new String( String.valueOf(OUTPUT + SymbolTable.getLocation(instructions[i + 1] )) );
				}
			} 
			
			for (String B : branch.getNames() )
			{
				if ( instructions[i].contains(B) )
				{
					returnString[i / 2] = new String( String.valueOf(BRANCH + SymbolTable.getLocation(instructions[i + 1] )) );
				}
			}
			
			for (String B : branchIf0.getNames() )
			{
				if ( instructions[i].contains(B) )
				{
					returnString[i / 2] = new String( String.valueOf(BRANCHif0 + SymbolTable.getLocation(instructions[i + 1] )) );
				}
			}
			
			for (String B : branchIfGTR0.getNames() )
			{
				if ( instructions[i].contains(B) )
				{
					returnString[i / 2] = new String( String.valueOf(BRANCHifGTR0 + SymbolTable.getLocation(instructions[i + 1] )) );
				}
			}
						
			for (String B : DC.getNames() )
			{
				if ( instructions[i].contains(B) )
				{
					returnString[i / 2] = instructions[i+1];
				}
			}
			
			if ( addTwo == true )
			{
				i += 2;
			}
		}
		
		return returnString;
	}*/

	/**
	 * @param string
	 * @return
	 */
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

	// Accessors


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
