package com.stuypulse.rocket;

import com.stuypulse.rocket.rockets.outline.*;
import com.stuypulse.rocket.rockets.*;


public final class Simulation {

    private Rocket[] rockets = {
        new Pulsar94()
    };

    public Simulation() {
    }

    public void start() {
        for(Rocket rocket : rockets) {
            rocket.start();
        }
    }

    public void periodic() {
        for(Rocket rocket : rockets) {
            rocket.periodic();
        }
    }

    public void printRockets() {
        System.out.println("Current Rocket Status:");

        for(Rocket rocket : this.rockets) {
            System.out.println("\t- " + rocket);
        }
    }

}