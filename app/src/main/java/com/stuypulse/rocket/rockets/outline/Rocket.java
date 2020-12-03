package com.stuypulse.rocket.rockets.outline;

// Used for simulating real world input
import com.stuypulse.stuylib.streams.IStream;
import com.stuypulse.stuylib.streams.FilteredIStream;
import com.stuypulse.stuylib.streams.PollingIStream;
import com.stuypulse.stuylib.streams.filters.LowPassFilter;

// Constants for characteristics of the rocket
import com.stuypulse.stuylib.math.Vector2D;
import com.stuypulse.stuylib.math.Angle;
import com.stuypulse.stuylib.math.SLMath;

import com.stuypulse.rocket.physics.Constants;
import com.stuypulse.rocket.physics.RocketPhysics;

/**
 * This is an abstract class for a Rocket. By implementing the
 * .execute() function, and calling the functions in this class,
 * you are able to create a rocket that will function in this
 * simulation.
 */
public abstract class Rocket {
    
    /***************************************/
    /*** ABSTRACT FUNCTIONS TO IMPLEMENT ***/
    /***************************************/
    
    /**
     * Return the name of the author, this way,
     * you can name the rocket what ever you want
     * 
     * @return the name of the author of the rocket
     */
    public abstract String getAuthor();

    /**
     * This function gets called every cycle of the rocket,
     * any algorithms that you create for the rocket will
     * be defined in here.
     */
    protected abstract void execute(); 

    /**********************************/
    /*** Important functions to use ***/
    /**********************************/

    /**
     * Set the thruster of the rocket, make sure to keep
     * the values of thrust between 0 and 1 or your rocket
     * will explode.
     * 
     * Note that the rocket will not immediately be set to
     * this thrust value, there will be some real world delay
     * 
     * @param thrust the intensity of the thrust [0.0 - 1.0]
     */
    protected final void setThrust(double thrust) {
        if(0 <= thrust && thrust <= 1) {
            this.thrust = thrust;
        } else {
            throw new RocketException("Invalid Thrust Value (" + thrust + ")");
        }
    }

    /**
     * Angle of the thruster of the rocket. This will cause
     * the rocket to turn if the thruster is also used.
     * 
     * Note that the rocket thruster will not immediately be set 
     * to this thrust value, the rocket thruster may take a little
     * bit in order to turn to that value
     * 
     * Angle value of -1 will turn the thruster to about -45 deg.
     * Angle value of 1 will turn the thruster to about 45 deg.
     * 
     * @param angle the amount that the thruster should turn. [-1.0 - 1.0]
     */
    protected final void setThrustAngle(double angle) {
        if(-1 <= angle && angle <= 1) {
            this.thrustAngle = angle;
        } else {
            throw new RocketException("Invalid Angle Value (" + angle + ")");
        }
    }

    /**
     * Stops the rocket entirely.
     */
    protected final void stop() {
        setThrust(0);
        setThrustAngle(0);
    }

    /**
     * Stops the rocket entirely AND cuts control.
     */
    protected final void forceStop() {
        stop();
        status = RocketStatus.STOPPED;
    }

    /*******************************/
    /*** Kindof Important things ***/
    /*******************************/

    /**
     * Gets the state of the Rocket.
     * 
     * This is extremely important as the state of the
     * rocket is the only way that you are going to be
     * able to tell where the rocket is.
     * 
     * The State contains things about 
     * position, velocity, and angle
     * 
     * @return the state of the rocket
     */
    public final RocketState getState() {
        return this.state;
    }

    /**
     * Gets the real world thrust that the rocket
     * is currently outputting. It may not be identicle
     * to the value that you gave it as nothing is perfect.
     * 
     * @return the real world thrust of the rocket
     */
    public final double getThrust() {
        return this.realThrust.get();
    }
    /**
     * Gets the real world thruster angle that the rocket
     * is in. It may not be identicle to the value that 
     * you gave it as nothing is perfect.
     * 
     * @return the real world thruster angle of the rocket
     */
    public final Angle getThrustAngle() {
        return Angle.fromDegrees(this.realThrustAngle.get());
    }

    /*************************/
    /*** Rest of the class ***/
    /*************************/

    // Current status of Rocket
    private RocketStatus status;
    private String explodedReason;

    // Target Thrust / Angle for Rocket
    private double thrust;
    private double thrustAngle;

    // Real World Thrust / Angle for Rocket
    private IStream realThrust;
    private IStream realThrustAngle;

    // Position and Velocity data for Rocket
    private RocketPhysics simulator;
    private RocketState state;

    // Initialize Rocket
    public Rocket() {
        // Initial state of rocket
        status = RocketStatus.INITIALIZED;
        explodedReason = "None";

        // Set trust and angle
        thrust = 0;
        thrustAngle = 0;

        // Apply single low pass filter in order to simulate
        // real world latency
        realThrust = new PollingIStream(
            new FilteredIStream(
                () -> thrust,
                new LowPassFilter(Constants.Rocket.THRUST_RC)
            ),
            50.0 // Thrust updates 50 times a second
        );

        // Apply a triple low pass filter in order to simulate
        // real world inperfections and also momentum. 
        realThrustAngle = new PollingIStream(
            new FilteredIStream(
                () -> thrustAngle * Constants.Rocket.THRUST_ANG_REACH,
                new LowPassFilter(Constants.Rocket.THRUST_ANG_RC),
                new LowPassFilter(Constants.Rocket.THRUST_ANG_RC),
                new LowPassFilter(Constants.Rocket.THRUST_ANG_RC)
            ),
            50.0 // Thrust updates 50 times a second
        );

        // Create Physics object and State object
        simulator = new RocketPhysics(this);
        state = new RocketState();
    }

    /**
     * Begin running the rocket
     */
    public final void start() {
        if(status != RocketStatus.INITIALIZED) {
            throw new RocketException("Attempted to start rocket while \"" + status + "\"!");
        } else {
            status = RocketStatus.RUNNING;
            explodedReason = "None";
        }

    }

    /**
     * This function is called in order to step forward in the
     * simulation, and also call the user defined functions.
     */
    public final void periodic() {
        if(status == RocketStatus.RUNNING) {
            try {
                this.execute();
            } catch (RocketException e) {
                this.explode(e.getMessage());
            }
        } else {
            stop();
        }

        state = simulator.getNextState();
    }

    /**
     * Stop the rocket, explode, ends the rockets simulation
     */
    public final void explode() {
        explode("No Reason Given");
    }

    /**
     * Stop the rocket, explode, ends the rockets simulation
     * 
     * @param msg reason the rocket exploded
     */
    public final void explode(String msg) {
        if(status != RocketStatus.EXPLODED) {
            status = RocketStatus.EXPLODED;
            explodedReason = msg;
        }
    }

    /**
     * Gets the current status of the rocket
     * @return current status of the robot
     */
    public final RocketStatus getStatus() {
        return status;
    }

    /**
     * Combine the Author, Rocket Name, and State into
     * a single string. Good to represent the rocket.
     */
    public final String toString() {
        if(status == RocketStatus.EXPLODED) {
            return this.getAuthor() + "'s " 
                + this.getClass().getSimpleName() 
                + " [" + this.getStatus() + ": " + explodedReason + "]";
        } 

        return this.getAuthor() + "'s " 
             + this.getClass().getSimpleName() 
             + " [" + this.getStatus() + "]";
    }




}