//YASUO go EEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEE
package com.stuypulse.rocket.rocket.rockets;

import java.awt.Color;
import com.stuypulse.rocket.rocket.Rocket;

public class Yasuo extends Rocket{

    public Yasuo(){
    }

    public String getAuthor() {
        return "Tianlang";
    }

    protected void execute() {
        double x = getState().getPosition().x;
        double y = getState().getPosition().y;

        setThrust(1);
        setThrustAngle(0);
        if (y>=100) setThrust(0);
        else setThrust(1);
        

    }

    public Color getColor() {
        return Color.BLACK;
    }
}
