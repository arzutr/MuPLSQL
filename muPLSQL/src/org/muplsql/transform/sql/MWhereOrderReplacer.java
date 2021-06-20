package org.muplsql.transform.sql;

import java.util.ArrayList;
import java.util.List;

import org.muplsql.transform.MITransformer;
import org.muplsql.transform.MutaOperatorType;

public class MWhereOrderReplacer extends MITransformer {

	static String changeList[][] = new String[][] { { "asc", "desc" }, { "desc", "asc" } };

	public MWhereOrderReplacer() {
		this.mtype = MutaOperatorType.Mot18.name();
		this.mopId = 18;
	}

	@Override
	public List<String> mutateAsList(String value) {
		List<String> result = new ArrayList<>();

		for (int i = 0; i < 2; i++) {
			if (value.equalsIgnoreCase(changeList[i][0])) {
				result.add(changeList[i][1]); // instead of replace 062020 put value
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
