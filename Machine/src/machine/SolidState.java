/**
 * Machine
 * machine
 * SolidState.java
 */
package machine;

/**
 * @class	SolidState
 * @author 	Chris 
 * @date	May 28, 2017
 *
 */
public class SolidState extends PersistantStorage {

	//private Program program;
	/**
	 * 
	 */
	public SolidState() {
		// TODO Auto-generated constructor stub
		this(100);
		
	}

	/**
	 * @param size
	 */
	public SolidState(int size) {
		super(size);
		//program = new Program();
		setLatency();
		writeToDisk();
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see machine.PersistantStorage#setLatency()
	 */
	@Override
	public void setLatency() {
		// TODO Auto-generated method stub
		setLatency(0.2076);
	}


	/* (non-Javadoc)
	 * @see machine.PersistantStorage#menu()
	 */
	@Override
	public short menu() {
		// TODO Auto-generated method stub
		return Program.menu();

	}

	/**
	 * @author Chris
	 */
	public void writeToDisk()
	{
		super.setFiles();
	}
}
