package com.stuypulse.rocket.rocket.rockets;

import java.awt.Color;
import com.stuypulse.rocket.rocket.Rocket;

import com.stuypulse.stuylib.math.*;
import com.stuypulse.stuylib.control.*;

/**
 * The
 * <ul>
 *  <li>P - ersonable</li>
 *  <li>e - xtendable</li>
 *  <li>n - on-negotiable</li>
 *  <li>15th edition</li>
 * </ul>
 * Pensil15 rockt.
 */
public class Pensil15 extends Rocket {
    
    private double targetHeight = 100;

    public Pensil15() {
    }

    public String getAuthor() {
        return "Myles";
    }

    private double getHeight() {
        return getState().getPosition().y;
    }

    private double getHVel() {
        return getState().getVelocity().y;
    }

    protected void execute() {

        final double error = targetHeight - getHeight() + getHVel()*0.05;

        setThrust(
            SLMath.limit(error * 0.05, 0, 1)
        );

    }

    public Color getColor() {
        return Color.BLACK;
    }
}