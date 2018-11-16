/*Name: Yunyun Yao
  CSCI4300 Web Programming Fall 2016 31786
  Email: yy21584@uga.edu
  This is DbAccessInterface.java
  The prototype of access function 
 */

package edu.uga.cs4300.persistlayer;

import java.sql.*;

//prototype of access function 
public interface DbAccessInterface {
	public Connection connect();
	public ResultSet retrieve (Connection con, String query);
	public int create (Connection con, String query);
	public int update (Connection con, String query);
	public int delete (Connection con, String query);
	public void disconnect(Connection con);
}
