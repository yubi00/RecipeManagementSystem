/*
Name: Yuba Raj Khadka
Student Id: 18040483
Username: 18040483
Course Code: CSE4OAD

*/
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;
import com.google.gson.Gson;

public class RecipeWS extends HttpServlet
{
	RecipeDSC recipeDSC = new RecipeDSC();

	/*	To get one  (by id) or all s
	*/
	public void doGet(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException
	{
		// TODO
		try
		{
			//	Optional: Get request content type if we want to check
			String requestContentType = request.getContentType();
			System.out.println(">>> request content type: " + requestContentType);

			// Optional: Get request method if we want to check
			String requestMethod = request.getMethod();
			System.out.println(">>> request method: " + requestMethod);

			// Get id
			String id = request.getParameter("id");
			System.out.println(">>> id: " + id);


			//	Get method is used to find one Recipe or all recipes
			if (id != null)	// to find one Recipe
			{
				Recipe recipe = recipeDSC.find(Integer.parseInt(id));

				response.setContentType("application/json");
				PrintWriter out = response.getWriter();
				out.print( Helper.getJSON(recipe));
			}
			else
			//	Get method to get all recipes
			{
				List<Recipe> recipes = recipeDSC.findAll();

				response.setContentType("application/json");
				PrintWriter out = response.getWriter();
				out.print(Helper.getJSONList(recipes, Recipe.class));
			}

		}
		catch(Exception e)
		{
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		}
		
	}

	/*	To add a recipe
	*/
	public void doPost(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException
	{
		// TODO
		try
		{
			//	Get request content type
			String requestContentType = request.getContentType();
			System.out.println(">>> request content type: " + requestContentType);

			// Get request method
			String requestMethod = request.getMethod();
			System.out.println(">>> request method: " + requestMethod);

			// Get id
			String id = request.getParameter("id");
			System.out.println(">>> id: " + id);

			// Get data
			BufferedReader in = request.getReader();
			StringBuffer dataSB = new StringBuffer();
			String line = in.readLine();
			while(line != null)
			{
				dataSB.append(line).append("\n");
				line = in.readLine();
			}
			String data = dataSB.toString();
			System.out.println(">>> data:\n*" + data +"*");

			// Convert Json to Recipe
			Recipe recipe = Helper.getObject(data, Recipe.class);

			recipeDSC.add(recipe);

			response.setContentType("application/json");
			PrintWriter out = response.getWriter();
			out.print(data);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			response.sendError(800, e.getMessage());
		}
	}

	/*	To upadate a recipe
	*/
	public void doPut(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException
	{
		// TODO
		try
		{
			//	Get request content type
			String requestContentType = request.getContentType();
			System.out.println(">>> request content type: " + requestContentType);

			// Get request method
			String requestMethod = request.getMethod();
			System.out.println(">>> request method: " + requestMethod);

			// Get id
			String id = request.getParameter("id");
			System.out.println(">>> id: " + id);

			// Get data
			BufferedReader in = request.getReader();
			StringBuffer dataSB = new StringBuffer();
			String line = in.readLine();
			while(line != null)
			{
				dataSB.append(line).append("\n");
				line = in.readLine();
			}
			String data = dataSB.toString();
			System.out.println(">>> data:\n*" + data +"*");

			// Convert Json to Recipe
			Recipe recipe = Helper.getObject(data, Recipe.class);

			recipeDSC.update(recipe);

			response.setContentType("application/json");
			PrintWriter out = response.getWriter();
			out.print(data);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			response.sendError(800, e.getMessage());
		}

	}// doPut


	// To delete a recipe
	//
	public void doDelete(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException
	{
		// TODO
		try
		{
			//	Get request content type
			String requestContentType = request.getContentType();
			System.out.println(">>> request content type: " + requestContentType);

			// Get request method
			String requestMethod = request.getMethod();
			System.out.println(">>> request method: " + requestMethod);

			// Get id
			String id = request.getParameter("id");
			System.out.println(">>> id: " + id);

			//	This doDelete method is called when we have a DELETE request
			// Hence, we go ahead with the request and remove the Recipe
			recipeDSC.delete(Integer.parseInt(id));

			// We do not send back any data

		}
		catch(Exception e)
		{
			e.printStackTrace();
			response.sendError(800, e.getMessage());
		}
	}
}

