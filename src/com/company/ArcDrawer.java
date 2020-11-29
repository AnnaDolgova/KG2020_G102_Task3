package com.company;

import java.awt.*;
import java.awt.image.BufferedImage;

public interface ArcDrawer {
    BufferedImage drawArc(BufferedImage bi,ScreenConverter sc,RealPoint center, double width, double heght, int startAngle, int arcAngle);

}
