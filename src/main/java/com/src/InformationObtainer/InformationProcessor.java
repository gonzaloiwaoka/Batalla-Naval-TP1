package com.src.InformationObtainer;

import com.src.ConsoleCleaner.ClearConsole;
import com.src.entity.PlayerAction;
import com.src.entity.Position;

import java.util.Scanner;

public class InformationProcessor {

    private static final int MIN_ROW_COLUMN = 1;
    private static final int MAX_ROW_COLUMN = 5;
    private static final int MIN_ACTION = 1;
    private static final int MAX_ACTION = 2;

    private static final String CHARACTER_NO_VALID = "El caracter ingrasado no es valido.";
    private static final String ROW_NO_VALID = "La fila tiene que estar entre las posiciones del " + MIN_ROW_COLUMN + " al " + MAX_ROW_COLUMN;
    private static final String COlUMN_NO_VALID = "La columna tiene que estar entre las posiciones del " + MIN_ROW_COLUMN + " al " + MAX_ROW_COLUMN;
    private static final String ACTION_NO_VALID = "La accion ingresada no es valida tiene que ser: " + MIN_ACTION + " Ataque | " + MAX_ACTION + " Movimiento.";

    /**
     * Obtiene una fila, y una columna.
     */
    public static Position getRowColumn() {
        Position position = new Position();
        Scanner sc = new Scanner(System.in);
        String string;
        Integer value = null;

        do {
            System.out.print("Elija la fila (numero del 1 al 5) : ");
            string = sc.nextLine();
            if (isNumeric(string)) {
                value = Integer.valueOf(string);
                if (value < MIN_ROW_COLUMN || value > MAX_ROW_COLUMN) {
                    System.out.println(ROW_NO_VALID);
                }
            } else {
                System.out.println(CHARACTER_NO_VALID);
            }

        } while (!isNumeric(string) || value < MIN_ROW_COLUMN || value > MAX_ROW_COLUMN);

        position.setRow(value-1);

        do {
            System.out.print("Elija la columa (numero del 1 al 5) : ");
            string = sc.nextLine();

            if (isNumeric(string)) {
                value = Integer.valueOf(string);
                if (value < MIN_ROW_COLUMN || value > MAX_ROW_COLUMN) {
                    System.out.println(COlUMN_NO_VALID);
                }
            } else {
                System.out.println(CHARACTER_NO_VALID);
            }


        } while (!isNumeric(string) || (value < MIN_ROW_COLUMN || value > MAX_ROW_COLUMN));

        position.setColumn(value-1);

        return position;
    }

    public static PlayerAction getActionRowColumn() {
        PlayerAction playerAction = new PlayerAction();
        Scanner sc = new Scanner(System.in);
        String string;
        Integer value = null;

        do {
            System.out.print("Ingrese la accion (1: ataque | 2: movimiento): ");
            string = sc.nextLine();

            if (isNumeric(string)) {
                value = Integer.valueOf(string);
                if (value < MIN_ACTION || value > MAX_ACTION) {
                    System.out.println(ACTION_NO_VALID);
                }
            } else {
                System.out.println(CHARACTER_NO_VALID);
            }


        } while (!isNumeric (string) || value < MIN_ACTION || value > MAX_ACTION);

        playerAction.setAction(value);

        Position position = getRowColumn();

        playerAction.setRow(position.getRow());
        playerAction.setColumn(position.getColumn());

        return playerAction;
    }


    public static String getString() {
        Scanner scanner = new Scanner(System.in);
        String string = scanner.nextLine();


        ClearConsole.clearScreen();
        return string;
    }

    public static int getInt() {
        Scanner scanner = new Scanner(System.in);
        int value = scanner.nextInt();

        ClearConsole.clearScreen();
        return value;
    }

    public static void showStringAndLine(String string) {
        System.out.println(string);
    }

    public static void showString(String string) {
        System.out.println(string);
    }

    private static boolean isNumeric(String str){
        return str != null && str.matches("[0-9.]+");
    }
}
