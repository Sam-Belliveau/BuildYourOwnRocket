package com.stuypulse.rocket.physics;

import com.stuypulse.stuylib.math.Vector2D;
import com.stuypulse.stuylib.math.Angle;

public interface Constants {

    public Vector2D GRAVITY = new Vector2D(0, -10);

    public double MAX_LANDING_ANGLE = 10;
    public double MAX_LANDING_VELOCITY = 100;

    public double RESTITUTION = 0.05;

    public interface Rocket {
        // Inperfections in Rocket Thruster
        public double THRUST_RC = 0.1;
        public double THRUST_ANG_RC = 0.05;

        // Thruster Information
        public double THRUST_ANG_REACH = 60;
        public double THRUST_FORCE = 200;

        // Rocket Information
        public double HEIGHT = 10;
        public double WIDTH = 1;
        public double WEIGHT = 11;
    }

}