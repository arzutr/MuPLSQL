package org.muplsql.transform.sql;

import java.util.ArrayList;
import java.util.List;

import org.muplsql.mcase.Case;
import org.muplsql.transform.MITransformer;
import org.muplsql.transform.MutaOperatorType;

public class MIRTConstantReplacer  extends MITransformer {


	public MIRTConstantReplacer() {
		this.mtype = MutaOperatorType.Mot34.name();
		this.mopId = 34;oCcase = new Case();
	}


	@Override
	public List<String> mutateAsList(String value) {
		List<String> result = new ArrayList<>();




		if (value.equalsIgnoreCase("'") && oCcase.tStarted == 1  &&  checkPreCondition(null,null)){
			oCcase.tStarted=0;
			if (value.equalsIgnoreCase("NULL") && hasNotNot()) {
				result.add("xyz'"  );
			}
		}
		if (value.equalsIgnoreCase("'")  && oCcase.tStarted == 0)
			oCcase.tStarted++;
		
		if (value.equalsIgnoreCase(";")  && checkPreCondition(null,null)){
			oCcase.reset();
		}
		if (value.equalsIgnoreCase("where") )
			oCcase.whereStarted++;
		if (value.equalsIgnoreCase("select") )
			oCcase.selectStarted++;		
		return result;
	}

	private boolean hasNotNot() {
		return oCcase.preString== null || !oCcase.preString.equalsIgnoreCase("not");
	}

	public static void main(String[] a) {

	}

	@Override
	public boolean checkPreCondition(String value, String valueNext) {
		return oCcase.whereStarted==0 || oCcase.selectStarted ==0;
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
