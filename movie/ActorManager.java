package movie;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;


public class ActorManager {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet results = null;

	//define SQL inquiry
		String createActorSql = "INSERT INTO ACTOR (ID, FIRSTNAME,LASTNAME,DATEOFBIRTH) VALUES (?, ?, ?, ?);";
		String readActorSql = "SELECT * FROM ACTOR WHERE ID=?;";
		String readAllActorsSql = "SELECT * FROM ACTOR;";
		String updateActorSql = "UPDATE ACTOR SET FIRSTNAME=? AND LASTNAME=? AND DATEOFBIRTH=? WHERE ID=?;";
		String deleteActorSql = "DELETE FROM ACTOR WHERE ID=?";

    //create a actor
		public void createActor(Actor newActor) {
			try {
				connection = ds.getConnection();
				statement = connection.prepareStatement(createActorSql);
				statement.setString(1, newActor.getId());
				statement.setString(2, newActor.getFirstName());
				statement.setString(3, newActor.getLastName());
				Date date = new Date(newActor.getDateOfBirth().getTime());
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

		public List<Actor> readAllActors() {
			List<Actor> actorlist = new ArrayList<Actor>();
			try {
				connection = ds.getConnection();
				statement = connection.prepareStatement(readAllActorsSql);
				results = statement.executeQuery();
				while(results.next()) {
					Actor actor = new Actor();//(results.getString("id"), results.getString("firstName"), results.getString("lastName"), results.getDate("dateOfBirth"));
					actor.setId(results.getString("id"));
					actor.setFirstName(results.getString("firstname"));
					actor.setLastName(results.getString("lastname"));
					actor.setDateOfBirth(results.getDate("dateofBirth"));
					
					actorlist.add(actor);
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
			return actorlist;
		}

		public Actor readActor(String id) {
			Actor actor = new Actor();
			try {
				connection = ds.getConnection();
				statement = connection.prepareStatement(readActorSql);
				statement.setString(1, id);
				results = statement.executeQuery();
				
				if(results.next()) {
					
					actor.setId(results.getString("id"));
					actor.setFirstName(results.getString("firstname"));
					actor.setLastName(results.getString("lastname"));
					actor.setDateOfBirth(results.getDate("dateofBirth"));
					
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
			return actor;
		}

		public void updateActor(String id, Actor actor) {
			try {
				connection = ds.getConnection();
				statement = connection.prepareStatement(updateActorSql);
				statement.setString(1, actor.getFirstName());
				statement.setString(2, actor.getLastName());
				statement.setDate(3, actor.getDateOfBirth());
				statement.setString(4, id);
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
		public void deleteActor(String id) {
			try {
				connection = ds.getConnection();
				statement = connection.prepareStatement(deleteActorSql);
				statement.setString(1, id);
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
		DataSource ds;
		public ActorManager() {
			try {
				Context ctx = new InitialContext();
				ds = (DataSource) ctx.lookup("java:comp/env/jdbc/movie");
			} catch (NamingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
