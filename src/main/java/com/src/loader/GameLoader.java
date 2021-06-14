package com.src.loader;

import com.src.ConsoleCleaner.ClearConsole;
import com.src.InformationObtainer.InfoObtainer;
import com.src.entity.TypeUserIp;
import com.src.screens.*;

import java.util.Scanner;

public class GameLoader {

    private ScreenLoad screenLoad;
    private ScreenMain screenMain;
    private ScreenCreateGame screenCreateGame;
    private ScreenJoinGame screenJoinGame;

    public GameLoader(ScreenLoad screenLoad, ScreenMain screenMain, ScreenCreateGame screenCreateGame, ScreenJoinGame screenJoinGame) {
        this.screenLoad = screenLoad;
        this.screenMain = screenMain;
        this.screenCreateGame = screenCreateGame;
        this.screenJoinGame = screenJoinGame;
    }

    /**
     * esta flojo de papeles, cabe la posibilidad que ponga un try and catch para atrapar errores
     * @return
     */
    public TypeUserIp loadGame() {
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

    private TypeUserIp createGame() {
        screenCreateGame.showMessage();
        TypeUserIp typeUserIp = new TypeUserIp();

        String input = InfoObtainer.getString();

        if (input != "S") {
            System.out.print("Ingrese el puerto: ");
            typeUserIp.setPort(InfoObtainer.getInt());
            typeUserIp.setIp(input);
            typeUserIp.setTypeUser(true);
        }





        return typeUserIp;
    }

    public TypeUserIp joinGame() {
        screenJoinGame.showMessage();
        TypeUserIp typeUserIp = new TypeUserIp();

        String input = InfoObtainer.getString();

        if (input != "S") {
            System.out.print("Ingrese el puerto: ");
            typeUserIp.setPort(InfoObtainer.getInt());
            typeUserIp.setIp(input);
            typeUserIp.setTypeUser(true);
        }

        return typeUserIp;
    }

}
