package com.src.validator;

import com.src.entity.Ninja;
import com.src.entity.Position;
import com.src.entity.Tile;
import com.src.errorManagement.ErrorFunctions;
import com.src.mapper.GridMapper;

public class ActionValidator {
    private AttackValidator attackValidator;
    private MoveValidator moveValidator;
    private ErrorFunctions errorFunctions;

    private final static String TILE_ATTACK_IS_NO_VALID = "La tile a la que estas tratando de atacar no es valida";

    private final static String GENERAL_IS_NOT_ALIVE = "No te podes mover por que el general no esta vivo";
    private final static String TILE_MOVEMENT_IS_NO_VALID = "La tile a la que tratas moverte, no tiene un terreno valido";
    private final static String MOVING_MORE_THAN_ONE_TILE_AWAY = "Te estas tratando de mover a mas de 1 tile de distancia";
    private final static String NINJA_ALREADY_MOVED = "El ninja ya se movio el ultimo turno, tenes que atacar en este!";

    public MoveValidator getMoveValidator() {
        return moveValidator;
    }

    public ActionValidator(AttackValidator attackValidator, MoveValidator moveValidator, ErrorFunctions errorFunctions) {
        this.attackValidator = attackValidator;
        this.moveValidator = moveValidator;
        this.errorFunctions = errorFunctions;
    }

    public boolean attack(Tile enemyTile) {
        errorFunctions.removeErrors();
        boolean success;

        success = attackValidator.attackTerrain(enemyTile);

        if (!success) {
            errorFunctions.addError(TILE_ATTACK_IS_NO_VALID);
        }

        return success;
    }


    public boolean move(GridMapper gridMapper, Position pos, Ninja ninja) {
        errorFunctions.removeErrors();
        boolean success = true;

        if (!moveValidator.isGeneralAlive(ninja)) {
            errorFunctions.addError(GENERAL_IS_NOT_ALIVE);
            success = false;
        }

        if (!moveValidator.isTileWalkable(gridMapper.getTile(pos.getRow(), pos.getColumn()))) {
            errorFunctions.addError(TILE_MOVEMENT_IS_NO_VALID);
            success = false;
        }

        if (!moveValidator.movingOneTileAway(gridMapper, ninja, pos)) {
            errorFunctions.addError(MOVING_MORE_THAN_ONE_TILE_AWAY);
            success = false;
        }

        if (moveValidator.movedLastRound(ninja)) {
            errorFunctions.addError(NINJA_ALREADY_MOVED);
            success = false;
        }

        return success;
    }

    public ErrorFunctions getErrorFunctions() {
        return errorFunctions;
    }

    public AttackValidator getAttackValidator() {
        return attackValidator;
    }
}
