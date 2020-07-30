package org.muplsql.transform;
import java.util.List;

import org.muplsql.mcase.Case;
/****
 * 
 * 
 * 
 * @author arzubt
 *
 */
public abstract class MITransformer {
	protected int mopId  ;
	protected String mtype;

	protected org.muplsql.mcase.Case oCcase ;
	
	public boolean hasPreCondition() {
		return false;
	}
	
	public boolean checkPreCondition(String value, String valueNext) {
		return value.equalsIgnoreCase(valueNext);
	}
	
	public List<String> mutateAsList(String value) {
		return null;
	}
	
	public List<String> mutateAsList(String value, String ref) {
		return null;
	}
	
	public String mutate(String value, String source, String wanted, MutaOperatorType type) {
		
		switch (type) {
		case Mot001:
			return value.replace(source,wanted);
		default:
			break;
		}
		return "";
	}

	public int getMopId() {
		return mopId;
	}

	public void setMopId(int mopId) {
		this.mopId = mopId;
	}

	public String getMtype() {
		return mtype;
	}

	public void setMtype(String mtype) {
		this.mtype = mtype;
	}
	
	public boolean isRef() {
		
		return false;
		
	}
	

	public String getPreConditionVal() {
		return "";
	}
	
	public Case getCase() {
		return new Case();
	}

	public String eval(String line, String preVal) {
		// line icin case calisacak
		if (oCcase==null) return "";
		
		if (oCcase.whereStarted ==0 && line.equalsIgnoreCase("WHERE")) {
			oCcase.whereStarted++;
			return "";
		}
		
		if (line.equalsIgnoreCase("IF") && !preVal.equalsIgnoreCase("end")) {
			oCcase.ifStarted++;
			return "";
		}
		
		if (line.equalsIgnoreCase(";") && oCcase.whereStarted > 0) {
			oCcase.whereStarted = 0;
			return "";
		}
		
		if (line.equalsIgnoreCase("then") && ( oCcase.whereStarted ==0)) {
			oCcase.ifStarted--;
			return "";
		} else {
			if  (line.equalsIgnoreCase("THEN") && oCcase.ifStarted > 0){
				System.out.println("buraya gelmemeliydi" + line); 
			}
		
		}
		
		if (line.equalsIgnoreCase(">")  && oCcase.lesserStarted > 0 ){
			oCcase.lesserStarted = 0;
			return  "<>";
		} else 	if (line.equalsIgnoreCase(">")) {
			oCcase.greaterStarted++;
		}
		
		if (line.equalsIgnoreCase("<")){
			oCcase.lesserStarted++;
			return "";
		}
		
		if (line.equalsIgnoreCase("=") && oCcase.greaterStarted > 0 ){
			return  ">=";
		} else if (line.equalsIgnoreCase("=") && oCcase.lesserStarted > 0 ){
			return  "<=";
		} else  if (line.equalsIgnoreCase("=")){
			return "=";
		}
		return "";

}
	
	protected boolean isNumber(String value) {
		Double val = null;
		try {
			val = Double.parseDouble(value); 
			return true;
		} catch (Exception e) {

			return false;
		}

	}
	
	protected double getNumber(String value) {
		Double val = null;
		try {
			val = Double.parseDouble(value); 
			return val.doubleValue();
		} catch (Exception e) {
		}
		return -999999;// it was searched in code base and not used value is put it here READ this iin future from config

	}
	
	public void setPreVal(String val ) {
		if (this.oCcase == null) return;
		
		oCcase.preString = val;
	}
}
