package movie;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class MovieManager {
	Connection connection = null;
	PreparedStatement statement = null;
	ResultSet results = null;
	String createMovieSql = "INSERT INTO MOVIE (ID, TITLE,POSTERIMAGE,RELEASEDATE) VALUES (?, ?, ?, ?);";
	String readMovie = "SELECT * FROM MOVIE WHERE ID=?;";
	String readAllMoviesSql = "SELECT * FROM MOVIE;";
	String updateMovieSql = "UPDATE MOVIE SET TITLE=? AND POSTERIMAGE=? AND RELEASEDATE=? WHERE ID=?;";
	String deleteMovieSql = "DELETE FROM MOVIE WHERE ID=?";
	DataSource ds;
	
	public MovieManager() {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/movie");
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
   //void createMovie(Movie newMovie);
	public void createMovie(Movie newMovie) {
		try {
			connection = ds.getConnection();
			statement = connection.prepareStatement(createMovieSql);
			statement.setString(1, newMovie.getId());
			statement.setString(2, newMovie.getTitle());
			statement.setString(3, newMovie.getPosterImage());
			Date date = new Date(newMovie.getReleaseDate().getTime());
			statement.setDate(4, date);
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
   //List<Movie> readAllMovies();
	public List<Movie> readAllMovies() {
		List<Movie> movies = new ArrayList<Movie>();
		try {
			connection = ds.getConnection();
			statement = connection.prepareStatement(readAllMoviesSql);
			results = statement.executeQuery();
			while(results.next()) {
				
				Movie movie =new Movie();
				movie.setId(results.getString("id"));
				movie.setTitle(results.getString("title"));
				movie.setPosterImage(results.getString("posterImage"));
				movie.setReleaseDate(results.getDate("releaseDate"));
				movies.add(movie);
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
		return movies;
	}
	
    //Movie readMovie(String movieId); By movie Id
	public Movie readMovie(String movieId) {
		try {
			connection = ds.getConnection();
			statement = connection.prepareStatement(readMovie);
			statement.setString(1, movieId);
			results = statement.executeQuery();
			if(results.next()) {
				Movie movie =new Movie();
				movie.setId(results.getString("id"));
				movie.setTitle(results.getString("title"));
				movie.setPosterImage(results.getString("posterImage"));
				movie.setReleaseDate(results.getDate("releaseDate"));
				return movie;
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
  //void updateMovie(String movieId, Movie movie);
	public void updateMovie(String movieId, Movie movie) {
		try {
			connection = ds.getConnection();
			statement = connection.prepareStatement(updateMovieSql);
			statement.setString(1, movie.getTitle());
			statement.setString(2, movieId);
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
	
	//void deleteMovie(String movieId);
	public void deleteMovie(String movieId) {
		try {
			connection = ds.getConnection();
			statement = connection.prepareStatement(deleteMovieSql);
			statement.setString(1, movieId);
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
