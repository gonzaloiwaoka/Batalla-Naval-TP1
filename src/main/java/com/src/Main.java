package com.src;

import com.src.checker.DataChecker;
import com.src.entity.Player;
import com.src.entity.TypeUserIp;
import com.src.errorManagement.ErrorFunctions;
import com.src.factory.PlayerFactory;
import com.src.manager.ClientManager;
import com.src.loader.GameLoader;
import com.src.manager.GameManagerV2;
import com.src.manager.ServerManager;
import com.src.screens.ScreenCreateGame;
import com.src.screens.ScreenJoinGame;
import com.src.screens.ScreenLoad;
import com.src.screens.ScreenMain;
import com.src.server.connectiontoserver.ConnectionServer;
import com.src.server.creator.CreateServer;
import com.src.setuper.SetUperData;

import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException, InterruptedException {
        GameLoader gameLoader = new GameLoader(new ScreenLoad(), new ScreenMain(), new ScreenCreateGame(), new ScreenJoinGame());
        ServerManager serverManager = new ServerManager();

        ClientManager clientManager = new ClientManager();
        Scanner sc = new Scanner(System.in);

        Player host = PlayerFactory.create();
        Player noHost = PlayerFactory.create();
        TypeUserIp typeUserIp = gameLoader.loadGame();

        if (typeUserIp.isTypeUser()) {
            host.setIpPort(CreateServer.createServer(typeUserIp, clientManager, host, noHost));
            host.setHost(true);
            ConnectionServer.hostDecideWhatToDo(serverManager, noHost);
        } else {
            noHost.setIpPort(CreateServer.createServer(typeUserIp, clientManager, host, noHost));
            ConnectionServer.noHostDecideWhatToDo(serverManager, noHost);
        }

        GameManagerV2 gM = new GameManagerV2(host, noHost);
        if (host.isHost()) {
            gM.playGame();
        }

    }

}
