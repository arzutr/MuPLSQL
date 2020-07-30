package org.muplsql;


/***
 * 
 * @author Arzu
 *
 */
public class MOperator {

	
	private int operatorId;
	private String className;
	private String type;
	private String operatorCode;
	
	public MOperator(int operatorId, String className, String type,
			String operatorCode) {
		super();
		this.operatorId = operatorId;
		this.className = className;
		this.type = type;
		this.operatorCode = operatorCode;
	}
	
	public int getOperatorId() {
		return operatorId;
	}
	public void setOperatorId(int operatorId) {
		this.operatorId = operatorId;
	}
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getOperatorCode() {
		return operatorCode;
	}
	public void setOperatorCode(String operatorCode) {
		this.operatorCode = operatorCode;
	}
	
	
	
	
}
