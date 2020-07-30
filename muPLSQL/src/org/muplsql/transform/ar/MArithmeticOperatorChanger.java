package org.muplsql.transform.ar;

import java.util.ArrayList;
import java.util.List;

import org.muplsql.transform.MITransformer;
import org.muplsql.transform.MutaOperatorType;

//in future  select clauses will be detected in source code not used * in select but pk_course left  stillborn 072020

public class MArithmeticOperatorChanger extends MITransformer {
	 
	
	static String changeList[][] = new String[][] { { "+", "-" }, { "+", "*" }, { "+", "/" }, { "*", "-" },
			{ "*", "+" }, { "*", "/" }, { "/", "-" }, { "/", "*" }, { "/", "+" }, { "-", "+" }, { "-", "*" },
			{ "-", "/" } };

	public MArithmeticOperatorChanger() {
		this.mtype = MutaOperatorType.Mot001.name();
		this.mopId = 1;
	}

	@Override
	public List<String> mutateAsList(String value) {
		List<String> result  = new ArrayList<>();
		
		for (int i = 0; i < 12; i++) {
			if (value.equals(changeList[i][0])){
				result.add(value.replace(changeList[i][0], changeList[i][1]));
			}
		}

		return result;
	}

	public static void main(String[] a) {
		for (int i = 0; i <12; i++) {
			for (int j = 0; j < 2; j++) {
				System.out.println(i + " " + j + ": " + changeList[i][j]);
			}
		}

	}

}
