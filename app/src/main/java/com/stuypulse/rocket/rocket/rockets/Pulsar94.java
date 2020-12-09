package com.stuypulse.rocket.rocket.rockets;

import java.awt.Color;
import com.stuypulse.rocket.rocket.Rocket;

public class Pulsar94 extends Rocket {

    public Pulsar94(){

    }

    public String getAuthor() {
        return "Mark";//:P
    }
    
    protected void execute() {
        double x = getState().getPosition().x;
        double y = getState().getPosition().y;
        double vy = getState().getVelocity().y;
        double a = getState().getAngle().toDegrees();
        

        
        if (y >= 20 && y < 100){
            setThrust(0);//yes
            setThrustAngle(0);//yes
            if (a <= -45) {
                setThrust(1);//yes
                setThrustAngle(-0.1);//yes
            } 
            else {
                setThrust(1);//yes
                setThrustAngle(0.1);//yes
            }
        }
        else if (y >= 100){
            setThrust(1);//yes
            setThrustAngle(0);
        }
        else {
            setThrust(1);//yes
            setThrustAngle(0);
        }
    }

        /*if (y >= 100){
            setThrust(0);//yes
            setThrustAngle(0);//yes
        }
        else if (y > 90){
            setThrust(0.25);//yes
            setThrustAngle(0);//yes
        }
        else if (y > 80){
            setThrust(0.5);//yes
            setThrustAngle(0);//yes
        }
        else {
            setThrust(1);//yes
            setThrustAngle(0);//no
        }//;-;
        
    }
    */

    public Color getColor() {
        return Color.BLACK;
    }
}