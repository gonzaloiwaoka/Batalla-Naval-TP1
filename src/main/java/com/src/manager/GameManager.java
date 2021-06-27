package com.src.manager;

import com.src.checker.DataChecker;
import com.src.entity.*;
import com.src.enumarate.TerrainType;
import com.src.errorManagement.ErrorFunctions;
import com.src.setuper.SetUperData;

import javax.xml.crypto.Data;
import java.io.IOException;

public class GameManager {
    private static final int GENERAL_HP = 2;
    private static final int ATTACK = 1;
    private static final int MOVE = 2;

    private static final int ATTACK_VALID = 1;
    private static final int MOVE_VALID = 2;
    private static final int ATTACK_ENEMY_GRID_VALID = 3;
    private static final int SHOW_GRID = 99;
    private static final int WAIT = 100;
    private SetUperData setUperData;
    private Player host;
    private Player noHost;
    private ServerManager serverManager;
    private ActionManager actionManager;
    private ErrorFunctions errorFunctions;
    private DataChecker dataChecker;

    public GameManager(SetUperData setUperData, Player host, Player noHost, ServerManager serverManager, ActionManager actionManager, ErrorFunctions errorFunctions, DataChecker dataChecker) {
        this.setUperData = setUperData;
        this.host = host;
        this.noHost = noHost;
        this.serverManager = serverManager;
        this.actionManager = actionManager;
        this.errorFunctions = errorFunctions;
        this.dataChecker = dataChecker;
    }

    /**
     * Hace un set-up del game antes de empezar:
     * Decide quien va primero
     * Añade ninjas al host
     * Añade ninjas al no-host
     * @throws IOException
     */
    public void setGame() throws IOException {
        setUperData.decideFirst(host, noHost);

        waitForTurn(noHost);
        setUperData.addNinjas(host, serverManager);
        waitForTurn(host);
        setUperData.addNinjas(noHost, serverManager);

        waitForTurn(noHost);
        waitForTurn(host);

    }

    /**
     * Se encarga de ejecutar el juego por turnos siempre y cuando los players tengan ninjas
     * @throws IOException
     */
    public void playGame() throws IOException {
        setGame();

        while (host.hasNinjas() && noHost.hasNinjas()) {
            if (host.isFirst()) {
                executeTurn(host, noHost);
                executeTurn(noHost, host);
            } else {
                executeTurn(noHost, host);
                executeTurn(host, noHost);}
        }

        if (host.getNinjas().isEmpty()) {
            serverManager.sendString(host, "Perdiste!");
            serverManager.sendString(noHost, "Ganaste!");
        } else {
            serverManager.sendString(noHost, "Perdiste!");
            serverManager.sendString(host, "Ganaste!");
        }

    }

    /**
     * Por cada ninja que el jugador actualmente tiene, se ejecuta un accion
     * se encarga de verificar si la accion fue valida, y si fue asi
     * hace cambios.
     * @param allyPlayer Es su turno
     * @param enemyPlayer Se utiliza en caso de que la accion sea un ataque
     * @throws IOException
     */
    private void executeTurn(Player allyPlayer, Player enemyPlayer) throws IOException {
        try {
            dataChecker.checkNinjas(allyPlayer);
            showGrid(allyPlayer);

            for (Ninja element : allyPlayer.getNinjas()){

                boolean success = false;
                DataClient dataClient = new DataClient();

                do {
                    element.setPosition(actionManager.getActionValidator().getMoveValidator().searchNinja(allyPlayer.getPlayerGrid(), element));
                    String turn = "Turno del ninja en la posicion: "  + (element.getPosition().getRow() + 1) + " " +(element.getPosition().getColumn() + 1);

                    PlayerAction playerAction = serverManager.obtainPlayerAction(allyPlayer, turn);

                    Position position = new Position();
                    position.setRow(playerAction.getRow());
                    position.setColumn(playerAction.getColumn());

                    if (playerAction.getAction() == ATTACK) {
                        success = actionManager.doAttack(playerAction, enemyPlayer.getPlayerGrid(), element);

                        dataClient = createDataClient(position, TerrainType.Destroyed, ATTACK_VALID);
                    }

                    if (playerAction.getAction() == MOVE) {
                        success = actionManager.validateMovement(allyPlayer.getPlayerGrid(), playerAction, element);

                        dataClient = createDataClient(position, allyPlayer.getPlayerGrid().getTile(playerAction.getRow(), playerAction.getColumn()).getTerrain(),
                                MOVE_VALID, element);
                    }

                    if (!success) {
                        errorFunctions.removeErrors();
                        errorFunctions.addErrors(actionManager.getErrors());

                        dataClient.setActionType(SHOW_GRID);

                        serverManager.modifyValues(allyPlayer, dataClient);
                        serverManager.sendErrors(allyPlayer, errorFunctions.getErrors());

                    } else {
                        serverManager.modifyValues(allyPlayer, dataClient);

                        if(dataClient.getActionType() == ATTACK_VALID) {
                            if (attackedNinja()) {
                                serverManager.sendString(allyPlayer, "Le pegaste a un ninja en la posicion: " + (position.getRow() + 1) + " " + (position.getColumn() + 1));
                            }
                            dataClient.setActionType(ATTACK_ENEMY_GRID_VALID);
                            serverManager.modifyValues(enemyPlayer, dataClient);
                        }
                    }

                } while(!success);
            }
            waitForTurn(allyPlayer);
        } catch (IOException ioException) {
            System.out.println(ioException.getMessage());
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    private void showGrid(Player player) throws IOException {
        DataClient dC = new DataClient();
        dC.setActionType(SHOW_GRID);
        serverManager.modifyValues(player, dC);
    }

    private void waitForTurn(Player player) throws IOException {
        DataClient dataClient = new DataClient();
        dataClient.setActionType(WAIT);
        serverManager.modifyValues(player, dataClient);
    }

    private DataClient createDataClient(Position position, TerrainType terrainType, int actionType) {
        DataClient dataClient = new DataClient();

        dataClient.setPosition(position);
        dataClient.setTerrainType(terrainType);
        dataClient.setActionType(actionType);

        return dataClient;
    }

    private DataClient createDataClient(Position position, TerrainType terrainType, int actionType, Ninja ninja) {
        DataClient dataClient = new DataClient();

        dataClient.setPosition(position);
        dataClient.setTerrainType(terrainType);
        dataClient.setActionType(actionType);
        dataClient.setNinja(ninja);

        return dataClient;
    }

    private boolean attackedNinja() {
        boolean success = false;

        if (actionManager.getActionValidator().getAttackValidator().isAttackedNinja()){
            success = true;
        }

        return success;
    }
}
