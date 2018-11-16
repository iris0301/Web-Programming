package edu.uga.cs4300.objectlayer;

public class Pet {
	String name;
	String species;
	int age;
	String color;
	String gender;
	String location;
	String image;
	int id; 
	public String getName()
	{
		return this.name;
	}
	public int getAge()
	{
		return this.age;
	}
	public void setAge(int age)
	{
		this.age = age;
	}
	public void setName(String name)
	{
		this.name = name;
	}
	public String getSpecies()
	{
		return this.species;
	}
	public void setSpecies(String species)
	{
		this.species = species;
	}
	public String getColor()
	{
		return this.color;
	}
	public void setColor(String color)
	{
		this.color = color;
	}
	public String getGender()
	{
		return this.gender;
	}
	public void setGender(String gender)
	{
		this.gender = gender;
	}
	public String getLocation()
	{
		return this.location;
	}
	public void setLocation(String location)
	{
		this.location = location;
	}
	public String getImage()
	{
		return this.image;
	}
	public void setImage(String image)
	{
		this.image = image;
	}
	public void setId(int id)
	{
		this.id = id;
	}
	public int getId()
	{
		return this.id;
	}
	
}
