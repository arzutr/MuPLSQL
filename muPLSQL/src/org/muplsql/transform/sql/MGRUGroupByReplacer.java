package org.muplsql.transform.sql;

import java.util.ArrayList;


import java.util.List;

import org.muplsql.mcase.Case;
import org.muplsql.transform.MITransformer;
import org.muplsql.transform.MutaOperatorType;

import java.util.ArrayList;
import java.util.List;

public class MGRUGroupByReplacer extends MITransformer {

	public MGRUGroupByReplacer() {
		this.mtype = MutaOperatorType.Mot46.name();//FIXME
		this.mopId =46;
		oCcase = new  Case();
	}

	@Override
	public List<String> mutateAsList(String value) {
		List<String> result = new ArrayList<>();

		if (value.equalsIgnoreCase("WHERE")) {
			oCcase.whereStarted++;
		}
		else
		if (value.equalsIgnoreCase("GROUP")) {
			oCcase.groupByStarted++;
			result.add("GRU_START"  );
		}
		else
		if (value.equalsIgnoreCase("BY") &&  oCcase.groupByStarted>0) {
			result.add("GRU_START"  );
		}
		else if (oCcase.whereStarted>0 &&  oCcase.groupByStarted>0 ){
			result.add("GRU_START"  );
		}
		if (!value.equalsIgnoreCase(";") &&  oCcase.whereStarted>0 &&  oCcase.groupByStarted>0 ) {
			result.add("GRU_END"  );
			oCcase.whereStarted = 0;
			oCcase.groupByStarted=0;
		}
		
		if (value.equalsIgnoreCase(";") && oCcase.whereStarted > 0) {
			oCcase.whereStarted = 0;
			oCcase.groupByStarted=0;
		}
		return result;
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
