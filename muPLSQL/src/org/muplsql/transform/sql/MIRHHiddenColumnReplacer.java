package org.muplsql.transform.sql;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.muplsql.mcase.*;
import org.muplsql.transform.*;

import org.muplsql.transform.constant.*;



public class MIRHHiddenColumnReplacer extends MITransformer implements ITransformer {

	public MIRHHiddenColumnReplacer() {
		this.mtype = MutaOperatorType.Mot26.name();//FIXME
		this.mopId =26;
		oCcase = new Case();
	}

	@Override
	public List<String> mutateAsList(String value) {
		List<String> result = new ArrayList<>();

		return result;
	}

	public static void main(String[] a) {
		for (int i = 0; i < 12; i++) {
			for (int j = 0; j < 2; j++) {
				//System.out.println(i + " " + j + ": " + changeList[i][j]);
			}
		}

	}

	@Override
	public List<String> mutateAsListExtended(String value, ColumnTypeList ref) {
		List<String> result = new ArrayList<>();

		if (oCcase.whereStarted>0 ){
			List<DBObject> l = ref.hiddenColumns.stream().filter(o->o.cname.equalsIgnoreCase(value)).collect(Collectors.toList());
			if (!l.isEmpty()){
				String val = l.get(0).ncname;
				//String val2  =  ref.hiddenColumns.stream().filter(o->o.ca)/
				result.add(val);
			}
		}
	
		if (value.equalsIgnoreCase(";")  && checkPreCondition(null,null)){
			oCcase.reset();
		}
		

		if (value.equalsIgnoreCase("where") )
			oCcase.whereStarted++;

		if (value.equalsIgnoreCase("select") )
			oCcase.selectStarted++;
		return result;
	}
	

	


	@Override
	public boolean checkPreCondition(String value, String valueNext) {
		return oCcase.whereStarted>=0 ;
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
