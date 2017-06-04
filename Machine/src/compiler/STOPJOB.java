/**
 * Machine
 * compiler
 * STOP.java
 */
package compiler;

import java.util.Arrays;
import java.util.List;

/**
 * @class	STOP
 * @author 	Chris 
 * @date	Jun 3, 2017
 *
 */
public enum STOPJOB  implements EnumInterface {

	STOP,
	stop;

	/* (non-Javadoc)
	 * @see compiler.EnumInterface#getNames()
	 */
	
	public static String[] getNames(boolean isStatic) {
		// TODO Auto-generated method stub
		List<STOPJOB> L = Arrays.asList(STOPJOB.values());
		String Str = L.toString();
		String[] S = Str.substring(1, Str.length() - 1).split(",\\s");
		return S;
	}

	/* (non-Javadoc)
	 * @see compiler.EnumInterface#getNames()
	 */
	@Override
	public String[] getNames() {
		// TODO Auto-generated method stub
		List<STOPJOB> L = Arrays.asList(STOPJOB.values());
		String Str = L.toString();
		String[] S = Str.substring(1, Str.length() - 1).split(",\\s");
		return S;
	}
	
}
