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
import edu.uga.cs4300.objectlayer.Pet;
import edu.uga.cs4300.objectlayer.User;
import freemarker.template.*;

/**
 * View to select which review to delete from selected review
 */
@WebServlet("/SearchPetsByQuery")
public class SearchPetsByQuery extends HttpServlet {
	String templateDir = "WEB-INF/Templates";
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchPetsByQuery() {
        super();
        // TODO Auto-generated constructor stub
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String gender = request.getParameter("gender");
		String species = request.getParameter("species");
		String color = request.getParameter("color");
		PetLogicImpl petController = new PetLogicImpl();
		List<Pet> genderList = petController.findPetsByGender(gender);
		System.out.println(genderList.size());
		List<Pet> speciesList = petController.findPetsBySpecies(species);
		System.out.println(speciesList.size());
		System.out.println(genderList.size());
		List<Pet>commonList = petController.findCommonElements(genderList, speciesList);
		List<String[]>formattedPets = new ArrayList<String[]>();
		for(Pet pet: commonList)
		{
			String[]userInfo = {pet.getSpecies(),pet.getGender(),pet.getAge()+"",pet.getName()};
			
			formattedPets.add(userInfo);
		}
		Configuration cfg = new Configuration();
		cfg.setServletContextForTemplateLoading(getServletContext(), templateDir);
		//Find all reviews for movie selected
		//make list of reviews
		Template template = cfg.getTemplate("SeeAllPets.ftl");
		Map<String, Object> data = new HashMap<String, Object>();
		//Shows user list of reviews for chosen movie
		data.put("petsList",formattedPets);
		Writer out = response.getWriter();
		try {
			template.process(data, out);
		} catch (TemplateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}

	


