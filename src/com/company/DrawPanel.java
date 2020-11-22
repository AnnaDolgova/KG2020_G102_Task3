package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class DrawPanel extends JPanel implements MouseListener, MouseMotionListener {

    private ScreenConverter sc = new ScreenConverter(-400, 400, 800, 800, 800, 800);

    private int rad1=50;
    private int rad2=100;
    public int num;
    JTextField field = new JTextField("50");

    public DrawPanel() {
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
    }



    @Override
    public void paint(Graphics g) {

        sc.setsW(getWidth());
        sc.setsH(getHeight());
        BufferedImage bi = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_RGB);
        Graphics2D gr = bi.createGraphics();
        gr.setColor(Color.WHITE);
        gr.fillRect(0, 0, getWidth(), getHeight());
        gr.dispose();
        PixelDrawer pd = new BuffereImagePixelDrawer(bi);
        field.setPreferredSize(new Dimension(200, 70));
        field.setForeground(Color.BLACK);


        num= Integer.parseInt(field.getText());
        Arc cr = new Arc(pd);
        
        Ray rays = new Ray(pd);
        rays.drawRays(sc.r2s(new RealPoint(0,0)),num,rad1,rad2);
        cr.drawEllipse(rad1,rad1,sc.r2s(new RealPoint(0,0)));

        cr.drawArc(sc.r2s(new RealPoint(0,0)),rad1,rad1,0,360);

        g.drawImage(bi, 0, 0, null);
        this.add(field);
    }

    private RealPoint oldPoint = null;

    @Override
    public void mouseClicked(MouseEvent mouseEvent) {


    }

    @Override
    public void mousePressed(MouseEvent mouseEvent) {
        oldPoint = sc.s2r(new ScreenPoint(mouseEvent.getX(), mouseEvent.getY()));
    }

    @Override
    public void mouseReleased(MouseEvent mouseEvent) {
        oldPoint = null;
    }

    @Override
    public void mouseEntered(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseExited(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseDragged(MouseEvent mouseEvent) {
        RealPoint newPoint = sc.s2r(new ScreenPoint(mouseEvent.getX(), mouseEvent.getY()));
        double dx = newPoint.getX() - oldPoint.getX();
        double dy = newPoint.getY() - oldPoint.getY();
        double x= oldPoint.getX();
        double y= oldPoint.getY();
        ScreenPoint center = new ScreenPoint(0,0);
        double count = Math.sqrt(Math.pow(center.getX()-oldPoint.getX(),2)+Math.pow(center.getY()-oldPoint.getY(),2));
        double length=Math.sqrt(dx*dx+dy*dy);
        if (oldPoint != null){


            if (count<=rad1){
                if((length*10)>20){

                    rad2=rad2-rad1;
                    rad1=(int)(length*10);
                    rad2=rad1+rad2;
                }
                else rad1=20;
            }
            else if (count<=rad2 && count>rad1){
                if((length*10)>50 && rad2>rad1+30) rad2=(int)(length*10)+rad1;
                else rad2=rad1+50;

            }
            else {
                sc.setrX(sc.getrX() - dx);
                sc.setrY(sc.getrY() - dy);
            }
            repaint();
        }
    }

    @Override
    public void mouseMoved(MouseEvent mouseEvent) {

    }
}
