package edu.uga.cs4300.boundary;

import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.uga.cs4300.logiclayer.PetLogicImpl;
import edu.uga.cs4300.objectlayer.User;
import freemarker.template.*;

/**
 * View to select which review to delete from selected review
 */
@WebServlet("/SeeAllUsers")
public class ViewAllUsers extends HttpServlet {
	String templateDir = "WEB-INF/Templates";
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ViewAllUsers() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PetLogicImpl movieController = new PetLogicImpl();
		List<User> listOfUsers = movieController.findAllUsers();
		System.out.println(listOfUsers.size());
		List<String[]>formattedUsers = new ArrayList<String[]>();
		for(User user: listOfUsers)
		{
			String[]userInfo = {user.getFirstName(),user.getLastName(),user.getUsername()};
			System.out.println(user.getUsername());
			formattedUsers.add(userInfo);
		}
		Configuration cfg = new Configuration();
		cfg.setServletContextForTemplateLoading(getServletContext(), templateDir);
		//Find all reviews for movie selected
		//make list of reviews
		Template template = cfg.getTemplate("ViewAllUsers.ftl");
		Map<String, Object> data = new HashMap<String, Object>();
		//Shows user list of reviews for chosen movie
		data.put("usersList",formattedUsers);
		Writer out = response.getWriter();
		try {
			template.process(data, out);
		} catch (TemplateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String movieName = request.getParameter("options");
		System.out.println("movie nane:"+movieName);
		PetLogicImpl movieController = new PetLogicImpl();
		List<User> listOfUsers = movieController.findAllUsers();
		System.out.println(listOfUsers.size());
		List<String[]>formattedUsers = new ArrayList<String[]>();
		for(User user: listOfUsers)
		{
			String[]userInfo = {user.getFirstName(),user.getLastName(),user.getUsername()};
			System.out.println(userInfo);
			formattedUsers.add(userInfo);
		}
		Configuration cfg = new Configuration();
		cfg.setServletContextForTemplateLoading(getServletContext(), templateDir);
		//Find all reviews for movie selected
		//make list of reviews
		Template template = cfg.getTemplate("ViewAllUsers.ftl");
		Map<String, Object> data = new HashMap<String, Object>();
		//Shows user list of reviews for chosen movie
		data.put("usersList",formattedUsers);
		Writer out = response.getWriter();
		try {
			template.process(data, out);
		} catch (TemplateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
