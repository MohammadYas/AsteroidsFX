package dk.sdu.mmmi.mmy.playersystem;

import dk.sdu.mmmi.mmy.common.data.Entity;

public class Player extends Entity {

    private double dx;
    private double dy;

    public double getDx() {
        return dx;
    }

    public void setDx(double dx) {
        this.dx = dx;
    }

    public double getDy() {
        return dy;
    }

    public void setDy(double dy) {
        this.dy = dy;
    }
}
