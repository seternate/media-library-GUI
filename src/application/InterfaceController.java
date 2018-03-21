package application;

import java.io.File;
import java.io.IOException;
import java.util.List;

import database.Movie;
import database.Season;
import database.TMDB;
import database.TVShow;
import database.VideoList;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.application.Preloader;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import javafx.util.StringConverter;

public class InterfaceController extends Application{
	

	
	
	//Static variable
	private final static String YOUTUBEEMBED = "https://www.youtube.com/embed/",
						 TMDBIMAGEPOSTERURL = "https://image.tmdb.org/t/p/w500",
						 TMDBIMAGEBACKURL = "https://image.tmdb.org/t/p/w1280",
						 MOVIE_PATH = "\\\\WDMYCLOUD-JECK\\Public\\Movies etc\\Movies",
						 TV_PATH = "\\\\WDMYCLOUD-JECK\\Public\\Movies etc\\TV Shows",
						 LIST_PATH = "\\\\WDMYCLOUD-JECK\\Public\\movieList.ser",
						 LIST_PATH_TV = "\\\\WDMYCLOUD-JECK\\Public\\tvList.ser",
						 FXML = "fxml\\Interface.fxml",
						 CSS = "InterfaceStyle.css",
						 ICON = "resources\\mediaPlayer.png",
						 TITLE = "Media Library",
						 POSTER_PREVIEW = "https://image.tmdb.org/t/p/w92";

	
	
	
	//Instance variables
	private VideoList<Movie> movieList;
	private VideoList<TVShow> tvshowList;
	private TMDB tmdb = new TMDB();
	private ObservableList<Movie> movies  = FXCollections.observableArrayList();
	private ObservableList<TVShow> tvshows = FXCollections.observableArrayList();
	private ObservableList<Season> seasons = FXCollections.observableArrayList();
	private Image imagePoster, imageBackdrop, imageCheck, imageUnCheck;

	
	
	
	//FXML Object
	@FXML
	private Label lblTitle, lblOverview, lblReleaseDate, lblRuntime, lblBudget, lblRevenue, lblGenre, lblProductionCompanies;
	@FXML
	private ImageView ivPoster, ivBackDrop;
	@FXML
	private ListView<Movie> lvMovie;
	@FXML
	private ListView<Season> lvSeason;
	@FXML
	private WebView wvTrailer;
	@FXML
	private StackPane spInfo;
	@FXML
	private HBox hbxOverlay;
	@FXML
	private Button btnWatched; 
	@FXML
	private ComboBox<Movie> searchField;
	@FXML
	private TabPane tabPane;
	@FXML
	private GridPane gridPane;
	@FXML
	private ListView<TVShow> lvTVShow;
	
	
	
	
	@Override
	public void init(){
		movieList = new VideoList<>(InterfaceController.MOVIE_PATH, InterfaceController.LIST_PATH);
		if(movieList.isListFile()){
			movieList = VideoList.loadMovieList(movieList.getListPath());
			movieList.setListPath(InterfaceController.LIST_PATH);
			movieList.setVideoPath(InterfaceController.MOVIE_PATH);
		}
		else{
			movieList = VideoList.buildMovieList(movieList.getVideoPath());
			movieList.setListPath(InterfaceController.LIST_PATH);
			movieList.setVideoPath(InterfaceController.MOVIE_PATH);
			movieList.saveList();
		}
		tvshowList = new VideoList<>(InterfaceController.TV_PATH, InterfaceController.LIST_PATH_TV);
		if(tvshowList.isListFile()){
			tvshowList = VideoList.loadTVShowList(tvshowList.getListPath());
			tvshowList.setListPath(InterfaceController.LIST_PATH_TV);
			tvshowList.setVideoPath(InterfaceController.TV_PATH);
		}
		else{
			tvshowList = VideoList.buildTVShowList(tvshowList.getVideoPath());
			tvshowList.setListPath(InterfaceController.LIST_PATH_TV);  
			tvshowList.setVideoPath(InterfaceController.TV_PATH);
			tvshowList.saveList();
		}
		this.initLoadImages();
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource(InterfaceController.FXML));
		loader.setController(this);
		Parent root = loader.load();
		Scene scene = new Scene(root);
		scene.getStylesheets().add(getClass().getResource(InterfaceController.CSS).toExternalForm());
		primaryStage.setScene(scene);
		primaryStage.setTitle(InterfaceController.TITLE);
		primaryStage.getIcons().add(new Image(getClass().getResource(InterfaceController.ICON).toExternalForm()));
		primaryStage.sizeToScene();
		primaryStage.heightProperty().addListener(new ChangeListener<Number>(){

			@Override
			public void changed(ObservableValue<? extends Number> arg0, Number arg1, Number arg2) {
				ivBackDrop.setFitHeight((double)arg2 - 58 - 40);				
			}
			
		});
		primaryStage.widthProperty().addListener(new ChangeListener<Number>(){

			@Override
			public void changed(ObservableValue<? extends Number> arg0, Number arg1, Number arg2) {
				ivBackDrop.setFitWidth((double)arg2 - 300);				
			}
			
		});
		primaryStage.setMinHeight(850);
		primaryStage.setMinWidth(1280);
		primaryStage.show();
	}
	
	
	
	
	private void initLoadImages(){
		imageCheck = new Image(getClass().getResource("resources//Check.png").toExternalForm(), false);
		imageUnCheck = new Image(getClass().getResource("resources//Uncheck.png").toExternalForm(), false);
		imagePoster = new Image(TMDBIMAGEPOSTERURL + movieList.get(0).getPosterPath(), false);
		imageBackdrop = new Image(TMDBIMAGEBACKURL + movieList.get(0).getBackdropPath(), false);
	}
	
	@FXML
	public void initialize(){
		tabPane.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Tab>(){

			@Override
			public void changed(ObservableValue<? extends Tab> arg0, Tab arg1, Tab arg2) {
				if(arg2.getText().equals("Movies")){
					gridPane.setVisible(true);
					lvSeason.setVisible(false);
					refresh(lvMovie.getSelectionModel().getSelectedItem());
				}
				else{
					gridPane.setVisible(false);
					
					lvSeason.setVisible(true);
					refresh(lvTVShow.getSelectionModel().getSelectedItem());
				}
				
			}
			
		});
		//ComboBox Button Cell Show
		searchField.setConverter(new StringConverter<Movie>() {
			        
			@Override
			public String toString(Movie item) {
				if(item != null){
					return item.getTitle();
			    }
				return "";
			}

			@Override
			public Movie fromString(String string) {
				return null;
			}
			
		});
		//ComboBox List Cell Show
		searchField.setCellFactory(c -> new ListCell<Movie>(){
			@Override
			protected void updateItem(Movie item, boolean empty){
				super.updateItem(item, empty);
		        setGraphic(null);
		        setText(null);
		        if(item != null){
		        	if(item.getPosterPath() != null){
		        		ImageView imageView = new ImageView(new Image(POSTER_PREVIEW + item.getPosterPath(), true));
			            imageView.setFitWidth(92/2);
			            imageView.setFitHeight(138/2);
			            setGraphic(imageView);
		        	}
		            setText(item.getTitle());
		        }
			}
		});
		//searchField Listener new input String
		searchField.getEditor().textProperty().addListener(new ChangeListener<String>() {
	        @Override
	        public void changed(ObservableValue <? extends String> observable, String t, String t1) {
	        	Thread thread = new Thread(new Runnable() {
	        		@Override
	        		public void run() {
			        	TMDB tmdb = new TMDB();
			        	try{
			        		List<Movie> searchRes = tmdb.searchMovies(searchField.getEditor().getText() != null ? searchField.getEditor().getText():"");
			        		ObservableList<Movie> result = FXCollections.observableArrayList(searchRes);
			        		if(result.size() > 1){
			        			Platform.runLater(new Runnable(){

									@Override
									public void run() {
										searchField.setItems(result);
										searchField.hide();
								       	searchField.show();
									}
											
						        });
			        		}			        		
			        	}
			        	catch(NullPointerException e){
			        		Platform.runLater(new Runnable(){

								@Override
								public void run() {
									searchField.hide();
								}
										
					        });
			        		
			        	}
	   	         	}
	        	});
	        	thread.start();
	        }    
	    });
		movies.addAll(movieList.toList());
		tvshows.addAll(tvshowList.toList());
		lvTVShow.setItems(tvshows);
		lvMovie.setItems(movies);
		lvTVShow.getSelectionModel().selectFirst();
		lvMovie.getSelectionModel().selectFirst();
		lvMovie.getSelectionModel().getSelectedItems().addListener(new ListChangeListener<Movie>(){
			
			@Override
		    public void onChanged(Change<? extends Movie> arg0)
		    {
				refresh(lvMovie.getSelectionModel().getSelectedItem());
		    }
			
		});
		lvMovie.setCellFactory(c -> new ListCell<Movie>(){
			@Override
			protected void updateItem(Movie item, boolean empty){
				super.updateItem(item, empty);
		        setGraphic(null);
		        setText(null);
		        if(item != null){
		        	if(item.getPosterPath() != null){
		        		ImageView imageView = new ImageView(new Image(POSTER_PREVIEW + item.getPosterPath(), true));
			            imageView.setFitWidth(92/2);
			            imageView.setFitHeight(138/2);
			            setGraphic(imageView);
		        	}
		            setText(item.getTitle());
		        }
			}
		});		
		lvTVShow.getSelectionModel().getSelectedItems().addListener(new ListChangeListener<TVShow>(){
			
			@Override
		    public void onChanged(Change<? extends TVShow> arg0)
		    {
				refresh(lvTVShow.getSelectionModel().getSelectedItem());
		    }

			
		});
		lvTVShow.setCellFactory(c -> new ListCell<TVShow>(){
			@Override
			protected void updateItem(TVShow item, boolean empty){
				super.updateItem(item, empty);
		        setGraphic(null);
		        setText(null);
		        if(item != null){
		        	if(item.getPosterPath() != null){
		        		ImageView imageView = new ImageView(new Image(POSTER_PREVIEW + item.getPosterPath(), true));
			            imageView.setFitWidth(92/2);
			            imageView.setFitHeight(138/2);
			            setGraphic(imageView);
		        	}
		            setText(item.getTitle());
		        }
			}
		});		
		Movie entry = lvMovie.getSelectionModel().getSelectedItem();
		ivPoster.setImage(imagePoster);
		ivBackDrop.setImage(imageBackdrop);
		if(entry.isWatched()){
			btnWatched.setGraphic(new ImageView(imageCheck));
		}
		else{
			btnWatched.setGraphic(new ImageView(imageUnCheck));
		}
		lblTitle.setText(entry.getTitle());
		lblOverview.setText(entry.getOverview());
		lblRuntime.setText(entry.getRuntimeParsed());
		lblBudget.setText(entry.getBudgetParsed());
		lblReleaseDate.setText(entry.getReleaseDate());
		lblRevenue.setText(entry.getRevenueParsed());
		lblGenre.setText(entry.getGenreNameAll());
		lblProductionCompanies.setText(entry.getProductionCompaniesNameAll());
		notifyPreloader(new Preloader.ProgressNotification(1));
	}
	
	protected void refresh(TVShow selectedItem) {
		ivBackDrop.setImage(new Image(TMDBIMAGEBACKURL + lvTVShow.getSelectionModel().getSelectedItem().getBackdropPath(), false));
		seasons.addAll(selectedItem.getSeasons());
		lvSeason.setItems(seasons);
		
	}

	private void loadEntryImages(Movie entry){
		imagePoster = new Image(TMDBIMAGEPOSTERURL + entry.getPosterPath(), false);
		imageBackdrop = new Image(TMDBIMAGEBACKURL + entry.getBackdropPath(), false);
	}

	private void refresh(Movie entry){
		btnWatched.setDisable(false);
		loadEntryImages(entry);
		ivPoster.setImage(imagePoster);
		ivBackDrop.setImage(imageBackdrop);
		if(entry.isWatched()){
			btnWatched.setGraphic(new ImageView(imageCheck));
		}
		else{
			btnWatched.setGraphic(new ImageView(imageUnCheck));
		}
		lblTitle.setText(entry.getTitle());
		lblOverview.setText(entry.getOverview());
		lblRuntime.setText(entry.getRuntimeParsed());
		lblBudget.setText(entry.getBudgetParsed());
		lblReleaseDate.setText(entry.getReleaseDate());
		lblRevenue.setText(entry.getRevenueParsed());
		lblGenre.setText(entry.getGenreNameAll());
		lblProductionCompanies.setText(entry.getProductionCompaniesNameAll());
		if(wvTrailer.isVisible()){
			wvTrailer.getEngine().load(YOUTUBEEMBED + tmdb.getMoviesVideos(entry.getId()));
		}
	}
	
	
	
	
	@FXML
	protected void searchSelect(ActionEvent event){
		TMDB tmdb = new TMDB();
		if(searchField.isFocused()){
			refresh(tmdb.getMoviesDetails(searchField.getValue().getId()));
			btnWatched.setDisable(true);
		}
	}
	
	@FXML
	protected void playTrailer(ActionEvent event) throws IOException{
		Movie entry = lvMovie.getSelectionModel().getSelectedItem();
		wvTrailer.getEngine().load("https://www.youtube.com/embed/" + tmdb.getMoviesVideos(entry.getId()));
		wvTrailer.setVisible(true);
		wvTrailer.requestFocus();
	}
	
	@FXML
	protected void exitTrailer(KeyEvent event){
		if(event.getCode() == KeyCode.ESCAPE){
			wvTrailer.setVisible(false);
			wvTrailer.getEngine().load(null);
		}
	}
	
	@FXML
	protected void playMovie(ActionEvent event) throws IOException{
		Movie entry = lvMovie.getSelectionModel().getSelectedItem();
		String file = ((Movie)entry).getLocalPath();
		String[] name = new File(file).list();
		Runtime.getRuntime().exec(new String[]{"cmd", "/c", file + "\\" + name[0]});
	}
	
	@FXML
	protected void btnWatched(ActionEvent event){
		Movie entry = lvMovie.getSelectionModel().getSelectedItem();
		if(entry.isWatched()){
			btnWatched.setGraphic(new ImageView(new Image(getClass().getResource("Uncheck.png").toExternalForm())));
			entry.setWatched(false);
		}
		else{
			btnWatched.setGraphic(new ImageView(new Image(getClass().getResource("Check.png").toExternalForm())));
			entry.setWatched(true);
		}
		Thread t = new Thread(new Runnable() {
	         @Override
	         public void run() {
	        	 movieList.saveList();
	         }	         
	});
		t.start();
	}
	
	@FXML
	protected void openConfig(ActionEvent event){
		Thread t = new Thread(new Runnable() {
	         @Override
	         public void run() {
	        	movieList = VideoList.buildMovieList(movieList.getVideoPath());
	     		movieList.setListPath(InterfaceController.LIST_PATH);
	     		movieList.setVideoPath(InterfaceController.MOVIE_PATH);
	     		movieList.saveList();
	     		movies.addAll(movieList.toList());
	         }	         
		});
		t.start();
	}
	
	
	
}
