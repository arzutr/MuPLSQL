package org.muplsql.transform.sql;

import java.util.ArrayList;
import java.util.List;

import org.muplsql.mcase.Case;
import org.muplsql.model.MutationConstants;
import org.muplsql.transform.MITransformer;
import org.muplsql.transform.MutaOperatorType;

public class MSQLAbsConverter extends MITransformer {
	public MSQLAbsConverter() {
		this.mtype = MutaOperatorType.Mot16.name();// FIXME
		this.mopId = 16;
		oCcase = new Case();
	}

	@Override
	public List<String> mutateAsList(String value) {
		List<String> result = new ArrayList<>();
		//System.out.println("preString" + oCcase.preString);
		if (isNumber(value)) {
			double valueTemp = getNumber(value);
			if (valueTemp == 0) {
				result.add("-abs(1)");
			} else if  (valueTemp >0 && hasNotMinus()  ) {
				result.add("-1*abs(" + value + ")");
			} else  {
				result.add("1*-1*abs(" + value + ")");
			}
		}

		return result;
	}

	
	private boolean hasNotMinus() {
		return oCcase.preString != null && !oCcase.preString.equals(MutationConstants.MINUS);
	}
	
	@Override
	public boolean checkPreCondition(String value, String valueNext) {
		return !(oCcase.whereStarted == 0); // in where clauses

	}
	
	@Override
	public  boolean hasPreCondition(){
		return true;
	}
}
