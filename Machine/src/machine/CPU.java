/**
 * Machine
 * machine
 * CPU.java
 */
package machine;

/**
 * @class	CPU
 * @author 	Chris Fedun
 * @date	May 19, 2017
 *
 */
public class CPU {

	/**
	 * @Description Main Data Register
	 */
	private MemElem MDR; 
	/**
	 * @Description Memory Address Register
	 */
	private MemElem MAR; // Memory Address Register
	private Accumulator ACC;
	private ProgramCounter PC;
	private InstructionRegister IR;
	private short OpCode;
	private short Operand;
	private short ProgramLength;
	private final short STOP = 0;
	private final short LOAD = 1;
	private final short STORE = 2;
	private final short ADD = 3;
	private final short SUBTRACT = 4;
	private final short MULTIPLY = 5;
	private final short DIVIDE = 6;
	private final short INPUT = 7;
	private final short OUTPUT = 8;
	private final short BRANCH= 9;
	private final short BRANCHif0 = 10;
	private final short BRANCHifGTR0 = 11;
	private boolean InFlag;
	private boolean OutFlag;
	private boolean StoreFlag;
	private boolean LoadFlag;
	private boolean RunFlag;
	private boolean executeInstFlag;



	
	public CPU() throws Invalid {
		this.ACC = new Accumulator();
		this.PC = new ProgramCounter(0);
		this.IR = new InstructionRegister();
		this.setMDR(new MemElem());
		this.setMAR(new MemElem());
		setOpCode((short) 0);
		setOperand((short) 0);
		setInFlag(false);
		setOutFlag(false);
		setStoreFlag(false);
		setLoadFlag(false);
		setRunFlag(false);
		setProgramLength((short) 0);
		setExecuteInstFlag(true);
	}



	/**
	 * @return the aCC
	 */
	public Accumulator getACC() {
		return ACC;
	}

	/**
	 * @param aCC the aCC to set
	 */
	public void setACC(Accumulator aCC) {
		ACC = aCC;
	}

	/**
	 * @return the pC
	 */
	public ProgramCounter getPC() {
		return PC;
	}

	/**
	 * @param pC the pC to set
	 */
	public void setPC(ProgramCounter pC) {
		PC = pC;
	}

	/**
	 * @return the iR
	 */
	public InstructionRegister getIR() {
		return IR;
	}

	/**
	 * @param iR the iR to set
	 */
	public void setIR(InstructionRegister iR) {
		IR = iR;
	}

	public void PostfetchInstruction()
	{
		//TODO: Implement MAR
		this.IR.write( this.getMDR().read() );
		OpCode = this.IR.getOpCode();
		Operand = this.IR.getOperand();
		setMAR(getOperand());
		PC.inc();
	}
	
	/**
	 * 
	 * @throws Invalid
	 */
	public void execute() throws Invalid
	{
		//TODO: Implement MAR
		switch(OpCode)
		{
				case STOP: 
				{
					setRunFlag(false);
					break;
				}
				case LOAD:
				{
					this.ACC.write(this.getMDR().read());
					break;
				}
				case STORE:
				{
					
					this.setMDR(this.ACC.read());
					setStoreFlag(true);
					break;
				}
				case ADD:
				{
					this.ACC.add(this.getMDR().read());
					break;
				}
				case SUBTRACT:
				{
					this.ACC.subtract(this.getMDR().read());
					break;
				}
				case MULTIPLY:
				{
					this.ACC.multiply(this.getMDR().read());
					break;
				}
				case DIVIDE:
				{
					this.ACC.divide(this.getMDR().read());
					break;
				}
				case INPUT:
				{
					
					break;
				}
				case OUTPUT:
				{
					
					break;
				}
				case BRANCH:
				{
					this.PC.branch(Operand);
					break;
				}
				case BRANCHif0:
				{
					if ( this.ACC.isZero() == true )
					{
						this.PC.branch(Operand);
					}
					break;
				}
				case BRANCHifGTR0:
				{
					if ( this.ACC.isGreaterThanZero() == true )
					{
						this.PC.branch(Operand);
					}
					break;
				}
		}
		
		if ( OpCode != STORE )
		{
			//TODO: Implement MAR
			setOperand( );
		}
	}
	
	
	public void decode()
	{
		switch(OpCode)
		{
				
				case LOAD:
				{
					setLoadFlag(true);
					break;
				}
				case STORE:
				{
					//setStoreFlag(true);
					setMAR(getOperand());
					break;
				}
				case ADD:
				{
					setLoadFlag(true);
					break;
				}
				case SUBTRACT:
				{
					setLoadFlag(true);
					break;
				}
				case MULTIPLY:
				{
					setLoadFlag(true);
					break;
				}
				case DIVIDE:
				{
					setLoadFlag(true);
					break;
				}
				case INPUT:
				{
					setInFlag(true);
					break;
				}
				case OUTPUT:
				{
					setOutFlag(true);
					break;
				}
				
		}
	}
	
