package application;

import com.sun.javafx.application.LauncherImpl;

public class Main{
	
	//Start GUI with PreloaderScreen
	public static void main(String[] args) {
		LauncherImpl.launchApplication(InterfaceController.class, PreloaderController.class, args);
	}
	
}
