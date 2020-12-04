package com.stuypulse.rocket.rocket.rockets;

import java.awt.Color;
import com.stuypulse.rocket.rocket.Rocket;

/**
 * This is my shot at a robot that hovers around y = 100
 * 
 * @author Sam Belliveau (sam.belliveau@gmail.com)
 */
public class SteadyBoi extends Rocket {

    public SteadyBoi() {
        // This really is the whole class
    }

    public String getAuthor() {
        return "Sam";
    }

    protected void execute() {
        // Get Position of the rocket
        double x = getState().getPosition().x;
        double y = getState().getPosition().y;

        // Get Velocity of the Rocket
        double vx = getState().getVelocity().x;
        double vy = getState().getVelocity().y;
        
        // Get Angle of the rocket
        double ang = getState().getAngle().toDegrees();

        // Get rocket to good height
        double err = 100 - (y + 1.25 * vy);
        setThrust(Math.max(0, Math.min(1, err)));

        // Move Rocket Left and Right 
        if(Math.abs(ang) > 20) {
            // When the angle is too large,
            // Emergency correct it
            if(ang < 0) {
                setThrustAngle(-1);
            } else {
                setThrustAngle(1);
            }
        } else {
            // slightly turn the rocket to demonstraight 
            // straightening algorithm
            setThrustAngle(-0.01);
        }
    }

    public Color getColor() {
        return Color.magenta;
    }
}