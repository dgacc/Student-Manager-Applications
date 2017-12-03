package Connect;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class ConnectDatabase {
	Connection connection;
	Statement statement;
	ResultSet resultSet;
	String url, username, password;
	
    public ConnectDatabase() {
    	Properties properties = new Properties();
		try {
			properties.load(new FileInputStream("src/connect/connect.properties"));
			url = properties.getProperty("url");
			username = properties.getProperty("user");
			password = properties.getProperty("pass");
			
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(url, username, password);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
    
    public ResultSet returnData(String SqlCommand) {
    	if(statement ==  null) {
    		try {
				statement = connection.createStatement();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	}
    	
    	try {
			resultSet = statement.executeQuery(SqlCommand);
			return resultSet;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
    	
    }
    
    public void updateData(String SqlCommand) {
    	try {
			statement = connection.createStatement();
			statement.executeUpdate(SqlCommand);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}
