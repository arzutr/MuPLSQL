package org.muplsql.transform.ar;

import java.util.ArrayList;
import java.util.List;

import org.muplsql_full.mcase.Case;
import org.muplsql_full.transform.MITransformer;
import org.muplsql_full.transform.MutaOperatorType;

public class MArithmeticChanger extends MITransformer {

	static String changeList[][] = new String[][] { { "+", "-" }, { "+", "*" }, { "+", "/" }, { "*", "-" },
			{ "*", "+" }, { "*", "/" }, { "/", "-" }, { "/", "*" }, { "/", "+" }, { "-", "+" }, { "-", "*" },
			{ "-", "/" } };

	public MArithmeticChanger() {
		this.mtype = MutaOperatorType.Mot1.name();
		this.mopId = 1;
		oCcase = new Case();
	}

	@Override
	public List<String> mutateAsList(String value) {
		List<String> result = new ArrayList<>();

		if (oCcase.whereStarted==0 && oCcase.preString != null && 
				!(oCcase.preString.equalsIgnoreCase("SELECT") || oCcase.preString.equalsIgnoreCase("(") || oCcase.preString.equalsIgnoreCase(";") ||oCcase.preString.equalsIgnoreCase("=")|| oCcase.preString.equalsIgnoreCase(":=") || oCcase.preString.equalsIgnoreCase("'")  )){
			for (int i = 0; i < 12; i++) {
				if (value.equals(changeList[i][0])) {
				//	 System.out.println(value + " :oCcase.preString: " + oCcase.preString);
					result.add(value.replace(changeList[i][0], changeList[i][1]));
				}
			}
		}
		
		if (value.equalsIgnoreCase(";") ) {
			oCcase.reset();
		}

		if (value.equalsIgnoreCase("where"))
			oCcase.whereStarted++;
		
		oCcase.preString = value!=null && value.trim().equals("") || value.trim().equals(" ") || value.contains(" ")?oCcase.preString:value;
		
		 
		return result;
	}

	@Override
	public boolean hasPreCondition() {
		return true;
	}

	@Override
	public boolean checkPreCondition(String value, String valueNext) {
//		System.out.println("preString: " + value);
		return (oCcase.whereStarted == 0); // not in where clauses
	}

	public static void main(String[] a) {
		for (int i = 0; i < 12; i++) {
			for (int j = 0; j < 2; j++) {
				System.out.println(i + " " + j + ": " + changeList[i][j]);
			}
		}

	}

}
