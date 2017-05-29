/**
 * Machine
 * machine
 * ProgramCounter.java
 */
package machine;

/**
 * @class	ProgramCounter
 * @author 	Chris 
 * @date	May 18, 2017
 *
 */
public class ProgramCounter extends MemElem {

	
	/**
	 * 
	 */
	public ProgramCounter() {
		// TODO Auto-generated constructor stub
		super(0);
	}

	/**
	 * @param elem
	 */
	public ProgramCounter(int elem) {
		super(elem);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param elem
	 */
	public ProgramCounter(short elem) {
		super(elem);
		
		// TODO Auto-generated constructor stub
	}

	
	public void inc()
	{
		super.write((short) (super.read() + 1));
	}
	
	public void branch( short val)
	{
		super.write(val);
	}



	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		int pc = this.read();
		return String.format("%+03d", pc);
	}
}
