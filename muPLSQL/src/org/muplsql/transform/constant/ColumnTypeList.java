package org.muplsql.transform.constant;
import java.util.*;

public class ColumnTypeList {
	String object_name;

	public List<DBObject> numbericColumns = new ArrayList<DBObject>();
	public List<DBObject> hiddenColumns = new ArrayList<DBObject>();
	
	public ColumnTypeList(String val) {
		this.object_name = val;

	}

	public List<DBObject> getList() {
		return numbericColumns;

	}

	public String getObject_name() {
		return object_name;
	}

	public void setObject_name(String object_name) {
		this.object_name = object_name;
	}

	public List<DBObject> getNumbericColumns() {
		return numbericColumns;
	}

	public void setNumbericColumns(List<DBObject> numbericColumns) {
		this.numbericColumns = numbericColumns;
	}

}
