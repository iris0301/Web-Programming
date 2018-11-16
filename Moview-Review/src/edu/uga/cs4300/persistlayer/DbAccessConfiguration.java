/*Name: Yunyun Yao
  CSCI4300 Web Programming Fall 2016 31786
  Email: yy21584@uga.edu
  This is DbAccessConfiguration.java
  Includes all the variables (with proper values) required to connect to the database 
 */

package edu.uga.cs4300.persistlayer;

//all the variables (with proper values) required to connect to the database 
public abstract class DbAccessConfiguration {
	public final static String DB_DRIVE_NAME = "com.mysql.jdbc.Driver";
	public final static String DB_CONNECTION_URL = "jdbc:mysql://localhost:3306/imdb2";
	public final static String DB_CONNECTION_USERNAME  = "demo";
	public final static String DB_CONNECTION_PASSWORD = "demo";
}
