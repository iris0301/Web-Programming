package edu.uga.cs4300.persistlayer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import com.mysql.*;
public class DbAccessImpl extends DbAccessConfiguration implements DbAccessInterface{

	Connection con = null;
	public Connection connect() {
		try {
			Class.forName(DRIVE_NAME);
			con = DriverManager.getConnection(CONNECTION_URL, DB_CONNECTION_USERNAME, DB_CONNECTION_PASSWORD);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return con;
	}

	@Override
	public ResultSet retrieve(Connection con, String query) {
		ResultSet rset = null;
		try {
			Statement stmt = con.createStatement();
			rset = stmt.executeQuery(query);
			return rset;
		} catch (SQLException e) {
			e.printStackTrace();
			return rset;
		}
	}

	@Override
	public int create(Connection con, String query) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(Connection con, String query) {
		// TODO Auto-generated method stub
		return 0;
	}
	public int update(Connection con, PreparedStatement statement) throws SQLException {
		 statement.executeUpdate();
		 ResultSet rs = statement.executeQuery("SELECT last_insert_id()");
         if(rs.next())
         {
             int last_inserted_id = rs.getInt(1);
             System.out.println("DB:"+last_inserted_id);
             return last_inserted_id;

         }
         return 0;

	}

	@Override
	public int delete(Connection con, String query) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void disconnect(Connection con) {
		// TODO Auto-generated method stub
		
	}

}
