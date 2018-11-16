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
import javax.servlet.http.HttpSession;

import edu.uga.cs4300.logiclayer.PetLogicImpl;
import edu.uga.cs4300.objectlayer.Pet;
import edu.uga.cs4300.objectlayer.User;
import freemarker.template.*;

/**
 * View to select which review to delete from selected review
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	String templateDir = "WEB-INF/Templates";
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
        // TODO Auto-generated constructor stub
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		PetLogicImpl petController = new PetLogicImpl();
		Configuration cfg = new Configuration();
		User user = petController.login(username, password);
		if(user.getId()==0)
		{
			System.out.print("ERROR");
			cfg.setServletContextForTemplateLoading(getServletContext(), templateDir);
			Template template = cfg.getTemplate("error.ftl");
			//Find all reviews for movie selected
			//make list of reviews
			Map<String, Object> data = new HashMap<String, Object>();
			//Shows user list of reviews for chosen movie
			data.put("user",user.getFirstName());
			Writer out = response.getWriter();
			try {
				template.process(data, out);
			} catch (TemplateException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		else
		{
			 HttpSession session=request.getSession();  
			 session.setAttribute("user",user);  
			cfg.setServletContextForTemplateLoading(getServletContext(), templateDir);

			Template template = cfg.getTemplate("LoginSuccess.ftl");
			//Find all reviews for movie selected
			//make list of reviews
			Map<String, Object> data = new HashMap<String, Object>();
			//Shows user list of reviews for chosen movie
			data.put("user",user.getFirstName());
			Writer out = response.getWriter();
			try {
				template.process(data, out);
			} catch (TemplateException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

	
		}
		
		
	}
}

	


