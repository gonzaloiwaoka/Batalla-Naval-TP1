package com.src.mapper;

import com.src.entity.Ninja;
import com.src.entity.Position;
import com.src.entity.Tile;
import com.src.enumarate.TerrainType;

import java.util.ArrayList;
import java.util.List;

public class GridMapper {

    private List<List<Tile>> grid;
    private static final int AMOUNT_ROW = 5;
    private static final int AMOUNT_COLUMN = 5;

    public void setGrid(List<List<Tile>> grid) {
        this.grid = grid;
    }

    public List<List<Tile>> getGrid() {
        if(grid == null) {
            grid = new ArrayList<List<Tile>>();
            createGrid();
        }
        return grid;
    }

    private void createGrid() {

        for (int i = 0; i < AMOUNT_COLUMN; i++) {
            List <Tile> row = new ArrayList<>();
            for (int j = 0; j < AMOUNT_ROW; j++) {

                Tile tile = new Tile();
                tile.setTerrain(TerrainType.Walkable);
                row.add(tile);
            }
            grid.add(i, row);
        }
    }

    public Tile getTile(int row, int column) {
        return getGrid().get(row).get(column);
    }

    public void addNinja(Ninja ninja, TerrainType terrainType , Position position) {
        getGrid().get(position.getRow()).get(position.getColumn()).setNinja(ninja);
        getGrid().get(position.getRow()).get(position.getColumn()).setTerrain(terrainType);
    }


}
