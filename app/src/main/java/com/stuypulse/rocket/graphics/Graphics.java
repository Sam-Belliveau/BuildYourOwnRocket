package com.stuypulse.rocket.graphics;

import com.stuypulse.stuylib.math.Vector2D;
import com.stuypulse.stuylib.math.Angle;

import java.awt.Color;

import com.stuypulse.rocket.physics.Constants;
import com.stuypulse.rocket.rockets.outline.Rocket;
import com.stuypulse.rocket.rockets.outline.RocketStatus;

public class Graphics {

    private Rocket[] rockets;
    
    public Graphics(Rocket[] r) {
        rockets = r;
        
        StdDraw.setXscale(-25, 25);
        StdDraw.setYscale(-5, 45);
    
    }

    public void line(Vector2D a, Vector2D b) {
        StdDraw.line(a.x,a.y,b.x,b.y);
    }

    public void display() {
        StdDraw.enableDoubleBuffering();
        StdDraw.clear();

        StdDraw.setPenRadius(0.01);
        StdDraw.setPenColor(Color.BLACK);
        line(new Vector2D(-50, 0), new Vector2D(50, 0));

        for(Rocket rocket : rockets) {
            Vector2D pos = rocket.getState().getPosition();
            StdDraw.text(pos.x, pos.y + Constants.Rocket.HEIGHT, rocket.toString());

            if(rocket.getStatus() == RocketStatus.EXPLODED) {
                StdDraw.setPenRadius(0.1);
                StdDraw.setPenColor(Color.RED);
                line(pos, pos);
                StdDraw.setPenRadius(0.066);
                StdDraw.setPenColor(Color.YELLOW);
                line(pos, pos);
                StdDraw.setPenRadius(0.025);
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
    
                StdDraw.setPenRadius(0.02);
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