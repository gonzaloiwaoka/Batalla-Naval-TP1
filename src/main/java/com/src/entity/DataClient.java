package com.src.entity;

import com.src.enumarate.TerrainType;

public class DataClient {

    private Position position;
    private Ninja ninja;
    private TerrainType terrainType;
    private int actionType;

    public int getActionType() {
        return actionType;
    }

    public void setActionType(int actionType) {
        this.actionType = actionType;
    }

    public TerrainType getTerrainType() {
        return terrainType;
    }

    public void setTerrainType(TerrainType terrainType) {
        this.terrainType = terrainType;
    }

    public int getRow() {
        return getPosition().getRow();
    }

    public int getColumn() {
        return getPosition().getColumn();
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public Ninja getNinja() {
        return ninja;
    }

    public void setNinja(Ninja ninja) {
        this.ninja = ninja;
    }
}
