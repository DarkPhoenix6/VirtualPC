/**
 * Machine
 * machine
 * InstructionRegister.java
 */
package machine;

/**
 * @class	InstructionRegister
 * @author 	Chris 
 * @date	May 18, 2017
 *
 */
public class InstructionRegister extends MemElem {

	//private MemElem[] OpCode;
	
	
	/**
	 * 
	 */
	public InstructionRegister() {
		// TODO Auto-generated constructor stub
		super(0);
	}

	/**
	 * @param elem
	 */
	public InstructionRegister(int elem) {
		super(elem);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param elem
	 */
	public InstructionRegister(short elem) {
		super(elem);
		// TODO Auto-generated constructor stub
	}

	public void setIR( short val )
	{
		super.write(val);
	}
	/**
	 * @return the opCode
	 */
	public short getOpCode() {
		return (short) ( super.read() / 100);
	}

	
	public short getOperand()
	{
		return (short) ( super.read() % 100);
	}

	
}
