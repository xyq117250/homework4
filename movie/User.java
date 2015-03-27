package movie;

import java.sql.Date;

public class User {
	protected String username;
	protected String password;
	protected String firstName;
	protected String lastName;
	protected String email;
	protected Date dateOfBirth;
	
	public User() {}
	
	public User(String username, String password, String firstName,String lastName,String email,Date dateOfBirth ) {
		super();
		this.username = username;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.dateOfBirth = dateOfBirth;
	}
	public String getUsername() {
		  return username;
	}
	public void setUsername(String username) {
		  this.username = username;
	}
	public String getPassword(){
		  return password;
	}
	public void setPassword(String password) {
		  this.password = password;
	}
	public String getFirstName() {
		  return firstName;
	}
	public void setFirstName(String firstName) {
		  this.firstName = firstName;
	} 
	public String getLastName() {
		  return lastName;
	}
	public void setLastName(String lastName) {
		  this.lastName = lastName;
	}
	public String getEmail() {
		  return email;
	}
	public void setEmail(String email) {
		  this.email = email;
	}
	public Date getDateOfBirth() {
		  return dateOfBirth;
	}
	public void setDateOfBirth(Date dateOfBirth) {
		  this.dateOfBirth = dateOfBirth;
	}
}
