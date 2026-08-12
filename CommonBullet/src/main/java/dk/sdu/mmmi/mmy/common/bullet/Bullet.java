package dk.sdu.mmmi.mmy.common.bullet;

import dk.sdu.mmmi.mmy.common.data.Entity;

public class Bullet extends Entity {

    private String ownerID;
    private int lifeTime = 90;

    public String getOwnerID() {
        return ownerID;
    }

    public void setOwnerID(String ownerID) {
        this.ownerID = ownerID;
    }

    public int getLifeTime() {
        return lifeTime;
    }

    public void setLifeTime(int lifeTime) {
        this.lifeTime = lifeTime;
    }
}
