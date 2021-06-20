package org.muplsql.transform.rel;

import java.util.ArrayList;
import java.util.List;

import org.muplsql.mcase.Case;
import org.muplsql.transform.MITransformer;
import org.muplsql.transform.MutaOperatorType;

public class MLogicalConnectorChanger extends MITransformer {
	static String changeList[][] = new String[][] { { "and", "or" }, { "or", "and" } };

	public MLogicalConnectorChanger() {
		this.mtype = MutaOperatorType.Mot2.name();
		this.mopId = 2;
		oCcase = new Case();
	}

	@Override
	public List<String> mutateAsList(String value) {
		List<String> result = new ArrayList<>();

		for (int i = 0; i < 2; i++) {
			if (value.equalsIgnoreCase(changeList[i][0])) {
				result.add(changeList[i][1]);
			}
		}

		return result;
	}
	
	@Override
	public  boolean hasPreCondition(){
		return true;
	}
	
	@Override
	public boolean checkPreCondition(String value, String valueNext) {
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
