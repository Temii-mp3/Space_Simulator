package com.example;

import com.example.space_simulator.Star;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Planet {
    private double RADIUS;
    private double DISTANCE;
    private double ANGLE;
    private double SCREEN_HEIGHT;
    private double SCREEN_WIDTH;
    private Star STAR;
    double XAXIS;
    double YAXIS;
    Circle Planet = new Circle();
    private double ANGULAR_VELOCITY;

    public Planet(double width, double height, double mass, double radius, double angle, double distance,double angularv,  Star star){
        SCREEN_HEIGHT = height;
        SCREEN_WIDTH = width;
        RADIUS = radius;
        DISTANCE = distance;
        ANGLE = angle;
        ANGULAR_VELOCITY = angularv;
        Planet.setRadius(RADIUS);
        Planet.setFill(Color.GREEN);
        this.STAR = star;
        planetPosition();
    }

    public void setRADIUS(double radius){
        RADIUS = radius;
    }

    private void planetPosition(){
        XAXIS = DISTANCE * Math.cos(ANGLE);
        YAXIS = DISTANCE * Math.sin(ANGLE);
        //set planet position relative to star
        Planet.setCenterX(STAR.getStarXPosition() + STAR.getRADIUS() + XAXIS);
        Planet.setCenterY(STAR.getStarYPosition() + STAR.getRADIUS() + YAXIS);

    }

    public void updatePosition(){
        XAXIS = STAR.getStarXPosition() + DISTANCE * Math.cos(ANGLE);
        YAXIS = STAR.getStarYPosition() + DISTANCE * Math.sin(ANGLE);
        Planet.setCenterX(XAXIS);
        Planet.setCenterY(YAXIS);
    }
    public Circle getPlanet(){
        return Planet;
    }

    public double getDISTANCE(){
        return DISTANCE;
    }

    public void setANGULAR_VELOCITY(double velocity){
        ANGULAR_VELOCITY += velocity;
    }

    public void setANGLE(double angle){
        ANGLE += angle;
    }

    public double getANGULAR_VELOCITY(){
        return ANGULAR_VELOCITY;
    }
}
