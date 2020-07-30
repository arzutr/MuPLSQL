package org.muplsql.transform.sql;

import java.util.ArrayList;
import java.util.List;

import org.muplsql.mcase.Case;
import org.muplsql.model.MutationConstants;
import org.muplsql.transform.MITransformer;
import org.muplsql.transform.MutaOperatorType;
/****
 * 
 * @author arzu
 *
 * FIXME in future minus and intersect keywords could be added and will  put in  PLSQL operator
 */
public class MUnionReplacer extends MITransformer {

	static String changeList[][] = new String[][] { { "union", "union all" }, { "union all", "union" } };

	public MUnionReplacer() {
		this.mtype = MutaOperatorType.Mot021.name();
		this.mopId = 21;
		oCcase = new Case();
	}

	@Override
	public List<String> mutateAsList(String value) {
		List<String> result = new ArrayList<>();

		for (int i = 0; i < 2; i++) {
			if (value.equalsIgnoreCase(changeList[i][0])) {
				result.add( changeList[i][1]);
			}
		}
		
		if (value.equalsIgnoreCase(MutationConstants.ALL)) {
			if (hasUnion()) {
				result.add( " ");
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
	
	private boolean hasUnion() {
		return oCcase.preString != null && !oCcase.preString.equalsIgnoreCase(MutationConstants.UNION);
	}
		
	@Override
	public  boolean hasPreCondition(){
		return true;
	}
}
