package com;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Inquiry {
	

	
	//A common method to connect to the DB
//A common method to connect to the DB
private Connection connect(){
	Connection con = null;
	try{
		Class.forName("com.mysql.cj.jdbc.Driver");

		//Provide the correct details: DBServer/DBName, username, password
		con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/concretepage", "root", "123456");
		
	}catch (Exception e){
		e.printStackTrace();
	}
	
	return con;
}
		
	
	
	//Insert Project Details
	public String insertInquiry(String inquiry_id, String full_name, String phone_no, String email, String address, String description){
		String output = "";
		try{
			Connection con = connect();
				if (con == null){
					return "Error while connecting to the database for inserting."; 
			}
			
				
				// create a prepared statement
				String query = "INSERT INTO `inquiries`(`inquiry_id`, `full_name`, `phone_no`, `email`,`address`, `description`) VALUES (?,?,?,?,?,?)";
				PreparedStatement preparedStmt = con.prepareStatement(query);
				
				
				 // binding values
				 preparedStmt.setInt(1, 0);
				 preparedStmt.setString(2, full_name);
				 preparedStmt.setString(3, phone_no);
				 preparedStmt.setString(4, email);
				 preparedStmt.setString(5, address); 
				 preparedStmt.setString(6, description);
				 
				 // execute the statement
				 preparedStmt.execute();
				 con.close();
				 
				 String newInquiry = readInquiry(); 
				 output = "{\"status\":\"success\", \"data\": \"" + newInquiry + "\"}";
				 
				 }catch (Exception e)
				 {
					 
					 output = "{\"status\":\"error\", \"data\":\"Error while inserting the Inquiry to system.\"}"; 
					 System.err.println(e.getMessage());
				 }
		 return output;
	 }
	
	
	// Method for create Table and Get Data from the Database
	
	public String readInquiry(){
		String output = "";
		try{
			Connection con = connect();
				if (con == null){
					return "Error while connecting to the database for reading."; 
		}
				
			// Prepare the html table to be displayed
			output = 
					"<table border='1' >"+ 
					"<tr >" +
						 "<th >Full Name</th>" +
						 "<th >Phone Number</th>" +
						 "<th>Email</th>" +
						 "<th>Address</th>" +
						 "<th>Description</th>" +
						 "<th>Update</th>" +
						 "<th>Remove</th>" +
					 "</tr>";

			String query = "select * from `inquiries`";
			 Statement stmt = con.createStatement();
			 ResultSet rs = stmt.executeQuery(query);
			 
			 
			 // iterate through the rows in the result set
			 while (rs.next()){
				 
				 
				 String inquiry_id =  Integer.toString(rs.getInt("inquiry_id"));
				 String full_name = rs.getString("full_name");
				 String phone_no = rs.getString("phone_no");
				 String email = rs.getString("email");
				 String address = rs.getString("address");
				 String description = rs.getString(("description"));

				 
				 // Add into the html table
				 
				 //output += "<tr><td>" + orderId + "</td>";
				 output += "<td>" + full_name + "</td>";
				 output += "<td>" + phone_no + "</td>";
				 output += "<td>" + email + "</td>";
				 output += "<td>" + address + "</td>";
				 output += "<td>" + description + "</td>";
	
				 
				 // buttons
				
				 output += "<td><input name='btnUpdate' type='button' value='Update' "
							+ "class='btnUpdate btn btn-secondary' data-userid='" +inquiry_id + "'></td>"
							+ "<td><input name='btnRemove' type='button' value='Remove' "
							+ "class='btnRemove btn btn-danger' data-userid='" + inquiry_id + "'></td></tr>"; 
			 }
		 con.close();
		 
		 // Complete the html table
		 output += "</table>";
		 
		 
		 }catch (Exception e){
			 
			 output = "Error while reading the connections.";
			 System.err.println(e.getMessage());
		 }
		 return output;
		 
	}
	
	//Method for Update the database information
	
	public String updateInquiry(String inquiry_id, String full_name, String phone_no, String email,String address, String description){ 
		String output = ""; 
		try{
			Connection con = connect();
			if (con == null){
				return "Error while connecting to the database for updating."; 
			} 
			
			 // create a prepared statement
			String query = "UPDATE `inquiries` SET `full_name`=?,`phone_no`=?,`email`=?,`address`=?,`description`=? WHERE `inquiry_id`=?";
			 PreparedStatement preparedStmt = con.prepareStatement(query); 
			 
			 // binding values
			  
			 preparedStmt.setString(1, full_name);
			 preparedStmt.setString(2, phone_no);
			 preparedStmt.setString(3, email);
			 preparedStmt.setString(4, address);
			 preparedStmt.setString(5, description); 
			 preparedStmt.setString(6, inquiry_id);
			 
			// preparedStmt.setString(4, sector);

			 
			 // execute the statement
			 preparedStmt.execute(); 
			 con.close(); 
			 
			 String newInquiry = readInquiry(); 
			 output = "{\"status\":\"success\", \"data\": \"" + newInquiry + "\"}";
			 
	
			 } catch (Exception e) {
				 
				 output = "{\"status\":\"error\", \"data\": \"Error while updating the inquiry.\"}";
				 System.err.println(e.getMessage()); 
			 } 
			 return output; 
	 }
	
	//Method for delete data

	public String deleteInquiry(String inquiry_id) { 
		String output = ""; 
		try{ 
			Connection con = connect();
			if (con == null) { 
				return "Error while connecting to the database for deleting."; 
			} 
				// create a prepared statement
			    String query ="DELETE FROM `inquiries` WHERE inquiry_id=?";
				PreparedStatement preparedStmt = con.prepareStatement(query); 
				
				// binding values
				preparedStmt.setInt(1, Integer.parseInt(inquiry_id)); 
				
				// execute the statement
				preparedStmt.execute(); 
				con.close(); 
				
				String newInquiry = readInquiry(); 
				output = "{\"status\":\"success\", \"data\": \"" + newInquiry + "\"}"; 
				
		} catch (Exception e) { 
			output = "{\"status\":\"error\", \"data\": \"Error while deleting the connection.\"}"; 
			System.err.println(e.getMessage()); 
		} 
		return output; 
	}
	

}
