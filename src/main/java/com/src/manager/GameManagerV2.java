package com.src.manager;

import com.src.checker.DataChecker;
import com.src.entity.*;
import com.src.enumarate.TerrainType;
import com.src.errorManagement.ErrorFunctions;
import com.src.setuper.SetUperData;
import com.src.validator.ActionValidator;

import javax.xml.crypto.Data;
import java.io.IOException;

public class GameManagerV2 {
    private static final int GENERAL_HP = 2;
    private static final int ATTACK = 1;
    private static final int MOVE = 2;

    private SetUperData setUperData;
    private Player host;
    private Player noHost;
    private ServerManager serverManager;
    private ActionManagerV2 actionManager;
    private ErrorFunctions errorFunctions;
    private DataChecker dataChecker;
    public GameManagerV2(Player host, Player noHost) {
        this.setUperData = new SetUperData();
        this.serverManager = new ServerManager();
        actionManager = new ActionManagerV2(new ErrorFunctions(), new ActionValidator());
        this.host = host;
        this.noHost = noHost;
        this.errorFunctions = new ErrorFunctions();
        this.dataChecker = new DataChecker();
    }

    public void setGame() throws IOException {
        setUperData.decideFirst(host, noHost);

        setUperData.addNinjas(host, serverManager);
        setUperData.addNinjas(noHost, serverManager);

        // Funcion que se encargue de llamado para pedir datos al host
    }

    public void playGame() throws IOException {
        setGame();

        while (host.hasNinjas() && noHost.hasNinjas()) {
            if (host.isHost()) {
                executeTurn(host, noHost);
                executeTurn(noHost, host);
            } else {
                executeTurn(noHost, host);
                executeTurn(host, noHost);}
        }
    }

    private void executeTurn(Player allyPlayer, Player enemyPlayer) throws IOException {
        dataChecker.checkNinjas(allyPlayer);

        DataClient dC = new DataClient();
        dC.setActionType(99);
        serverManager.modifyValues(allyPlayer, dC);

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
                    dataClient.setPosition(position);
                    dataClient.setTerrainType(TerrainType.Destroyed);
                    dataClient.setActionType(1);
                }

                if (playerAction.getAction() == MOVE) {
                    success = actionManager.validateMovement(allyPlayer.getPlayerGrid(), playerAction, element);
                    dataClient.setNinja(element);
                    dataClient.setTerrainType(allyPlayer.getPlayerGrid().getTile(playerAction.getRow(), playerAction.getColumn()).getTerrain());
                    dataClient.setPosition(position);
                    dataClient.setActionType(2);
                }

                if (!success) {
                    errorFunctions.removeErrors();
                    errorFunctions.addErrors(actionManager.getErrors());
                    dataClient.setActionType(99);
                    serverManager.modifyValues(allyPlayer, dataClient);
                    serverManager.sendErrors(allyPlayer, errorFunctions.getErrors());
                } else {
                    serverManager.modifyValues(allyPlayer, dataClient);

                    if(dataClient.getActionType() == 1) {
                        dataClient.setActionType(3);
                        serverManager.modifyValues(enemyPlayer, dataClient);
                    }
                }

            } while(!success);

        }
        DataClient dataClient = new DataClient();
        dataClient.setActionType(100);
        serverManager.modifyValues(allyPlayer, dataClient);

    }

}
