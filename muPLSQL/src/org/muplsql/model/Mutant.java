package org.muplsql.model;

public class Mutant {
	
	private String objectName;
	private String newObjectName;
	private int mutantId;
	private String type;
	
	public Mutant( String _objectName , String _newObjectName ,int _mutantId, String _type){
		this.objectName = _objectName;
		this.newObjectName = _newObjectName;
		this.mutantId = _mutantId;
		this.type = _type;
	}
	
	public String getObjectName() {
		return objectName;
	}
	public void setObjectName(String objectName) {
		this.objectName = objectName;
	}
	public String getNewObjectName() {
		return newObjectName;
	}
	public void setNewObjectName(String newObjectName) {
		this.newObjectName = newObjectName;
	}
	public int getMutantId() {
		return mutantId;
	}
	public void setMutantId(int mutantId) {
		this.mutantId = mutantId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getExt() {
		if (type.equals(MutationConstants.PACKAGE)) {
			return MutationConstants.PKB;
		} else if (type.equals(MutationConstants.PROCEDURE)) {
			return  MutationConstants.PRC;
		} else if (type.equals(MutationConstants.FUNCTION)) {
			return  MutationConstants.FNC;	
		}
		return null;
	}
	
	public boolean hasPks (){
		return type.equals(MutationConstants.PACKAGE);
	}
	
	

}
