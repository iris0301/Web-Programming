/*Name: Yunyun Yao
  CSCI4300 Web Programming Fall 2016 31786
  Email: yy21584@uga.edu
  This is MovieLogiclmpl.java
  Contains the Java class MovieLogicImpl. This class is the controller class 
  for all the functionalities of the application. MovieLogicImpl class directly 
  interacts with the Servlet and persistent layer classes.
 */

package edu.uga.cs4300.logiclayer;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import objectlayer.Movie;
import objectlayer.Review;
import persistlayer.MoviePersistImpl;
import edu.uga.cs4300.objectlayer.*;
import edu.uga.cs4300.persistlayer.*;
import freemarker.core.ParseException;
import freemarker.template.MalformedTemplateNameException;
import freemarker.template.TemplateNotFoundException;

public class MovieLogicImpl {
	//process movielist
	public Map<String, List<String>> processMovielist() throws TemplateNotFoundException, MalformedTemplateNameException, ParseException, IOException {
    	List<String> resLst = processResult(MoviePersistImpl.getMovieList());
    	Map<String, List<String>> rootMap = new HashMap<String,List<String>>();
    	rootMap.put("genres", resLst);
		return rootMap;
    	
    }
	
	// map movie, processGetMoviesByGenre
	public Map<String, List<Movie>> processGetMoviesByGenre(String genre) throws TemplateNotFoundException, MalformedTemplateNameException, ParseException, IOException {
    	List<String> tmpLst = processResult(MoviePersistImpl.findMoviesByGenre(genre));
    	List<Movie> resLst = packMovies(tmpLst);
    	Map<String, List<Movie>> rootMap = new HashMap<String,List<Movie>>();
    	rootMap.put("movies", resLst);
		return rootMap;
    	
    }
	
	public List<Movie> packMovies(List<String> tmpLst) { //set, add movies
		List<Movie> resList = new ArrayList<Movie>();
		for(int i = 0; i < tmpLst.size(); i+=4) {
			Movie movie = new Movie();
			movie.setId(tmpLst.get(i));
			movie.setName(tmpLst.get(i+1));
			movie.setYear(tmpLst.get(i+2));
			movie.setRank(tmpLst.get(i+3));
			resList.add(movie);
		}
		return resList;
	}
	
	public List<Review> packReviews(List<String> tmpLst) { //pack, set, add reviews
		List<Review> resList = new ArrayList<Review>();
		for(int i = 0; i < tmpLst.size(); i+=3) {
			resList.add(new Review(tmpLst.get(i),tmpLst.get(i+1),tmpLst.get(i+2)));
		}
		return resList;
	}
	
	//process add movie, if wrong return failed, else return succeeded
	public String processAddMovie(String genre, String name, String year, String rank) {
		int iRet = MoviePersistImpl.addMovie(name, year, rank);
    	if(iRet == 0) {
    		return "Add movie failed, Maybe format error. Check your input!";
    	}
    	
    	List<String> tmpLst = processResult(MoviePersistImpl.findMoviesByName(name));
    	if(tmpLst.size() == 0 || tmpLst.size() > 1) {
    		return "Add movie failed, maybe name duplicate";
    	}

    	iRet = MoviePersistImpl.addMovieGenre(tmpLst.get(0), genre);
    	if(iRet == 0) {
    		return "Add movie failed";
    	}
		return "Add movie succeeded";
	}
	
	//process delete movie, if wrong, return failed, else return succeeded
	public String processdeleteMovie(String id) {
		int iRet = MoviePersistImpl.deleteGenresByMovieId(id);
    	if(iRet == 0) {
    		return "Delete movie failed";
    	}
    	MoviePersistImpl.deleteDirectorsByMovieId(id);
    	MoviePersistImpl.deleteReviewsByMovieId(id);
    	iRet = MoviePersistImpl.deleteMovieById(id);
    	if(iRet == 0) {
    		return "Delete movie failed";
    	}
    	return "Delete movie succeeded";
	}
	
	//process add review, if wrong return failed, else return succeeded
	public String processAddReview(String movieId, String review) {
		int iRet = MoviePersistImpl.addReview(movieId, review);
    	if(iRet == 0) {
    		return "Add review failed";
    	}
		return "Add review succeeded";
	}
	
	//process delete review, if wrong return failed, else return succeeded
	public String processdeleteReview(String id) {
    	if(MoviePersistImpl.deleteReviewById(id) == 0) {
    		return "Delete review failed";
    	}
		return "Delete review succeeded";
	}
	
	//list movie details
	public Map<String, Object> processMovieDetails(String id) {
		Map<String, Object> ret = new HashMap<String, Object>();
		List<String> tmpLst = processResult(MoviePersistImpl.findMovieById(id));
		if(tmpLst.size() == 0) 
		{
			return ret;
		}
		ret.put("movie", new Movie(id,tmpLst.get(1),tmpLst.get(2),tmpLst.get(3)));
		System.out.println(tmpLst.get(1)+' '+tmpLst.get(2)+' '+tmpLst.get(3));
		
		tmpLst = processResult(MoviePersistImpl.findDirectorsByMovieId(id));
		ret.put("directors", tmpLst);
		tmpLst = processResult(MoviePersistImpl.findReviewsByMovieId(id));
		List<Review> revList = packReviews(tmpLst);
		ret.put("reviews", revList);
		return ret;
	}
	
	//Extract Strings from the result set
	public List<String> processResult(ResultSet resultSet) {
		List<String> ret = new ArrayList<String>();
		try {
			System.out.println(resultSet.getMetaData().getColumnCount()
					+ " columns");
			while (resultSet.next()) {
				for (int i = 1; i <= resultSet.getMetaData().getColumnCount(); i++)
					ret.add(resultSet.getString(i));
			}
			System.out.println("amount: " + ret.size());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ret;
	}
	
	
}