	/**
	 * @return the opCode
	 */
	public short getOpCode() {
		return OpCode;
	}


	/**
	 * @param opCode the opCode to set
	 */
	public void setOpCode(short opCode) {
		OpCode = opCode;
	}
	

	/**
	 * @return the operand
	 */
	public short getOperand() {
		//TODO: Implement MAR
		return Operand;
	}

	public void setOperand() {
		//TODO: Implement MAR
		Operand = this.getPC().read();
	}
	/**
	 * @param operand the operand to set
	 */
	public void setOperand(short operand) {
		//TODO: Implement MAR
		Operand = operand;
	}

	/**
	 * @return the inFlag
	 */
	public boolean isInFlag() {
		return InFlag;
	}

	/**
	 * @param inFlag the inFlag to set
	 */
	public void setInFlag(boolean inFlag) {
		InFlag = inFlag;
	}

	/**
	 * @return the outFlag
	 */
	public boolean isOutFlag() {
		return OutFlag;
	}

	/**
	 * @param outFlag the outFlag to set
	 */
	public void setOutFlag(boolean outFlag) {
		OutFlag = outFlag;
	}

	/**
	 * @return the storeFlag
	 */
	public boolean isStoreFlag() {
		return StoreFlag;
	}

	/**
	 * @param storeFlag the storeFlag to set
	 */
	public void setStoreFlag(boolean storeFlag) {
		StoreFlag = storeFlag;
	}

	/**
	 * @return the loadFlag
	 */
	public boolean isLoadFlag() {
		return LoadFlag;
	}

	/**
	 * @param loadFlag the loadFlag to set
	 */
	public void setLoadFlag(boolean loadFlag) {
		LoadFlag = loadFlag;
	}
	
	public void falseifyFlags()
	{
		LoadFlag = false;
		StoreFlag = false;
		InFlag = false;
		OutFlag = false;
	}

	
	/**
	 * @return
	 */
	public boolean getRunFlag() {
		// TODO Auto-generated method stub
		return RunFlag;
	}


	/**
	 * @param b
	 */
	public void setRunFlag(boolean b) {
		// TODO Auto-generated method stub
		RunFlag = b;
	}


	/**
	 * @return the MDR
	 */
	public MemElem getMDR() {
		return MDR;
	}


	/**
	 * @param val the MDR to set
	 */
	public void setMDR(short val) {
		MDR.write(val);
	}

	/**
	 * @param memElem
	 */
	private void setMDR(MemElem memElem) {
		// TODO Auto-generated method stub
		MDR = memElem;
	}

	/**
	 * @return the MAR
	 */
	public MemElem getMAR() {
		return MAR;
	}

	/**
	 * @param val the MDR value to set
	 */
	public void setMAR(short val) {
		MAR.write(val);
	}
	/**
	 * @param memElem the MAR to set
	 */
	public void setMAR(MemElem memElem) {
		MAR = memElem;
	}
	/**
	 * 
	 */
	public void preFetch() {
		// TODO Auto-generated method stub
		setLoadFlag(true);
	}

	/**
	 * @deprecated
	 */
	public void postFetch() {
		// TODO Auto-generated method stub
		this.PostfetchInstruction();
	}


	/**
	 * @param length
	 * @throws Invalid 
	 */
	public void setProgramLength(short length) throws Invalid {
		// TODO Auto-generated method stub
		if ( getProgramLength() != 0 )
		{
			Invalid LengthModificationError = new Invalid("The Program length has already been set by the program.");
			throw LengthModificationError;
		}
		else
		{
			ProgramLength = length;
		}
	}
	
	public short getProgramLength()
	{
		return ProgramLength;
	}
	
	/**
	 * @return
	 */
	public boolean executeInstructionFlag() {
		// TODO Auto-generated method stub
		return this.isExecuteInstFlag();
	}


	public void setExecuteInstructionFlag(boolean executeInstrustionFlag) {
		setExecuteInstFlag(executeInstrustionFlag);
	}

	/**
	 * @return the executeInstFlag
	 */
	private boolean isExecuteInstFlag() {
		return executeInstFlag;
	}


	/**
	 * @param executeInstFlag the executeInstFlag to set
	 */
	private void setExecuteInstFlag(boolean executeInstFlag) {
		this.executeInstFlag = executeInstFlag;
	}
	
	/**
	 *  (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		
		return String.format(
				"REGISTERS:\n"
				+ "Accumulator\t\t%s"
				+ "\n"
				+ "ProgramCounter\t\t%s"
				+ "\n"
				+ "InstructionRegister\t%s"
				+ "\n"
				+ "OperationCode\t\t%+03d"
				+ "\n"
				+ "Operand\t\t\t%+03d"
				+ "\n"
				+ "MainDataRegister\t%s"
				+ "\n", this.ACC.toString(), this.PC.toString(), this.IR.toString(), (int)this.OpCode, (int)this.Operand, this.MDR.toString() );
		
	}


}
