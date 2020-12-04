package com.stuypulse.rocket;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        Simulation sim = new Simulation();

        sim.printRockets();

        sim.start();

        while(true) {
            Thread.sleep(10);
            sim.periodic();
            sim.printRockets();
            System.out.println();
        }
    }
}
