package org.muplsql_full.transform.rel;

import java.util.ArrayList;
import java.util.List;

import org.muplsql_full.mcase.Case;
import org.muplsql_full.mcase.ICondition;
import org.muplsql_full.transform.MITransformer;
import org.muplsql_full.transform.MutaOperatorType;

/**
 * 
 * replace mutants with checking conditions IF statement and conditional
 * operator check
 * 
 * */
public class MIFRelationalOpChanger extends MITransformer {
	ICondition condition;

	static String changeList[][] = new String[][] { { ">", "<" },
			{ ">", "<=" }, { ">", "!=" }, { ">", "=" }, { ">", ">=" },
			{ "<", ">" }, { "<", "!=" }, { "<", "=" }, { "<", "<=" },
			{ "<", ">=" }, { "<=", ">" }, { "<=", "!=" }, { "<=", "=" },
			{ "<=", "<" }, { "<=", ">=" }, { ">=", "<" }, { ">=", "<=" },
			{ ">=", "!=" }, { ">=", "=" }, { ">=", ">" }, { "=", "<" },
			{ "=", "<=" }, { "=", "!=" },  { "=", ">" },
			{ "=", ">=" }, { "!=", "<" }, { "!=", "<=" }, { "!=", "=" },
			{ "!=", ">=" }, { "!=", ">" }, { "<>", "<" }, { "<>", "<=" },
			{ "<>", "=" }, { "<>", ">=" }, { "<>", ">" } };

	public MIFRelationalOpChanger() {
		this.mtype = MutaOperatorType.Mot7.name();
		this.mopId = 7;
		oCcase = new Case();

	}

	@Override
	public List<String> mutateAsList(String value) {
		List<String> result = new ArrayList<>();
		if (oCcase.ifStarted > 0 ) {
		for (int i = 0; i < 35; i++) {
			if (value.equals(changeList[i][0])) {
				result.add(value.replace(changeList[i][0], changeList[i][1]));
			}
		}
		}
		eval(value,oCcase.preString); 
		return result;
	}

	public static void main(String[] a) {
		for (int i = 0; i < 36; i++) {
			for (int j = 0; j < 2; j++) {
				System.out.println(i + " " + j + ": " + changeList[i][j]);
			}
		}
	}

	@Override
	public boolean checkPreCondition(String value, String valueNext) {
		return oCcase.ifStarted > 0  && oCcase.whereStarted == 0;
		// return value.equalsIgnoreCase(valueNext);
	}

	@Override
	public Case getCase() {
		return this.oCcase;
	}

	@Override
	public boolean hasPreCondition() {
		return true;
	}

	@Override
	public String eval(String line, String preVal) {
		// line icin case calisacak

		if (oCcase.whereStarted == 0 && line.equalsIgnoreCase("WHERE")) {
			oCcase.whereStarted++;
			return "";
		}

		if (line.equalsIgnoreCase("IF") && !preVal.equalsIgnoreCase("end")) {
			oCcase.ifStarted++;
			return "";
		}

		if (line.equals(";")  || line.equalsIgnoreCase("end")) {
			oCcase.whereStarted = 0;
			oCcase.ifStarted = 0;
			return "";
		}

		if (line.equalsIgnoreCase("then") ) {
			oCcase.ifStarted--;
			return "";
		} else {
			if ((line.equalsIgnoreCase("then") || line.equalsIgnoreCase("end")) && oCcase.ifStarted > 0) {
				oCcase.ifStarted--;
				System.out.println("buraya gelmemeliydi" + line);
			}
		}

		if (line.equalsIgnoreCase(">") && oCcase.lesserStarted > 0) {
			oCcase.lesserStarted = 0; 
			return "<>";
		} else if (line.equalsIgnoreCase(">")) {
			oCcase.greaterStarted++;
		}

		if (line.equalsIgnoreCase("<")) {
			oCcase.lesserStarted++;
			return "";
		}

		if (line.equalsIgnoreCase("=") && oCcase.greaterStarted > 0) {
			return ">=";
		} else if (line.equalsIgnoreCase("=") && oCcase.lesserStarted > 0) {
			return "<=";
		} else if (line.equalsIgnoreCase("=")) {
			return "=";
		}
		return "";

	}
}
