package com.src.manager;

import com.src.InformationObtainer.InformationProcessor;
import com.src.entity.*;
import com.src.enumarate.TerrainType;
import com.src.factory.PlayerFactory;
import com.src.mapper.GridMapper;
import com.src.mapper.GridShower;

import javax.xml.crypto.Data;

public class ClientManager {

    private Player player;
    private GridShower gridShower;
    public ClientManager() {
        player = PlayerFactory.create();
        gridShower = new GridShower();
    }

    public void modifyNinja(DataClient dataClient) {
        // Position, Ninja
        Tile tile = getTile(player.getPlayerGrid(), dataClient.getPosition());
        tile.setNinja(dataClient.getNinja());

        modifyTerrain(tile, dataClient.getTerrainType());
    }

    public void attackedTerrain(DataClient dataClient) {
        Tile tile = getTile(player.getPlayerGrid(), dataClient.getPosition());
        if (tile.getNinja() != null && tile.getNinja().getHp() == 2) {
            tile.getNinja().setHp(tile.getNinja().getHp()-1);
        } else {
            modifyTerrain(tile, TerrainType.Destroyed);
            tile.setNinja(null);
        }
    }

    public void moveNinja(DataClient dataClient) {
        Position oldPos = dataClient.getNinja().getPosition();

        Tile oldTile = getTile(player.getPlayerGrid(), oldPos);
        Tile newTile = getTile(player.getPlayerGrid(), dataClient.getPosition());

        oldTile.setNinja(null);
        modifyTerrain(oldTile, TerrainType.Walkable);

        newTile.setNinja(dataClient.getNinja());
        modifyTerrain(newTile, dataClient.getTerrainType());
    }

    public void attackTerrain(DataClient dataClient) {
        Tile tile = getTile(player.getAttackGrid(), dataClient.getPosition());
        modifyTerrain(tile, dataClient.getTerrainType());
    }

    public void modifyTerrain(Tile tile, TerrainType terrainType) {
        tile.setTerrain(terrainType);
    }

    public void ShowGrid() {
        InformationProcessor.showStringAndLine("Grid jugador");
        gridShower.showGrid(player.getPlayerGrid());
        System.out.println("----------");
        InformationProcessor.showStringAndLine("Grid ataques");
        gridShower.showGrid(player.getAttackGrid());
    }

    public Tile getTile(GridMapper grid, Position pos){
        return grid.getTile(pos.getRow(), pos.getColumn());
    }

}
