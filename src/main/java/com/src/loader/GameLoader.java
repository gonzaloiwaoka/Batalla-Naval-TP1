package com.src.loader;

import com.src.ConsoleCleaner.ClearConsole;
import com.src.InformationObtainer.InformationProcessor;
import com.src.entity.TypeUserIp;
import com.src.screens.*;

import java.util.Scanner;

public class GameLoader {

    private ScreenLoad screenLoad;
    private ScreenMain screenMain;
    private ScreenCreateGame screenCreateGame;
    private ScreenJoinGame screenJoinGame;

    private static final int HOST_PORT = 2727;
    private static final int NO_HOST_PORT = 8000;

    public GameLoader(ScreenLoad screenLoad, ScreenMain screenMain, ScreenCreateGame screenCreateGame) {
        this.screenLoad = screenLoad;
        this.screenMain = screenMain;
        this.screenCreateGame = screenCreateGame;
    }

    /**
     * esta flojo de papeles, cabe la posibilidad que ponga un try and catch para atrapar errores
     * @return
     */
    public TypeUserIp loadGame() throws InterruptedException {
        boolean exit = false;
        Scanner scanner = new Scanner(System.in);

        screenLoad.showMessage();
        String input = scanner.nextLine();

        ClearConsole.clearScreen();

        screenMain.showMessage();
        input = scanner.nextLine();

        ClearConsole.clearScreen();

        TypeUserIp typeUserIp = new TypeUserIp();

        while (!exit && !input.contains("Q")) {
                switch (input) {
                    case "C":
                        typeUserIp = createGame();

                        if (typeUserIp.getIp() != null) {
                           typeUserIp.setTypeUser(true);
                            exit = true;
                        }

                        break;
                    case "J":
                        typeUserIp = joinGame();

                        if (typeUserIp.getIp() != null) {
                            typeUserIp.setTypeUser(false);
                            exit = true;
                        }
                        break;
                    case "Q":
                        break;
                    default:
                        System.out.println("La tecla presionada no es valida");
                        break;
                }
                if (!exit) {
                    ClearConsole.clearScreen();
                    screenMain.showMessage();
                    input = scanner.nextLine();
                }


            }

        return typeUserIp;
    }

    private TypeUserIp createGame() throws InterruptedException {
        screenCreateGame.showMessage();
        TypeUserIp typeUserIp = new TypeUserIp();

        String input = InformationProcessor.getString();

        if (input != "S") {
            typeUserIp.setPort(HOST_PORT);
            typeUserIp.setIp(input);
            typeUserIp.setTypeUser(true);
        }

        return typeUserIp;
    }

    public TypeUserIp joinGame() {
        screenCreateGame.showMessage();
        TypeUserIp typeUserIp = new TypeUserIp();

        String input = InformationProcessor.getString();

        if (input != "S") {
            typeUserIp.setPort(NO_HOST_PORT);
            typeUserIp.setIp(input);
            typeUserIp.setTypeUser(true);
        }

        return typeUserIp;
    }

}
