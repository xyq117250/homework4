package movie;

public class Cast {
	protected String id;
	protected String movieId;
	protected String actorId;
	protected String characterName;
	
	
	public Cast() {}
	public Cast(String id,String characterName, String movieId, String actorId) 
	{
		super(); //super is used to invoke user constructor
		this.id = id;
		this.characterName = characterName;
		this.movieId = movieId;
		this.actorId = actorId;
		
	}
	
	//id
	public void setId(String id) {
		  this.id = id;
	}
	public String getId() {
		  return id;
	}
	//movieId
	public void setMovieId(String movieId) {
		  this.movieId = movieId;
	}
	public String getMovieId() {
		  return movieId;
	}
	//actorId
	public void setActorId(String actorId) {
		  this.actorId = actorId;
	}
	public String getActorId() {
	      return actorId;
	}
	//characterName
	public void setCharacterName(String characterName) {
		  this.characterName = characterName;
	}
	public String getCharacterName() {
		  return characterName;
	}
}