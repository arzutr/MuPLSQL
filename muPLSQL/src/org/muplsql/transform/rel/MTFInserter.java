package org.muplsql_full.transform.rel;
/***
 * 3kasim2020 kullanilmiyor sanirim
 */
import java.util.ArrayList;
import java.util.List;

import org.muplsql_full.transform.MITransformer;
import org.muplsql_full.transform.MutaOperatorType;

public class MTFInserter extends MITransformer {
	static String changeList[][] = new String[][] { { "and", " and false and" }, { "or", " or true or" } };

	public MTFInserter() {/*
		this.mtype = MutaOperatorType.Mot3.name();
		this.mopId = 3;*/
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

	public static void main(String[] a) {
		for (int i = 0; i < 12; i++) {
			for (int j = 0; j < 2; j++) {
				System.out.println(i + " " + j + ": " + changeList[i][j]);
			}
		}

	}
}
