package org.muplsql.transform.sql;

import java.util.ArrayList;
import java.util.List;

import org.muplsql.transform.MITransformer;
import org.muplsql.transform.MutaOperatorType;
/***
 * 
 * @author arzubt
 *
 *
 * 		join is not used commonly
 * 
 *     in future left and right could be implemented if it will be required
 */
public class MJoinReplacer extends MITransformer {

	static String changeList[][] = new String[][] { { "join", "left join" }, { "join", "right join" } };

	public MJoinReplacer() {
		this.mtype = MutaOperatorType.Mot024.name();
		this.mopId = 24;
	}

	@Override
	public List<String> mutateAsList(String value) {
		List<String> result = new ArrayList<>();

		for (int i = 0; i < 2; i++) {
			if (value.equalsIgnoreCase(changeList[i][0])) {
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
