package org.muplsql.transform.sql;

import java.util.ArrayList;


import java.util.List;

import org.muplsql.mcase.Case;
import org.muplsql.transform.MITransformer;
import org.muplsql.transform.MutaOperatorType;

import java.util.ArrayList;
import java.util.List;

public class MNLFNullcheckReplacer extends MITransformer {

	public MNLFNullcheckReplacer() {
		this.mtype = MutaOperatorType.Mot27.name();//FIXME
		this.mopId =19;
		oCcase = new  Case();
	}

	@Override
	public List<String> mutateAsList(String value) {
		List<String> result = new ArrayList<>();

		if (oCcase.whereStarted > 0 && value.equalsIgnoreCase("NULL") && hasNotNot()) {
			result.add(" 1 "  );
			result.add(" 'emptystring' "  );
		}
			

		if (value.equalsIgnoreCase("where") )
			oCcase.whereStarted++;

		if (value.equalsIgnoreCase("select") )
			oCcase.selectStarted++;

		if (value.equalsIgnoreCase(";") ){
			oCcase.reset();
		}

		return result;
	}

	private boolean hasNotNot() {
		return oCcase.preString== null || oCcase.preString.contains("=");
	}

	public static void main(String[] a) {

	}

	@Override
	public boolean checkPreCondition(String value, String valueNext) {
		return oCcase.whereStarted>=0;
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
