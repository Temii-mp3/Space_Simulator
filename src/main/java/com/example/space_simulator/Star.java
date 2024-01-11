package com.example.space_simulator;

import com.example.Planet;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import java.util.ArrayList;

public class Star {
    final double GRAVITATIONAL_CONSTANT = 6.67430E-11;
    private double MASS;
    private double SCREEN_HEIGHT;
    private double SCREEN_WIDTH;
    private double RADIUS;

    private final double TIMESTEP = 3600 * 24; //1 Day
    private double STAR_XAXIS;
    private double STAR_YAXIS;
    private double starCenterX;
    private double starCenterY;
    private Circle newStar = new Circle();



    public Star(double mass, double radius, double width, double height){
        SCREEN_HEIGHT = height;
        SCREEN_WIDTH = width;
        MASS = mass;
        RADIUS = radius;
        newStar.setFill(Color.RED);
        newStar.setRadius(RADIUS);
        starCenterY = (SCREEN_HEIGHT - RADIUS * 2) / 2;
        starCenterX = (SCREEN_WIDTH - RADIUS * 2) / 2;

        this.setStarXPosition();
        this.setStarYPosition();
    }

    public void setMASS(double mass){
        MASS = mass;
    }

    public void setRADIUS(double radius){
        RADIUS = radius;
    }

    public double getMASS(){
        return MASS;
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

    public void calculateSunAttraction(ArrayList<Planet> planets){
        double forcex = 0;
        double forcey = 0;
        double total_forcex = 0;
        double total_forcey = 0;

        for(int i = 0; i < planets.size(); i++){
            forcex = this.calculateXAttraction(planets.get(i));
            forcey = this.calculateYAttraction(planets.get(i));
            total_forcey += forcey;
            total_forcex += forcex;
            planets.get(i).setX_VELOCITY(total_forcex / this.MASS * this.TIMESTEP);
            planets.get(i).setY_VELOCITY(total_forcey / this.MASS * this.TIMESTEP);
            planets.get(i).setXAXIS(this.getStarXPosition() + planets.get(i).getXAXIS());
            planets.get(i).setYAXIS(this.getStarYPosition() + planets.get(i).getYAXIS());
        }


    }

    public double calculateXAttraction(Star this, Planet other){
        double other_xaxis = other.getXAXIS();
        double other_yaxis = other.getYAXIS();

        double x_axisDistance = other_xaxis - this.getStarXPosition();
        double y_axisDistance = other_yaxis - this.getStarYPosition();


        double total_Distance = Math.sqrt((Math.pow(x_axisDistance,2)) + (Math.pow(y_axisDistance, 2)));
        System.out.println("X Axis : " + x_axisDistance);


        double gravitationalForce = ((this.GRAVITATIONAL_CONSTANT * this.MASS * other.getMASS()) / Math.pow(total_Distance, 2)) ;
        double angle_theta = Math.atan2(y_axisDistance, x_axisDistance);
        double force_x = Math.cos(angle_theta) * gravitationalForce;

        return force_x;
    }

    public double calculateYAttraction(Star this, Planet other){
        double other_xaxis = other.getX_VELOCITY();
        double other_yaxis = other.getY_VELOCITY();
        double x_axisDistance = other_xaxis - this.getStarXPosition();
        double y_axisDistance = other_yaxis - this.getStarYPosition();
        double total_Distance = Math.sqrt((x_axisDistance * x_axisDistance) + (y_axisDistance * y_axisDistance));

        double gravitationalForce = (this.GRAVITATIONAL_CONSTANT * this.MASS * other.getMASS()) / Math.pow(total_Distance, 2);
        double angle_theta = Math.atan2(y_axisDistance, x_axisDistance);
        double force_y = Math.sin(angle_theta) * gravitationalForce;

        return force_y;
    }

    public Circle getNewStar(){
        return newStar;
    }
}
