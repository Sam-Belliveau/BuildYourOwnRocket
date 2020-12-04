package com.stuypulse.rocket.physics;

import com.stuypulse.stuylib.math.Vector2D;
import com.stuypulse.stuylib.math.Angle;

public interface Constants {

    public Vector2D GRAVITY = new Vector2D(0, -10);

    public double MAX_LANDING_ANGLE = 10;
    public double MAX_LANDING_VELOCITY = 100;

    public double RESTITUTION = 0.25;

    public double DRAG = 0.16;
    public double ANGLE_DRAG = 0.08;

    public interface Rocket {
        public double DISTANCE = 20;
        public double THICKNESS = 0.04;
        public double HEAD_THICKNESS = 0.05;
        public double THRUSTER_THICKNESS = 0.03;
        public double THRUSTER_LENGTH = 2;

        // Inperfections in Rocket Thruster
        public double THRUST_RC = 0.05;
        public double THRUST_ANG_RC = 0.05;

        // Thruster Information
        public double THRUST_ANG_REACH = 75;
        public double THRUST_FORCE = 250;

        // Rocket Information
        public double HEIGHT = 10;
        public double WIDTH = 1;
        public double WEIGHT = 15;
    }

}