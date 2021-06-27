package com.src.validator;

import com.src.entity.Tile;
import com.src.enumarate.TerrainType;
import com.src.errorManagement.ErrorFunctions;

public class AttackValidator {
    private boolean attackedNinja;
    private ErrorFunctions errorFunctions;
    public AttackValidator() {
        this.errorFunctions = new ErrorFunctions();
    }

    /**
     * Verifica si en la enemyTile, es valida:
     * Si no esta destruida
     * Si hay un ninja
     * @param enemyTile
     * @return
     */
    public boolean attackTerrain(Tile enemyTile) {
        boolean success = false;
        attackedNinja = false;
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
            attackedNinja = true;
            success = true;
        }

            return success;
    }

    public boolean isAttackedNinja() {
        return attackedNinja;
    }
}
