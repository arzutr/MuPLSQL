package org.muplsql.transform;

import java.util.ArrayList;


/***
 * 
 * after ; point this class adds throw statement
 * 
 *  end of if or set or call proc or any transaction
 * 
 */
import java.util.List;
/****
 * 
 * 
 * @author arzubt
 * purpose: 
 * After every statement, broken code is added to the source code to raise error in runtime.
 * 
 */
public class MBomber extends MITransformer {
	static String changeList[][] = new String[][] { { ";", "; \n execute immediate '1=0'; " }};

	public MBomber() {
		this.mtype = MutaOperatorType.Mot030.name();
		this.mopId =30;
	}

	@Override
	public List<String> mutateAsList(String value) {
		List<String> result = new ArrayList<>();

		for (int i = 0; i < 1; i++) {
			if (value.equals(changeList[i][0])) {
				result.add(value.replace(changeList[i][0], changeList[i][1]));
			}
		}

		return result;
	}

	public static void main(String[] a) {
		for (int i = 0; i < 12; i++) {
			for (int j = 0; j < 2; j++) {
				System.out.println(i + " " + j + ": " + changeList[i][j]);
			}
		}

	}
}
