package com.example;

import com.example.space_simulator.Star;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Planet {
    final double AU = 149.6e6 * 1000;
    final double GRAVITATIONAL_CONSTANT = 6.67430E-11;
    private double MASS;
    final double SCALE = 250 / AU; //1AU = 100 pixels
    private final double TIMESTEP = 3600 * 24; //1 Day
    private double X_VELOCITY;
    private String NAME;
    private double Y_VELOCITY;
    private double RADIUS;
    private double DISTANCE;
    private double SCREEN_HEIGHT;
    Map<Double, Double> orbit = new HashMap<>();
    private double SCREEN_WIDTH;
    private Star STAR;
    double XAXIS;
    double YAXIS;
    Circle Planet = new Circle();
    private double ANGULAR_VELOCITY;

    public Planet(String Name, double x_axis, double y_axis, double mass, double radius, double distance,double angularv,double x_vel, double y_vel, Star star){
        NAME = Name;
        YAXIS = y_axis;
        XAXIS = x_axis;
        MASS = mass;
        RADIUS = radius;
        DISTANCE = distance;
        ANGULAR_VELOCITY = angularv;
        Planet.setRadius(RADIUS);
        Planet.setFill(Color.GREEN);
        this.STAR = star;
        planetPosition();
        X_VELOCITY = x_vel;
        Y_VELOCITY = y_vel;
    }

    public void setRADIUS(double radius){
        RADIUS = radius;
    }

    public void planetPosition(){
        XAXIS = XAXIS * SCALE;
        YAXIS = YAXIS  * SCALE;

        System.out.println("Xaxis: " + XAXIS +
                            "Yaxis: " + YAXIS);
        //set planet position relative to star
        Planet.setCenterY(STAR.getStarYPosition() + YAXIS);
        Planet.setCenterX(STAR.getStarXPosition() + XAXIS);
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
//
//    public void setANGLE(double angle){
//        ANGLE += angle;
//    }

    public double getANGULAR_VELOCITY(){
        return ANGULAR_VELOCITY;
    }




    public double calculateXAttraction(Planet this, Planet other){
        double other_xaxis = other.XAXIS;
        double other_yaxis = other.YAXIS;
        double x_axisDistance = other_xaxis - this.XAXIS;
        double y_axisDistance = other_yaxis - this.YAXIS;
        double total_Distance = Math.sqrt((x_axisDistance * x_axisDistance) + (y_axisDistance * y_axisDistance));

        double gravitationalForce = (this.GRAVITATIONAL_CONSTANT * ((this.MASS * other.MASS) / Math.pow(total_Distance, 2)));
        double angle_theta = Math.atan2(y_axisDistance, x_axisDistance);
        double force_x = Math.cos(angle_theta) * gravitationalForce;

        return force_x;
    }

    public double calculateYAttraction(Planet this, Planet other){
        double other_xaxis = other.XAXIS;
        double other_yaxis = other.YAXIS;
        double x_axisDistance = other_xaxis - this.XAXIS;
        double y_axisDistance = other_yaxis - this.YAXIS;
        double total_Distance = Math.sqrt((x_axisDistance * x_axisDistance) + (y_axisDistance * y_axisDistance));

        double gravitationalForce = (this.GRAVITATIONAL_CONSTANT * ((this.MASS * other.MASS) / Math.pow(total_Distance, 2)));
        double angle_theta = Math.atan2(y_axisDistance, x_axisDistance);
        double force_y = Math.sin(angle_theta) * gravitationalForce;

        return force_y;
    }


    public void updatePosition(ArrayList<Planet> planets){
        double forcex = 0, forcey = 0;
        double total_forcex = 0, total_forcey = 0;
        for (Planet planet : planets){
            if(this == planet)
                continue;
            forcex = this.calculateXAttraction(planet);
            forcey = this.calculateYAttraction(planet);
            total_forcey += forcey;
            total_forcex += forcex;

            //calculate velocities
            this.X_VELOCITY += total_forcex / this.MASS * this.TIMESTEP;
            this.Y_VELOCITY += total_forcey / this.MASS * this.TIMESTEP;
        }
        this.XAXIS += this.X_VELOCITY * TIMESTEP;
        this.YAXIS += this.Y_VELOCITY * TIMESTEP;

        this.orbit.put(this.XAXIS, this.YAXIS);

        Planet.setCenterX(XAXIS);
        Planet.setCenterY(YAXIS);
    }

    public void setY_VELOCITY(double y_VELOCITY) {
        Y_VELOCITY += y_VELOCITY;
    }

    public void setX_VELOCITY(double x_VELOCITY) {
        X_VELOCITY += x_VELOCITY;
    }

    public double getX_VELOCITY() {
        return X_VELOCITY;
    }

    public double getY_VELOCITY() {
        return Y_VELOCITY;
    }

    public double getMASS() {
        return MASS;
    }


    public void setXAXIS(double XAXIS) {
        this.XAXIS += XAXIS;
    }

    public void setYAXIS(double YAXIS) {
        this.YAXIS += YAXIS;
    }

    public double getXAXIS() {
        return XAXIS;
    }

    public double getYAXIS() {
        return YAXIS;
    }

    @Override
    public String toString(){
        return this.NAME + ": "
                + "\n Y Axis: " + this.YAXIS
                +"\n X Axis: " + this.XAXIS;
    }

//    public void drawDistance(){
//        XAXIS = this.XAXIS * this.SCALE + STAR.getStarXPosition();
//        YAXIS = this.XAXIS * this.SCALE + STAR.getStarYPosition();
//    }

}
