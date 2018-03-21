package application;

import java.io.InputStream;

import javafx.application.Preloader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class PreloaderController extends Preloader{

	
	
	
	private Stage preloaderStage;
	
	private final StageStyle STAGE_STYLE = StageStyle.UNDECORATED;
	private final String APP_TITLE = "Media Library";
	private final InputStream LOADING_IMAGE = getClass().getResourceAsStream("\\resources\\loadingscreen.gif"),
						 APP_ICON = getClass().getResourceAsStream("\\resources\\mediaPlayer.png");
	
	
	
	
	@Override
	public void handleApplicationNotification(PreloaderNotification notification) {
		if(((ProgressNotification)notification).getProgress() == 1){
			this.preloaderStage.hide();
	    }
	}

	
	
	
	@Override
	public void start(Stage preloaderStage) throws Exception {
		this.preloaderStage = preloaderStage;
		Pane loadingScreen = new Pane();
		Image loadingGif = new Image(this.LOADING_IMAGE);
		ImageView ivLoadingGif = new ImageView(loadingGif);
		ivLoadingGif.setFitHeight(loadingGif.getHeight()/2);
		ivLoadingGif.setFitWidth(loadingGif.getWidth()/2);
		loadingScreen.getChildren().add(ivLoadingGif);
		Scene scene = new Scene(loadingScreen);
		preloaderStage.setScene(scene);
		preloaderStage.initStyle(this.STAGE_STYLE);
		preloaderStage.setTitle(this.APP_TITLE);
		preloaderStage.getIcons().add(new Image(this.APP_ICON));
		preloaderStage.centerOnScreen();
		preloaderStage.show();
	}

}
