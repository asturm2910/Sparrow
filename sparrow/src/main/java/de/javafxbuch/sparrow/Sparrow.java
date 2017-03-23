package de.javafxbuch.sparrow;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import twitter4j.ResponseList;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;

public class Sparrow extends Application {

    private static final Logger log = LoggerFactory.getLogger(Sparrow.class);

    public static void main(String[] args) throws Exception {
        launch(args);
    }
    
    public void init() {
    	log.debug("init");
    }

    public void start(Stage stage) throws Exception {

        log.info("Starting Hello JavaFX and Maven demonstration application");

        StackPane rootNode = new StackPane();
        Label text = new Label();
        rootNode.getChildren().add(text);
        
        try {
        	log.info("Connection to Twitter...");
        	Twitter twitter = TwitterFactory.getSingleton();
        	log.info("done!");
        	log.info("Requesting Timeline for registered account...");
        	ResponseList<Status> homeTimeline = twitter.getHomeTimeline();
        	text.setText(homeTimeline.get(0).getText());
        	log.info("done!");
        }
        catch (TwitterException ex) {
        	log.error("Twitter Exception", ex);
        }
        
        log.debug("Showing JFX scene");
        Scene scene = new Scene(rootNode, 1200, 200);
//        scene.getStylesheets().add("/styles/styles.css");

        stage.setTitle("SparrowFX Application");
        stage.setScene(scene);
        stage.show();
    }
    
    public void stop() {
    	log.debug("Stopping Application");
    }
}
