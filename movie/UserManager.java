package movie;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class UserManager {
	Connection connection = null;
	PreparedStatement statement = null;
	ResultSet results = null;

	String createUserSql = "INSERT INTO USER (USERNAME, PASSWORD,FIRSTNAME,LASTNAME,EMAIL,DATEOFBIRTH) VALUES (?, ?, ?, ?, ?, ?);";
	String readUser = "SELECT * FROM USER WHERE USERNAME=?;";
	String readAllUsersSql = "SELECT * FROM USER;";
	String updateUserSql = "UPDATE USER SET PASSWORD=? AND FIRSTNAME=? AND LASTNAME=? AND EMAIL=? AND DATEOFBIRTH=? WHERE USERNAME=?;";
	String deleteUserSql = "DELETE FROM USER WHERE USERNAME=?";
	DataSource ds;
	
	public UserManager() {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/movie");
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		 }
	}
  //void createUser(User newUser);
	public void createUser(User newUser) {
		try {
			connection = ds.getConnection();
			statement = connection.prepareStatement(createUserSql);
			statement.setString(1, newUser.getUsername());
			statement.setString(2, newUser.getPassword());
			statement.setString(3, newUser.getFirstName());
			statement.setString(4, newUser.getLastName());
			statement.setString(5, newUser.getEmail());
			Date date = new Date(newUser.getDateOfBirth().getTime());
			statement.setDate(6, date);
			statement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
  //List<User> readAllUsers();
	public List<User> readAllUsers() {
		List<User> users = new ArrayList<User>();
		try {
			connection = ds.getConnection();
			statement = connection.prepareStatement(readAllUsersSql);
			results = statement.executeQuery();
			while(results.next()) {
				
				User user =new User();
				user.setUsername(results.getString("username"));
				user.setPassword(results.getString("password"));
				user.setFirstName(results.getString("firstName"));
				user.setLastName(results.getString("lastName"));
				user.setEmail(results.getString("email"));
				user.setDateOfBirth(results.getDate("dateOfBirth"));
				users.add(user);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return users;
	}

	//User readUser(String username);
	public User readUser(String username) {
		try {
			connection = ds.getConnection();
			statement = connection.prepareStatement(readUser);
			statement.setString(1, username);
			results = statement.executeQuery();
			if(results.next()) {
				User user =new User();
				user.setUsername(results.getString("username"));
				user.setPassword(results.getString("password"));
				user.setFirstName(results.getString("firstName"));
				user.setLastName(results.getString("lastName"));
				user.setEmail(results.getString("email"));
				user.setDateOfBirth(results.getDate("dateOfBirth"));
				return user;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;
	}
 
	//void updateUser(String username, User user);
	public void updateUser(String username, User user) {
		try {
			connection = ds.getConnection();
			statement = connection.prepareStatement(updateUserSql);
			statement.setString(1, user.getPassword());
			statement.setString(2, username);
			statement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	//void deleteUser(String username);
	public void deleteUser(String username) {
		try {
			connection = ds.getConnection();
			statement = connection.prepareStatement(deleteUserSql);
			statement.setString(1, username);
			statement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			 }
		}
	}


	}

