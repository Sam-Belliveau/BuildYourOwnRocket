package com.stuypulse.rocket.graphics;

import com.stuypulse.stuylib.math.Vector2D;


import com.stuypulse.stuylib.math.Angle;

import java.awt.Color;

import com.stuypulse.rocket.physics.Constants;
import com.stuypulse.rocket.rockets.outline.Rocket;
import com.stuypulse.rocket.rockets.outline.RocketStatus;

public class Graphics {

    private Rocket[] rockets;
    
    private double[] x_cloud = new double[128];
    private double[] y_cloud = new double[x_cloud.length];
    private double[] r_cloud = new double[x_cloud.length];

    public Graphics(Rocket[] r) {
        rockets = r;
    
        for(int i = 0; i < x_cloud.length; ++i) {
            x_cloud[i] = 200 * (Math.random() - 0.5);
            y_cloud[i] = 10 + 200 * Math.random();
            r_cloud[i] = (Math.random() + 0.2) * 3;
        }
    }

    public void line(Vector2D a, Vector2D b) {
        StdDraw.line(a.x,a.y,b.x,b.y);
    }

    private final double BUFFER = 25;
    public void display() {

        double minx = -1, miny = -1, maxx = 1, maxy = 1;

        for(Rocket rocket : rockets) {
            Vector2D pos = rocket.getState().getPosition();
            
            minx = Math.min(minx, pos.x);
            maxx = Math.max(maxx, pos.x);
            
            miny = Math.min(miny, pos.y);
            maxy = Math.max(maxy, pos.y);
        }

        if(maxx - minx > miny - maxy) { 
            miny = (miny + maxy) / 2 - (maxx - minx) / 2;
            maxy = (miny + maxy) / 2 + (maxx - minx) / 2;
        } else {
            minx = (minx + maxx) / 2 - (maxy - miny) / 2;
            maxx = (minx + maxx) / 2 + (maxy - miny) / 2;
        }

        StdDraw.setXscale(minx - BUFFER, maxx + BUFFER);
        StdDraw.setYscale(miny - BUFFER, maxy + BUFFER);

        StdDraw.enableDoubleBuffering();
        StdDraw.clear();

        StdDraw.setPenColor(Color.CYAN);
        StdDraw.filledRectangle(0, 200, 1000, 200);

        StdDraw.setPenColor(Color.GREEN);
        StdDraw.filledRectangle(0, -50, 1000, 50);

        StdDraw.setPenColor(Color.white);
        for(int i = 0; i < x_cloud.length; ++i) {
            StdDraw.filledCircle(x_cloud[i], y_cloud[i], r_cloud[i]);
        }

        for(Rocket rocket : rockets) {
            Vector2D pos = rocket.getState().getPosition();
            
            StdDraw.setPenColor(Color.BLACK);
            StdDraw.text(pos.x, pos.y + Constants.Rocket.HEIGHT, rocket.toString());

            if(rocket.getStatus() == RocketStatus.EXPLODED) {
                StdDraw.setPenRadius(0.1);
                StdDraw.setPenColor(Color.RED);
                line(pos, pos);
                StdDraw.setPenRadius(0.066);
                StdDraw.setPenColor(Color.YELLOW);
                line(pos, pos);
                StdDraw.setPenRadius(0.02);
                StdDraw.setPenColor(Color.WHITE);
                line(pos, pos);

            } else {
                Angle ang = rocket.getState().getAngle();
                Angle tang = (ang.add(Angle.k180deg)).add(rocket.getThrustAngle());
    
                Vector2D head = pos.add(ang.add(Angle.k90deg).getVector().mul(Constants.Rocket.HEIGHT/2));
                Vector2D tail = pos.sub(ang.add(Angle.k90deg).getVector().mul(Constants.Rocket.HEIGHT/2));
                Vector2D thrust = tail.add(tang.add(Angle.k90deg).getVector().mul(4 * rocket.getThrust()));
    
                StdDraw.setPenRadius(0.02);
                StdDraw.setPenColor(Color.BLACK);
                line(head, tail);
    
                StdDraw.setPenRadius(0.03);
                StdDraw.setPenColor(Color.BLUE);
                line(pos, pos);
    
                StdDraw.setPenRadius(0.01);
                StdDraw.setPenColor(Color.RED);
                line(tail, thrust);

            }
        }
        StdDraw.show();
    }

}