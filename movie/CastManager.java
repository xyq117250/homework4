package movie;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import movie.Cast;

public class CastManager {

	Connection connection = null;
	PreparedStatement statement = null;
	ResultSet results = null;

	String createCastSql = "INSERT INTO CAST (ID, MOVIEID, ACTORID, CHARACTERNAME) VALUES (?,?,?,?);";
	String readAllCastsSql = "SELECT * FROM CAST;";
	String readAllCastsForActorSql = "SELECT CAST.* FROM CAST, ACTOR WHERE CAST.ACTORID=ACTOR.ID AND ACTOR.ID=?;";
	String readAllCastsForMovieSql = "SELECT CAST.* FROM CAST, MOVIE WHERE CAST.MOVIEID=MOVIE.ID AND MOVIE.ID=?;";
	String readCastForIdSql = "SELECT CAST.* FROM CAST WHERE ID=?;";
	String updateCastSql = "UPDATE CAST SET CHARACTERNAME=? WHERE ID=?;";
	String deleteCastSql = "DELETE FROM CAST WHERE ID=?;";
	
	DataSource ds;
	public CastManager() {
		try {
			Context jndi = new InitialContext();
			ds = (DataSource) jndi.lookup("java:comp/env/jdbc/movie");
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	//void createCast(Cast cast);
	public void createCast(Cast Cast) {
		try {
			connection = ds.getConnection();
			statement = connection.prepareStatement(createCastSql);
			statement.setString(1, Cast.getId());
			statement.setString(2, Cast.getMovieId());
			statement.setString(3, Cast.getActorId());
			statement.setString(4, Cast.getCharacterName());
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

	//List<Cast> readAllCasts();
	public List<Cast> readAllCasts() {
		List<Cast> casts = new ArrayList<Cast>();
		try {
			connection = ds.getConnection();
			statement = connection.prepareStatement(readAllCastsSql);
			results = statement.executeQuery();
			while(results.next()) {
				Cast cast = new Cast();
				cast.setId(results.getString("castId"));
				cast.setMovieId(results.getString("movieId"));
				cast.setActorId(results.getString("actorId"));
				cast.setCharacterName(results.getString("characterName"));
				casts.add(cast);//add cast to the list
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
		return casts;//return the list of casts
	}

	//List<Cast> readAllCastsForActor(String actorId);
	public List<Cast> readAllCastsForActor(String actorId) {
		List<Cast> casts = new ArrayList<Cast>();
		try {
			connection = ds.getConnection();
			statement = connection.prepareStatement(readAllCastsForActorSql);
			statement.setString(1, actorId);
			results = statement.executeQuery();
			if(results.next()) {
				Cast cast = new Cast();
				cast.setId(results.getString("castId"));
				cast.setMovieId(results.getString("movieId"));
				cast.setActorId(results.getString("actorId"));
				cast.setCharacterName(results.getString("characterName"));
				casts.add(cast);//add cast to the list
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
		return casts;//return the list of casts
	}

	//List<Cast> readAllCastsForMovie(String movieId);
	public List<Cast> readAllCastsForMovie(String movieId) {
		List<Cast> castlist = new ArrayList<Cast>();
		try {
			connection = ds.getConnection();
			statement = connection.prepareStatement(readAllCastsForMovieSql);
			statement.setString(1, movieId);
			results = statement.executeQuery();
			if(results.next()) {
				
				Cast cast = new Cast();
				cast.setId(results.getString("castId"));
				cast.setMovieId(results.getString("movieId"));
				cast.setActorId(results.getString("actorId"));
				cast.setCharacterName(results.getString("characterName"));
				castlist.add(cast);//add cast to the list
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
		return castlist;//return the list of casts
	}	

	//Cast readCastForId(String castId);
	public Cast readCastForId(String castId) {
		try {
			connection = ds.getConnection();
			statement = connection.prepareStatement(readCastForIdSql);
			statement.setString(1, castId);
			results = statement.executeQuery();
			if(results.next()) {
				Cast cast = new Cast();
				cast.setId(results.getString("castId"));
				cast.setMovieId(results.getString("movieId"));
				cast.setActorId(results.getString("actorId"));
				cast.setCharacterName(results.getString("characterName"));
				return cast;//return movie data
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

	//void updateCast(String castId, Cast cast);
	public void updateCast(String id, Cast Cast) {
		try {
			connection = ds.getConnection();
			statement = connection.prepareStatement(updateCastSql);
			statement.setString(1, Cast.getCharacterName());
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
	
	//void deleteCast(String castId);
	public void deleteCast(String castId) {
		try {
			connection = ds.getConnection();
			statement = connection.prepareStatement(deleteCastSql);
			statement.setString(1, castId);
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