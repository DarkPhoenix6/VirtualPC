/**
 * Machine
 * machine
 * Program.java
 */
package machine;

import java.util.Scanner;

/**
 * @class	Program
 * @author 	Chris Fedun
 * @date	May 26, 2017
 *
 */
public class Program {

	
	private final static short ADD = 0;
	private final static short SUBTRACT = 1;
	private final static short MULTIPLY = 2;
	private final static short DIVIDE = 3;
	private final static short GREATEST = 4;
	private final static short SMALLEST = 5;
	private final static short SUMMATIONOFSERIES = 6;
	private static final short numberOfFiles = 7;
	private static Scanner scan;
	
	/**
	 * 
	 */
	public Program() {
		// TODO Auto-generated constructor stub
		super();
	}



	public static void summation( short a, short b)
	{
		
	}
	
	public static short[] WhichIsGreater()
	{
		short[] program = { 799, 798, 199, 498, 1107, 898, 908, 899, 000 };
		return program;
	}
	
	/**
	 * @input 1 is the first number in the series
	 * @input 2 is the final number in the series
	 * @input 3 is the number of terms in the series
	 * @return The program to Sum a Geometric Series
	 */
	public static short[] summingAnArithmeticSeries() // Var c is number of terms
	{
		short[] program = { 799, 798, 797, 199, 398, 597, 610, 296, 896, 000, 002 };
		return program;
	}
	
	public static short[] Addition()
	{
		short[] program = { 799, 798, 199, 398, 297, 897, 000 };
		return program;
	}
	
	public static short[] Subtraction()
	{
		short[] program = { 799, 798, 199, 498, 297, 897, 000 };
		return program;
	}

	/**
	 * @return
	 */
	private static short[] WhichIsSmaller() {
		// TODO Auto-generated method stub
		short[] program = { 799, 798, 199, 498, 1107, 899, 908, 898, 000 };
		return program;
	}



	/**
	 * @return
	 */
	private static short[] Divide() {
		// TODO Auto-generated method stub
		short[] program = { 799, 798, 199, 698, 297, 897, 000 };
		return program;
	}



	/**
	 * @return
	 */
	private static short[] Multiply() {
		// TODO Auto-generated method stub
		short[] program = { 799, 798, 199, 598, 297, 897, 000 };
		return program;
	}

	/**
	 * 
	 */
	public static short[] getFiles( short number) {
		// TODO Auto-generated method stub
		switch ( number ) 
		{
			case ADD:
			{
				return Addition();
			}
			case SUBTRACT:
			{
				return Subtraction();
			}
			case MULTIPLY:
			{
				return Multiply();
			}
			case DIVIDE:
			{
				return Divide();
			}
			case GREATEST:
			{
				return WhichIsGreater();
			}
			case SMALLEST:
			{
				return WhichIsSmaller();
			}
			case SUMMATIONOFSERIES:
			{
				return summingAnArithmeticSeries();
			}
			
		}
		return null;
		
	}

	public static short menu()
	{
		short choice = 0;
		scan = new Scanner(System.in);
		String Menu = new String();
		Menu = "Please select a program:\n"
				+ " For Addition select: 0\n"
				+ " For Subtraction select: 1\n"
				+ " For Multiplication select: 2\n"
				+ " For Division Select: 3\n"
				+ " To Determine the Greater of Two Numbers Select: 4\n"
				+ " To Determine the Lesser of Two Numbers Select: 5\n"
				+ " To Get the Summation of a Arithmetic Series: Press 6\n"
				+ "\n"
				+ "\n Enter Selection: ";
		
		System.out.println(Menu);
		choice = scan.nextShort();
		return choice;
	}

	/**
	 * @return the number of files
	 */
	public static short getNumberoffiles() {
		return numberOfFiles;
	}
}
