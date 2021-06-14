package com.src.entity;

import com.src.enumarate.TerrainType;

public class Tile {

    private TerrainType terrain;
    private Ninja ninja;

    public TerrainType getTerrain() {
        return terrain;
    }

    public void setTerrain(TerrainType terrain) {
        this.terrain = terrain;
    }

    public Ninja getNinja() {
        return ninja;
    }

    public void setNinja(Ninja ninja) {
        this.ninja = ninja;
    }
}
