package database;

import java.util.ArrayList;
import java.util.List;

import database.JSON.JSONArray;
import database.JSON.JSONObject;

public final class Season extends VideoObject {
	
	
	
	//Static variables
	private static final long serialVersionUID = 3234203493321655469L;
	private static final String RELEASEDATE = "air_date",
								TITLE = "name",
								POSTERPATH = "poster_path",
								EPISODECOUNT = "episode_count",
								SEASONNUMBER = "season_number",
								EPISODE = "episode";
	
	
	
	//Instance variables
	private List<Episode> episodeList;
	
	
	
	//Constructors
	public Season(){
		super();
		this.episodeList = new ArrayList<Episode>();
	}
	
	public Season(JSONObject jsonObj){
		super(jsonObj);
		this.episodeList = new ArrayList<Episode>();
		JSONArray list = (JSONArray)super.get(Season.EPISODE);
		list.forEach(element -> {this.episodeList.add(new Episode((JSONObject)element));});
	}
	
	
	
	//Abstract instance methods implementations
	@Override
	public final String getReleaseDate(){
		return super.getDate(Season.RELEASEDATE);						
	}

	@Override
	public final String getTitle(){
		return super.getString(Season.TITLE);
	}
	
	@Override
	public final String getPosterPath(){
		return super.getString(Season.POSTERPATH);
	}

	
	
	//Instance methods	
	public final int getEpisodeCount(){
		return super.getInt(Season.EPISODECOUNT);
	}
	
	public final int getSeasonNumber(){
		return super.getInt(Season.SEASONNUMBER);
	}
	
	
	
	public final List<Episode> getEpisodes(){
		return this.episodeList;
	}
	
	public final Episode getEpisode(int index){
		return this.getEpisodes().get(index);
	}

}
