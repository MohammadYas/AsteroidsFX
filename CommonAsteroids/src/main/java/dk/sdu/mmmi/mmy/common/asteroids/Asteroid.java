package dk.sdu.mmmi.mmy.common.asteroids;

import dk.sdu.mmmi.mmy.common.data.Entity;

public class Asteroid extends Entity {

    private double speed = 1;

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }
}
