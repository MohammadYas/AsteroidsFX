package dk.sdu.mmmi.mmy.common.data;

import java.io.Serializable;
import java.util.UUID;

public class Entity implements Serializable {

    private final UUID id = UUID.randomUUID();

    private double[] polygonCoordinates;
    private double x;
    private double y;
    private double rotation;
    private double radius = 8;
    private int hitPoints = 1;

    public String getID() {
        return id.toString();
    }

    public double[] getPolygonCoordinates() {
        return polygonCoordinates;
    }

    public void setPolygonCoordinates(double... coordinates) {
        this.polygonCoordinates = coordinates;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getRotation() {
        return rotation;
    }

    public void setRotation(double rotation) {
        this.rotation = rotation;
    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }

    public int getHitPoints() {
        return hitPoints;
    }

    public void setHitPoints(int hitPoints) {
        this.hitPoints = hitPoints;
    }

    public boolean takeDamage() {
        hitPoints--;
        return hitPoints <= 0;
    }
}
