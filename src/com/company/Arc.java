package com.company;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Arc  implements ArcDrawer {
    private PixelDrawer pd;

    public Arc(PixelDrawer pd) {
        this.pd = pd;
    }


    public BufferedImage drawArc(BufferedImage bi,ScreenConverter sc,RealPoint center, double width, double heght, int startAngle, int arcAngle){
        ScreenPoint p1=sc.r2s(new RealPoint(center.getX()-width/2,center.getY()+width/2));
        Graphics g = bi.getGraphics();
        g.setColor(Color.RED);
        g.fillArc(p1.getX(), p1.getY(), (int)(2*width/2),(int)(2*width/2),0,360);
        return bi;
    }

}
