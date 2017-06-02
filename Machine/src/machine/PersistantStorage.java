/**
 * Machine
 * machine
 * PersistantStorage.java
 */
package machine;

import java.util.Arrays;
import java.util.List;

/**
 * 
 * @class	PersistantStorage
 * @author 	Chris 
 * @date	May 26, 2017
 *
 */
public abstract class PersistantStorage extends MainMem{
	
	/**
	 * @var MFT is the Master File Table for the emulated filesystem
	 * @var MFT[0][x] is the index of the beginning of the file
	 * @var MFT[1][x] is the index of the end of the file
	 * 
	 */
	private MainMem[] MFT = new MainMem[2]; 
	private short endOfFinalFile;
	/**
	 * @var latency is the simulated latency of the "hardware"
	 */
	protected double latency; // Milliseconds 
	private double totalLatency; // Milliseconds
	
	/**
	 * @Description Default Constructor calls one-integer-paramater Constructor
	 */
	public PersistantStorage() 
	{
		this(1000);
	}


	/**
	 * @param size
	 */
	public PersistantStorage(int size) {
		super(size);
		initializeMFT(size/10);
		setEndOfFinalFile((short) 0);
		setLatency(100);
		setTotalLatency(0);
		// TODO Auto-generated constructor stub
	}

	
	/**
	 * @param size 
	 * @Description Initializes the array simulating the Master File Table
	 */
	private void initializeMFT(int size) {
		// TODO Auto-generated method stub
		for (int i = 0; i < 2; i++ )
		{
			MFT[i] = new MainMem(size);
		}
	}

	/**
	 * @return the endOfFinalFile
	 */
	public short getEndOfFinalFile() {
		return endOfFinalFile;
	}

	/**
	 * @param endOfFinalFile the endOfFinalFile to set
	 */
	public void setEndOfFinalFile(short endOfFinalFile) {
		this.endOfFinalFile += endOfFinalFile;
	}
	
	private void IncrementEndOfFinalFile()
	{
		this.setEndOfFinalFile((short) 1);
	}

	/**
	 * @return the latency
	 */
	public double getLatency() {
		return latency;
	}
	
	
	public long getLatencyMil() {
		return (long) latency;
	}

	public int getLatencyNano() {
		double nano = getLatency() * 1000000;
		int nanoResult = (int) (nano % 1000000);
		return nanoResult;
	}
	
	/**
	 * @return the totalLatency
	 */
	public double getTotalLatency() {
		return totalLatency;
	}

	/**
	 * @param totalLatency the totalLatency to set
	 */
	public void setTotalLatency(double totalLatency) {
		this.totalLatency = totalLatency;
	}
	
	public void setTotalLatency()
	{
		setTotalLatency( getTotalLatency() + getLatency() );
	}
	/**
	 * @param latency the latency to set in milliseconds
	 */
	public void setLatency(double latency) {
		// TODO Auto-generated method stub
		this.latency = latency;
		
	}
	
	/**
	 * 
	 * @param index
	 * @return the array containing the selected program
	 * @throws BadIndex If the Index is does not contain a program
	 */
	public List<MemElem> getProgram( short index ) throws BadIndex
	{
		if ( index < 0 || index >= Program.getNumberoffiles() ){
			BadIndex BadIndexError = new BadIndex("Bad Index Error:"
					+ " Out of Bounds");
			throw BadIndexError;
		}
		else{
			List<MemElem> gram = Arrays.asList( super.getMem()).subList(
					this.getMFTIndex((short) 0, index), this.getMFTIndex((short) 1, index) + 1);
			return gram;
		}
	}
	
	public void setFiles()
	{
		short[] arr = null;
		short k = 0;
		for (short i = 0; i < Program.getNumberoffiles() && this.getEndOfFinalFile() < super.getMem().length; i++ )
		{
			arr = Program.getFiles(i);
			this.setMFT( (short) 0, k, this.getEndOfFinalFile());
			for (short j = 0; j < arr.length && this.getEndOfFinalFile() < super.getMem().length; j++ )
			{
				implementLatency();
				write( this.getEndOfFinalFile(), arr[ j ] );
				this.IncrementEndOfFinalFile();
			}
			this.setMFT( (short) 1, k, (short) (this.getEndOfFinalFile() - 1) );
			
			k++;
		}	
	}

	/**
	 * @return the mFT
	 */
	public MainMem[] getMFT() {
		return MFT;
	}

	public short getMFTIndex(short row, short column )
	{
		return this.MFT[row].read(column);
	}


	/**
	 * @param mFT the MFT to set
	 */
	public void setMFT(MainMem[] mFT) {
		MFT = mFT;
	}
	
	/**
	 * 
	 * @param row
	 * @param column
	 * @param val
	 */
	public void setMFT( short row, short column, short val ) {
		MFT[row].write( column, val );
		
	}
	
	public abstract void setLatency();

	public abstract short menu();

	
	protected void implementLatency()
	{
		try 
		{
			Thread.sleep( this.getLatencyMil(), this.getLatencyNano() );
			setTotalLatency();
		}
		catch (InterruptedException IE) 
		{
			System.err.println("Bad Stuff Happened!");
		}
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		String instanceType = null;
		if ( this instanceof HardDrive){
			instanceType = "Hard Disk Drive";
		}
		else if ( this instanceof SolidState){
			instanceType = "Solid State Drive";
		}
		else {
			instanceType = "Drive of Unknown Medium";
		}
		return String.format("PersistantStorage: "
				+ "\n TYPE:\t%s"
				+ "\n endOfFinalFile=%s"
				+ "\n latency=%s"
				+ "\n latencyMS=%d"
				+ "\n latencyNS=%d"
				+ "\n TotalLatency= %f"
				+ "\n MFT[0]:"
				+ "\n%s"
				+ "\n \n"
				+ "MFT[1]: "
				+ "\n%s"
				+ "\n"
				+ "\n"
				+ "File System:\n"
				+ "%s", instanceType,
				endOfFinalFile, latency, getLatencyMil(), getLatencyNano(), getTotalLatency(), MFT[0].Print2String(), 
				MFT[1].Print2String(), super.Print2String());
	}




	
	

}
