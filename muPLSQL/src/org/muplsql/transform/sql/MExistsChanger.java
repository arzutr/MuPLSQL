package org.muplsql.transform.sql;

import java.util.ArrayList;
import java.util.List;

import org.muplsql.mcase.Case;
import org.muplsql.model.MutationConstants;
import org.muplsql.transform.MITransformer;
import org.muplsql.transform.MutaOperatorType;

// not  exists (select 1 from dual ) and exists 


public class MExistsChanger extends MITransformer {
	public MExistsChanger() {
		this.mtype = MutaOperatorType.Mot037.name();// FIXME
		this.mopId = 37;
		oCcase = new Case();
	}

	@Override
	public List<String> mutateAsList(String value) {
		List<String> result = new ArrayList<>();
		
		if (value.equalsIgnoreCase(MutationConstants.EXISTS)) {
			if (hasNot()) {
				result.add(" exists (select 1 from dual ) and exists ");
			} else {
				result.add(" not exists ");
			}
		}
		
		return result;
	}
	
	private boolean hasNot() {
		return oCcase.preString != null && oCcase.preString.equals(MutationConstants.NOT);
	}
	

	
	@Override
	public  boolean hasPreCondition(){
		return false;
	}
}
