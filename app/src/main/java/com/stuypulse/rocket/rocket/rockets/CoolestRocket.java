package com.stuypulse.rocket.rocket.rockets;

import java.awt.Color;
import com.stuypulse.rocket.rocket.Rocket;

public class CoolestRocket extends Rocket {

    public CoolestRocket() {
        // This really is the whole class
    }

    public String getAuthor() {
        return "Andrew";
    }

    protected void execute() {
        
        double x = getState().getPosition().x;
        double y = getState().getPosition().y;
        
        setThrust(1);
        setThrustAngle(0); 

        if(y>=100){
            setThrust(0);
            setThrustAngle(0);
        }
    }

    public Color getColor() {
        return Color.BLACK;
    }

}
