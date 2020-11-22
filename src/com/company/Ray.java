package com.company;


import java.awt.*;
import java.util.ArrayList;

public class Ray {
    private PixelDrawer pd;

    public Ray(PixelDrawer pd) {
        this.pd = pd;
    }



    public void drawRays(ScreenPoint p1,int count,int rad1,int rad2){
        for (int i =0;i<count;i++){
            DDALineDrawer ray = new DDALineDrawer(pd);
            ray.drawLine(new ScreenPoint((int)(p1.getX()+rad1*Math.cos(360/count*i)),(int)(p1.getY()+rad1*Math.sin(360/count*i))),new ScreenPoint((int)(p1.getX()+rad2*Math.cos(360/count*i)),(int)(p1.getY()+rad2*Math.sin(360/count*i)) ));
        }
    }

}
