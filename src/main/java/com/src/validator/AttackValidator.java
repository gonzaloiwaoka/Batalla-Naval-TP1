package com.src.validator;

import com.src.entity.Tile;
import com.src.enumarate.TerrainType;
import com.src.errorManagement.ErrorFunctions;

public class AttackValidator {

    private ErrorFunctions errorFunctions;
    public AttackValidator() {
        this.errorFunctions = new ErrorFunctions();
    }

    public boolean attackTerrain(Tile enemyTile) {
        boolean success = false;
        errorFunctions.removeErrors();

        if (enemyTile.getTerrain() == TerrainType.Walkable) {
            enemyTile.setTerrain(TerrainType.Destroyed);
            success = true;
        }

        if ((enemyTile.getTerrain() == TerrainType.NormalNinja || enemyTile.getTerrain() == TerrainType.GeneralNinja) && !success) {
            enemyTile.getNinja().setHp(enemyTile.getNinja().getHp() - 1);

            if (enemyTile.getNinja().getHp() == 0) {
                enemyTile.setTerrain(TerrainType.Destroyed);
                enemyTile.setNinja(null);
            }
            success = true;
        }

            return success;
    }

}
