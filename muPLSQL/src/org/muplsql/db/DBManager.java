package org.muplsql.db;

import java.sql.*;

public class DBManager {

	Connection conn;

	private String userName = "";
	private String password = "";
	private String dbUrl = "";

	public DBManager() {
		throw new RuntimeException("Not supported!");

	}

	public DBManager(String _userName, String _password, String _dbUrl) {
		userName = _userName;
		password = _password;
		this.dbUrl = _dbUrl;
	}

	public void prepareConnection() {

		try {
			DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
		} catch (SQLException e) {

			e.printStackTrace();
		}

		try {
			this.conn = DriverManager.getConnection(this.dbUrl, this.userName, this.password);
		} catch (SQLException e) {

			this.conn = null;
			e.printStackTrace();

		}

	}

	public Connection getConnection() {
		if (this.conn == null)
			prepareConnection();

		return this.conn;
	}

	public void stop() {
		try {
			if (this.conn != null && !this.conn.isClosed())
				this.conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		this.conn = null;

	}

}
