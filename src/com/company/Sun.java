package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Sun extends JPanel {
    private PixelDrawer pd;

    private double rad1;
    private double rad2;
    private RealPoint center;
    private int count;

    public Sun(){
        rad1 = 50;
        rad2 = 100;
        center = new RealPoint(0,0);
        count = 50;
    }
    public Sun(PixelDrawer pd,double rad1,double rad2,RealPoint center,int count){
        this.pd = pd;
        this.rad1 = rad1;
        this.rad2 = rad2;
        this.center = center;
        this.count = count;
    }

    public BufferedImage drawSun(LineDrawer line,ScreenConverter sc,ArcDrawer arc,BufferedImage bi){
        for (int i =0;i<count;i++){
            double radian=Math.toRadians(360.0/count*i);
            ScreenPoint p1= sc.r2s(new RealPoint((center.getX()+rad1*Math.cos(radian)),center.getY()+rad1*Math.sin(radian)));
            ScreenPoint p2= sc.r2s(new RealPoint((center.getX()+rad2*Math.cos(radian)),center.getY()+rad2*Math.sin(radian)));
            line.drawLine(p1,p2);
        }
        arc.drawArc(bi,sc,center,2*rad1,2*rad1,0,360);
        return  bi;
    }




    public double getRad1() {
        return rad1;
    }

    public void setRad1(double rad1) {
        this.rad1 = rad1;
    }

    public double getRad2() {
        return rad2;
    }

    public void setRad2(double rad2) {
        this.rad2 = rad2;
    }

    public RealPoint getCenter() {
        return center;
    }

    public void setCenter(RealPoint center) {
        this.center = center;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
