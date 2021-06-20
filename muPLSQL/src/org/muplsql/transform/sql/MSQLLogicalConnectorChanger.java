package org.muplsql.transform.sql;

import java.util.ArrayList;
import java.util.List;

import org.muplsql.mcase.Case;
import org.muplsql.transform.MITransformer;
import org.muplsql.transform.MutaOperatorType;
/****
 * 
 * 
 * @author arzu
 *
 *
 * TF changer in sql statements 
 */
public class MSQLLogicalConnectorChanger extends MITransformer {
	static String changeList[][] = new String[][] { { "and", "or" }, { "or", "and" } };
	public MSQLLogicalConnectorChanger() {
		this.mtype = MutaOperatorType.Mot4.name();//FIXME
		this.mopId =4;
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
		return !(oCcase.whereStarted == 0); // in where clauses

	}
}