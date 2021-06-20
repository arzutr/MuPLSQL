package org.muplsql.transform;

import java.util.List;

import org.muplsql.transform.constant.ColumnTypeList;

public interface ITransformer {

	public List<String> mutateAsListExtended(String value, ColumnTypeList ref) ;

	
}
