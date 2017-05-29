/**
 * Machine
 * machine
 * MainMem.java
 */
package machine;

/**
 * @class	MainMem
 * @author 	Chris Fedun
 * @date	May 18, 2017
 *
 */
public class MainMem {

	private MemElem[] mem;
	
	public MainMem()
	{
		mem = new MemElem[100];
		this.initializeMemory();
	}
	
	/**
	 * 
	 */
	public MainMem( int size) {
		// TODO Auto-generated constructor stub
		mem = new MemElem[ size ];
		this.initializeMemory();
	}
	
	/**
	 * 
	 */
	private void initializeMemory() {
		// TODO Auto-generated method stub
		int j = 0;
		while ( j < this.mem.length )
		{
			this.mem[j] = new MemElem();
			j++;
		}
	}
	
	/**
	 * @return the mem
	 */
	public MemElem[] getMem() {
		return mem;
	}
	
	/**
	 * @param mem the mem to set
	 */
	protected void setMem(MemElem[] mem) {
		this.mem = mem;
	}
	
	/**
	 * 
	 * @param loc
	 * @return
	 */
	public short read( short loc )
	{
		return this.mem[loc].read();
		
	}
	
	/**
	 * 
	 * @param loc
	 * @param val
	 */
	public void write( int loc, short val )
	{
		mem[ loc ].write( val );
	}
	
	/**
	 * 
	 * @param loc
	 * @param val
	 */
	public void write(short loc, MemElem val) {
		// TODO Auto-generated method stub
		mem[ loc ] = val;
	}
	
	private String TableHeader()
	{
		String TblHdr = new String();
		
		for ( int j = 0; j < this.getMem().length / 10 && j < 10 ; j++ )
		{
			TblHdr +=  String.format("%7d", j);
		}

		return String.format("  %s", TblHdr);
		
	}
	
	protected String Print2String() throws NullPointerException
	{
		String MemoryString = new String();
		
		MemoryString += TableHeader();
		MemoryString += "\n";
		MemoryString += " " + 0 + " ";
		for (int k = 0; k < this.getMem().length; k++)
	    {
			
			MemoryString += this.mem[k].toString() + " ";
	        if (k % 10 == 9)
	        {
	        	if ( k + 1 < this.getMem().length)
	        	{
		        	MemoryString += "\n";
		        	MemoryString += k + 1 + " ";
	        	}
			}
		}
		
		MemoryString += "\n";
		return MemoryString;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return String.format("MEMORY: \n"
				+ "%s", Print2String());
	}
	
	

	
}
