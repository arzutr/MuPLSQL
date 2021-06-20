package org.muplsql.transform.sql;

import java.util.ArrayList;


import java.util.List;

import org.muplsql.mcase.Case;
import org.muplsql.transform.MITransformer;
import org.muplsql.transform.MutaOperatorType;

import java.util.ArrayList;
import java.util.List;
//OK 2020
public class MNLSNullInSelectListReplacer extends MITransformer {

	public MNLSNullInSelectListReplacer() {
		this.mtype = MutaOperatorType.Mot28.name();//FIXME
		this.mopId =28;
		oCcase = new  Case();
	}

	@Override
	public List<String> mutateAsList(String value) {
		List<String> result = new ArrayList<>();


		if (value.equalsIgnoreCase("select"))
			oCcase.selectStarted++;
		
		if (value.equalsIgnoreCase("NULL") ) {
			result.add(" '' "  );
			result.add(" 1 "  );
		}

		
		if (value.equalsIgnoreCase("from") )
			oCcase.selectStarted=0;
		
		return result;
	}

	private boolean hasNotNot() {
		return oCcase.preString== null || !oCcase.preString.equalsIgnoreCase("not");
	}

	public static void main(String[] a) {

	}

	@Override
	public boolean checkPreCondition(String value, String valueNext) {
		return oCcase.selectStarted>0;
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
