package database;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public final class VideoList<T extends VideoObject> implements Serializable{

	
	
	
	//Static variables
	private static final long serialVersionUID = 5393588967560012395L;
	
	
	
	
	//Static methods
	public static VideoList<Movie> buildMovieList(String videoPath){
		TMDB tmdb = new TMDB();
		Movie movie;
		VideoList<Movie> videoList = new VideoList<>();
		File directory = new File(videoPath);
		String[] subDirectory = directory.list();
		for(String videoObject: subDirectory){
			File childDirectory = new File(directory, videoObject);
			if(childDirectory.isDirectory()){
				movie = tmdb.searchMovies(videoObject).get(0);
				movie = tmdb.getMoviesDetails(movie.getId());
				movie.setLocalPath(videoPath + "\\" + videoObject);
				videoList.add(movie);
			}
		}
		return videoList;
	}
	
	public static VideoList<TVShow> buildTVShowList(String videoPath){
		TMDB tmdb = new TMDB();
		TVShow tv;
		VideoList<TVShow> videoList = new VideoList<>();
		File directory = new File(videoPath);
		String[] subDirectory = directory.list();
		for(String videoObject: subDirectory){
			File childDirectory = new File(directory, videoObject);
			if(childDirectory.isDirectory()){
				tv = tmdb.searchTVShows(videoObject).get(0);
				tv = tmdb.getTVShowsDetails(tv.getId());
				//tv.setLocalPath(videoPath + "\\" + videoObject);
				videoList.add(tv);
			}
		}
		return videoList;
	}
	
	@SuppressWarnings("unchecked")
	public static VideoList<Movie> loadMovieList(String listPath){
	    FileInputStream fileIn;
	    ObjectInputStream in;
		try {
			fileIn = new FileInputStream(listPath);
			in = new ObjectInputStream(fileIn);
			VideoList<Movie> movieList = (VideoList<Movie>)in.readObject();
			fileIn.close();
			in.close();
			return movieList;
		} catch (IOException | ClassNotFoundException e) {
			System.out.println("Can't load the VideoList!");
			return null;
		}	  
	}
	
	@SuppressWarnings("unchecked")
	public static VideoList<TVShow> loadTVShowList(String listPath){
	    FileInputStream fileIn;
	    ObjectInputStream in;
		try {
			fileIn = new FileInputStream(listPath);
			in = new ObjectInputStream(fileIn);
			VideoList<TVShow> tvList = (VideoList<TVShow>)in.readObject();
			fileIn.close();
			in.close();
			return tvList;
		} catch (IOException | ClassNotFoundException e) {
			System.out.println("Can't load the VideoList!");
			return null;
		}	  
	}
	
	
	
	
	//Instance variables
	private List<T> videoList; 
	private String videoPath;
	private transient String listPath;
	
	
	

	//Constructors
	public VideoList(){
		this.videoList = new ArrayList<T>();
		this.videoPath = new String();
		this.listPath = new String();
	}
	
	public VideoList(String videoPath){
		this();
		this.videoPath = videoPath;
	}
	
	public VideoList(String videoPath, String listPath){
		this(videoPath);
		this.listPath = listPath;
	}
	
	
	
	
	//Instance methods
	public void saveList(){
		try {
			 System.out.println(this.listPath);
	         FileOutputStream fileOut = new FileOutputStream(this.listPath);
	         ObjectOutputStream out = new ObjectOutputStream(fileOut);
	         out.writeObject(this);
	         fileOut.close();
	         out.close();
	      }catch(IOException i) {
	    	  i.printStackTrace();
	      }
	}
	
	public boolean isListFile(){
		return new File(this.listPath).exists();
	}

	public List<T> toList(){
		return this.videoList;
	}
	
	public void add(T entry){
		this.videoList.add(entry);
	}
	
	public T get(int index){
		return this.videoList.get(index);
	}
	
	public String getVideoPath(){
		return this.videoPath;
	}
	
	public void setVideoPath(String videoPath){
		this.videoPath = videoPath;
	}
	
	public String getListPath(){
		return this.listPath;
	}
	
	public void setListPath(String listPath){
		this.listPath = listPath;
	}
	
}
