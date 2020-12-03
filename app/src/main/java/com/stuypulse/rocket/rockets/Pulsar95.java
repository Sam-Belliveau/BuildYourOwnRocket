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
public class Pulsar95 extends Rocket {

    public Pulsar95() {
        // This really is the whole class
    }

    public String getAuthor() {
        return "Sam Belliveau";
    }

    protected void execute() {
        setThrust(0.5);

        if(getState().getPosition().y < 10) {
            setThrustAngle(0);
        } else {
            if(getState().getAngle().toDegrees() > 0) {
                setThrustAngle(0.2);
            } else {
                setThrustAngle(-0.2);
            }
        }
    }

}