package com.company;

import javax.swing.*;
import java.awt.*;

public class MainWindow extends JFrame {
    public DrawPanel dp;

    public MainWindow() throws HeadlessException {


        dp = new DrawPanel();

        this.add(dp);
    }

}
