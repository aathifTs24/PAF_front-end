package RequestConnection;
import java.sql.*;

public class User {
		
	//A common method to connect to the DB
	private Connection connect() {
		 
			Connection con = null;
		 try {
		 
			 Class.forName("com.mysql.jdbc.Driver");
	
			 //Provide the correct details: DBServer/DBName, category, phone
			 con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/test", "root", "");
			 }
			catch (Exception e)
			 	{e.printStackTrace();}
			 return con;
			 }
	/*Insert user*/
	public String insertUser(String name, String address, String NIC,String category,String phone,String connection) {
		 
			String output = "";
		 try
		 {
				 Connection con = connect();
				 if (con == null)
				 {return "Error while connecting to the database for inserting."; }
				 // create a prepared statement
				 String query = " insert into user (`cid`,`name`,`address`,`NIC`,`category`,`phone`,`connection`)"
				 + " values (?, ?, ?, ?,?,?,?)";
				
				 PreparedStatement preparedStmt = con.prepareStatement(query);
				 // binding values
				 preparedStmt.setInt(1, 0);
				 preparedStmt.setString(2, name);
				 preparedStmt.setString(3, address);
				 preparedStmt.setString(4, NIC);
				 preparedStmt.setString(5, category);
				 preparedStmt.setString(6, phone);
				 preparedStmt.setString(7, connection);
			
				 
				 // execute the statement
				 
				 preparedStmt.execute();
				 con.close();
				 String newItems = readUser();
				 output = "{\"status\":\"successfully inserted\", \"data\": \"" +newItems + "\"}";
				 }
				 catch (Exception e)
				 {
				 output = "{\"status\":\"error\", \"data\":\"Error while inserting the data.\"}";
				 System.err.println(e.getMessage());
				 } 
		 return output;
		 }
		
		/*read User*/
		public String readUser(){
		 
			String output = "";
		 try
		 {
			 Connection con = connect();
			 if (con == null)
			 {return "Error while connecting to the database for reading."; }
			 // Prepare the html table to be displayed
			 output = "<table border='1'><tr><th>Name</th><th>Address</th>" +
			 "<th>NIC</th><th>Consumer Category</th><th>Phone</th><th>connection</th>" +
			 "<th>Update</th><th>Remove</th></tr>";
	
			 String query = "select * from user";
			 Statement stmt = con.createStatement();
			 ResultSet rs = stmt.executeQuery(query);
			 // iterate through the rows in the result set
		 while (rs.next()) {
		 
				 String cid = Integer.toString(rs.getInt("cid"));
				 String name = rs.getString("name");
				 String address = rs.getString("address");
				 String NIC = rs.getString("NIC");
				 String category = rs.getString("category");
				 String phone = rs.getString("phone");
				 String connection = rs.getString("connection");
		
				 
				 // Add into the html table
				 output += "<tr><td>" +name + "</td>";
				 output += "<td>" + address + "</td>";
				 output += "<td>" + NIC + "</td>";
				 output += "<td>" + category + "</td>";
				 output += "<td>" + phone + "</td>";
				 output += "<td>" + connection + "</td>";
				
				 
				 // buttons
				 output += "<td><input name='btnUpdate' type='button' value='Update' "
							+ "class='btnUpdate btn btn-secondary' data-itemid='" + cid + "'></td>"
							+ "<td><input name='btnRemove' type='button' value='Remove' "
							+ "class='btnRemove btn btn-danger' data-itemid='" + cid + "'></td></tr>";
				 }
				 con.close();
				 // Complete the html table
				 output += "</table>";
		 }
		 catch (Exception e) {
			 output = "Error while reading the users.";
			 System.err.println(e.getMessage());
			 }
		
		 return output;
		 }
		
		/*update User*/
		public String updateUser(String cid, String name, String address,String NIC, String category,String phone,String connection)
		{
			 String output = "";
			 try {
			 
			 Connection con = connect();
			 if (con == null){
				 return "Error while connecting to the database for updating.";
				 }
				 // create a prepared statement
				 String query = "UPDATE `user` SET `name`=?,`address`=?,`NIC`=?,`category`=?,`phone`=?,`connection`=? WHERE `cid`=?";
				 PreparedStatement preparedStmt = con.prepareStatement(query);
				 // binding values
				 preparedStmt.setString(1, name);
				 preparedStmt.setString(2, address);
				 preparedStmt.setString(3, NIC);
				// preparedStmt.setDouble(3, Double.parseDouble(NIC));
				 preparedStmt.setString(4, category);
				 preparedStmt.setString(5, phone);
				 preparedStmt.setString(6, connection);
				
				 
				 preparedStmt.setInt(7, Integer.parseInt(cid));
				 // execute the statement
				 preparedStmt.execute();
				 con.close();
				 String newItems = readUser();
				 output = "{\"status\":\"update successfull\", \"data\": \"" +newItems + "\"}";
				 }
				 catch (Exception e)
				 {
				 output = "{\"status\":\"error\", \"data\":\"Error while updating the status.\"}";
				 System.err.println(e.getMessage());
				 } 
		 return output;
		 }
		
		/*Delete User*/
		public String deleteUser(String cid) {
			 String output = "";
			try{
			  Connection con = connect();
			  if (con == null){
				  return "Error while connecting to the database for deleting."; 
				  }
					 // create a prepared statement
					 String query = "delete from user where cid=?";
					 PreparedStatement preparedStmt = con.prepareStatement(query);
					 // binding values
					 preparedStmt.setInt(1, Integer.parseInt(cid));
					 // execute the statement
					 preparedStmt.execute();
					 con.close();
					 String newItems = readUser();
					 output = "{\"status\":\"successfully deleted\", \"data\": \"" +newItems + "\"}";
					 }
					 catch (Exception e)
					 {
					 output = "{\"status\":\"error\", \"data\":\"Error while deleting the data.\"}";
					 System.err.println(e.getMessage());
					 } 
			 return output;
			 } 
		
	
	
	
}
