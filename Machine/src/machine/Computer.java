/**
 * Machine
 * machine
 * Computer.java
 */
package machine;

import java.util.List;
import compiler.Compiler;
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
	private Compiler compiler;
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
		this.compiler = new Compiler();
		this.Storage = new PersistantStorage[ 2 ];
		this.setStorage( (short) 1, new HardDrive());
		this.setStorage( (short) 0, new SolidState());
		LoadProgram(getStorage()[0].getProgram(getStorage()[0].menu()), 0);
	}

	/**
	 * 
	 * @param args
	 * @throws NullPointerException
	 * @throws Invalid
	 */
	public Computer( String[] args ) throws NullPointerException, Invalid {
		this.cpu = new CPU();
		this.InDev = new InputDev();
		this.OutDev = new OutputDev();
		this.Memory = new MainMem();
		this.compiler = new Compiler();
		this.Storage = new PersistantStorage[ 2 ];
		this.setStorage( (short) 1, new HardDrive());
		this.setStorage( (short) 0, new SolidState());
		LoadProgram(compiler.compile(args));
	}
	/**
	 * 
	 * @param program
	 * @throws Invalid 
	 * @throws NullPointerException 
	 * @deprecated
	 */
	public Computer(short[] program) throws NullPointerException, Invalid {
		
		this.cpu = new CPU();
		this.InDev = new InputDev();
		this.OutDev = new OutputDev();
		this.Memory = new MainMem();
		this.Storage = new PersistantStorage[ 2 ];
		this.setStorage( (short) 1, new HardDrive());
		this.setStorage( (short) 0, new SolidState());
		LoadProgram(program);
	}
	
	/**
	 * 
	 * @param program
	 * @throws Invalid 
	 * @throws NullPointerException 
	 * 
	 */
	public Computer(List<MemElem> program) throws NullPointerException, Invalid {
		
		this.cpu = new CPU();
		this.InDev = new InputDev();
		this.OutDev = new OutputDev();
		this.Memory = new MainMem();
		this.Storage = new PersistantStorage[ 2 ];
		this.setStorage( (short) 1, new HardDrive());
		this.setStorage( (short) 0, new SolidState());
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
		System.out.print( this.toString() );
		compiler.getWrite().openFile( "dump.txt" );
		compiler.getWrite().writeFile( this.toString() );
		compiler.getWrite().closeFile();
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
	 * 
	 */
	private void LoadProgram(List<MemElem> program) throws NullPointerException, Invalid {
		// TODO Auto-generated method stub
		while ( this.cpu.getPC().read() < program.toArray().length ) // for each "x" in "program"
		{
			this.Memory.write(this.cpu.getPC().read(), program.get(this.cpu.getPC().read()));
			this.cpu.getPC().inc();
		}
		

		this.cpu.setProgramLength((short) program.toArray().length);
		this.cpu.getPC().branch((short) 0);
		this.cpu.setRunFlag(true);
	}
	
	/**
	 * 
	 * @param instructions
	 * @throws NullPointerException
	 * @throws Invalid
	 */
	private void LoadProgram(String[] instructions) throws NullPointerException, Invalid
	{
		while ( this.cpu.getPC().read() < instructions.length ) // for each "x" in "program"
		{
			this.Memory.write( this.cpu.getPC().read(), Short.valueOf( instructions[ this.cpu.getPC().read() ] ) );
			this.cpu.getPC().inc();
		}
		

		this.cpu.setProgramLength((short) instructions.length );
		this.cpu.getPC().branch((short) 0);
		this.cpu.setRunFlag(true);
	}
	
	/**
	 * 
	 * @param program The List Containing the Selected Program
	 * @param index The Persistent Storage Drive Number
	 * @throws NullPointerException
	 * @throws Invalid
	 */
	private void LoadProgram(List<MemElem> program, int index) throws NullPointerException, Invalid {
		// TODO Auto-generated method stub
		while ( this.cpu.getPC().read() < program.toArray().length ) // for each "x" in "program"
		{
			this.getStorage()[index].implementLatency();
			this.Memory.write(this.cpu.getPC().read(), program.get(this.cpu.getPC().read()));
			this.cpu.getPC().inc();
		}
		
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
	 * @param index The Persistent Storage Drive Number
	 */
	public void setStorage( short index, PersistantStorage storage) {
		Storage[index] = storage;
	}
	
	/**
	 * @return
	 */
	private String PrintStorage() {
		// TODO Auto-generated method stub
		String storeageString = new String();
		for ( PersistantStorage s: Storage)
		{
			storeageString += s + "\n";
		}
		return storeageString;
	}
	
	/**
	 * 
	 */
	@Override
	public String toString() {
		String pst = this.Memory.toString();
		String CPUStr = this.cpu.toString();
		String storageString = PrintStorage();
		return String.format(
				"\n%s"
				+ "%s\n"
				+ "%s\n"
				+ "%s", CPUStr, pst, storageString, compiler.toString() );
		
	}


}
