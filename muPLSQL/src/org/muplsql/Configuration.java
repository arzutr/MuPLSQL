package org.muplsql;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.muplsql.model.Mutation;
import org.muplsql.model.MutationOperator;

public class Configuration {

	String dbUserPassword;
	String dbUserName;
	String dbUrl;
	String dbType;

	String dbTargetUserPassword;
	String dbTargetUserName;
	String dbTargetUrl;

	String schemaName;
	String sourceFolder;
	String outputFolder;
	String fileREadCharset;
	
	String workingMode;

	public void load() {
		try (InputStream inputStream = new FileInputStream("muPLSQL.properties")) {
			Properties prop = new Properties();
			prop.load(inputStream);
			this.sourceFolder = prop.getProperty("SOURCE_FOLDER_NAME");
			this.fileREadCharset = prop.getProperty("FILE_OPEN_CHARSET");
			this.outputFolder = prop.getProperty("WRITE_FOLDER_NAME");
			this.dbUserName = prop.getProperty("DB_USERNAME");
			this.dbUserPassword = prop.getProperty("DB_PASSWORD");
			this.dbUrl = prop.getProperty("DB_URL");
			this.dbTargetUserName = prop.getProperty("DB_MUTANT_USERNAME");
			this.dbTargetUserPassword = prop.getProperty("DB_MUTANT_PASSWORD");
			this.dbTargetUrl = prop.getProperty("DB_MUTANT_URL");
			this.schemaName = prop.getProperty("DB_SCHEMA_NAME");
			this.workingMode=prop.getProperty("WORKING_MODE");
		} catch (IOException ex) {
			ex.printStackTrace();
		}

	}

	public List<MOperator> readOperators(List<MutationOperator> listOf) {
		return null;
		// FIXME in future Not hard coded no reflection..
	}

	public List<MFile> readFiles(List<Mutation> listofDBObjects) {
		List<MFile> list = new ArrayList<MFile>();
		for (Mutation m : listofDBObjects) {
			MFile mf = new MFile(m);
			list.add(mf);
		}
		return list;
	}

	public String getDbUserPassword() {
		return dbUserPassword;
	}

	public void setDbUserPassword(String dbUserPassword) {
		this.dbUserPassword = dbUserPassword;
	}

	public String getDbUserName() {
		return dbUserName;
	}

	public void setDbUserName(String dbUserName) {
		this.dbUserName = dbUserName;
	}

	public String getDbUrl() {
		return dbUrl;
	}

	public void setDbUrl(String dbUrl) {
		this.dbUrl = dbUrl;
	}

	public String getDbType() {
		return dbType;
	}

	public void setDbType(String dbType) {
		this.dbType = dbType;
	}

	public String getDbTargetUserPassword() {
		return dbTargetUserPassword;
	}

	public void setDbTargetUserPassword(String dbTargetUserPassword) {
		this.dbTargetUserPassword = dbTargetUserPassword;
	}

	public String getDbTargetUserName() {
		return dbTargetUserName;
	}

	public void setDbTargetUserName(String dbTargetUserName) {
		this.dbTargetUserName = dbTargetUserName;
	}

	public String getDbTargetUrl() {
		return dbTargetUrl;
	}

	public void setDbTargetUrl(String dbTargetUrl) {
		this.dbTargetUrl = dbTargetUrl;
	}

	public String getSchemaName() {
		return schemaName;
	}

	public void setSchemaName(String schemaName) {
		this.schemaName = schemaName;
	}

	public String getSourceFolder() {
		return sourceFolder;
	}

	public void setSourceFolder(String sourceFolder) {
		this.sourceFolder = sourceFolder;
	}

	public String getOutputFolder() {
		return outputFolder;
	}

	public void setOutputFolder(String outputFolder) {
		this.outputFolder = outputFolder;
	}

	public String getFileREadCharset() {
		return fileREadCharset;
	}

	public void setFileREadCharset(String fileREadCharset) {
		this.fileREadCharset = fileREadCharset;
	}

	public boolean isNotValidForMutantCreate() {
		return (this.sourceFolder == null || this.fileREadCharset == null || this.outputFolder == null
				|| this.dbUserName == null || this.dbUserPassword == null || this.dbUrl == null
				|| this.schemaName == null);
	}

	public boolean isValidForMutantExecute() {
		return !(this.sourceFolder == null || this.fileREadCharset == null || this.outputFolder == null
				|| this.dbUserName == null || this.dbUserPassword == null || this.dbUrl == null
				|| this.dbTargetUserName == null || this.dbTargetUserPassword == null || this.dbTargetUrl == null
				|| this.schemaName == null);
	}

}
