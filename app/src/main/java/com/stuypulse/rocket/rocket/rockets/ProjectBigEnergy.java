package com.stuypulse.rocket.rocket.rockets;

import java.awt.Color;
import com.stuypulse.rocket.rocket.Rocket;

public class ProjectBigEnergy extends Rocket {

    public ProjectBigEnergy() {
        
    }

    public String getAuthor() {
        return "Elwin";
    }

    protected void execute() {
    
        double x = getState().getPosition().x;
        double y = getState().getPosition().y;
        
        setThrustAngle(0);
        if (y>=100) {
            setThrust(0);
        } else if (y<=95) {
            setThrust(0.7);
        } 
    }

    public Color getColor() {
        return Color.BLACK;
    }
}