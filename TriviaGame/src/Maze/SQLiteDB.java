package Maze;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class SQLiteDB {
	Connection connection = null;
	Statement statement = null;
	
	SQLiteDB(){
		try {
			Class.forName("org.sqlite.JDBC");
			connection = DriverManager.getConnection("jdbc:sqlite:Questions.sqlite");
		}catch(Exception e) {
			System.out.println("Error: " + e.getMessage());
		}
	}

	
	
	public String getQuestion(int num) {
		try {
			this.statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery("SELECT * FROM Questions where question_number =" + num);
			
			String question = resultSet.getString("question");
			
			for(int i = 1; i < 5; i++) {
				String choice = resultSet.getString("choice" + i);
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
			this.statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery("SELECT answer FROM Questions where question_number =" + num);
			return resultSet.getString("answer");
		}catch(Exception e) {
			System.out.println("Error: " + e.getMessage());
		}	
		return null;
	}
	
	public void close() {
		try {
			connection.close();
		}catch(Exception e) {
			System.out.println("Error: " + e.getMessage());
		}
	}
	
	
}
