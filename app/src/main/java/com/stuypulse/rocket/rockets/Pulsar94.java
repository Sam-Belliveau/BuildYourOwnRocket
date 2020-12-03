package com.stuypulse.rocket.rockets;

// Needed to make your rocket class
import com.stuypulse.rocket.rockets.outline.Rocket;

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
public class Pulsar94 extends Rocket {

    public Pulsar94() {
        // This really is the whole class
    }

    public String getAuthor() {
        return "Sam Belliveau";
    }

    protected void execute() {
        setThrust(1);

        double height = getState().getPosition().y;
        setThrustAngle(0.1);
    }

}