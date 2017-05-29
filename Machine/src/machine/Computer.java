/**
 * Machine
 * machine
 * Computer.java
 */
package machine;

import java.util.List;

/**
 * @class	Computer
 * @author 	Chris Fedun
 * @date	May 25, 2017
 *
 */
public class Computer {
	private CPU cpu;
	private InputDev InDev;
	private OutputDev OutDev;
	private MainMem Memory;
	private PersistantStorage[] Storage;
	
	/**
	 * 
	 * @throws NullPointerException
	 * @throws Invalid
	 */
	public Computer() throws NullPointerException, Invalid {
		this.cpu = new CPU();
		this.InDev = new InputDev();
		this.OutDev = new OutputDev();
		this.Memory = new MainMem();
		this.Storage = new PersistantStorage[ 2 ];
		this.setStorage( (short) 1, new HardDrive());
		this.setStorage( (short) 0, new SolidState());
		LoadProgram(getStorage()[0].getProgram(getStorage()[0].menu()));
	}


	/**
	 * 
	 * @param program
	 * @throws Invalid 
	 * @throws NullPointerException 
	 */
	public Computer(short[] program) throws NullPointerException, Invalid {
		
		this.cpu = new CPU();
		this.InDev = new InputDev();
		this.OutDev = new OutputDev();
		this.Memory = new MainMem();
		LoadProgram(program);
	}

	public void RunProgram() throws Invalid
	{
		for (; ;) // ever
		{
			if (cpu.getRunFlag())
			{
				
				cpu.preFetch();
				busRefresh();
				cpu.PostfetchInstruction();
				cpu.decode();
				busRefresh();
				try {
					if ( cpu.executeInstructionFlag() )
						{cpu.execute();}
					busRefresh();
				} catch (Invalid e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					dump();
				}
				
				
			}
			else
			{
				break;
			}
		}
		dump();
		
	}

	/**
	 * @return
	 */
	private short getValue() {
		return this.Memory.read(cpu.getOperand());
	}

	
	/**
	 * 
	 * @deprecated
	 * @throws Invalid
	 */
	public void run() throws Invalid
	{
		while (cpu.getRunFlag())
		{
			cpu.preFetch();
			busRefresh();
			cpu.postFetch();
			cpu.decode();
			busRefresh();
			try {
				if ( cpu.executeInstructionFlag() )
				{
					cpu.execute();
				}
				busRefresh();
			} catch (Invalid e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				dump();
			}
		}
		
		dump();
		System.exit(0);
	}
	
	/**
	 * 
	 */
	private void dump() {
		// TODO Auto-generated method stub
		System.out.print(this.toString());
	}

	/**
	 * @throws Invalid 
	 * @Purpose Refreshes the virtual System Bus
	 */
	private void busRefresh() throws Invalid {
		// TODO Auto-generated method stub
		if ( cpu.isLoadFlag() )
		{
			this.cpu.setMDR(getValue());
			cpu.falseifyFlags();
		}
		else if ( cpu.isStoreFlag() )
		{
			//cpu.execute();
			this.Memory.write( cpu.getOperand(), this.cpu.getMDR().read() );
			cpu.falseifyFlags();
			cpu.setOperand();
			//cpu.setExecuteInstructionFlag(false);
		}
		else if ( cpu.isInFlag() )
		{
			this.cpu.setMDR(this.InDev.input());
			this.Memory.write( cpu.getOperand(), cpu.getMDR().read() );
			cpu.falseifyFlags();
		}
		else if ( cpu.isOutFlag() )
		{
			this.OutDev.output( getValue() );
			cpu.falseifyFlags();
		}
		else{
			cpu.falseifyFlags();
			cpu.setExecuteInstructionFlag(true);
		}
		
	}

	/**
	 * 
	 * @param program
	 * @throws NullPointerException
	 * @throws Invalid
	 * @deprecated
	 */
	private void LoadProgram(short[] program) throws NullPointerException, Invalid{
		// TODO Auto-generated method stub
		
		while ( this.cpu.getPC().read() < program.length ) // for each "x" in "program"
		{
			this.Memory.write(this.cpu.getPC().read(), program[this.cpu.getPC().read()]);
			this.cpu.getPC().inc();
		}
		
		/*for ( short x : program) // for each "x" in "program"
		{
			this.Memory.write(PC.read(), x);
			this.PC.inc();
		}*/
		this.cpu.setProgramLength((short) program.length);
		this.cpu.getPC().branch((short) 0);
		this.cpu.setRunFlag(true);
	}
	
	/**
	 * 
	 * @param program
	 * @throws NullPointerException
	 * @throws Invalid
	 */
	private void LoadProgram(List<MemElem> program) throws NullPointerException, Invalid {
		// TODO Auto-generated method stub
		while ( this.cpu.getPC().read() < program.toArray().length ) // for each "x" in "program"
		{
			this.Memory.write(this.cpu.getPC().read(), program.get(this.cpu.getPC().read()));
			this.cpu.getPC().inc();
			;
		}
		
		/*for ( short x : program) // for each "x" in "program"
		{
			this.Memory.write(PC.read(), x);
			this.PC.inc();
		}*/
		this.cpu.setProgramLength((short) program.toArray().length);
		this.cpu.getPC().branch((short) 0);
		this.cpu.setRunFlag(true);
	}
	
	/**
	 * @return the storage
	 */
	public PersistantStorage[] getStorage() {
		return Storage;
	}

	
	/**
	 * @param storage the storage to set
	 */
	public void setStorage( short index, PersistantStorage storage) {
		Storage[index] = storage;
	}
	
	@Override
	public String toString() {
		String pst = this.Memory.toString();
		String CPUStr = this.cpu.toString();
		return String.format(
				"%s"
				+ "%s"
				+ "%s", CPUStr, pst, Storage.toString());
		
	}
}
