/**
 * Machine
 * compiler
 * Compiler.java
 */
package compiler;

import java.io.IOException;

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
    private Translate t;
	private MLPIsTranslator translate;
	

	public Compiler() {
		read = new ReadFile();
		write = new WriteFile();
		translate = new MLPIsTranslator();
		SymbolTable = new SymbolTableList();
	}

	public String[] compile( ) throws OutOfMemoryException, IOException
	{
		String[] s = new String[1];
		s[0] = new String( "ToBeCompiled.txt" );
		return compile( s );
		
	}
	
	public String[] compile( String[] args) throws OutOfMemoryException, IOException
	{
		read.openFile( args[0] );
		write.openFile( );
		String[] toWrite = SymbolTable.generateSymbolTable(read.getInstructions());
		write.writeFile(toWrite);
		read.closeFile();
		write.closeFile();
		read.openFile("InstructionsWritten.txt");
		write.openFile("MLPInstructions.txt");
		String[] MLPInstructions = translate.translate( read.getInstructions(), SymbolTable );
		write.writeFile(MLPInstructions);
		read.closeFile();
		write.closeFile();
		return MLPInstructions;
		
	}
	/**
	 * @param args
	 * @throws OutOfMemoryException 
	 * @throws InvalidCommandException 
	 * @throws IOException 
	 */
	public static void main(String[] args) throws OutOfMemoryException, InvalidCommandException, IOException {
		// TODO Auto-generated method stub
		Translate t;
		SymbolTableList SymbolTable;
		ReadFile read;
		WriteFile write;

		MLPIsTranslator translate;
		
		read = new ReadFile();
		write = new WriteFile();
		translate = new MLPIsTranslator();
		t = new Translate();

		SymbolTable = new SymbolTableList();
		passOne(t, SymbolTable, read, write);
		String[] MLPInstructions = passTwo(t, SymbolTable, read, write);
		write.openFile("SymbolTable.txt");
		write.writeFile(SymbolTable.toString());
		
		write.closeFile();
		writeBinary(t, SymbolTable, write, MLPInstructions);
		readBinary( read );
	}




	/**
	 * @param t
	 * @param SymbolTable
	 * @param read
	 * @param write
	 * @throws OutOfMemoryException
	 * @throws IOException
	 */
	private static void passOne(Translate t, SymbolTableList SymbolTable, ReadFile read, WriteFile write)
			throws OutOfMemoryException, IOException {
		read.openFile("NewTest.txt");
		//read.openFile("stuffTest.txt");
		//read.openFile("ToBeCompiled.txt");
		String[] preTrans = read.getInstructions();
		read.closeFile();
//		for (String string : preTrans) {
//			System.out.println(string);
//		}
		
		String[] trans = t.translate(preTrans);
		for (String string : trans) {
			System.out.println(string);

		}
		write.openFile();
		String[] toWrite = SymbolTable.generateSymbolTable(trans);
		
//		String[] toWrite = SymbolTable.generateSymbolTable(read.getInstructions());
		write.writeFile(toWrite);
//		read.closeFile();
		write.closeFile();
	}
	
	/**
	 * @param t
	 * @param SymbolTable
	 * @param read
	 * @param write
	 * @return
	 * @throws InvalidCommandException
	 * @throws IOException
	 */
	private static String[] passTwo(Translate t, SymbolTableList SymbolTable, ReadFile read, WriteFile write)
			throws InvalidCommandException, IOException {
		read.openFile("InstructionsWritten.txt");
///*		String[] test = read.getInstructions();
//		for ( String S : test )
//		{
//			System.out.println(S);
//		}*/
		write.openFile("MLPInstructions.txt");
		//String[] MLPInstructions = translate.translate( read.getInstructions(), SymbolTable );
		String[] MLPInstructions = t.translateToAssemblyCode( read.getInstructions(), SymbolTable );
		write.writeFile(MLPInstructions);
		read.closeFile();
		write.closeFile();
		return MLPInstructions;
	}


	/**
	 * @param t
	 * @param SymbolTable
	 * @param write
	 * @param MLPInstructions
	 * @throws IOException
	 */
	private static void writeBinary(Translate t, SymbolTableList SymbolTable, WriteFile write, String[] MLPInstructions)
			throws IOException {
		short[] MLPInstructionsBinary = t.generateExecutableShort( 
				t.generateExecutableString( MLPInstructions, SymbolTable ) );
		String[] MLPInstructionsBinaryString = t.generateBinary(MLPInstructions, SymbolTable);
		write.openBinaryFile();
		write.writeBinaryFile(MLPInstructionsBinary);
		write.closeFile();
		write.openFile("BinaryMLPInstructions.txt");
		write.writeFile(MLPInstructionsBinaryString);
		write.closeFile();
	}
	/**
	 * @param read2
	 * @throws IOException 
	 */
	private static void readBinary(ReadFile read2) throws IOException {
		// TODO Auto-generated method stub
		read2.openBinaryFile();
		short[] binFile = read2.readBinaryFile();
		read2.closeFile();
		for ( short s : binFile )
		{
			System.out.println(s);
		}
	}
	/**
	 * @return the read
	 */
	public ReadFile getRead() {
		return read;
	}

	/**
	 * @return the write
	 */
	public WriteFile getWrite() {
		return write;
	}

	/**
	 * @param read the read to set
	 */
	public void setRead(ReadFile read) {
		this.read = read;
	}

	/**
	 * @param write the write to set
	 */
	public void setWrite(WriteFile write) {
		this.write = write;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return String.format("%s\n", SymbolTable);
	}
	
	

}
