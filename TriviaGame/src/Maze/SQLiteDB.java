package Maze;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class SQLiteDB {
	Connection c = null;
	Statement stmt = null;
	
	SQLiteDB(){
		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:Questions.sqlite");
		}catch(Exception e) {
			System.out.println("Error: " + e.getMessage());
		}
	}

	
	
	public String getQuestion(int num) {
		try {
			this.stmt = c.createStatement();
			ResultSet s = stmt.executeQuery("SELECT * FROM Questions where question_number =" + num);
			
			String question = s.getString("question");
			
			for(int i = 1; i < 5; i++) {
				String choice = s.getString("choice" + i);
				if(choice.length() > 0) 
					question = question + "\n" + choice;
			}
			return question;
			
		} catch(Exception e) {
			System.out.println("Error: " + e.getMessage());
		}
		return null;	
	}
	
	public String getAnswer(int num) {
		try {
			this.stmt = c.createStatement();
			ResultSet s = stmt.executeQuery("SELECT answer FROM Questions where question_number =" + num);
			return s.getString("answer");
		}catch(Exception e) {
			System.out.println("Error: " + e.getMessage());
		}	
		return null;
	}
	
	public void close() {
		try {
			c.close();
		}catch(Exception e) {
			System.out.println("Error: " + e.getMessage());
		}
	}
	
	
}
