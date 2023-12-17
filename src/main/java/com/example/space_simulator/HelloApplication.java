package com.example.space_simulator;

import com.example.Planet;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        final double GRAVITATIONAL_CONSTANT = 6.67430E-11;
//        final double SCALE_FACTOR = 1.7857142857142857E-4;
        //setting the width of the screen
        final double SCREEN_WIDTH = 1920.0;
        //setting the height of the screen
        final double SCREEN_HEIGHT = 1080.0;

        Group rootGroup = new Group();

        Image spaceBackground = new Image("file:starBckg.png");
        ImageView viewImg = new ImageView(spaceBackground);
        HBox hbox = new HBox(viewImg);
        Scene scene = new Scene(rootGroup, SCREEN_WIDTH, SCREEN_HEIGHT);



        Group celestialBodies = new Group();
       Star sun = new Star(1.989E30, 100, SCREEN_WIDTH, SCREEN_HEIGHT);
       Planet earth = new Planet(SCREEN_WIDTH,SCREEN_HEIGHT, 50,50, Math.toRadians(45), 200,0.0, sun);

        Timeline timeline = new Timeline();

        KeyFrame keyFrame = new KeyFrame(Duration.seconds(0.1), actionEvent -> {
            double gravitationalForce = (GRAVITATIONAL_CONSTANT * sun.getSOLAR_MASS())/ Math.pow(earth.getDISTANCE(),2);
            earth.setANGULAR_VELOCITY(gravitationalForce);
            earth.setANGLE(Math.toRadians(earth.getANGULAR_VELOCITY()));
            earth.updatePosition();
        });

        timeline.getKeyFrames().add(keyFrame);
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();

       System.out.println(sun.getStarYPosition());
        System.out.println(sun.getStarXPosition());

        celestialBodies.getChildren().addAll(sun.getNewStar(), earth.getPlanet());

        rootGroup.getChildren().addAll(viewImg, celestialBodies);

        stage.setTitle("Space Simulator");
        stage.setScene(scene);
        stage.show();
    }
    public static void main(String[] args) {
        launch();
    }
}

