/*
Name: Yuba Raj Khadka
Student Id: 18040483
Username: 18040483
Course Code: CSE4OAD

*/
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.*;

import java.util.ArrayList;
import java.util.List;

import validate.*;	// Notice this

public class RecipeDSC
{
	private  Connection connection;
	private  Statement statement;
	private  PreparedStatement preparedStatement;
	private  String password;
	private final String driver = "com.mysql.jdbc.Driver";


	public RecipeDSC()
	{
		 try {
        Class.forName(driver);
    } catch (ClassNotFoundException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
    }
	}
	public  void getPassword()
	{
		char [] pwd = System.console().readPassword("Password: ");
		password = new String(pwd);
	}

	public  void connect() throws SQLException
	{
		 String url = "jdbc:mysql://latcs7.cs.latrobe.edu.au:3306/18040483";
		// String user = "username";

		//String url = "jdbc:mysql://localhost:3306/recipedb";
		String user = "18040483";
		password = "helloworld";
		connection = DriverManager.getConnection(url, user, password);
		statement = connection.createStatement();
	}

	public  void disconnect() throws SQLException
	{
		if (preparedStatement != null) preparedStatement.close();
		if (statement != null) statement.close();
		if (connection != null) connection.close();
	}

	/*
	 * TODO: This method should find a Recipe with the given id.
	 * @param id The id of the Recipe to be found.
	 * @return The Recipe with the given id if it exists. Otherwise return null.
	 * @throws SQLException
	 *	(May or may not be needed for the GUI part)
	 */
	public Recipe find(int id) throws SQLException
	{
		connect();
		ResultSet rs = statement.executeQuery(
			"select * from recipe where id = " + id);

		boolean hasRecords = rs.next();
		Recipe recipe = null;
		if(hasRecords)
		{
			recipe =  new Recipe(
				rs.getInt(1),	 	// id
				rs.getString(2), 	// name
				rs.getInt(3), 		// serves
				rs.getString(4),	// ingredients
				rs.getString(5),	// steps
				rs.getString(6));	// remarks
		}
		disconnect();
		return recipe;
	}


	/*
	 * TODO: This method should count the total number of Recipes in the database
	 * @return An int representing the number of Recipes
	 * @throws SQLException
	 * (May or may not be needed for the GUI part)
	 */
	public int count() throws SQLException
	{
		connect();
		ResultSet rs = statement.executeQuery(
			"select * from recipe");
		int count =  rs.last()? rs.getRow(): 0;
		disconnect();
		return count;
	}


	/**
	 * TODO: This method should obtain the list of all Recipes from the database
	 * @return A list of all stored Recipes
	 * @throws SQLException
	 */
	public List<Recipe> findAll() throws SQLException
	{
		connect();

		String queryString = "select * from recipe";
		ResultSet rs = statement.executeQuery(queryString);

		List<Recipe> recipes = new ArrayList<Recipe>();

		while (rs.next())
		{
			int id = rs.getInt(1);
			String name = rs.getString(2);
			int serves = rs.getInt(3);
			String ingredients = rs.getString(4);
			String steps = rs.getString(5);
			String remarks = rs.getString(6);

			recipes.add(new Recipe(id, name, serves, ingredients, steps, remarks));
		}

		disconnect();
		return recipes;
	}



	/*
	 * TODO: This method should try to add a new Recipe with the details
	 * provided by the Recipe parameter.
	 * @param recipe The Recipe to be updated
	 * @throws SQLException
	 */
	public int add(String name, int serves, String ingredients, String steps, String remarks) throws SQLException
	{

		connect();

		String command = "insert into recipe values(?, ?, ?, ?, ?, ?)";
		preparedStatement = connection.prepareStatement(command);

		preparedStatement.setInt(1, 0);
		preparedStatement.setString(2, name);
		preparedStatement.setInt(3, serves);
		preparedStatement.setString(4, ingredients);
		preparedStatement.setString(5, steps);
		preparedStatement.setString(6, remarks);

		preparedStatement.executeUpdate();

		ResultSet rs = statement.executeQuery("select last_insert_id()");
		rs.next();
		int newId = rs.getInt(1);

		disconnect();
		return newId;
	}

	/*
	 * TODO: This method should try to add the given Recipe to the database.
	 * Note: The ID of this Recipe must be unique
	 * @param recipe The recipe to be added
	 * @throws SQLException
	 */

	public void add(Recipe recipe) throws SQLException, Exception
	{
		// TO DO

		// Simply call the other add method
		// The id of recipe is simply ignored
		//

		Validator.validate(recipe);

		add(recipe.getName(), recipe.getServes(), recipe.getIngredients(),
			recipe.getSteps(), recipe.getRemarks());

	}

	/**
	 * TODO: This method should try to update an existing Recipe using the
	 * details provided by the given Recipe parameter.
	 * @param Recipe The Recipe to be updated (in fact, it can be a proxy)
	 * @throws SQLException
	 */
	public void update(Recipe recipe) throws SQLException, Exception
	{
		if(find(recipe.getId()) == null)
		{
			throw new Exception("ERROR: Recipe does not exist!");
		}

		connect();

		String command = "UPDATE recipe SET name = ?, serves = ?, " +
			"ingredients = ?, steps = ?, remarks = ? WHERE id = ?";
		preparedStatement = connection.prepareStatement(command);

		preparedStatement.setString(1, recipe.getName());
		preparedStatement.setInt(2, recipe.getServes());
		preparedStatement.setString(3, recipe.getIngredients());
		preparedStatement.setString(4, recipe.getSteps());
		preparedStatement.setString(5, recipe.getRemarks());
		preparedStatement.setInt(6, recipe.getId());

		preparedStatement.executeUpdate();
		disconnect();
	}

	public void delete(int id) throws SQLException
	{
		// TO DO HERE: Check the precodition first

		connect();
		ResultSet rs = statement.executeQuery(
			"select count(*) from recipe where id = " + id);
		boolean pre = rs.next();
		if(! pre)
		{
			throw new RuntimeException("The movie review does not exist!");
		}

		statement.executeUpdate("delete from  recipe where id = " + id);
		disconnect();
	}


	/**
	 * TODO: This method should try to delete a Recipe record from the database
	 * @param Recipe The Recipe to be deleted (it can be a proxy)
	 * @throws SQLException If the ID of the Recipe doesn't already exist
	 */
	public void delete(Recipe recipe) throws SQLException
	{
		delete(recipe.getId());
	}

	public static void main(String [] args) throws Exception
	{
		RecipeDSC dsc = new RecipeDSC();
		//dsc.getPassword();

		List<Recipe> list = dsc.findAll();
		System.out.println(list);

		Recipe recipe = dsc.find(4);
		System.out.println(recipe);

		recipe = dsc.find(100);
		System.out.println(recipe);

		int id = dsc.add("name 200", 100, "ingredients 200", "step 1 , 2, 3, 4", "easy");
		System.out.println("id: " + id);

		recipe = dsc.find(4);
		recipe.setName("Drunken chicken");
		recipe.setServes(10000);
		recipe.setIngredients("Drunken chicken 10 of them; RICE 100kg");
		recipe.setSteps("\n1. Cook chicken\n2.Cook rice");
		recipe.setRemarks("Enjoy the festival!");

		System.out.println(">>> updated recipe: " + recipe);

		dsc.update(recipe);
	}

}
