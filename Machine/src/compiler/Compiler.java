/**
 * Machine
 * compiler
 * Compiler.java
 */
package compiler;

import java.util.Arrays;
import java.util.List;

/**
 * @class	Compiler
 * @author 	Chris 
 * @date	Jun 3, 2017
 *
 */
public class Compiler {

	protected static SymbolTableList SymbolTable;
	private static ReadFile read;
	private static WriteFile write;
	
	private static MLPIsTranslator translate;
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		read = new ReadFile();
		write = new WriteFile();
		

		SymbolTable = new SymbolTableList();
		read.openFile("ToBeCompiled.txt");
		write.openFile();
		String[] toWrite = SymbolTable.generateSymbolTable(read.getInstructions());
		write.writeFile(toWrite);
		//write.writeFile(read.getInstructions());
		read.closeFile();
		write.closeFile();
		//read.openFile("InstructionsWritten.txt");
		//String[] MLPInstructions = read.getMLPI();*/
	}

}