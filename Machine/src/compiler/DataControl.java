/**
 * Machine
 * compiler
 * DataControl.java
 */
package compiler;

import java.util.Arrays;
import java.util.List;

/**
 * @class	DataControl
 * @author 	Chris Fedun
 * @date	Jun 3, 2017
 *
 */
public enum DataControl implements EnumInterface {
	DC,
	dc;

	/* (non-Javadoc)
	 * @see compiler.EnumInterface#getNames()
	 */
	@Override
	public String[] getNames() {
		// TODO Auto-generated method stub
		List<DataControl> L = Arrays.asList(DataControl.values());
		String Str = L.toString();
		String[] S = Str.substring(1, Str.length() - 1).split(",\\s");
		return S;
	}

}
