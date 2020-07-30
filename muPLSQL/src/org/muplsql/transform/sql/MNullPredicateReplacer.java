package org.muplsql.transform.sql;

import java.util.ArrayList;
import java.util.List;

import org.muplsql.mcase.Case;
import org.muplsql.transform.MITransformer;
import org.muplsql.transform.MutaOperatorType;

public class MNullPredicateReplacer extends MITransformer {

	public MNullPredicateReplacer() {
		this.mtype = MutaOperatorType.Mot019.name();//FIXME
		this.mopId =19;
		oCcase = new  Case();
	}

	@Override
	public List<String> mutateAsList(String value) {
		List<String> result = new ArrayList<>();

		if (value.equalsIgnoreCase("NULL") && hasNotNot())
			result.add(" not null"  );

		return result;
	}

	private boolean hasNotNot() {
		return oCcase.preString== null || !oCcase.preString.equalsIgnoreCase("not");
	}

	public static void main(String[] a) {

	}

	@Override
	public boolean checkPreCondition(String value, String valueNext) {
		return oCcase.whereStarted>0;
		//return value.equalsIgnoreCase(valueNext);
	}
	
	@Override
	public Case getCase() {
		return this.oCcase;
	}

	@Override
	public  boolean hasPreCondition(){
		return true;
	}
}
