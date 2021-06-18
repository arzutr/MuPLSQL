package org.muplsql_full.transform.sql;

import java.util.ArrayList;


import java.util.List;

import org.muplsql_full.mcase.Case;
import org.muplsql_full.transform.MITransformer;
import org.muplsql_full.transform.MutaOperatorType;

import java.util.ArrayList;
import java.util.List;
//NLO 
public class MNLONullOtherReplacer extends MITransformer {

	public MNLONullOtherReplacer() {
		this.mtype = MutaOperatorType.Mot19.name();
		this.mopId =27;
		oCcase = new  Case();
	}

	@Override
	public List<String> mutateAsList(String value) {
		List<String> result = new ArrayList<>();

		if (value.equalsIgnoreCase("NULL") && hasNotNot()){
			if (isNumber(value)) {
				result.add("1"  );
			} else {
				result.add("'nostring'");
			}
		}

		return result;
	}

	private boolean hasNotNot() {
		return oCcase.preString== null || oCcase.preString.equalsIgnoreCase("=");
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
