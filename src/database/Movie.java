package database;

import java.util.List;
import java.util.Map;

import database.JSON.JSONException;
import database.JSON.JSONObject;

public final class Movie extends VideoObject{
	
	
	
	
	//Static variables
	private static final long serialVersionUID = -6183760790619948725L;
	private static final String RELEASEDATE = "release_date",
								TITLE = "title",
								POSTERPATH = "poster_path",
								ADULT = "adult",
								GENREIDS = "genre_ids",
								ORIGINALTITLE = "original_title",
								ORIGINALLANGUAGE = "original_language",
								BACKDROPPATH = "backdrop_path",
								POPULARITY = "popularity",
								VOTECOUNT = "vote_count",
								VIDEO = "video",
								VOTEAVERAGE = "vote_average",
								BELONGSTOCOLLECTION = "belongs_to_collection",
								BUDGET = "budget",
								GENRE = "genres",														
								HOMEPAGE = "homepage",
								IMDBID = "imdb_id",
								GENRENAME = "name",
								GENREID = "id",
								PRODUCTIONCOMPANIES = "production_companies",
								PRODUCTIONCOMPANIESNAME = "name",
								PRODUCTIONCOMPANIESID = "id",
								PRODUCTIONCOUNTRIES = "production_countries",
								PRODUCTIONCOUNTRIESNAME = "name",
								PRODUCTIONCOUNTRIESISO = "iso_3166_1",
								REVENUE = "revenue",
								RUNTIME = "runtime",
								STATUS = "status",
								TAGLINE = "tagline",
								SPOKENLANGUAGE = "spoken_language",
								SPOKENLANGUAGENAME = "name",
								SPOKENLANGUAGEISO = "iso_639_1",
								WATCHED = "watched",
								LOCALPATH = "local_path";
	
	
	
	
	//Constructors
	public Movie(){
		super();
		setWatched(false);
	}
	
	public Movie(JSONObject jsonObj){
		super(jsonObj);
		setWatched(false);
	}
	
	public Movie(JSONObject jsonObj, String path){
		super(jsonObj);
		setWatched(false);
		setLocalPath(path);
	}
	
	
	
	
	//Abstract method implementations
	@Override
	public final String getReleaseDate(){
		return super.getDate(Movie.RELEASEDATE);						
	}
	
	@Override
	public final String getTitle(){
		return super.getString(Movie.TITLE);
	}
	
	@Override
	public final String getPosterPath(){
		return super.getString(Movie.POSTERPATH);
	}
		
	
	
	
	//Instance methods	
	public final boolean IsAdult(){
		return super.getBoolean(Movie.ADULT);
	}
	
	public final String getBackdropPath(){
		return super.getString(Movie.BACKDROPPATH);
	}
	
	public final Object getBelongsToCollection(){
		return super.get(Movie.BELONGSTOCOLLECTION);
	}
	
	public final int getBudget(){
		return super.getInt(Movie.BUDGET);
	}
	
	public final String getBudgetParsed(){
		return super.getCurrency(Movie.BUDGET);
	}
	
	
	
	
	private final Map<String, Integer> getGenre(){
		return super.getNamesIds(Movie.GENRE, Movie.GENRENAME, Movie.GENREID);
	}
	
	public final List<Integer> getGenreIds(){
		try{
			return super.getListInteger(Movie.GENREIDS);
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
	
	
	
	
	public final String getHomepage(){
		return super.getString(Movie.HOMEPAGE);
	}
	
	public final String getImdbId(){
		return super.getString(Movie.IMDBID);
	}
	
	public final String getOriginalLanguage(){
		return super.getString(Movie.ORIGINALLANGUAGE);
	}
	
	public final String getOriginalTitle(){
		return super.getString(Movie.ORIGINALTITLE);
	}
	
	public final double getPopularity(){
		return super.getDouble(Movie.POPULARITY);
	}
	
	
	
	
	private final Map<String, Integer> getProductionCompanies(){
		return super.getNamesIds(Movie.PRODUCTIONCOMPANIES, Movie.PRODUCTIONCOMPANIESNAME, Movie.PRODUCTIONCOMPANIESID);
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
		return this.getAllNames(this.getProductionCompaniesNames());
	}
	
	
	
	
	private final Map<String, String> getProductionCountries(){
		return super.getNamesIsos(Movie.PRODUCTIONCOUNTRIES, Movie.PRODUCTIONCOUNTRIESNAME, Movie.PRODUCTIONCOUNTRIESISO);
	}
	
	public final List<String> getProductionCountriesNames(){
		return super.getNames(this.getProductionCountries());
	}
	
	public final List<String> getProductionCountriesIsos(){
		return super.getIsos(this.getProductionCountries());
	}
	
	public final String getProductionCountryName(int index){
		return this.getProductionCountriesNames().get(index);
	}
	
	public final String getProductionCountryIso(int index){
		return this.getProductionCountriesIsos().get(index);
	}
	
	public final String getProductionCountriesNameAll(){
		return super.getAllNames(this.getProductionCountriesNames());
	}
	
	
	
	
	public final Number getRevenue(){
		return super.getNumber(Movie.REVENUE);
	}
	
	public String getRevenueParsed(){
		return super.getCurrency(Movie.REVENUE);
	}
	
	public final int getRuntime(){
		return super.getInt(Movie.RUNTIME);
	}
	
	public final String getRuntimeParsed(){
		return this.getRuntime() + " min";
	}
	
	
	
	
	private final Map<String, String> getSpokenLanguages(){
		return super.getNamesIsos(Movie.SPOKENLANGUAGE, Movie.SPOKENLANGUAGENAME, Movie.SPOKENLANGUAGEISO);
	}
	
	public final List<String> getSpokenLanguagesNames(){
		return super.getNames(this.getSpokenLanguages());
	}
	
	public final List<String> getSpokenLanguagesIsos(){
		return super.getIsos(this.getSpokenLanguages());
	}
	
	public final String getSpokenLanguageName(int index){
		return this.getSpokenLanguagesNames().get(index);
	}
	
	public final String getSpokenLanguageIso(int index){
		return this.getSpokenLanguagesIsos().get(index);
	}
	
	public final String getSpokenLanguagesNameAll(){
		return super.getAllNames(this.getSpokenLanguagesNames());
	}
	
	
	
	
	public final String getStatus(){
		return super.getString(Movie.STATUS);
	}
	
	public final String getTagline(){
		return super.getString(Movie.TAGLINE);
	}
	
	public final boolean IsVideo(){
		return super.getBoolean(Movie.VIDEO);
	}
	
	public final double getVoteAverage(){
		return super.getDouble(Movie.VOTEAVERAGE);
	}
	
	public final int getVoteCount(){
		return super.getInt(Movie.VOTECOUNT);
	}
	
	
	
	
	public boolean isWatched() {
		return super.getBoolean(Movie.WATCHED);
	}

	public void setWatched(boolean watched) {
		super.put(Movie.WATCHED, watched);
	}

	public String getLocalPath() {
		return super.getString(Movie.LOCALPATH);
	}

	public void setLocalPath(String localPath) {
		super.put(Movie.LOCALPATH, localPath);
	}
	
}