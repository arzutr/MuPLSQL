package org.muplsql.transform.array;

import java.util.ArrayList;
import java.util.List;

import org.muplsql.transform.MITransformer;
import org.muplsql.transform.MutaOperatorType;
//AAR operator

//change array index
public class ArrayIndexReplacer  extends MITransformer {

	public ArrayIndexReplacer() {
		this.mtype = MutaOperatorType.Mot12.name();
		this.mopId = 12;
	}

	@Override
	public List<String> mutateAsList(String value) {
		List<String> result = new ArrayList<>();

		if (value.contains("EXTEND"))
			result.add( "COUNT" );  //this is for varray impl

		if (value.contains("("))
			result.add(value + "1" ); //if no error in compile time this is an array 

		
		return result;
	}

	

}
