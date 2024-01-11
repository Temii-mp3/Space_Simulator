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
import java.util.ArrayList;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        final double GRAVITATIONAL_CONSTANT = 6.67430E-11;
        final double AU = 149.6e6 * 1000; //Distance from earth to sun (converted to meters)
         final double SCALE = 250/AU; //1AU = 100 pixels

//        final double SCALE_FACTOR = 1.7857142857142857E-4;
        //setting the width of the screen
        final double SCREEN_WIDTH = 800;
        //setting the height of the screen
        final double SCREEN_HEIGHT = 800;

        Group rootGroup = new Group();

        Image spaceBackground = new Image("file:starBckg.png");
        ImageView viewImg = new ImageView(spaceBackground);
        HBox hbox = new HBox(viewImg);
        Scene scene = new Scene(rootGroup, SCREEN_WIDTH, SCREEN_HEIGHT);



        Group celestialBodies = new Group();
        ArrayList<Planet> planets = new ArrayList<>();
       Star sun = new Star(1.989E30, 30, SCREEN_WIDTH, SCREEN_HEIGHT);

       Planet earth = new Planet("Earth", -1*AU,0, 5.9742e24,16,0.0,0.0, 0.0, 0.0,sun);
       //earth.setY_VELOCITY(29);
       Planet mars = new Planet("Mars", -1.524 * AU ,0, 6.39e23,12, 0.0,0.0, 0.0, 0.0,sun);
       //mars.setY_VELOCITY(47.4 * 1000);
       Planet mecury  = new Planet("Mecury", 0.387 * AU ,0, 3.30e23,8, 0.0,0.0, 0.0, 0.0,sun);
       //mecury.setY_VELOCITY(-47.4 * 1000);
       Planet venus = new Planet("Venus", 0.723 * AU,0, 4.8685e24,14, 0.0 ,0.0, 0.0, 0.0,sun);
//       venus.setY_VELOCITY(-35.02 * 1000);
       planets.add(earth);
       planets.add(mars);
       planets.add(mecury);
       planets.add(venus);

//        Timeline timeline = new Timeline();
//
//        KeyFrame keyFrame = new KeyFrame(Duration.seconds(0.1), actionEvent -> {
//            for (Planet p : planets){
//                sun.calculateSunAttraction(planets);
//                p.updatePosition(planets);
//                //System.out.println(p.getXAXIS());
//                //System.out.println(p.getYAXIS());
//
//            }
//
//        });
//
//        timeline.getKeyFrames().add(keyFrame);
//        timeline.setCycleCount(Animation.INDEFINITE);
//        timeline.play();

       System.out.println(sun.getStarYPosition());
       System.out.println(sun.getStarXPosition());


        celestialBodies.getChildren().addAll(sun.getNewStar());
        //add all planets to the celestialBodies scene
        for(Planet p : planets){
            celestialBodies.getChildren().add(p.getPlanet());
        }

        rootGroup.getChildren().addAll(viewImg, celestialBodies);

        stage.setTitle("Space Simulator");
        stage.setScene(scene);
        stage.show();
    }
    public static void main(String[] args) {
        launch();
    }
}

