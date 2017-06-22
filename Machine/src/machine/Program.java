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
	private final static short TEST = 7;
	private static final short numberOfFiles = 8;
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

	private static short[] testProgram()
	{
		short[] program = { 184, 297, 197, 597, 296, 183, 196, 699, 295, 182,
				581, 294, 193, 299, 395, 292, 192, 484, 291, 191,
				394, 290, 190, 289, 199, 397, 296, 196, 293, 184,
				288, 889, 188, 480, 1137, 179, 938, 178, 287, 188, 
				480, 1044, 179, 945, 178, 286, 187, 386, 1152, 1052, 
				179, 953, 178, 285, 185, 1062, 189, 388, 296, 196,
				289, 889, 893, 193, 389, 296, 196, 293, 893, 970, 
				0, 0, 0, 0, 0, 0, 0, 0, 1, 0,
				-1, 9, 6, 2, 3, 0, 0, 0, 0, 0,
				0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
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
			case TEST:
			{
				return testProgram();
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
