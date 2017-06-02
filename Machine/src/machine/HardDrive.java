/**
 * Machine
 * machine
 * HardDrive.java
 */
package machine;

/**
 * @class	HardDrive
 * @author 	Chris 
 * @date	May 26, 2017
 *
 */
public class HardDrive extends PersistantStorage {

	private Program program;
	
	/**
	 * 
	 */
	public HardDrive() {
		this(1000);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param size
	 */
	public HardDrive(int size) {
		super(size);
		program = new Program();
		setLatency();
		this.writeToDisk();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param program
	 */
	public HardDrive(Program program) {
		super();
		this.program = program;
		setLatency();
	}
	
	public HardDrive(int size, Program program) {
		super(size);
		this.program = program;
		setLatency();
	}

	/**
	 * @return the program
	 */
	public Program getProgram() {
		return program;
	}

	/**
	 * @param program the program to set
	 */
	public void setProgram(Program program) {
		this.program = program;
	}
	
	/**
	 * @author Chris
	 */
	public void writeToDisk()
	{
		super.setFiles();
	}




	

	/* (non-Javadoc)
	 * @see machine.PersistantStorage#setLatency()
	 */
	@Override
	public void setLatency() {
		// TODO Auto-generated method stub
		this.setLatency(5.60314);
	}

	/* (non-Javadoc)
	 * @see machine.PersistantStorage#menu()
	 */
	@Override
	public short menu() {
		// TODO Auto-generated method stub
		return Program.menu();

	}
	
}
