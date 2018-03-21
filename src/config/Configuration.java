package config;

import java.io.Serializable;

public class Configuration implements Serializable{
	
	
	
	//Static variables
	private static final long serialVersionUID = 6480315477988733458L;
	private static String region = "de",
						  dateFormat = "dd.MM.yyyy",
						  textFormat = "UTF-8",
						  moviePath = "\\\\WDMYCLOUD-JECK\\Public\\Movies etc\\Movies",
						  movieTestPath = "C:\\Users\\Chris\\eclipse-workspace\\media library\\Test_Data",
						  user = System.getProperty("user.name");
	
	
	
	//Static methods
	public static String getRegion(){
		return Configuration.region;
	}
	
	public static void setRegion(String region){
		Configuration.region = region;
	}
	
	public static String getDateFormat(){
		return Configuration.dateFormat;
	}
	
	public static void setDateFormat(String dateFormat){
		Configuration.dateFormat = dateFormat;
	}
	
	public static String getTextFormat(){
		return Configuration.textFormat;
	}
	
	public static void setTextFormat(String textFormat){
		Configuration.textFormat = textFormat;
	}
	
	public static String getMoviePath(){
		return Configuration.moviePath;
	}
	
	public static void setMoviePath(String moviePath){
		Configuration.moviePath = moviePath;
	}

	public static String getUser(){
		return Configuration.user;
	}

	public static void setUser(String user){
		Configuration.user = user;
	}

	public static String getMovieTestPath() {
		return movieTestPath;
	}

	public static void setMovieTestPath(String movieTestPath) {
		Configuration.movieTestPath = movieTestPath;
	}
	
}
