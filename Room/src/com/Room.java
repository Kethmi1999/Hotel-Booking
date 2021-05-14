package com;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Room {
	
	public Connection connect()
	{ 
	 Connection con = null; 
	 
	 try 
	 { 
	 Class.forName("com.mysql.cj.jdbc.Driver"); 
	 con= DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/user", "root", ""); 
	 //For testing
	 System.out.print("Successfully connected"); 
	 } 
	 catch(Exception e) 
	 { 
	 e.printStackTrace(); 
	 } 
	 
	 return con; 
		}
	
	public String insertItem(String roomid, String name, String type, String date) {
		
		 String output = "";
		 
		 try {
		
		Connection con = connect();
		if (con == null) 
		{ 
		return "Error while connecting to the database"; 
		}
		
		// create a prepared statement
		String query = "insert into room (id, roomId, roomName, roomType, roomdate)"
				 + " values (?, ?, ?, ?, ?)"; 
		PreparedStatement preparedStmt = con.prepareStatement(query); 
		// binding values
		preparedStmt.setInt(1, 0); 
		preparedStmt.setString(2, roomid); 
		preparedStmt.setString(3, name); 
		preparedStmt.setString(4, type); 
		preparedStmt.setString(5, date);
		
		System.out.println(roomid);
		 
		 preparedStmt.execute(); 
		 con.close(); 
		 
		 String newItems = readRooms();
		 output =  "{\"status\":\"success\", \"data\": \"" + 
				 newItems + "\"}"; 
		 } 

		catch (Exception e) 
		 { 
			output = "{\"status\":\"error\", \"data\": \"Error while inserting the item.\"}";  
		 System.err.println(e.getMessage()); 
		 } 
		return output; 
		}
	
	public String readRooms()
	{ 
			 String output = ""; 
			try
			 { 
			 Connection con = connect(); 
			 if (con == null) 
			 { 
			 return "Error while connecting to the database for reading."; 
			 } 
			 // Prepare the html table to be displayed
			 output = "<table border='1'><tr><th>Room Id</th>" 
			 +"<th>RoomName</th><th>Room Type </th>"
			 + "<th>Room Date</th>" 
			 + "<th>Update</th><th>Remove</th></tr>"; 
			 String query = "select * from room"; 
			 java.sql.Statement stmt = con.createStatement(); 
			 ResultSet rs = stmt.executeQuery(query); 
			 // iterate through the rows in the result set
			 while (rs.next()) 
			 { 
			 String id = Integer.toString(rs.getInt("id")); 
			 String roomid = rs.getString("roomId"); 
			 String name = rs.getString("roomName"); 
			 String type = rs.getString("roomType"); 
			 String date = rs.getString("roomDate"); 
			 // Add a row into the html table
			 output += "<tr><td><input id='hidItemIDUpdate' name='hidItemIDUpdate' type='hidden' value='" + id + "'>"
					 + roomid + "</td>";
			 output += "<td>" + name + "</td>"; 
			 output += "<td>" + type + "</td>"; 
			 
			 output += "<td>" + date + "</td>";
			 // buttons
			 output += "<td><input name='btnUpdate' " 
			 + " type='button' value='Update' class =' btnUpdate btn btn-secondary'data-itemid='" + id + "'></td>"
			 + "<td><form method='post' action='Room.jsp'>"
			 + "<input name='btnRemove' " 
			 + " type='button' value='Remove' class='btnRemove btn btn-danger' data-itemid='" + id + "'>"
			 + "<input name='hidItemIDDelete' type='hidden' " 
			 + " value='" + id + "'>" + "</form></td></tr>"; 
			 } 
			 con.close(); 
			 // Complete the html table
			 output += "</table>"; 
			 } 
			catch (Exception e) 
			 { 
			 output = "Error while reading the items."; 
			 System.err.println(e.getMessage()); 
			 } 
			return output; 
		}

		
	public String deleteItem(String id)
	{ 
	 String output = ""; 
	try
	 { 
	 Connection con = connect(); 
	 if (con == null) 
	 { 
	 return "Error while connecting to the database for deleting."; 
	 } 
	 // create a prepared statement
	 String query = "delete from room where id=?"; 
	 PreparedStatement preparedStmt = con.prepareStatement(query); 
	 // binding values
	 preparedStmt.setInt(1, Integer.parseInt(id)); 
	 
	 // execute the statement
	 preparedStmt.execute(); 
	 con.close(); 
	 String newItems = readRooms();
	 output =  "{\"status\":\"success\", \"data\": \"" + 
			 newItems + "\"}"; 
	 } 

	catch (Exception e) 
	 { 
		output = "{\"status\":\"error\", \"data\": \"Error while deleting the item.\"}";  
	 System.err.println(e.getMessage()); 
	 } 
	return output; 
		}
	
	public String updateItem(String id, String roomid, String name, String type, String date)
	//4
	{
	String output = "";
	try {
	Connection conn = connect();
	if (conn == null) {
	return "Error while connecting to the database for updating.";
	}

	// create a prepared statement
	String query = "UPDATE room SET roomId=?,roomName=?,roomtype=?,roomDate=? WHERE id=?";
	PreparedStatement preparedStmt = conn.prepareStatement(query);
	//binding values
	preparedStmt.setString(1, roomid);
	preparedStmt.setString(2, name);
	preparedStmt.setString(3, type);
	preparedStmt.setString(4, date);
	preparedStmt.setInt(5, Integer.parseInt(id));
	//execute the statement
	preparedStmt.execute();
	conn.close();
	String newItems = readRooms();
	 output =  "{\"status\":\"success\", \"data\": \"" + 
			 newItems + "\"}"; 
	 } 

	catch (Exception e) 
	 { 
		output = "{\"status\":\"error\", \"data\": \"Error while Updating the item.\"}";  
	
	System.err.println(e.getMessage());
	}
	return output;
	}

}
