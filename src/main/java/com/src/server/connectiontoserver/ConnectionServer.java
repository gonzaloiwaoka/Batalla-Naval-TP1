package com.src.server.connectiontoserver;

import com.src.InformationObtainer.InformationProcessor;
import com.src.entity.Player;
import com.src.manager.ServerManager;

import java.io.IOException;
import java.util.Scanner;

public class ConnectionServer {

    private static final int INVITE = 0;
    private static final int WAIT = 1;
    private static final int JOIN = 2;
    private static int action;
    private static String ipPort;

    public static String hostDecideWhatToDo(ServerManager serverManager, Player noHost) throws IOException, InterruptedException {
        String newIpPort = null;
        InformationProcessor.showStringAndLine("Quiere invitar a alguien (0) o esperar a que se una (1): ");
        action = InformationProcessor.getInt();

        if (action == INVITE) {
            InformationProcessor.showStringAndLine("Ingrese la ip y el port de la persona que quieras invitar (port = 8000) separados por un ':' : ");
            ipPort = InformationProcessor.getString();
            newIpPort = inviteClient(serverManager);
            noHost.setIpPort(newIpPort);
        }

        if (action == WAIT) {

            while (noHost.getIpPort() == null) {
                Thread.sleep(1000);
            }
            System.out.println("Alguien se te unio!");
        }

        return newIpPort;
    }

    public static void noHostDecideWhatToDo(ServerManager serverManager, Player noHost) {
        //no instance

        try {
            InformationProcessor.showStringAndLine("Quiere esperar a que lo inviten (1) o unirse (2): ");
            action = InformationProcessor.getInt();
            String response = "NO";

            if (action == JOIN) {
                InformationProcessor.showStringAndLine("Ingrese la ip y el port de la persona a la que quieras unirte (port = 2727) separados por un ':' : ");
                ipPort = InformationProcessor.getString();
                response = joinHost(serverManager, noHost.getIpPort());
                System.out.println("Uniendote...");
            }

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }



    private static String inviteClient(ServerManager serverManager) throws IOException, InterruptedException {
        String ipNoHost = null;
        try {
            ipNoHost = serverManager.invitePlayer(ipPort);
            while (ipNoHost.isEmpty()) {
                Thread.sleep(20000);
                ipNoHost = serverManager.invitePlayer(ipPort);
            }
            return ipNoHost;
        } catch (IOException ioException) {
            InformationProcessor.showStringAndLine(ioException.getMessage());
        } catch (Exception ex) {
            InformationProcessor.showStringAndLine(ex.getMessage());
        } finally {

            return ipNoHost;
        }
    }

    private static String joinHost(ServerManager serverManager, String ipPortNoHost) {
        String message = "NO";
        try {
            serverManager.joinPlayer(ipPort, ipPortNoHost);
        } catch (IOException ioException) {
            System.out.println(ioException.getMessage());
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        } finally {
            return message;
        }
    }
}