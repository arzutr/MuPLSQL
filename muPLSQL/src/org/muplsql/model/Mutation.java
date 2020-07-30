package org.muplsql.model;

import java.util.*;

/**********
 * 
 * @author arzu bt
 *
 * Corresponds to TMUTATION table
 * 
 */
public class Mutation {

	private String objectName;
	private String objectType;
	private String objectOwner;

	List<MutantDetail> listofMutations;

	public String getObjectName() {
		return objectName;
	}
	
	public void setObjectName(String objectName) {
		this.objectName = objectName;
	}
	
	public String getObjectType() {
		return objectType;
	}
	
	public void setObjectType(String objectType) {
		this.objectType = objectType;
	}
	
	public String getObjectOwner() {
		return objectOwner;
	}
	
	public void setObjectOwner(String objectOwner) {
		this.objectOwner = objectOwner;
	}
	
	public Mutation(String objectName, String objectType, String objectOwner) {
		super();
		this.objectName = objectName;
		this.objectType = objectType;
		this.objectOwner = objectOwner;
	}
	
	public List<MutantDetail> getListofMutations() {
		if (listofMutations==null) {
			listofMutations = new ArrayList<MutantDetail>();
		}
		return listofMutations;
	}
	
	public void setListofMutations(List<MutantDetail> listofMutations) {
		this.listofMutations = listofMutations;
	}
	
	

	
}
