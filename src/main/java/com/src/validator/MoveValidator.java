package com.src.validator;

import com.src.entity.Ninja;
import com.src.entity.Position;
import com.src.entity.Tile;
import com.src.enumarate.TerrainType;
import com.src.mapper.GridMapper;

import java.util.ArrayList;
import java.util.List;

public class MoveValidator {

    protected boolean isGeneralAlive(Ninja ninja) {
        return ninja.isGeneralAlive();
    }

    protected boolean movedLastRound(Ninja ninja) {
        return ninja.isMoved();
    }

    /**
     * Esta funcion se encarga de verificar, si el ninja se esta moviendo a mas de una tile de distancia
     * Lo hace primero llamando a una funcion llamada searchNinja, que va a devolver la posicion
     * del ninja y luego verificando si la resta entre la fila del ninja, y la fila objetivo
     * es >= -1 o <= 1, hace lo mismo para las columnas.
     */
    protected boolean movingOneTileAway(GridMapper gridMapper, Ninja ninja, Position posMove) {
        boolean success = false;
        Position posNinja = searchNinja(gridMapper, ninja);

        /* Reviso si la posicion de la columna es distinta a -1, dado que si
        * es -1 significa que no encontro al ninjaz|
        * */

        if (posNinja.getColumn() != -1) {
            if ((posNinja.getColumn() - posMove.getColumn() >= -1 && posNinja.getColumn() - posMove.getColumn() <= 1)
                    && (posNinja.getRow() - posMove.getRow() >= -1 && posNinja.getRow() - posMove.getRow() <= 1)) {
                success = true;
            }
        }

        return success;
    }

    protected boolean isTileWalkable(Tile allyTile) {

        return allyTile.getTerrain() == TerrainType.Walkable;
    }

    /**
     * Recibo el mapper aliado y el ninja, busco en este posicion por posicion, si se encuentra
     * en el, si es asi me guardo los valores donde se encuentra.
     * @return devuelvo una lista de ints, donde la primer posicion es la fila y la segunda la columna.
     */
    public Position searchNinja(GridMapper gridMapper ,Ninja ninja) {
        Position pos = new Position();

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (gridMapper.getGrid().get(i).get(j).getNinja() == ninja) {
                    pos.setRow(i);
                    pos.setColumn(j);
                }
            }
        }
        return pos;
    }

}
