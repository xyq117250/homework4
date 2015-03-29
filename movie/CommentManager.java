package movie;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import movie.Comment;

public class CommentManager {

	Connection connection = null;
	PreparedStatement statement = null;
	ResultSet results = null;

	String createCommentSql = "INSERT INTO COMMENT (ID,USERNAME, MOVIEID, COMMENT, DATE) VALUES (?,?,?,?,?);";
	String readAllCommentsSql = "SELECT * FROM COMMENT;";
	String readAllCommentsForUsernameSql = "SELECT COMMENT.COMMENT FROM COMMENT, USER WHERE COMMENT.USERNAME=USER.USERNAME AND USERNAME=?;";
	String readAllCommentsForMovieSql = "SELECT COMMENT.COMMENT FROM COMMENT, MOVIE WHERE MOVIE.ID=COMMENT.MOVIEID AND MOVIE.ID=?;";
	String readCommentForIdSql = "SELECT COMMENT.COMMENT FROM COMMENT WHERE ID=?;";
	String updateCommentSql = "UPDATE COMMENT SET COMMENT=? WHERE ID=?;";
	String deleteCommentSql = "DELETE FROM COMMENT WHERE ID=?;";
	DataSource ds;
	
	public CommentManager() {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/movie");
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	//void createComment(Comment newComment);
	public void createComment(Comment newComment) {
		try {
			connection = ds.getConnection();
			statement = connection.prepareStatement(createCommentSql);
			statement.setString(1, newComment.getId());
			statement.setString(2, newComment.getUsername());
			statement.setString(3, newComment.getMovieId());
			statement.setString(4, newComment.getComment());
			Date date = new Date(newComment.getDate().getTime());
			statement.setDate(5, date);
			statement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		// close SQL connection
		finally {
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	

	//List<Comment> readAllComments();
	public List<Comment> readAllComments() {
		List<Comment> commentlist = new ArrayList<Comment>();
		try {
			connection = ds.getConnection();
			statement = connection.prepareStatement(readAllCommentsSql);
			results = statement.executeQuery();
			while(results.next()) {
				Comment comment = new Comment();
				comment.setId(results.getString("id"));
				comment.setUsername(results.getString("username"));
				comment.setMovieId(results.getString("movieId"));
				comment.setComment(results.getString("comment"));
				comment.setDate(results.getDate("date"));
				
				commentlist.add(comment);//add comment to the list
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
		return commentlist;//return the list of comments
	}

	//List<Comment> readAllCommentsForUsername
	public List<Comment> readAllCommentsForUsername(String username) {
		List<Comment> commentlist = new ArrayList<Comment>();
		try {
			connection = ds.getConnection();
			statement = connection.prepareStatement(readAllCommentsForUsernameSql);
			statement.setString(1, username);
			results = statement.executeQuery();
			if(results.next()) {
				Comment comment = new Comment();
				comment.setId(results.getString("id"));
				comment.setUsername(results.getString("username"));
				comment.setMovieId(results.getString("movieId"));
				comment.setComment(results.getString("comment"));
				comment.setDate(results.getDate("date"));
				commentlist.add(comment);//add comment to the list
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
		return commentlist;//return the list
	}

	//List<Comment> readAllCommentsForMovie(String movieId);
	public List<Comment> readAllCommentsForMovie(String movieId) {
		List<Comment> comments = new ArrayList<Comment>();
		try {
			connection = ds.getConnection();
			statement = connection.prepareStatement(readAllCommentsForMovieSql);
			statement.setString(1, movieId);
			results = statement.executeQuery();
			if(results.next()) {
				Comment comment = new Comment();
				comment.setId(results.getString("id"));
				comment.setUsername(results.getString("username"));
				comment.setMovieId(results.getString("movieId"));
				comment.setComment(results.getString("comment"));
				comment.setDate(results.getDate("date"));
				comments.add(comment);//add comment to the list
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
		return comments;//return the list of comments
	}	

	//Comment readCommentForId(String commentId);
	public Comment readCommentForId(String commentId) {
		try {
			connection = ds.getConnection();
			statement = connection.prepareStatement(readCommentForIdSql);
			statement.setString(1, commentId);
			results = statement.executeQuery();
			if(results.next()) {
				Comment comment = new Comment();
				comment.setId(results.getString("id"));
				comment.setUsername(results.getString("username"));
				comment.setMovieId(results.getString("movieId"));
				comment.setComment(results.getString("comment"));
				comment.setDate(results.getDate("date"));
				return comment;//return comment data
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

	//void updateComment(String commentId, Comment newComment);
	public void updateComment(String id, Comment newComment) {
		try {
			connection = ds.getConnection();
			statement = connection.prepareStatement(updateCommentSql);
			statement.setString(1, newComment.getComment());
			statement.setString(2, id);
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
	
	//void deleteComment(String commentId);
	public void deleteComment(String commentId) {
		try {
			connection = ds.getConnection();
			statement = connection.prepareStatement(deleteCommentSql);
			statement.setString(1, commentId);
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