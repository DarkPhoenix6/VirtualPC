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
	private STOP stop;
	private LOAD load;
	private STORE store;
	private ADDITION add;
	private SUBTRACT sub;
	private MULTIPLY mult;
	private DIVIDE DIV;
	private INPUT in;
	private OUTPUT out;
	private BRANCHUNCONDITIONALLY branch;
	private BRANCHONLYIF0 branchIf0;
	private BRANCHIFGTR0 branchIfGTR0;
	private DataControl DC;

	
	public MLPIsTranslator()
	{
		
		Avail = 99;
		locationCounter = 0;
		
	}
	
	public int translate( String[] instructions, SymbolTableList SymbolTable )
	{
		String[] returnString = new String[instructions.length / 2];
		for ( int i = 0; i < instructions.length; )
		{
			for (String B : branch.getNames() )
			{
				if ( B == instructions[i] )
				{
					returnString[i / 2] = new String( String.valueOf(BRANCH + SymbolTable.getLocation(instructions[i + 1] )) );
				}
			}
			
			for (String B : branchIf0.getNames() )
			{
				if ( B == instructions[i] )
				{
					returnString[i / 2] = new String( String.valueOf(BRANCHif0 + SymbolTable.getLocation(instructions[i + 1] )) );
				}
			}
			
			for (String B : branchIfGTR0.getNames() )
			{
				if ( B == instructions[i] )
				{
					returnString[i / 2] = new String( String.valueOf(BRANCHifGTR0 + SymbolTable.getLocation(instructions[i + 1] )) );
				}
			}
						
			for (String B : DC.getNames() )
			{
				if ( B == instructions[i] )
				{
					returnString[i / 2] = instructions[i+1];
				}
			}
			i += 2;
		}
		
		return ReturnString
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
