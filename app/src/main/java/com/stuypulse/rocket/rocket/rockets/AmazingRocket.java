package com.stuypulse.rocket.rocket.rockets;

import java.awt.Color;
import com.stuypulse.rocket.rocket.Rocket;

public class AmazingRocket extends Rocket {
    public AmazingRocket () {

    }

    public String getAuthor () {
        return "Randy";
    }

    protected void execute () {

        double x = getState().getPosition().x;
        double y = getState().getPosition().y;

        if(y >= 100) {
            setThrust(0);
            setThrustAngle(0);
        } else {
            setThrust(1);
            setThrustAngle(0);
        };
    }

    public Color getColor () {
        return Color.RED;
    }
}