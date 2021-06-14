package com.src.entity;

import com.src.mapper.GridMapper;

import java.util.ArrayList;
import java.util.List;

public class Player {

    private List<Ninja> ninjas;
    private boolean isFirst;
    private boolean isHost;
    private GridMapper playerGrid;
    private GridMapper attackGrid;
    private String ipPort;



    public boolean isTileNotOccupied(Position position) {
        return playerGrid.getTile(position.getRow(), position.getColumn()).getNinja() == null;
    }

    public GridMapper getAttackGrid() {
        return attackGrid;
    }

    public Player(GridMapper playerGrid) {
        this.playerGrid = playerGrid;
        this.attackGrid = new GridMapper();
    }

    public void addNinja(Ninja ninja) {
        getNinjas().add(ninja);
    }

    public List<Ninja> getNinjas() {
        if (ninjas == null) {
            ninjas = new ArrayList<>();
        }

        return ninjas;
    }

    public boolean isFirst() {
        return isFirst;
    }

    public void setFirst(boolean first) {
        isFirst = first;
    }

    public GridMapper getPlayerGrid() {
        return playerGrid;
    }

    public boolean isHost() {
        return isHost;
    }

    public void setHost(boolean host) {
        isHost = host;
    }

    public boolean hasNinjas() {
        return !getNinjas().isEmpty();
    }

    public String getIpPort() {
        return ipPort;
    }

    public void setIpPort(String ipPort) {
        this.ipPort = ipPort;
    }
}
