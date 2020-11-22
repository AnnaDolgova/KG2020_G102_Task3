package com.company;

import java.awt.*;
import java.util.ArrayList;

public class Arc  implements ArcDrawer {
    private PixelDrawer pd;

    public Arc(PixelDrawer pd) {
        this.pd = pd;
    }



    public void drawArc(ScreenPoint p, int width, int heght, int startAngle, int arcAngle) {
        double pi =3.1415926535;
        int x;
        int y;
        double r;
        for (double i = startAngle;i<=arcAngle;i=i+0.001){
            r=(width*heght)/Math.sqrt(heght*heght*Math.cos(i*pi/180)*Math.cos(i*pi/180)+width*width*Math.sin(i*pi/180)*Math.sin(i*pi/180));
            x= p.getX()+(int)(Math.cos(i*pi/180)*r);
            y=p.getY()+(int)(Math.sin(i*pi/180)*r);
            pd.drawPixel(x,y,Color.RED);
        }
    }

    public void drawEllipse(int width,int heght,ScreenPoint p1){
        int x;
        int y;
        double rad;
        double pi =3.1415926535;
        for(int i=-heght;i<heght;i++){
            rad= (width * heght) / Math.sqrt(heght * heght * Math.cos(i * pi / 180) * Math.cos(i * pi / 180) + width * width * Math.sin(i * pi / 180) * Math.sin(i * pi / 180));
            DDALineDrawer line = new DDALineDrawer(pd);
            line.drawLine(new ScreenPoint(i+p1.getX(),(int)(-Math.sqrt(rad*rad-i*i))+p1.getY()),new ScreenPoint(i+p1.getX(),(int)(Math.sqrt(rad*rad-i*i))+p1.getY()));

        }
    }

}
