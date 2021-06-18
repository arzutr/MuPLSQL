package org.muplsql_full.transform.sql;

import java.util.ArrayList;
import java.util.List;

import org.muplsql_full.transform.MITransformer;
import org.muplsql_full.transform.MutaOperatorType;

public class MUnionReplacer extends MITransformer {

	static String changeList[][] = new String[][] { { "union", "union all" }, { "union all", "union" } ,{ "union", "intersect" },{ "union all", "intersect" } , { "union all", "minus" } ,{ "union", "minus" }, { "intersect", "minus" }};

	public MUnionReplacer() {
		this.mtype = MutaOperatorType.Mot21.name();
		this.mopId = 21;
	}

	@Override
	public List<String> mutateAsList(String value) {
		List<String> result = new ArrayList<>();

		for (int i = 0; i < 2; i++) {
			if (value.contains(changeList[i][0])) {
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
