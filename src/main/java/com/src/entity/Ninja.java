package com.src.entity;

public class Ninja {

    private int hp;
    private boolean moved;
    private boolean generalAlive;
    private boolean isGeneral;
    private Position position;

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public boolean isGeneral() {
        return isGeneral;
    }

    public Ninja(int hp, boolean isGeneral) {
        this.hp = hp;
        this.isGeneral = isGeneral;
    }

    public Ninja(int hp) {
        this.hp = hp;
        generalAlive = true;

    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public boolean isMoved() {
        return moved;
    }

    public void setMoved(boolean moved) {
        this.moved = moved;
    }

    public boolean isGeneralAlive() {
        return generalAlive;
    }

    public void setGeneralAlive(boolean generalAlive) {
        this.generalAlive = generalAlive;
    }
}
