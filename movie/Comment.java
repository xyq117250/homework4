package movie;

import java.sql.Date;

public class Comment {
	protected String id;
	protected String userName;
	protected String movieId;
	protected String comment;
	protected Date date;
	
	public Comment() {}
	
	public Comment(String id, String userName, String movieId, String comment, Date date) 
	{
		super(); //super is used to invoke user constructor
		this.id=id;
		this.userName = userName;
		this.movieId = movieId;
		this.comment = comment;
		this.date = date;
	}
	
	public void setId(String id) {
		  this.id = id;
	}
	public String getId() {
		  return id;
	}
	public void setUsername(String userName) {
		  this.userName = userName;
	}
	public String getUsername() {
		  return userName;
	}
	public void setMovieId(String movieId) {
		  this.movieId = movieId;
	}
	public String getMovieId() {
		  return movieId;
	}
	public void setComment(String comment) {
		  this.comment = comment;
	}
	public String getComment() {
		  return comment;
	}
	public void setDate(Date date) {
		  this.date = date;
	}
	public Date getDate() {
		  return date;
	}
}