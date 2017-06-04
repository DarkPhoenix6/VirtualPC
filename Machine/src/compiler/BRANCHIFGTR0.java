/**
 * Machine
 * compiler
 * BRANCHIFGTR0.java
 */
package compiler;

import java.util.Arrays;
import java.util.List;

/**
 * @class	BRANCHIFGTR0
 * @author 	Chris 
 * @date	Jun 3, 2017
 *
 */
public enum BRANCHIFGTR0 implements EnumInterface{

	BGTR,
	BRANCHIFGREATERTHAN0;

	/* (non-Javadoc)
	 * @see compiler.EnumInterface#getNames()
	 */
	@Override
	public String[] getNames() {
		// TODO Auto-generated method stub
		List<BRANCHIFGTR0> L = Arrays.asList(BRANCHIFGTR0.values());
		String Str = L.toString();
		String[] S = Str.substring(1, Str.length() - 1).split(",\\s");
		return S;
	}
}
