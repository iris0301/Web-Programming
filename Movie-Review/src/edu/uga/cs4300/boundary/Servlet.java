/*Name: Yunyun Yao
  CSCI4300 Web Programming Fall 2016 31786
  Email: yy21584@uga.edu
  This is Servlet.java
  Includes the Servlet class that handles all the requests and responses. 
  The Servlet directly interacts with the controller class. 
 */


package edu.uga.cs4300.boundary;
import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import edu.uga.cs4300.logiclayer.*;
import freemarker.template.*;
import logiclayer.MovieLogicImpl;

@WebServlet("/ProcessData")
public class Servlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	private Configuration cfg = null;
	private MovieLogicImpl controller = null;
	Template template = null;
	Object rootMap = null;
	PrintWriter out = null;
	@SuppressWarnings("deprecation")
	public void init() throws ServletException {  
        // Create a freeMarker instance
		cfg = new Configuration();
        // specific the FreeMarker files location
        cfg.setServletContextForTemplateLoading(getServletContext(),"/WEB-INF/templates");
        controller = new MovieLogicImpl();
    }  
    public void doPost(HttpServletRequest request, HttpServletResponse response)  
            throws ServletException, IOException {
    	if(request.getParameter("command") == null) 
		{
    		System.out.println("no command");
    		return;
		}
    	System.out.println(request.getParameter("command"));
    	switch(request.getParameter("command").toString()) {
    	case "movieListEntry":
    		//get template file
            template = cfg.getTemplate("movielist.ftl");
            rootMap = controller.processMovielist();
    		response.setContentType("text/html; charset=" + template.getEncoding());  
            out = response.getWriter();
            try {  
            	template.process(rootMap, out);
            } catch (TemplateException e) {  
                e.printStackTrace();  
            } catch (IOException e) {
    			e.printStackTrace();
    		}
            break;
    	case "getMoviesByGenre":
    		System.out.println(request.getParameter("genre"));
    		//get template file
    		template = cfg.getTemplate("movietable.ftl");
            rootMap = controller.processGetMoviesByGenre(request.getParameter("genre"));
    		response.setContentType("text/html; charset=" + template.getEncoding());  
    		out = response.getWriter();
            try {  
            	template.process(rootMap, out);
            } catch (TemplateException e) {  
                e.printStackTrace();  
            } catch (IOException e) {
    			e.printStackTrace();
    		}
    		break;
    	case "addMovie":
    		System.out.println(request.getParameter("genre"));
    		System.out.println(request.getParameter("name"));
    		System.out.println(request.getParameter("year"));
    		System.out.println(request.getParameter("rank"));
    		out = response.getWriter();
    		out.println(controller.processAddMovie(request.getParameter("genre"),
    				request.getParameter("name"),request.getParameter("year"),
    				request.getParameter("rank")));
    		break;
    	case "showDetails":
    		//get template file
    		template = cfg.getTemplate("moviedetails.ftl");
            rootMap = controller.processMovieDetails(request.getParameter("id"));
    		response.setContentType("text/html; charset=" + template.getEncoding());  
    		out = response.getWriter();
            try {  
            	template.process(rootMap, out);
            } catch (TemplateException e) {  
                e.printStackTrace();  
            } catch (IOException e) {
    			e.printStackTrace();
    		}
    		break;
    	case "deleteMovie":
    		System.out.println(request.getParameter("id"));
    		out = response.getWriter();
    		out.println(controller.processdeleteMovie(request.getParameter("id")));
    		break;
    	case "addReview":
    		System.out.println(request.getParameter("id"));
    		System.out.println(request.getParameter("review"));
    		out = response.getWriter();
    		out.println(controller.processAddReview(request.getParameter("movieId"),
    				request.getParameter("review")));
    		break;
    	case "deleteReview":
			System.out.println(request.getParameter("id"));
			out = response.getWriter();
    		out.println(controller.processdeleteReview(request.getParameter("id")));
    		break;
        default:
        	System.out.println("invalid command: " + request.getParameter("command"));
        	return;
    	}
    }  
    
    public void doGet(HttpServletRequest request, HttpServletResponse response)  
            throws ServletException, IOException {
        doPost(request, response);  
    }
    public void destroy() {  
        super.destroy();  
    }
    

}
