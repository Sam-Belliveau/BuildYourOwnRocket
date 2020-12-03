package com.stuypulse.rocket.physics;

import com.stuypulse.stuylib.math.Vector2D;
import com.stuypulse.stuylib.math.Angle;

import com.stuypulse.rocket.rockets.outline.Rocket;
import com.stuypulse.rocket.rockets.outline.RocketState;
import com.stuypulse.stuylib.util.StopWatch;

public final class RocketPhysics {

    private StopWatch timer;
    private Rocket rocket;

    public RocketPhysics(Rocket rocket) {
        this.timer = new StopWatch();
        this.rocket = rocket;
    }

    // TODO: Test this physics
    public RocketState getNextState() {
        // Time since last call
        final double dt = timer.reset();

        // Information about thruster
        final double thrust = rocket.getThrust();
        final Angle thrustAngle = rocket.getThrustAngle();

        final double thrustForce = thrust * Constants.Rocket.THRUST_FORCE;

        // State of Rocket
        RocketState state = rocket.getState();


        ///// PHYSICS /////

        // Calculating Torque
        double torque = thrustForce * -thrustAngle.sin() * Constants.Rocket.HEIGHT / 2;
    
        // Calculating Thrust onto rocket
        Angle totalThrustAngle = state.getAngle().add(thrustAngle).add(Angle.k90deg);
        Vector2D thrustVector = totalThrustAngle.getVector().mul(thrustForce * thrustAngle.cos());

        // Calculating final forces
        Vector2D forceVector = thrustVector.div(Constants.Rocket.WEIGHT).add(Constants.GRAVITY);
        double forceAngle = torque / Constants.Rocket.WEIGHT;
    
        // Applying Forces to Rocket
        Vector2D velocity = state.getVelocity().add(forceVector.mul(dt));
        double angularVelocity = state.getAngularVelocity() + forceAngle * dt;

        // Applying Velocities to Rocket
        Vector2D position = state.getPosition().add(velocity.mul(dt));
        Angle angle = state.getAngle().add(Angle.fromDegrees(angularVelocity * dt));

        // Checking for the floor
        double min_height = Constants.Rocket.HEIGHT * Math.abs(angle.cos()) / 2.0;
        if(position.y <= min_height) {
            // Set new position to y = 0
            position = new Vector2D(position.x, min_height);

            // Check if rocket landed safely
            if( (Math.abs(angle.toDegrees()) < Constants.MAX_LANDING_ANGLE)) {
                angle = Angle.fromDegrees(0);
            } else {
                angularVelocity = (-angularVelocity) * Constants.RESTITUTION;
                rocket.explode();
            }
            velocity = new Vector2D(Constants.RESTITUTION * velocity.x, Constants.RESTITUTION * Math.abs(velocity.y));
        }

        return new RocketState(position, velocity, angle, angularVelocity);
    } 

}