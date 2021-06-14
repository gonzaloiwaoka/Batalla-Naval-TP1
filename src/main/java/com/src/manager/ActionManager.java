package com.src.manager;

import com.src.entity.Ninja;
import com.src.entity.PlayerAction;
import com.src.entity.Tile;
import com.src.enumarate.TerrainType;
import com.src.errorManagement.ErrorFunctions;
import com.src.mapper.GridMapper;
import com.src.mapper.GridShower;
import com.src.validator.ActionValidator;

import java.util.List;


public class ActionManager {


    private ActionValidator ninjaValidator;
    private GridShower gridShower;
    private ErrorFunctions errorFunctions;

    private static final String ACTION_NO_VALID = "La accion que se ingreso no es valida";


    public ActionManager(ActionValidator ninjaValidator, GridShower gridShower, ErrorFunctions errorFunctions) {
        this.ninjaValidator = ninjaValidator;
        this.gridShower = gridShower;
        this.errorFunctions = errorFunctions;
    }
    /*
     Se encarga de validar la accion que el jugador esta por realizar en este turno.

    public boolean validateAction(PlayerAction pA, Ninja ninja , GridMapper allyGrid, GridMapper enemyGrid) {
        errorFunctions.removeErrors();
        boolean success = false;
        Tile enemyTile = obtainTile(enemyGrid, pA.getRow(), pA.getColumn());
        Tile allyTile = obtainTile(allyGrid, pA.getRow() , pA.getColumn());

        switch (pA.getAction()) {
            case 1:
                if (ninjaValidator.attack(enemyTile)) {
                    success = true;
                    ninja.setMoved(false);
                } else {
                    errorFunctions.addErrors(ninjaValidator.getErrorFunctions().getErrors());
                }
                break;
            case 2:
                if (ninjaValidator.move(allyGrid, pA.getRow(), pA.getColumn(), ninja)) {
                    moveNinja(allyGrid, ninja, pA.getRow(), pA.getColumn());
                    success = true;
                } else {
                    errorFunctions.addErrors(ninjaValidator.getErrorFunctions().getErrors());
                }
                break;
            default:
                errorFunctions.addError(ACTION_NO_VALID);
                success = false;
                break;
            }
        return success;
    }


    protected Tile obtainTile(GridMapper grid, int row, int column) {
        return grid.getTile(row, column);
    }

    public GridShower getGridShower() {
        return gridShower;
    }

    public void moveNinja(GridMapper gridMapper, Ninja ninja, int row, int column) {
        List<Integer> values;

        values = ninjaValidator.getMoveValidator().searchNinja(gridMapper, ninja);

        Tile oldTile = gridMapper.getTile(values.get(0), values.get(1)); // 0 filas | 1 columnas
        Tile newTile = gridMapper.getTile(row, column);

        if (oldTile.getTerrain() == TerrainType.GeneralNinja) {
            newTile.setTerrain(TerrainType.GeneralNinja);
        } else {
            newTile.setTerrain(TerrainType.NormalNinja);
        }

        newTile.setNinja(ninja);
        ninja.setMoved(true);

        oldTile.setTerrain(TerrainType.Walkable);
        oldTile.setNinja(null);
    }

    public ErrorFunctions getErrorFunctions() {
        return errorFunctions;
    }
    */
}
