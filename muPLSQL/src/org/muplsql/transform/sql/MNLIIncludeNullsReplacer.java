package org.muplsql_full.transform.sql;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.muplsql_full.mcase.Case;
import org.muplsql_full.transform.MITransformer;
import org.muplsql_full.transform.MutaOperatorType;

/**
nullable olan alani is null ile set yapiyoruz bunun yerine 1=0 da ayni.
*/
public class MNLIIncludeNullsReplacer extends MITransformer {

	public MNLIIncludeNullsReplacer() {
		this.mtype = MutaOperatorType.Mot20.name();//FIXME
		this.mopId =20;
		oCcase = new  Case();
	}

/*	@Override
	public List<String> mutateAsList(String value) {
		List<String> result = new ArrayList<>();

		if (value.equalsIgnoreCase("where"))
			result.add("where 1=0 and"  );

		return result;
	}
*/


	
	@Override
	public List<String> mutateAsList(String value) {
		List<String> result = new ArrayList<>();

		if (list.contains(value)  && checkPreCondition(null,null))
			result.add(value + " is null and " + value +" "  );

		
		if (value.equals(";")){
			oCcase.whereStarted=0;
		}
		
		if (value.equalsIgnoreCase("where")){
			oCcase.whereStarted++;
		}
		return result;
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
	
	List<String> list = Arrays.asList(( new String [] {""
    
    }));
}
