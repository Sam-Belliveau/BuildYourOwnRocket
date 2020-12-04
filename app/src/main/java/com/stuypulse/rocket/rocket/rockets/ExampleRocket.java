package com.stuypulse.rocket.rocket.rockets;

import java.awt.Color;
import com.stuypulse.rocket.rocket.Rocket;

/**
 * This is an example Rocket. It performs really poorly,
 * but it will show you what an example rocket is supposed
 * to look like.
 * 
 * Follow the design of this, (except for the algorithms),
 * if you are confused about making your rocket.
 * 
 * @author Sam Belliveau (sam.belliveau@gmail.com)
 */
public class ExampleRocket extends Rocket {

    public ExampleRocket() {
        // This really is the whole class
    }

    public String getAuthor() {
        return "Stuypulse";
    }

    protected void execute() {
        // Get Position of the rocket
        double x = getState().getPosition().x;
        double y = getState().getPosition().y;

        // Get Velocity of the Rocket
        double vx = getState().getVelocity().x;
        double vy = getState().getVelocity().y;
        
        // Fly Rocket
        if(y > 100) {
            setThrust(0);
            setThrustAngle(0);
        } else {
            setThrust(1);
            setThrustAngle(0.0);
        }
    }

    public Color getColor() {
        return Color.BLUE;
    }
}