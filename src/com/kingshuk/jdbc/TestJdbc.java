package com.kingshuk.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;

public class TestJdbc {

	public static void main(String[] args) {
		
		String jdbcUrl = "jdbc:mysql://localhost:3306/hb_student_tracker?useSSL=false"; 
		String user = "root";
		String password = "Puchki@0810";
		try {
			System.out.println("Connecting to database:"+jdbcUrl);
			Connection conn = DriverManager.getConnection(jdbcUrl, user, password);
			System.out.println("Successfully Connected!!!");
			
		}catch(Exception ex) {
			ex.printStackTrace();
		}

	}

}
