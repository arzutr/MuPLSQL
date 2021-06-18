package org.muplsql_full.transform.sql;

import java.util.ArrayList;
import java.util.List;

import org.muplsql_full.mcase.Case;
import org.muplsql_full.transform.MITransformer;
import org.muplsql_full.transform.MutaOperatorType;

public class MIRPParameterReplacer  extends MITransformer {

	public MIRPParameterReplacer() {
		this.mtype = MutaOperatorType.Mot33.name();
		this.mopId = 33;
		oCcase = new Case();
	}

	@Override
	public List<String> mutateAsList(String value) {
		List<String> result = new ArrayList<>();


		
		
		if (has() &&  checkPreCondition(null,null)){
			//FIXME check is number or varchar2
			if (isNumeric(value)) {
				result.add("1+ "  );	
			} else {
				result.add("'xyz' || "  );
			}
		}
		
		if (value.equalsIgnoreCase(";")  && checkPreCondition(null,null)){
			oCcase.reset();
		}

		if (value.equalsIgnoreCase("where") )
			oCcase.whereStarted++;

		return result;
	}

	

	private boolean has() {
		return oCcase.preString!= null && oCcase.preString.equalsIgnoreCase("=");
	}

	public static void main(String[] a) {

	}

	@Override
	public boolean checkPreCondition(String value, String valueNext) {
		return oCcase.whereStarted>0 ;
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
