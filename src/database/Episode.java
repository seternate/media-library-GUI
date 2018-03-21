package database;

import java.util.List;

import database.JSON.JSONArray;
import database.JSON.JSONObject;

public final class Episode extends VideoObject {
	
	
	
	//Static variables
	private static final long serialVersionUID = -705057806972177917L;
	private static final String RELEASEDATE = "air_date",
								TITLE = "name",
								POSTERPATH = "poster_path",
								CREW = "crew",
								EPISODENUMBER = "episode_number",
								GUESTSTAR = "guest_star",
								PRODUCTIONCODE = "production_code",
								SEASONNUMBER = "season_number",
								VOTEAVERAGE = "vote_average",
								VOTECOUNT = "vote_count";

	
	
	//Instance variables																				
	private boolean watched;
	private String localPath;


	//Constructors
	public Episode(){
		super();
		setWatched(false);
	}
	
	public Episode(JSONObject jsonObj){
		super(jsonObj);
		setWatched(false);
	}
	
	public Episode(JSONObject jsonObj, String path){
		super(jsonObj);
		setWatched(false);
		setLocalPath(path);
	}
	
	
	
	//Abstract method implementations
	@Override
	public final String getReleaseDate(){
		return super.getDate(Episode.RELEASEDATE);						
	}

	@Override
	public final String getTitle(){
		return super.getString(Episode.TITLE);
	}

	@Override
	public final String getPosterPath(){
		return super.getString(Episode.POSTERPATH);
	}

	
	
	//Instance methods
	public final List<Object> getCrew(){
		return ((JSONArray)super.get(Episode.CREW)).toList();
	}
	
	public final int getEpisodeNumber(){
		return super.getInt(Episode.EPISODENUMBER);
	}

	public final List<Object> getGuestStar(){
		return ((JSONArray)super.get(Episode.GUESTSTAR)).toList();
	}

	public final String getProductionCode(){
		return super.getString(Episode.PRODUCTIONCODE);
	}
	
	public final int getSeasonNumber(){
		return super.getInt(Episode.SEASONNUMBER);
	}

	public final double getVoteAverage(){
		return super.getDouble(Episode.VOTEAVERAGE);
	}

	public final int getVoteCount(){
		return super.getInt(Episode.VOTECOUNT);
	}

	
	
	public boolean isWatched() {
		return this.watched;
	}

	public void setWatched(boolean watched) {
		this.watched = watched;
	}

	public String getLocalPath() {
		return this.localPath;
	}

	public void setLocalPath(String localPath) {
		this.localPath = localPath;
	}

}