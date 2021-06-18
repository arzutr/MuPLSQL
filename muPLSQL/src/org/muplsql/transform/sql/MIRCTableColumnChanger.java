package org.muplsql_full.transform.sql;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.muplsql_full.mcase.Case;
import org.muplsql_full.transform.ITransformer;
import org.muplsql_full.transform.MITransformer;
import org.muplsql_full.transform.MutaOperatorType;
import org.muplsql_full.transform.constant.ColumnTypeList;
import org.muplsql_full.transform.constant.DBObject;
//FIXME 2020
/***
 * 
 * @author 
 *
 */
public class MIRCTableColumnChanger  extends MITransformer  implements ITransformer {

;

	public MIRCTableColumnChanger() {
		this.mtype = MutaOperatorType.Mot41.name();
		this.mopId = 41;
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

	//@Override
	public List<String> mutateAsListExtended(String value, ColumnTypeList ref) {
		List<String> result = new ArrayList<>();

		if (oCcase.whereStarted>0 ){
			//System.out.println(ref.getObject_name()+ "" + ref.numbericColumns.size() + value);
			List<DBObject> d = ref.numbericColumns.stream().filter(o->o.cname.equalsIgnoreCase(value)).collect(Collectors.toList());
			if (!d.isEmpty()) {			
				String val = d.get(0).ncname;
				if (val!=null){
					result.add(val);
					//System.out.println(val);
				}
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
	

	private boolean hasNotNot() {
		return oCcase.preString== null || !oCcase.preString.equalsIgnoreCase("not");
	}



	@Override
	public boolean checkPreCondition(String value, String valueNext) {
		return oCcase.whereStarted>0 ;
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
