/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gologolo.data;

import gologolo.GoLogoLoApp;
import jtps.jTPS_Transaction;

/**
 *
 * @author akillhalimi
 */
public class ChangeSizeCircle_Transaction implements jTPS_Transaction {
    GoLogoLoApp app;
    double newx;
    double newy;
    LogoCircle circle;
    double oldx;
    double oldy;
    public ChangeSizeCircle_Transaction(double x, double y,GoLogoLoApp app, LogoCircle circle) {
        newx = x;
        newy =y;
        this.app=app;
        this.circle=circle;
        oldx = circle.getTranslateX();
        oldy = circle.getTranslateY();
    }

    @Override
    public void doTransaction() {
        circle.setRadius(newx);
    }

    @Override
    public void undoTransaction() {
        circle.setRadius(oldx);
    }
}
