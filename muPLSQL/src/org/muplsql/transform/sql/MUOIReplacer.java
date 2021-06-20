package org.muplsql.transform.sql;

import org.muplsql.mcase.Case;
import org.muplsql.transform.MITransformer;
import org.muplsql.transform.MutaOperatorType;

import java.util.ArrayList;
import java.util.List;


public class MUOIReplacer extends MITransformer {


	public MUOIReplacer() {
		this.mtype = MutaOperatorType.Mot48.name();//FIXME
		this.mopId =48;
		oCcase = new Case();
	}

	@Override
	public List<String> mutateAsList(String value) {
		List<String> result = new ArrayList<>();

		if (value.equalsIgnoreCase("WHERE")) {
			oCcase.whereStarted++;
		}



		if (value.equalsIgnoreCase(";") && oCcase.whereStarted > 0) {
			oCcase.whereStarted = 0;
		}

		if (isNumber(value) && oCcase.whereStarted > 0) {

			result.add( "(1- " + value +")"  );
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
