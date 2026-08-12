package dk.sdu.mmmi.mmy.enemysystem;

import dk.sdu.mmmi.mmy.common.data.Entity;

public class Enemy extends Entity {

    private double speed = 1.2;

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }
}
