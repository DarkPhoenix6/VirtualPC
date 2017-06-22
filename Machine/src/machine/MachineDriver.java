/**
 * Machine
 * machine
 * MachineDriver.java
 */
package machine;

import java.io.IOException;

import compiler.OutOfMemoryException;

/**
 * @class	MachineDriver
 * @author 	Chris Fedun
 * @date	May 21, 2017
 *
 */
public class MachineDriver {

	
	/**
	 * @param args
	 * @throws NullPointerException 
	 * @throws RuntimeException 
	 * @throws Invalid 
	 * @throws OutOfMemoryException 
	 * @throws IOException 
	 * 
	 */
	public static void main(String[] args) throws RuntimeException, NullPointerException, Invalid, OutOfMemoryException, IOException {
		// TODO Auto-generated method stub
		
		Computer MYPC = new Computer( "myProgram.bin");
		MYPC.RunProgram();
		//MYPC.run();
	}

}
