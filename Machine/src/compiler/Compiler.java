/**
 * Machine
 * compiler
 * Compiler.java
 */
package compiler;

/**
 * @class	Compiler
 * @author 	Chris Fedun
 * @date	Jun 3, 2017
 *
 */
public class Compiler {


	
	protected SymbolTableList SymbolTable;
	private ReadFile read;
	private WriteFile write;

	private MLPIsTranslator translate;
	

	public Compiler() {
		read = new ReadFile();
		write = new WriteFile();
		translate = new MLPIsTranslator();
		SymbolTable = new SymbolTableList();
	}

	public String[] compile( )
	{
		String[] s = new String[1];
		s[0] = new String( "ToBeCompiled.txt" );
		return compile( s );
		
	}
	
	public String[] compile( String[] args)
	{
		read.openFile( args[0] );
		write.openFile( );
		String[] toWrite = SymbolTable.generateSymbolTable(read.getInstructions());
		write.writeFile(toWrite);
		read.closeFile();
		write.closeFile();
		read.openFile("InstructionsWritten.txt");
		String[] MLPInstructions = translate.translate( read.getInstructions(), SymbolTable );
		read.closeFile();
		return MLPInstructions;
		
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		SymbolTableList SymbolTable;
		ReadFile read;
		WriteFile write;

		MLPIsTranslator translate;
		
		read = new ReadFile();
		write = new WriteFile();
		translate = new MLPIsTranslator();


		SymbolTable = new SymbolTableList();
		read.openFile("ToBeCompiled.txt");
		write.openFile();
		String[] toWrite = SymbolTable.generateSymbolTable(read.getInstructions());
		write.writeFile(toWrite);
		read.closeFile();
		write.closeFile();
		read.openFile("InstructionsWritten.txt");
/*		String[] test = read.getInstructions();
		for ( String S : test )
		{
			System.out.println(S);
		}*/
		write.openFile("MLPInstructions.txt");
		String[] MLPInstructions = translate.translate( read.getInstructions(), SymbolTable );
		write.writeFile(MLPInstructions);
		read.closeFile();
		write.closeFile();
		write.openFile("SymbolTable.txt");
		write.writeFile(SymbolTable.toString());
		write.closeFile();
		
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return String.format("%s\n", SymbolTable);
	}
	
	

}
