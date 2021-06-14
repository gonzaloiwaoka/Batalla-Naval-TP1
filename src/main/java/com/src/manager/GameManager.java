package com.src.manager;

import com.src.InformationObtainer.InfoObtainer;
import com.src.checker.DataChecker;
import com.src.entity.Ninja;
import com.src.entity.Player;
import com.src.entity.PlayerAction;
import com.src.errorManagement.ErrorFunctions;
import com.src.factory.ActionManagerFactory;
import com.src.factory.PlayerFactory;
import com.src.manager.ActionManager;
import com.src.setuper.SetUperData;

import java.util.List;

public class GameManager {

    private SetUperData setUper;
    private DataChecker dataChecker;
    private ErrorFunctions errorFunctions;
    private ActionManager actionManager;

    private Player player1;
    private Player player2;

    public GameManager(SetUperData setUper, DataChecker dataChecker, ErrorFunctions errorFunctions) {
        this.setUper = setUper;
        this.dataChecker = dataChecker;
        this.errorFunctions = errorFunctions;
        this.actionManager = ActionManagerFactory.create();
    }

    /*
    public void executeGame() {
        setUpData();
        while (playerHasNinjas(player1) && playerHasNinjas(player2)) {

            if (player1.isFirst()) {
                System.out.println("player1");
                executeActions(player1, player2);
                executeActions(player2, player1);
            } else {
                System.out.println("player2");
                executeActions(player2, player1);
                executeActions(player1, player2);
            }
        }
    }


    /**
     * Por cada turno se van a ejecutar, distintas acciones, tantas como ninjas tenga el player.
     * Si en el algun momento se realiza una accion que no es valida, se va a tomar de nuevo datos
     * y se va a volver a verificar.

    private void executeActions(Player allyPlayer, Player enemyPlayer) {
        try {
            checkNinjas(allyPlayer);
            for (Ninja element : allyPlayer.getNinjas()) {
                // Podria agregar algo que muestre que ninja es el que se esta jugando
                boolean success;
                do {
                    PlayerAction playerAction = getInfo();
                    success = actionManager.validateAction(playerAction, element, allyPlayer.getGridMapper(), enemyPlayer.getGridMapper());
                    // podria hacer en otro lugar no se
                    errorFunctions.removeErrors();
                    errorFunctions.addErrors(actionManager.getErrorFunctions().getErrors());
                    errorFunctions.showErrors();
                } while (!success);
            }

            actionManager.getGridShower().showGrid(player1.getGridMapper());
            System.out.println("--------------------");
            actionManager.getGridShower().showGrid(player2.getGridMapper());

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

    }

    private boolean playerHasNinjas(Player player) {
        return !player.getNinjas().isEmpty();
    }


    /**
     * Se encarga de obtener la accion, fila y columna y guardarla

    private PlayerAction getInfo() {
        return InfoObtainer.getActionRowColumn();
    }

    /**
     * Un poco dudoso si esto deberia ir aca, pero es una funcion que se encarga de hacer de
     * pre-cargar datos necesarios para el funcionamiento del juego.

    private void setUpData() {
        player1 = PlayerFactory.create();
        player2 = PlayerFactory.create();
        setUper.decideFirst(player1, player2);
        setUper.addNinjas(player1);
        setUper.addNinjas(player2);
    }

    /**
     * Justo antes de entrar a ejecutar las acciones del turno del jugador
     * se checkea que existan ninjas vivos, y si el general esta vivo.

    private void checkNinjas(Player player) {
        dataChecker.ninjasAlive(player);
        dataChecker.generalAlive(player);
    }
    */
}
