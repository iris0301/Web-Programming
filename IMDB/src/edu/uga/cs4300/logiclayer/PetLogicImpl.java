package edu.uga.cs4300.logiclayer;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import edu.uga.cs4300.objectlayer.Pet;
import edu.uga.cs4300.objectlayer.User;
import edu.uga.cs4300.persistlayer.DbAccessImpl;
import edu.uga.cs4300.persistlayer.PetPersistsImpl;

public class PetLogicImpl {
	Pet pet;
	User user;
	PetPersistsImpl petPersist  = new PetPersistsImpl();
	public List<User> findAllUsers()
	{
		ResultSet rs = petPersist.searchAllUsers();
		List<User>list = new ArrayList();
		try {
			if(rs.next())
			{
			rs.beforeFirst();
			//Create table of responses
			while(rs.next())
			{
				String username = rs.getString("username");
				String first_name = rs.getString("first_name");
				String second_name = rs.getString("last_name");
				Date birthday = rs.getDate("birthday");
				boolean hadPetBefore = rs.getBoolean("hadPetBefore");
				String occupation = rs.getString("occupation");
				String housingType = rs.getString("housingType");
				int id = Integer.parseInt(rs.getString("id"));
				String type = rs.getString("user_type");
				User user = new User();
				user.setUsername(username);
				user.setId(id);
				user.setFirstName(first_name);
				user.setLastName(second_name);
				user.setBirthday(birthday);
				user.setOccupation(occupation);
				user.setHousingType(housingType);
				user.setHadPetBefore(hadPetBefore);
				user.setType(type);
				if(!user.getType().equalsIgnoreCase("admin"))
				{
				list.add(user);
				}
			}
			}
		} catch (NumberFormatException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;		
	}
	public User login(String usernameSent,String passwordSent)
	{
		ResultSet rs = petPersist.searchUserByCredentials(usernameSent, passwordSent);
		User user = new User();
		user.setId(0);
		try{
			if(rs.next())
			{
				String username = rs.getString("username");
				String first_name = rs.getString("first_name");
				String second_name = rs.getString("last_name");
				Date birthday = rs.getDate("birthday");
				boolean hadPetBefore = rs.getBoolean("hadPetBefore");
				String occupation = rs.getString("occupation");
				String housingType = rs.getString("housingType");
				int id = Integer.parseInt(rs.getString("id"));
				String type = rs.getString("user_type");
				user.setId(id);
				user.setFirstName(first_name);
				user.setLastName(second_name);
				user.setBirthday(birthday);
				user.setOccupation(occupation);
				user.setHousingType(housingType);
				user.setHadPetBefore(hadPetBefore);
				user.setType(type);
			}
		}
			catch (NumberFormatException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return user;	
		
		
	}
	public List<User> findAdmins()
	{
		ResultSet rs = petPersist.searchAllUsers();
		List<User>list = new ArrayList();
		try {
			if(rs.next())
			{
			rs.beforeFirst();
			//Create table of responses
			while(rs.next())
			{
				String username = rs.getString("username");
				String first_name = rs.getString("first_name");
				String second_name = rs.getString("last_name");
				Date birthday = rs.getDate("birthday");
				boolean hadPetBefore = rs.getBoolean("hadPetBefore");
				String occupation = rs.getString("occupation");
				String housingType = rs.getString("housingType");
				int id = Integer.parseInt(rs.getString("id"));
				String type = rs.getString("user_type");
				User user = new User();
				user.setId(id);
				user.setFirstName(first_name);
				user.setLastName(second_name);
				user.setBirthday(birthday);
				user.setOccupation(occupation);
				user.setHousingType(housingType);
				user.setHadPetBefore(hadPetBefore);
				user.setType(type);
				if(user.getType().equalsIgnoreCase("admin"))
				{
				list.add(user);
				}
			}
			}
		} catch (NumberFormatException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;		
	}
	public List<Pet> findAllPets()
	{
		ResultSet rs = petPersist.searchAllPets();
		List<Pet>list = new ArrayList();
		try {
			if(rs.next())
			{
			rs.beforeFirst();
			//Create table of responses
			while(rs.next())
			{
				//Retrieve by column name
				String name = rs.getString("name");
				int id = Integer.parseInt(rs.getString("id"));
				String species = rs.getString("species");
				String gender = rs.getString("gender");
				Pet pet = new Pet();
				String location = rs.getString("location");
				String image = rs.getString("image");
				String color = rs.getString("color");
				int age = rs.getInt("age");
				if(location==null)
				{
					location = "N/A";
				}
				if(image==null)
				{
					image = "N/A";
				}
				pet.setAge(age);
				pet.setName(name);
				pet.setId(id);
				pet.setColor(color);
				pet.setImage(image);
				pet.setGender(gender);
				pet.setLocation(location);
				pet.setSpecies(species);
				list.add(pet);
			}
			}
		} catch (NumberFormatException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;		
	}
	public List<Pet> findPetsByGender(String genderSearch)
	{
		ResultSet rs = petPersist.searchPetsByGender(genderSearch);
		List<Pet>list = new ArrayList();
		try {
			if(rs.next())
			{
			rs.beforeFirst();
			//Create table of responses
			while(rs.next())
			{
				//Retrieve by column name
				String name = rs.getString("name");
				int id = Integer.parseInt(rs.getString("id"));
				String species = rs.getString("species");
				String gender = rs.getString("gender");
				Pet pet = new Pet();
				String location = rs.getString("location");
				String image = rs.getString("image");
				String color = rs.getString("color");
				int age = rs.getInt("age");
				if(location==null)
				{
					location = "N/A";
				}
				if(image==null)
				{
					image = "N/A";
				}
				pet.setAge(age);
				pet.setName(name);
				pet.setId(id);
				pet.setColor(color);
				pet.setImage(image);
				pet.setGender(gender);
				pet.setLocation(location);
				pet.setSpecies(species);
				list.add(pet);
			}
			}
		} catch (NumberFormatException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;		
	}
	public List<Pet> findPetsBySpecies(String speciesSearch)
	{
		ResultSet rs = petPersist.searchPetsBySpecies(speciesSearch);
		List<Pet>list = new ArrayList();
		try {
			if(rs.next())
			{
			rs.beforeFirst();
			//Create table of responses
			while(rs.next())
			{
				//Retrieve by column name
				String name = rs.getString("name");
				int id = Integer.parseInt(rs.getString("id"));
				String species = rs.getString("species");
				String gender = rs.getString("gender");
				Pet pet = new Pet();
				String location = rs.getString("location");
				String image = rs.getString("image");
				String color = rs.getString("color");
				int age = rs.getInt("age");
				if(location==null)
				{
					location = "N/A";
				}
				if(image==null)
				{
					image = "N/A";
				}
				pet.setAge(age);
				pet.setName(name);
				pet.setId(id);
				pet.setColor(color);
				pet.setImage(image);
				pet.setGender(gender);
				pet.setLocation(location);
				pet.setSpecies(species);
				list.add(pet);
			}
			}
		} catch (NumberFormatException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;		
	}
	public List<Pet> findPetsByAge(int ageSearch)
	{
		ResultSet rs = petPersist.searchPetsByAge(ageSearch);
		List<Pet>list = new ArrayList();
		try {
			if(rs.next())
			{
			rs.beforeFirst();
			//Create table of responses
			while(rs.next())
			{
				//Retrieve by column name
				String name = rs.getString("name");
				int id = Integer.parseInt(rs.getString("id"));
				String species = rs.getString("species");
				String gender = rs.getString("gender");
				Pet pet = new Pet();
				String location = rs.getString("location");
				String image = rs.getString("image");
				String color = rs.getString("color");
				int age = rs.getInt("age");
				if(location==null)
				{
					location = "N/A";
				}
				if(image==null)
				{
					image = "N/A";
				}
				pet.setAge(age);
				pet.setName(name);
				pet.setId(id);
				pet.setColor(color);
				pet.setImage(image);
				pet.setGender(gender);
				pet.setLocation(location);
				pet.setSpecies(species);
				list.add(pet);
			}
			}
		} catch (NumberFormatException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;		
	}
	//If no responses are found by the sql query
	
	public List<Pet> findCommonElements(List<Pet> list1, List<Pet> list2)
	{
		List<Pet>commonList = new ArrayList<Pet>();
		for(Pet pet: list1)
		{
			for(Pet pet2: list2)
			{
				if(pet.getId()==pet2.getId())
				{
					commonList.add(pet);
				}
			}
		}
		return commonList;
	}
	public int addPet(Pet pet)
	{
		return petPersist.enterPet(pet);
	}
	public void deletePet(Pet pet)
	{
		petPersist.deletePet(pet);
	}
	public void deleteUser(User user)
	{
		petPersist.deleteUser(user);
	}
	public void addUser(User user)
	{
		petPersist.enterUser(user);
	}
	
}
