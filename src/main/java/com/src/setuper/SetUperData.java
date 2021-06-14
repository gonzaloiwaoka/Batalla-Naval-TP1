package com.src.setuper;

import com.src.entity.*;
import com.src.enumarate.TerrainType;
import com.src.errorManagement.ErrorFunctions;
import com.src.manager.ServerManager;

import java.io.IOException;
import java.util.Random;

public class SetUperData {
    private static final int HP_GENERAL = 2;
    private static final int HP_NORMAL_NINJA = 1;

    private ErrorFunctions errors;

    public SetUperData() {
        this.errors = new ErrorFunctions();
    }

    public void decideFirst(Player allyPlayer, Player enemyPlayer) {
        Random random = new Random();
        int randomNumber = random.nextInt();

        if (randomNumber % 2 == 0) {
            allyPlayer.setFirst(true);
        } else {
            enemyPlayer.setFirst(true);
        }
    }
    // Faltaria un poco tener una verificacion de que donde estoy poniendo al ninja no haya otro
    public void addNinjas(Player player, ServerManager serverManager) throws IOException {
        boolean success;
        DataClient dataClient = new DataClient();
        for (Ninja ninja : player.getNinjas()) {
            ninja.setGeneralAlive(true);
            do {
                Position position = serverManager.obtainPosition(player);

                if (player.isTileNotOccupied(position)) {
                    if (ninja.getHp() == HP_GENERAL) {
                        player.getPlayerGrid().addNinja(ninja, TerrainType.GeneralNinja, position);
                        dataClient.setTerrainType(TerrainType.GeneralNinja);
                    } else {
                        player.getPlayerGrid().addNinja(ninja, TerrainType.NormalNinja, position);
                        dataClient.setTerrainType(TerrainType.NormalNinja);
                    }
                    dataClient.setActionType(0);
                    dataClient.setNinja(ninja);
                    dataClient.setPosition(position);

                    serverManager.modifyValues(player, dataClient);

                    success = true;
                } else {
                    errors.removeErrors();
                    errors.addError("La posicion en la que se agrego el ninja ya estaba ocupada");
                    serverManager.sendErrors(player, errors.getErrors());
                    success = false;
                }
            } while (!success);

        }

    }

    /**
     * Se encarga de añadir un ninja, conociendo la fila y columna, se crea un nuevo ninja
     * con X cantidad de vida, se settea que el general esta vivo.
     * Accedo desde jugador al mapper, y a la lista de ninjas para agregarlos.
     * @param terrainType Va a ser el tipo de ninja (N para comandante y n para el normal)
     */




}
