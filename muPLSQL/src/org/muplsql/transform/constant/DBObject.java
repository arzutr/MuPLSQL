package org.muplsql.transform.constant;

public class DBObject {

	public String table_name;
	public String cname;
	public String ncname;
	public String ishidden;

	public DBObject() {

	}

	public DBObject(String v1, String v2) {
		this.cname = v1;
		this.ncname = v2;
	}

}
