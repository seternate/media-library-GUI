package database;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import config.Configuration;
import database.JSON.JSONArray;
import database.JSON.JSONObject;
import database.JSON.JSONTokener;

public final class TMDB{
	
	
	
	//Class variables
	private final static String apiKey = "?api_key=148405f4da110834674ba7eb5d13a126";
	private static long startTime = 0,
						endTime = 0;
	private static final String results = "results",
								searchMovieURL = "https://api.themoviedb.org/3/search/movie",
								searchTVShowURL = "https://api.themoviedb.org/3/search/tv",
								movieDetailsURL = "https://api.themoviedb.org/3/movie/",
								tvDetailsURL = "https://api.themoviedb.org/3/tv/",
								videosKey = "key";
	
	
	
	//Class methods
	private final static JSONObject getJSONAnswer(String jsonReq){		
		URL url;
		JSONTokener jsonAns;
		TMDB.startTime = System.currentTimeMillis();
		try {
			url = new URL(jsonReq);
		} catch (MalformedURLException e1) {
			return null;
		}
		if(startTime - endTime < 250){
			try {
				TimeUnit.MILLISECONDS.sleep(250 - (startTime - endTime));
			} catch (InterruptedException e) {
				return null;
			}
		}
		try {
			jsonAns = new JSONTokener(url.openStream());
		} catch (IOException e) {
			return null;
		}
		JSONObject jsonRes = new JSONObject(jsonAns);
		TMDB.endTime = System.currentTimeMillis();
		return jsonRes;
	}
	
	private static final String toURL(String encode){
		try {
			return URLEncoder.encode(encode, Configuration.getTextFormat());
		} catch (UnsupportedEncodingException e) {
			return null;
		}
	}
	
	private static final String getLanguage(){
		return TMDB.toURL(Configuration.getRegion());
	}
	
	
	
	//Instance methods
	public List<Movie> searchMovies(String query){
		String requestURL = TMDB.searchMovieURL + TMDB.apiKey + "&query=" + TMDB.toURL(query) +
							"&language=" + TMDB.getLanguage();		
		JSONArray jsonRes = TMDB.getJSONAnswer(requestURL).getJSONArray(TMDB.results);
		List<Movie> movieList = new ArrayList<Movie>(jsonRes.length());
		for(int i = 0; i < jsonRes.length(); i++){
			movieList.add(new Movie(jsonRes.getJSONObject(i)));
		}
		return movieList;
	}	
	
	public List<TVShow> searchTVShows(String query){
		String requestURL = TMDB.searchTVShowURL + TMDB.apiKey + "&query=" + TMDB.toURL(query) + 
							"&language=" + TMDB.getLanguage();
		JSONArray jsonRes = TMDB.getJSONAnswer(requestURL).getJSONArray(TMDB.results);
		List<TVShow> tvShowList = new ArrayList<TVShow>(jsonRes.length());
		for(int i = 0; i < jsonRes.length(); i++){
			tvShowList.add(new TVShow(jsonRes.getJSONObject(i)));
		}
		return tvShowList;
	}	
	
	
	
	public Movie getMoviesDetails(int movieId){
		String requestURL = TMDB.movieDetailsURL + movieId + TMDB.apiKey + "&language=" + TMDB.getLanguage();
		JSONObject jsonAns = getJSONAnswer(requestURL);
		return new Movie(jsonAns);
	}
	
	public TVShow getTVShowsDetails(int tvShowId){
		String requestURL = TMDB.tvDetailsURL + tvShowId + TMDB.apiKey + "&language=" + TMDB.getLanguage();
		JSONObject jsonAns = getJSONAnswer(requestURL);
		return new TVShow(jsonAns);
	}	
	
	
	
	
	
	
	public Episode getEpisodeDetails(int tvShowId, int seasonNumber, int episodeNumber){
		String requestURL = TMDB.tvDetailsURL + tvShowId + "/season/" + seasonNumber + "/episode/" + episodeNumber + TMDB.apiKey + 
							"&language=" + TMDB.getLanguage();
		JSONObject jsonAns = getJSONAnswer(requestURL);
		return new Episode(jsonAns);
	}		
	
	public Season getSeasonDetails(int tvShowId, int seasonNumber){
		String requestURL = TMDB.tvDetailsURL + tvShowId + "/season/" + seasonNumber + TMDB.apiKey +
							"&language=" + TMDB.getLanguage();
		JSONObject jsonAns = getJSONAnswer(requestURL);
		return new Season(jsonAns);
	}	

	
	
	
	public String getMoviesVideos(int movieId){
		String requestURL = TMDB.movieDetailsURL + movieId + "/videos" + TMDB.apiKey +
							"&language=" + TMDB.getLanguage();
		JSONObject jsonAns = getJSONAnswer(requestURL);
		return jsonAns.getJSONArray("results").getJSONObject(0).getString(TMDB.videosKey);
	}
	
}
