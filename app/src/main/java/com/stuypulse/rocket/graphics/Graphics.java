package com.stuypulse.rocket.graphics;

import com.stuypulse.stuylib.math.Vector2D;
import com.stuypulse.stuylib.math.Angle;
import com.stuypulse.stuylib.streams.filters.*;

import java.awt.Color;

import com.stuypulse.rocket.physics.Constants;
import com.stuypulse.rocket.rocket.Rocket;
import com.stuypulse.rocket.rocket.RocketState;
import com.stuypulse.rocket.rocket.RocketStatus;

public class Graphics {

    private Rocket[] rockets;
    
    private double[] x_cloud = new double[256];
    private double[] y_cloud = new double[x_cloud.length];
    private double[] r_cloud = new double[x_cloud.length];

    private IFilter makeFilter() {
        IFilter[] filter = new IFilter[16];
        for(int i = 0; i < filter.length; ++i) {
            filter[i] = new LowPassFilter(0.01);
        }
        return new IFilterGroup(filter);
    }

    private IFilter minxf = makeFilter();
    private IFilter maxxf = makeFilter();
    private IFilter minyf = makeFilter();
    private IFilter maxyf = makeFilter();
    

    public Graphics(Rocket[] r) {
        rockets = r;
        StdDraw.setCanvasSize(1024,1024);
    
        for(int i = 0; i < x_cloud.length; ++i) {
            x_cloud[i] = 1000 * (Math.random() - 0.5);
            y_cloud[i] = 10 + 500 * Math.random();
            r_cloud[i] = (Math.random() + 0.2) * 3;
        }
    }

    public void line(Vector2D a, Vector2D b) {
        StdDraw.line(a.x,a.y,b.x,b.y);
    }

    private final double BUFFER = 20;
    public void display() {

        double minx = -1, miny = -1, maxx = 1, maxy = 1;

        for(Rocket rocket : rockets) {
            Vector2D pos = rocket.getState().getPosition();
            
            if(rocket.getStatus() != RocketStatus.EXPLODED) {
                minx = Math.min(minx, pos.x);
                maxx = Math.max(maxx, pos.x);
                
                miny = Math.min(miny, pos.y);
                maxy = Math.max(maxy, pos.y);
            }
        }

        if(maxx - minx > maxy - miny) { 
            miny = (miny + maxy) / 2 - (maxx - minx) / 2;
            maxy = (miny + maxy) / 2 + (maxx - minx) / 2;
        } else {
            minx = (minx + maxx) / 2 - (maxy - miny) / 2;
            maxx = (minx + maxx) / 2 + (maxy - miny) / 2;
        }

        StdDraw.setXscale(minxf.get(minx - BUFFER), maxxf.get(maxx + BUFFER));
        StdDraw.setYscale(minyf.get(miny - BUFFER), maxyf.get(maxy + BUFFER));

        StdDraw.enableDoubleBuffering();
        StdDraw.clear();


        StdDraw.setPenColor(Color.CYAN);
        StdDraw.filledRectangle(0, 1000000, 1000000, 1000000);

        StdDraw.setPenColor(Color.GREEN);
        StdDraw.filledRectangle(0, -1000000, 1000000, 1000000);

        StdDraw.setPenColor(Color.white);
        for(int i = 0; i < x_cloud.length; ++i) {
            StdDraw.filledCircle(x_cloud[i], y_cloud[i], r_cloud[i]);
        }

        StdDraw.setPenColor(Color.RED);
        for(double y = 100; y <= 1000; y += 100) {
            StdDraw.filledRectangle(0, y, 1000000, 0.2);

            StdDraw.text(0, y + 5, "Y = " + y);
        }

        double xspacing = Constants.Rocket.DISTANCE * Math.ceil((maxx - miny) / 256);
        for(double x = -100 * xspacing; x <= 200 * xspacing; x += xspacing) {
            StdDraw.filledRectangle(x, -3, 0.2, 3);
            StdDraw.text(x, -8, "X = " + x);
        }

        double textHeight = 1;
        for(Rocket rocket : rockets) {
            Vector2D pos = rocket.getState().getPosition();
            Angle ang = rocket.getState().getAngle();
            Angle tang = (ang.add(Angle.k180deg)).add(rocket.getThrustAngle());

            Vector2D head = pos;
            Vector2D tail = pos.sub(ang.add(Angle.k90deg).getVector().mul(Constants.Rocket.HEIGHT));
            Vector2D thrust = tail.add(tang.add(Angle.k90deg).getVector().mul(Constants.Rocket.THRUSTER_LENGTH * rocket.getThrust()));

            if(rocket.getStatus() == RocketStatus.EXPLODED) {
                StdDraw.setPenRadius(0.2);
                StdDraw.setPenColor(Color.RED);
                line(pos, pos);
                StdDraw.setPenRadius(0.1);
                StdDraw.setPenColor(Color.YELLOW);
                line(pos, pos);
                StdDraw.setPenRadius(0.05);
                StdDraw.setPenColor(Color.WHITE);
                line(pos, pos);

                Vector2D text = head.add(tail).mul(0.5).add(new Vector2D(0, 6));
                StdDraw.setPenColor(Color.BLACK);
                StdDraw.text(text.x, text.y, rocket.toString(), 0);
            } else {
                StdDraw.setPenRadius(Constants.Rocket.THRUSTER_THICKNESS);
                StdDraw.setPenColor(Color.RED);
                line(tail, thrust);

                StdDraw.setPenColor(rocket.getColor());

                StdDraw.setPenRadius(Constants.Rocket.THICKNESS);
                line(head, tail);
    
                StdDraw.setPenRadius(Constants.Rocket.HEAD_THICKNESS);
                line(pos, pos);

                Vector2D text = head.add(tail).mul(0.5).add(ang.getVector().mul(3));
                StdDraw.text(text.x, text.y, rocket.toString(), (ang.add(Angle.k90deg).toDegrees(90) + 90) % 180 - 90);
            }
        }
        StdDraw.show();
    }

}