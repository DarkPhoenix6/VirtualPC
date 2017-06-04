/**
 * Machine
 * compiler
 * BRANCHIF0.java
 */
package compiler;

import java.util.Arrays;
import java.util.List;

/**
 * @class	BRANCHIF0
 * @author 	Chris 
 * @date	Jun 3, 2017
 *
 */
public enum BRANCHONLYIF0 implements EnumInterface {

	BRANCHif0,
	BRIF0,
	BRANCHIF0,
	BRANCHIf0;
	
	/* (non-Javadoc)
	 * @see compiler.EnumInterface#getNames()
	 */
	@Override
	public String[] getNames() {
		// TODO Auto-generated method stub
		List<BRANCHONLYIF0> L = Arrays.asList(BRANCHONLYIF0.values());
		String Str = L.toString();
		String[] S = Str.substring(1, Str.length() - 1).split(",\\s");
		return S;
		
	}
	
}
