/*Name: Yunyun Yao
  CSCI4300 Web Programming Fall 2016 31786
  Email: yy21584@uga.edu
  This is DbAccesslmpl.java
  Implement the DbAccessInterface interface
 */

package edu.uga.cs4300.persistlayer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.Statement;

import edu.uga.cs4300.persistlayer.DbAccessConfiguration;
import persistlayer.DbAccessInterface;

public class DbAccessImpl implements DbAccessInterface {

	//connect to the database and return the connection
	@Override
	public Connection connect() {
		Connection con = null;
		try {
			Class.forName(DbAccessConfiguration.DB_DRIVE_NAME);
			con = DriverManager.getConnection(DbAccessConfiguration.DB_CONNECTION_URL, 
				DbAccessConfiguration.DB_CONNECTION_USERNAME, DbAccessConfiguration.DB_CONNECTION_PASSWORD);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return con;
	}

	
	//retrieve data from database and return the resultset
	@Override
	public ResultSet retrieve(Connection con, String query) {
		ResultSet ret = null;
		try {
			Statement stmt = con.createStatement();
			ret = stmt.executeQuery(query);
			return ret;
		} catch (SQLException e) {
			e.printStackTrace();
			return ret;
		}
	}

	@Override
	public int create(Connection con, String query) {
		System.out.println(query);
		int ret = 0;
		try {
			Statement stmt = con.createStatement();
			ret = stmt.execute(query)?0:-1;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ret;
	}

	//add data to database
	@Override
	public int update(Connection con, String query) {
		System.out.println(query);
		int ret = 0;
		try {
			Statement stmt = con.createStatement();
			ret = stmt.executeUpdate(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ret;
	}

	//delete data from database
	@Override
	public int delete(Connection con, String query) {
		int ret = 0;
		try {
			Statement stmt = con.createStatement();
			ret = stmt.execute(query)?0:-1;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ret;
	}

	//close the connection
	@Override
	public void disconnect(Connection con) {
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
