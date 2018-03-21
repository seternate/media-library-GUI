package database;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import database.JSON.JSONArray;
import database.JSON.JSONException;
import database.JSON.JSONObject;

public final class TVShow extends VideoObject {

	
	
	//Static variables
	private static final long serialVersionUID = -571179342265327304L;
	private static final String RELEASEDATE = "first_air_date",
								TITLE = "name",
								POSTERPATH = "poster_path",
								POPULARITY = "popularity",
								BACKDROPPATH = "backdrop_path",
								VOTECOUNT = "vote_count",
								VOTEAVERAGE = "vote_average",
								ORIGINCOUNTRY = "origin_country",
								GENREIDS = "genre_ids",
								ORIGINALLANGUAGE = "original_language",
								ORIGINALTITLE = "original_title",
								CREATEDBY = "created_by",
								RUNTIME = "episode_run_time",
								GENRE = "genre",
								GENRENAME = "genre_name",
								GENREID = "genre_id",
								HOMEPAGE = "homepage",
								INPRODUCTION = "in_production",
								LANGUAGES = "languages",
								LASTAIRDATE = "last_air_date",
								NETWORKS = "networks",
								NETWORKSNAME = "name",
								NETWORKSID = "id",
								EPISODENUMBER = "number_of_episodes",
								SEASONNUMBER = "number_of_seasons",
								PRODUCTIONCOMPANIES = "production_companies",
								PRODUCTIONCOMPANIESNAME = "name",
								PRODUCTIONCOMPANIESID = "id",
								STATUS = "status",
								TYPE = "type",
								SEASON = "season";
	
	
	
	//Instance variables
	private List<Season> seasonList;
	
	
	
	//Constructors
	public TVShow(){
		super();
		this.seasonList = new ArrayList<Season>();
	}
	
	public TVShow(JSONObject jsonObj){
		super(jsonObj);
		this.seasonList = new ArrayList<Season>();
	}
	
	
	
	//Abstract instance methods implementations
	@Override
	public final String getReleaseDate(){
		return super.getDate(TVShow.RELEASEDATE);						
	}

	@Override
	public final String getTitle(){
		return super.getString(TVShow.TITLE);
	}

	@Override
	public final String getPosterPath(){
		return super.getString(TVShow.POSTERPATH);
	}

	
	
	//Instance methods
	public final double getPopularity(){
		return super.getDouble(TVShow.POPULARITY);
	}

	public final String getBackdropPath(){
		return super.getString(TVShow.BACKDROPPATH);
	}
	
	public final int getVoteCount(){
		return super.getInt(TVShow.VOTECOUNT);
	}
	
	public final double getVoteAverage(){
		return super.getDouble(TVShow.VOTEAVERAGE);
	}
	
	
	
	public final List<String> getOriginCountry(){
		return super.getNames(TVShow.ORIGINCOUNTRY);
	}
	
	public final String getOriginCountryAll(){
		return super.getAllNames(this.getOriginCountry());
	}
	
	
	
	private final Map<String, Integer> getGenre(){
		return super.getNamesIds(TVShow.GENRE, TVShow.GENRENAME, TVShow.GENREID);
	}
	
	public final List<Integer> getGenreIds(){
		try{
			return super.getListInteger(TVShow.GENREIDS);
		}
		catch(JSONException e){
			return super.getIds(this.getGenre());
		}
	}
	
	public final List<String> getGenreNames(){
		return super.getNames(this.getGenre());
	}
	
	public final int getGenreId(int index){
		return this.getGenreIds().get(index);
	}
	
	public final String getGenreName(int index){
		return this.getGenreNames().get(index);
	}
	
	public final String getGenreNameAll(){
		return this.getAllNames(this.getGenreNames());
	}
	
	
	
	public final String getOriginalLanguage(){
		return super.getString(TVShow.ORIGINALLANGUAGE);
	}
	
	public final String getOriginalTitle(){
		return super.getString(TVShow.ORIGINALTITLE);
	}
	
	
	
	public final List<Object> getCreatedBy(){
		return ((JSONArray)super.get(TVShow.CREATEDBY)).toList();
	}
	
	
	
	public final List<Integer> getEpisodeRunTime(){
		return super.getListInteger(TVShow.RUNTIME);
	}
	
	public final String getHomepage(){
		return super.getString(TVShow.HOMEPAGE);
	}
	
	public final boolean isInProduction(){
		return super.getBoolean(TVShow.INPRODUCTION);
	}

	
	
	public final List<String> getLanguages(){
		return super.getNames(TVShow.LANGUAGES);
	}
	
	public final String getLanguagesAll(){
		return super.getAllNames(this.getLanguages());
	}
	
	
	
	public final String getLastAirDate(){
		return super.getDate(TVShow.LASTAIRDATE);	
	}
	
	
	
	private final Map<String, Integer> getNetworks(){
		return super.getNamesIds(TVShow.NETWORKS, TVShow.NETWORKSNAME, TVShow.NETWORKSID);
	}
	
	public final List<String> getNetworksNames(){
		return super.getNames(this.getNetworks());
	}
	
	public final List<Integer> getNetworksIds(){
		return super.getIds(this.getNetworks());
	}
	
	public final String getNetworkName(int index){
		return this.getNetworksNames().get(index);
	}
	
	public final int getNetworkId(int index){
		return this.getNetworksIds().get(index);
	}
	
	public final String getProductionCountriesNameAll(){
		return super.getAllNames(this.getNetworksNames());
	}
	
	
	
	public final int getNumberOfEpisodes(){
		return super.getInt(TVShow.EPISODENUMBER);
	}
	
	public final int getNumberOfSeasons(){
		return super.getInt(TVShow.SEASONNUMBER);
	}
	
	
	
	private final Map<String, Integer> getProductionCompanies(){
		return super.getNamesIds(TVShow.PRODUCTIONCOMPANIES, TVShow.PRODUCTIONCOMPANIESNAME, TVShow.PRODUCTIONCOMPANIESID);
	}
	
	public final List<String> getProductionCompaniesNames(){
		return super.getNames(this.getProductionCompanies());
	}
	
	public final List<Integer> getProductionCompaniesIds(){
		return super.getIds(this.getProductionCompanies());
	}
	
	public final String getProductionCompanyName(int index){
		return this.getProductionCompaniesNames().get(index);
	}
	
	public final int getProductionCompanyId(int index){
		return this.getProductionCompaniesIds().get(index);
	}
	
	public final String getProductionCompaniesNameAll(){
		return super.getAllNames(this.getProductionCompaniesNames());
	}
	
	
	
	public final String getStatus(){
		return super.getString(TVShow.STATUS);
	}

	public final String getType() {
		return super.getString(TVShow.TYPE);
	}
	
	
	
	public final Season getSeason(int index){
		return this.getSeasons().get(index);
	}
	
	public final List<Season> getSeasons(){
		List<Season> seasons = new ArrayList<Season>();
		JSONArray list = (JSONArray)super.get(TVShow.SEASON);
		list.forEach(element -> {seasons.add(new Season((JSONObject)element));});
		return seasons;
	}
	
	public final Season getSeasonDetail(int index){
		return this.getSeasonDetail().get(index);
	}
	
	public final List<Season> getSeasonDetail(){
		return this.seasonList;
	}
	
}
