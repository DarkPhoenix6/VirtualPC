/**
 * Machine
 * compiler
 * LOAD.java
 */
package compiler;

import java.util.Arrays;
import java.util.List;

/**
 * @class	LOAD
 * @author 	Chris Fedun
 * @date	Jun 3, 2017
 *
 */
public enum LOADER  implements EnumInterface {
	
	LOAD,
	LD;

	/* (non-Javadoc)
	 * @see compiler.EnumInterface#getNames()
	 */
	@Override
	public String[] getNames() {
		// TODO Auto-generated method stub
		List<LOADER> L = Arrays.asList(LOADER.values());
		String Str = L.toString();
		String[] S = Str.substring(1, Str.length() - 1).split(",\\s");
		return S;
	}
		
}
