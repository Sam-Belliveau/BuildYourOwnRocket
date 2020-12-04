package com.stuypulse.rocket.rocket;

import com.stuypulse.stuylib.math.Vector2D;

import java.util.Vector;

import com.stuypulse.stuylib.math.Angle;

public final class RocketState {

    private final Vector2D position;
    private final Vector2D velocity;

    private final Angle angle;
    private final double angularVelocity;

    public RocketState(Vector2D position, Vector2D velocity, Angle angle, double angularVelocity) {
        this.position = position;
        this.velocity = velocity;
        this.angle = angle;
        this.angularVelocity = angularVelocity;
    }

    public RocketState() {
        this(
            new Vector2D(0,0), 
            new Vector2D(0,0), 
            Angle.fromDegrees(0),
            0.0
        );
    }

    public Vector2D getPosition() {
        return this.position;
    }

    public Vector2D getVelocity() {
        return this.velocity;
    }

    public Angle getAngle() {
        return this.angle;
    }

    public double getAngularVelocity() {
        return this.angularVelocity;
    }

    public String toString() {
        return "[" + this.position + ", " + this.angle + "]";
    }

}