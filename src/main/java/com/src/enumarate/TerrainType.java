package com.src.enumarate;

public enum TerrainType {
     Walkable(" ") , Destroyed("D"), NormalNinja("n"), GeneralNinja ("N");

    private String description;

    TerrainType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}


