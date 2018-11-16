package edu.uga.cs4300.persistlayer;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;

import com.mysql.jdbc.Statement;

import edu.uga.cs4300.objectlayer.Pet;
import edu.uga.cs4300.objectlayer.User;

public class PetPersistsImpl {
	DbAccessImpl db = new DbAccessImpl();
	Connection con = db.connect(); 

	public ResultSet searchAllUsers()
	{
		String query = "SELECT * "+
		"FROM users";
		System.out.println(query);
		ResultSet rs = db.retrieve(db.connect(), query);
		return rs;
	}
	public ResultSet searchUserById(int id)
	{
		String query = "SELECT * "+
		"FROM users WHERE id="+id+"";
		System.out.println(query);
		ResultSet rs = db.retrieve(db.connect(), query);
		return rs;
	}public ResultSet searchUserByCredentials(String username,String password)
	{
		String query = "SELECT * "+
		"FROM users WHERE username=\""+username+"\" AND password=\""+password+"\"";
		System.out.println(query);
		ResultSet rs = db.retrieve(db.connect(), query);
		return rs;
	}
	public ResultSet searchUsersByName(String firstname,String lastname)
	{
		String query = "SELECT * "+
		"FROM users WHERE first_name=\""+firstname+"\" AND last_name=\""+lastname+"\"x";
		System.out.println(query);
		ResultSet rs = db.retrieve(db.connect(), query);
		return rs;
	}
	public ResultSet searchAllPets()
	{
		String query = "SELECT * "+
		"FROM pets";
		System.out.println(query);
		ResultSet rs = db.retrieve(db.connect(), query);
		return rs;
	}
	public ResultSet searchPetsByName(String name)
	{
		String query = "SELECT * from pets WHERE name LIKE '%"+name+"%'";
		ResultSet rs = db.retrieve(db.connect(), query);
		return rs;
	}
	public ResultSet searchPetsBySpecies(String species)
	{
		String query = "SELECT * "+
		"FROM pets"+
		" WHERE species =\""+species+"\"";
		System.out.println(query);
		ResultSet rs = db.retrieve(db.connect(), query);
		return rs;
	}
	public ResultSet searchPetsByGender(String gender)
	{
		String query = "SELECT * "+
		"FROM pets"+
		" WHERE gender =\""+gender+"\"";
		System.out.println(query);
		ResultSet rs = db.retrieve(db.connect(), query);
		return rs;
	}
	public ResultSet searchPetsByColor(String color)
	{
		String query = "SELECT * "+
		"FROM pets"+
		"WHERE color =\""+color+"\"";
		System.out.println(query);
		ResultSet rs = db.retrieve(db.connect(), query);
		return rs;
	}
	public ResultSet searchPetsByLocation(String location)
	{
		String query = "SELECT * "+
		"FROM pets"+
		" WHERE location =\""+location+"\"";
		System.out.println(query);
		ResultSet rs = db.retrieve(db.connect(), query);
		return rs;
	}
	public ResultSet searchPetsByAge(int age)
	{
		String query = "SELECT * "+
		"FROM pets "+
		" WHERE age="+age+"";
		System.out.println(query);
		ResultSet rs = db.retrieve(db.connect(), query);
		return rs;
	}
	public ResultSet findAllMovies()
	{
		String query = "SELECT m.id,m.name,mg.genre,m.rank,m.year "+
		"FROM movies m "+
		"INNER JOIN movies_genres mg ON mg.movie_id=m.id "+
		"GROUP BY m.name";
		System.out.println(query);
		ResultSet rs = db.retrieve(db.connect(), query);
		return rs;
	}
	public int enterPet(Pet pet)
	{
		String insertTableSQL = "INSERT INTO pets"
				+ "(name,species,age,color,gender,location,image) VALUES"
				+ "(?,?,?,?,?,?,?)";
		int lastInsertId = 0;
		try {
			PreparedStatement preparedStatement = db.connect().prepareStatement(insertTableSQL,Statement.RETURN_GENERATED_KEYS);
			preparedStatement.setString(1,pet.getName());
			preparedStatement.setString(2,pet.getSpecies());
			preparedStatement.setInt(3, pet.getAge());
			preparedStatement.setString(4,pet.getColor());
			preparedStatement.setString(5,pet.getGender());
			preparedStatement.setString(6,pet.getLocation());
			preparedStatement.setString(7,pet.getImage());

			lastInsertId = db.update(db.connect(), preparedStatement);
			pet.setId(lastInsertId);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("Persist:"+lastInsertId);
		return lastInsertId;
	}
	public int enterUser(User user)
	{
		String insertTableSQL = "INSERT INTO users"
				+ "username,first_name,last_name,password,hadPetBefore,occupation,housingType,birthday,user_type) VALUES"
				+ "(?,?,?,?,?,?,?,?,?)";
		int lastInsertId = 0;
		try {
			PreparedStatement preparedStatement = db.connect().prepareStatement(insertTableSQL,Statement.RETURN_GENERATED_KEYS);
			preparedStatement.setString(1,user.getUsername());
			preparedStatement.setString(2,user.getFirstName());
			preparedStatement.setString(3, user.getLastName());
			preparedStatement.setString(4,user.getPassword());
			preparedStatement.setBoolean(5,user.isHadPetBefore());
			preparedStatement.setString(6,user.getOccupation());
			preparedStatement.setString(7,user.getHousingType());
			preparedStatement.setDate(8,user.getBirthday());
			preparedStatement.setString(9,user.getType());
			lastInsertId = db.update(db.connect(), preparedStatement);
			user.setId(lastInsertId);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("Persist:"+lastInsertId);
		return lastInsertId;
	}
	public int enterApplication(Pet pet,User user,String text)
	{
		String insertTableSQL = "INSERT INTO application"
				+ "(user_id,pet_id,date,status,text) VALUES"
				+ "(?,?,?,?,?)";
		int lastInsertId = 0;
		try {
			PreparedStatement preparedStatement = db.connect().prepareStatement(insertTableSQL,Statement.RETURN_GENERATED_KEYS);
			preparedStatement.setInt(1,user.getId());
			preparedStatement.setInt(2,pet.getId());
			Date currentDate = new Date(Calendar.getInstance().getTime().getTime());
			preparedStatement.setDate(3,currentDate);
			preparedStatement.setString(4,"Pending");
			preparedStatement.setString(5,text);
			lastInsertId = db.update(db.connect(), preparedStatement);
			pet.setId(lastInsertId);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("Persist:"+lastInsertId);
		return lastInsertId;
	}
	public int deletePet(Pet pet)
	{
		int lastInsertId = 0;
		try {
			String insertTableSQL = "DELETE FROM pets WHERE id = ?";
			PreparedStatement preparedStatement = db.connect().prepareStatement(insertTableSQL,Statement.RETURN_GENERATED_KEYS);
			preparedStatement.setInt(1,pet.getId());
			db.update(db.connect(), preparedStatement);
			
			insertTableSQL = "DELETE FROM applications WHERE pet_id = ?";
			preparedStatement = db.connect().prepareStatement(insertTableSQL,Statement.RETURN_GENERATED_KEYS);
			preparedStatement.setInt(1,pet.getId());
			db.update(db.connect(), preparedStatement);

		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		System.out.println("Persist:"+lastInsertId);
		return lastInsertId;
	}
	public int deleteUser(User user)
	{
		int lastInsertId = 0;
		try {
			String insertTableSQL = "DELETE FROM users WHERE id = ?";
			PreparedStatement preparedStatement = db.connect().prepareStatement(insertTableSQL,Statement.RETURN_GENERATED_KEYS);
			preparedStatement.setInt(1,user.getId());
			db.update(db.connect(), preparedStatement);
			
			insertTableSQL = "DELETE FROM applications WHERE user_id = ?";
			preparedStatement = db.connect().prepareStatement(insertTableSQL,Statement.RETURN_GENERATED_KEYS);
			preparedStatement.setInt(1,user.getId());
			db.update(db.connect(), preparedStatement);

		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		System.out.println("Persist:"+lastInsertId);
		return lastInsertId;
	}
	
}
