package com.example.space_simulator;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Star {
    private double SOLAR_MASS;
    private double SCREEN_HEIGHT;
    private double SCREEN_WIDTH;
    private double RADIUS;
    private double starCenterX;
    private double starCenterY;
    private Circle newStar = new Circle();



    public Star(double mass, double radius, double width, double height){
        SCREEN_HEIGHT = height;
        SCREEN_WIDTH = width;
        SOLAR_MASS = mass;
        RADIUS = radius;
        newStar.setFill(Color.RED);
        newStar.setRadius(RADIUS);
        starCenterY = (SCREEN_HEIGHT - RADIUS * 2) / 2;
        starCenterX = (SCREEN_WIDTH - RADIUS * 2) / 2;

        this.setStarXPosition();
        this.setStarYPosition();
    }

    public void setSOLAR_MASS(double mass){
        SOLAR_MASS = mass;
    }

    public void setRADIUS(double radius){
        RADIUS = radius;
    }

    public double getSOLAR_MASS(){
        return SOLAR_MASS;
    }

    public double getRADIUS() {
        return RADIUS;
    }

    //set position of star to center of screen
    private void setStarXPosition(){
        newStar.setCenterX(starCenterX + RADIUS);
    }

    private void setStarYPosition(){
        newStar.setCenterY(starCenterY + RADIUS);
    }

    public double getStarXPosition(){
        return newStar.getCenterX();
    }
    public double getStarYPosition(){
        return newStar.getCenterY();
    }

    public Circle getNewStar(){
        return newStar;
    }
}
