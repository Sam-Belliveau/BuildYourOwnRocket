package com.stuypulse.rocket.rocket.rockets;

import java.awt.Color;
import com.stuypulse.rocket.rocket.Rocket;
import com.stuypulse.rocket.rocket.RocketState;

/**
 * This is my shot at a robot that hovers around y = 100
 * 
 * The reason the code looks like this is so that you guys dont steal it.
 * This is a pretty good example of what the rocket should do, but I don't
 * want you to copy it.
 * 
 * @author Sam Belliveau (sam.belliveau@gmail.com)
 */
public class SteadyBoi extends Rocket {
    public SteadyBoi(){}public String getAuthor(){return "Sam";}double _P501=0.324523,_P502=7.165224,
    _P503=1.152612,_P504=3.257124,_P505=7.165224,_P506=6.26584,_P507=8.37268,_P508=35.52356,_P509=
    -1999.257124,_P510=1,_P511=0.01,_P512=0.01,_P513=1,_P516=-23.2356436,_P519=-23.2356436;RocketState 
    _R952;public void F_T21(){if(_P516<_P506){_P509=_P507;}else{_P509=_P511;}if(_P509<_P506){_P519=
    _R952.getAngle().sin();}else{_R952=getState();}}public void F_Q96(){if(_P506<_P507){_P509=_P505;}
    else{_P509=_P502;}if(_P509<_P506){_P501=100;_P508=_R952.getPosition().y;}else{_P508=_R952.getVelocity()
    .y;}}public void F_U89(){if(_P501<_P512){_P501=_P511;}if(_P501>_P510){_P501=_P513;}setThrust(_P501);}
    public void F_U90(){setThrustAngle(Math.signum(_P519)*_P511);}protected void execute(){_P516=_P511;
    F_T21();_P505=_P504;F_Q96();_P501-=_P508;_P516=_P511;F_T21();_P505=_P502;F_Q96();_P501-=_P503*_P508;
    F_U89();_P516=_P507;F_T21();F_U90();}public Color getColor() {return Color.magenta;}
}