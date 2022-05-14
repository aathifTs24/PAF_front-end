package RequestConnection;

//For REST Service
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
//For JSON
import com.google.gson.*;
//For XML
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;

@Path("/User")
public class userService {
	
	User userObj = new User();
	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readUser(){
		
		return userObj.readUser(); 
	 }

	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertUser(@FormParam("name") String name,
							 @FormParam("address") String address,
							 @FormParam("NIC") String NIC,
							 @FormParam("category") String category,
						     @FormParam("phone") String phone,
						     @FormParam("connection") String connection
						     ){
		
			String output = userObj.insertUser(name,address,NIC,category,phone,connection);
			return output;
	}
	
	
	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	
	public String updateUser(String userData){
	//Convert the input string to a JSON object
			 JsonObject userObject = new JsonParser().parse(userData).getAsJsonObject();
			//Read the values from the JSON object
			 String cid = userObject.get("cid").getAsString();
			 String name = userObject.get("name").getAsString();
			 String address = userObject.get("address").getAsString();
			 String NIC =userObject.get("NIC").getAsString();
			 String category =userObject.get("category").getAsString();
			 String phone =userObject.get("phone").getAsString();
			 String connection =userObject.get("connection").getAsString();
			
			 
			 String output = userObj.updateUser(cid,name,address,NIC,category,phone,connection);
			 
		return output;
	}
	
	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	
	public String deleteUser(String userData)
	{
		//Convert the input string to a JSON object
		 JsonObject userObject = new JsonParser().parse(userData).getAsJsonObject();
		
	//Read the value from the element <itemID>
		 String cid = userObject.get("cid").getAsString();
		 String output = userObj.deleteUser(cid);
		 
		 return output;
	}

	


}
