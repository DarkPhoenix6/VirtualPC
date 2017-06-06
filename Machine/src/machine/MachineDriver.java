/**
 * Machine
 * machine
 * MachineDriver.java
 */
package machine;



/**
 * @class	MachineDriver
 * @author 	Chris 
 * @date	May 21, 2017
 *
 */
public class MachineDriver {

	
	/**
	 * @param args
	 * @throws NullPointerException 
	 * @throws RuntimeException 
	 * @throws Invalid 
	 * 
	 */
	public static void main(String[] args) throws RuntimeException, NullPointerException, Invalid {
		// TODO Auto-generated method stub
		
		Computer MYPC = new Computer( args );
		MYPC.RunProgram();
		//MYPC.run();
	}

}
