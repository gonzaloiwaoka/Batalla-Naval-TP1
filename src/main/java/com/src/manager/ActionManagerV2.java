package com.src.manager;

import com.src.entity.*;
import com.src.enumarate.TerrainType;
import com.src.errorManagement.Error;
import com.src.errorManagement.ErrorFunctions;
import com.src.mapper.GridMapper;
import com.src.validator.ActionValidator;
import com.src.validator.AttackValidator;
import com.src.validator.MoveValidator;

import javax.swing.*;
import java.util.List;

public class ActionManagerV2 {

    private ErrorFunctions errorFunctions;
    private ActionValidator actionValidator;

    public ActionManagerV2(ErrorFunctions errorFunctions, ActionValidator actionValidator) {
        this.errorFunctions = errorFunctions;
        this.actionValidator = actionValidator;
    }

    public boolean doAttack(PlayerAction playerAction, GridMapper enemyGrid, Ninja allyNinja) {
        errorFunctions.removeErrors();
        Tile enemyTile = enemyGrid.getTile(playerAction.getRow(), playerAction.getColumn());

        boolean success = actionValidator.attack(enemyTile);

        if (!success) {
            errorFunctions.addErrors(actionValidator.getErrorFunctions().getErrors());
        } else {
            allyNinja.setMoved(false);
        }

        return success;
    }

    public boolean validateMovement(GridMapper allyGrid, PlayerAction pA , Ninja ninja) {
        errorFunctions.removeErrors();
        boolean success = true;
        try {
            Position position = new Position();

            position.setRow(pA.getRow());
            position.setColumn(pA.getColumn());

            success = actionValidator.move(allyGrid, position, ninja);

            if (success) {
                doMovement(allyGrid, position, ninja);
                ninja.setMoved(true);
            } else {
                errorFunctions.addErrors(actionValidator.getErrorFunctions().getErrors());
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
            return success;
        }

    }

    private void doMovement(GridMapper allyGrid, Position newPosition, Ninja ninja) {
        Position oldPosition = actionValidator.getMoveValidator().searchNinja(allyGrid, ninja);

        Tile newTile = allyGrid.getTile(newPosition.getRow(), newPosition.getColumn());
        Tile oldTile = allyGrid.getTile(oldPosition.getRow(), oldPosition.getColumn());

        if (ninja.isGeneral()) {
            newTile.setTerrain(TerrainType.GeneralNinja);
        } else {
            newTile.setTerrain(TerrainType.NormalNinja);
        }

        newTile.setNinja(ninja);

        oldTile.setTerrain(TerrainType.Walkable);
        oldTile.setNinja(null);
    }

    public List<Error> getErrors() {
        return errorFunctions.getErrors();
    }

    public ActionValidator getActionValidator() {
        return actionValidator;
    }
}
