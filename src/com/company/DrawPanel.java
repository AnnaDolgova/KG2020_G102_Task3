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

    private ScreenConverter sc = new ScreenConverter(-300, 300, 600, 600, 600, 600);

    private Sun sun = new Sun();
    JTextField field = new JTextField("50");

    public DrawPanel() {
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
    }



    @Override
    public void paint(Graphics g) {

        this.setLayout(new FlowLayout());
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


        sun.setCount(Integer.parseInt(field.getText()));
        sun.setCount(Integer.parseInt(field.getText()));
        sun.drawSun(new DDALineDrawer(pd),sc,new Arc(pd),bi);

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


            if (count<=sun.getRad1()){

                if((length)>20){

                    sun.setRad2(sun.getRad2()-sun.getRad1());

                    sun.setRad1(length);

                    sun.setRad2(sun.getRad1()+sun.getRad2());

                }
                else sun.setRad1(20);//rad1=20;
            }

            else if (count<=sun.getRad2() && count>sun.getRad1()){
                if((length)>50 && sun.getRad2()>sun.getRad1()) sun.setRad2(length+sun.getRad1());
                else sun.setRad2(sun.getRad1()+50);

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
