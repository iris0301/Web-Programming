/*Name: Yunyun Yao
  CSCI4300 Web Programming Fall 2016 31786
  Email: yy21584@uga.edu
  This is Movie.java
  Created according to the database tables, movies 
 */

package edu.uga.cs4300.objectlayer;
public class Movie{
	private String id;
	private String name;
	private String year;
	private String rank;
	public Movie() { //constructor for movie
		id = "NULL";
		name = "NULL";
		year = "NULL";
		rank = "NULL";
	}
	public Movie(String _id, String _name,String _year,String _rank) { //set movie
		id = "NULL";
		name = "NULL";
		year = "NULL";
		rank = "NULL";
		setId(_id);
		setName(_name);
		setYear(_year);
		setRank(_rank);
	}
	public void setId(String _id) { //set each member of movie
		if(_id != null)
			id = _id;
	}
	
	public void setName(String _name) {
		if(_name != null)
			name = _name;
	}
	
	public void setYear(String _year) {
		if(_year != null)
			year = _year;
	}
	
	public void setRank(String _rank) {
		if(_rank != null)
			rank = _rank;
	}
	
	public String getId() { //return each member of movie
		return id;
	}
	
	public String getName() {
		return name;
	}
	
	public String getYear() {
		return year;
	}
	
	public String getRank() {
		return rank;
	}
}
