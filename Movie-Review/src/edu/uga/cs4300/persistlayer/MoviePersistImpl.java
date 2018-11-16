/*Name: Yunyun Yao
  CSCI4300 Web Programming Fall 2016 31786
  Email: yy21584@uga.edu
  This is MoviePersultSet.java
  The primary persistent (model) layer class. This class must explicitly communicate 
  with the DbAccessImpl class to run the SQL queries.
 */

package edu.uga.cs4300.persistlayer;

import java.sql.Connection;
import java.sql.ResultSet;

import persistlayer.DbAccessImpl;

public class MoviePersistImpl {
	//get the movies list from movies_genres
	static public ResultSet getMovieList() {
		DbAccessImpl dbManager = new DbAccessImpl();
		Connection con = dbManager.connect();
		ResultSet res = dbManager.retrieve(con, "select genre from movies_genres group by genre");
		return res;
	}
	
	//find movies from movies_genres
	static public ResultSet findMoviesByGenre(String genre) {
		DbAccessImpl dbManager = new DbAccessImpl();
		Connection con = dbManager.connect();
		ResultSet res = dbManager.retrieve(con, "select id,name,year,rank from movies,movies_genres where movie_id = id and genre='"+genre+"'");
		return res;
	}
	
	//find movies by id
	static public ResultSet findMovieById(String id) {
		DbAccessImpl dbManager = new DbAccessImpl();
		Connection con = dbManager.connect();
		String query = "select * from movies where id = '" + id + "'";
		ResultSet resultSet = dbManager.retrieve(con, query);
		return resultSet;
	}
	
	//find directors by movie id
	static public ResultSet findDirectorsByMovieId(String id) {
		DbAccessImpl dbManager = new DbAccessImpl();
		Connection con = dbManager.connect();
		String query = "select first_name,last_name from movies_directors,directors where id = director_id "
				+ "and movie_id = '" + id + "'";
		ResultSet resultSet = dbManager.retrieve(con, query);
		return resultSet;
	}
	
	//find reviews by movie id
	static public ResultSet findReviewsByMovieId(String id) {
		DbAccessImpl dbManager = new DbAccessImpl();
		Connection con = dbManager.connect();
		String query = "select * from reviews where movie_id = '"+id+"'";
		ResultSet resultSet = dbManager.retrieve(con, query);
		return resultSet;
	}
	
	//delete reviews by id
	static public int deleteReviewById(String id) {
		DbAccessImpl dbManager = new DbAccessImpl();
		Connection con = dbManager.connect();
		int iRet = dbManager.update(con, "delete from reviews where id='"+id +"'");
		return iRet;
	}
	
	//add reviews
	static public int addReview(String movieId, String review) {
		DbAccessImpl dbManager = new DbAccessImpl();
		Connection con = dbManager.connect();
		int iRet = dbManager.update(con, "insert into reviews (movie_id,review) values ('"
			+movieId+ "','" + review + "')");
		return iRet;
	}
	
	//delete genres by movie id
	static public int deleteGenresByMovieId(String id) {
		DbAccessImpl dbManager = new DbAccessImpl();
		Connection con = dbManager.connect();
		int iRet = dbManager.update(con, "delete from movies_genres where movie_id='" +id+ "'");
		return iRet;
	}
	
	//delete directors by movie id
	static public int deleteDirectorsByMovieId(String id) {
		DbAccessImpl dbManager = new DbAccessImpl();
		Connection con = dbManager.connect();
		int iRet = dbManager.update(con, "delete from movies_directors where movie_id='" +id+ "'");
		return iRet;
	}
	
	//delete reviews by movie id
	static public int deleteReviewsByMovieId(String id) {
		DbAccessImpl dbManager = new DbAccessImpl();
		Connection con = dbManager.connect();
		int iRet = dbManager.update(con, "delete from reviews where movie_id='" +id+ "'");
		return iRet;
	}
	
	//delete movies by id
	static public int deleteMovieById(String id) {
		DbAccessImpl dbManager = new DbAccessImpl();
		Connection con = dbManager.connect();
		int iRet = dbManager.update(con, "delete from movies where id='" +id+ "'");
		return iRet;
	}
	
	//add movie
	static public int addMovie(String name, String year, String rank) {
		DbAccessImpl dbManager = new DbAccessImpl();
		Connection con = dbManager.connect();
		int iRet = dbManager.update(con, "INSERT INTO movies (name,year,rank) VALUES ('" 
				+ name + "','" + year + "','" + rank + "')");
		return iRet;
	}
	
	//find movies by name
	static public ResultSet findMoviesByName(String name) {
		DbAccessImpl dbManager = new DbAccessImpl();
		Connection con = dbManager.connect();
		ResultSet res = dbManager.retrieve(con, "select id from movies where name='"+name+"'");
		return res;
	}
	
	// add movie_genres
	static public int addMovieGenre(String movie_id, String genre) {
		DbAccessImpl dbManager = new DbAccessImpl();
		Connection con = dbManager.connect();
		int iRet = dbManager.update(con, "INSERT INTO movies_genres (movie_id,genre) VALUES ('" 
				+ movie_id + "','" + genre + "')");
		return iRet;
	}
}
