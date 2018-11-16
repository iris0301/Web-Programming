/*Name: Yunyun Yao
  CSCI4300 Web Programming Fall 2016 31786
  Email: yy21584@uga.edu
  This is review.java
  Created according to the database tables, reviews
 */

package edu.uga.cs4300.objectlayer;
public class Review{
	private String id;
	private String movieId;
	private String content;
	public Review() { //constructor of review
		id = "NULL";
		movieId = "NULL";
		content = "NULL";
	}
	public Review(String _id, String _movieId,String _content) { //set review
		id = "NULL";
		movieId = "NULL";
		content = "NULL";
		setId(_id);
		setMovieId(_movieId);
		setContent(_content);
	}
	public void setId(String _id) { //set each member of review
		if(_id != null)
			id = _id;
	}
	
	public void setMovieId(String _movieId) {
		if(_movieId != null)
			movieId = _movieId;
	}
	
	public void setContent(String _content) {
		if(_content != null)
			content = _content;
	}
	
	public String getId() { //return each member of review
		return id;
	}
	
	public String getMovieId() {
		return movieId;
	}
	
	public String getContent() {
		return content;
	}

}
