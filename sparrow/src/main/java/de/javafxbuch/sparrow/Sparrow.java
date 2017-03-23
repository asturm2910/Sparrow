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

    public void start(Stage stage) throws Exception {

        log.info("Starting Hello JavaFX and Maven demonstration application");

        StackPane rootNode = new StackPane();
        Label text = new Label();
        rootNode.getChildren().add(text);
        
        try {
        	Twitter twitter = TwitterFactory.getSingleton();
        	ResponseList<Status> homeTimeline = twitter.getHomeTimeline();
        	text.setText(homeTimeline.get(0).getText());
        }
        catch (TwitterException ex) {
        	log.error("Twitter Exception", ex);
        }
        
        log.debug("Showing JFX scene");
        Scene scene = new Scene(rootNode, 400, 200);
//        scene.getStylesheets().add("/styles/styles.css");

        stage.setTitle("SparrowFX Application");
        stage.setScene(scene);
        stage.show();
    }
}
