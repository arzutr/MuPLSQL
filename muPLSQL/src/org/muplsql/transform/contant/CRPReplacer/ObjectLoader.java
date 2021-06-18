package org.muplsql_full.transform.constant;

import java.util.ArrayList;
import java.util.List;

public class ObjectLoader {

	public List<ColumnTypeList> loadObjectColumn() {
		List<ColumnTypeList> mainList = new ArrayList<ColumnTypeList>();
    c.numbericColumns.add(new DBObject("",""));
    mainList.add(c);
    
    return mainList;
  }
	
	public List<ColumnTypeList> getHidden() {
		List<ColumnTypeList> mainList = new ArrayList<ColumnTypeList>();
		ColumnTypeList c = new ColumnTypeList("","");
		c.hiddenColumns.add(new DBObject("",""));
		mainList.add(c);
	}
}
