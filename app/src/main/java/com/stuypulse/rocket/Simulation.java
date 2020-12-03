package com.stuypulse.rocket;

import com.stuypulse.rocket.rockets.outline.*;
import com.stuypulse.rocket.graphics.Graphics;
import com.stuypulse.rocket.rockets.*;


public final class Simulation {

    private Rocket[] rockets = {
        new Pulsar94(),
        //new Pulsar95(),
        new Pulsar96(25, true)
    };

    private Graphics graphics = new Graphics(rockets);

    public Simulation() {
    }

    public void start() {
        for(Rocket rocket : rockets) {
            rocket.start();
        }
    }

    public void periodic() {
        graphics.display();
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