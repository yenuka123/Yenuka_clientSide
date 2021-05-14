package com;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Fin {
	
	//Database connection part
	private Connection connect() {
		
		Connection con = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			// DatabaseServer/DatabaseName/username/password
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/gadgetbadget_db", "root", "toor");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}

			public String readFin() {
				
				
				
				String output = "";
				

				try {
					Connection con = connect();
					if (con == null) {
						return "Error while connecting to the database for reading.";
					}

					// Prepare the html table to be displayed
					output = "<table border='1'><tr><th>Product Category</th>" + "<th>Category Price</th><th>Tax Rate</th>"
							 + "<th>Gross Price</th>" + "<th>Update</th><th>Delete</th></tr>";

					String query = "select * from finance";
					Statement stmt = con.createStatement();
					ResultSet rs = stmt.executeQuery(query);

					while (rs.next()) {

						String tax_id = Integer.toString(rs.getInt("tax_id"));
						String product_category = rs.getString("product_category");
						String category_price = rs.getString("category_price");
						String tax_rate = rs.getString("tax_rate");
						String gross_price = rs.getString("gross_price");

						// Add data into the html table

						//output += "<tr><td>" + FinID + "</td>";//<input id='hidFinIDUpdate' name='hidFinIDUpdate' type='hidden' value='"
						output += "<td>" + product_category + "</td>";		//+ FinID + "'>" + Name + "</td>";
						output += "<td>" + category_price + "</td>";
						output += "<td>" + tax_rate + "</td>";
						output += "<td>" + gross_price + "</td>";

						// buttons in table
						output += "<td><input name='btnUpdate' type='button' value='Update' class='btnUpdate btn btn-secondary'></td>"
								+ "<td><input name='btnRemove' type='button' value='Delete' class='btnRemove btn btn-danger' data-tax_id='"
								+ tax_id + "'>" + "</td></tr>";

					}

					con.close();

					
					output += "</table>";
					
				} catch (Exception e) {
					output = "Error while reading the Appointment.";
					System.err.println(e.getMessage());
				}
				
				

				return output;
				
				
			}

			
			public String insertFin(String product_category, String category_price, String tax_rate, String gross_price) {
				
				
				String output = "";
				
				
				System.out.println("Inserted in these details     " + product_category + category_price + tax_rate + gross_price);

				try {
					Connection con = connect();

					if (con == null) {
						return "Error while connecting to the database";
					}

					// create a prepared statement
					String query = " insert into finance (`product_category`,`category_price`,`tax_rate`,`gross_price`)"
							+ " values (?, ?, ?, ?)";

					PreparedStatement preparedStmt = con.prepareStatement(query);

					// binding values
					preparedStmt.setString(1, product_category);
					preparedStmt.setString(2, category_price);
					preparedStmt.setString(3, tax_rate);
					preparedStmt.setString(4, gross_price);

					
					preparedStmt.execute();
					con.close();

					// Create JSON Object to show successful msg.
					String newFin = readFin();
					output = "{\"status\":\"successful\", \"data\": \"" + newFin + "\"}";
				} catch (Exception e) {
					// Create JSON Object to show Error msg.
					output = "{\"status\":\"error\", \"data\": \"Error while Inserting Finance.\"}";
					System.err.println(e.getMessage());
				}

				return output;
			}

			// Update finance
			public String updateFin(String product_category, String category_price, String tax_rate, String gross_price, int tax_id) {
				
				
				String output = "";

				
				
				try {
					Connection con = connect();

					if (con == null) {
						return "Error while connecting to the database for updating part";
					}

					// create a prepared statement
					String query = "update finance SET product_category=?,category_price=?,tax_rate=?,gross_price=? WHERE tax_id=?";

					PreparedStatement preparedStmt = con.prepareStatement(query);

					// binding values
					preparedStmt.setString(1, product_category);
					preparedStmt.setString(2, category_price);
					preparedStmt.setString(3, tax_rate);
					preparedStmt.setString(4, gross_price);
					preparedStmt.setInt(5, tax_id);

					
					preparedStmt.execute();
					con.close();

					// create JSON object to show successful msg
					String newFin = readFin();
					output = "{\"status\":\"successful\", \"data\": \"" + newFin + "\"}";
				} catch (Exception e) {
					output = "{\"status\":\"error\", \"data\": \"Error while Updating Finance Details.\"}";
					System.err.println(e.getMessage());
				}

				return output;
			}

			
			
			public String deleteFin(String tax_id) {
				
				
				String output = "";
				
				

				try {
					Connection con = connect();

					if (con == null) {
						return "Error while connecting to the database for deleting.";
					}

					// create a prepared statement
					String query = "delete from finance where tax_id=?";

					PreparedStatement preparedStmt = con.prepareStatement(query);

					
					preparedStmt.setInt(1, Integer.parseInt(tax_id));
					// execute the statement
					preparedStmt.execute();
					con.close();

					
					String newFin = readFin();
					output = "{\"status\":\"success\", \"data\": \"" + newFin + "\"}";
					
				} catch (Exception e) {
					
					
					output = "{\"status\":\"error\", \"data\": \"Error while Deleting Finance.\"}";
					System.err.println(e.getMessage());

				}

				return output;
				
				
			}
		}

		
