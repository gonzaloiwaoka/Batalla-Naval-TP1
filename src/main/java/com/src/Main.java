package com.src;

import com.src.InformationObtainer.InformationProcessor;
import com.src.entity.Player;
import com.src.entity.TypeUserIp;
import com.src.factory.GameManagerFactory;
import com.src.factory.PlayerFactory;
import com.src.factory.ScreenFactory;
import com.src.manager.ClientManager;
import com.src.loader.GameLoader;
import com.src.manager.GameManager;
import com.src.manager.ServerManager;
import com.src.server.connect.Connect;
import com.src.server.connectiontoserver.ConnectionServer;
import com.src.server.creator.CreateServer;

import java.io.IOException;


public class Main {

    public static void main(String[] args) throws IOException, InterruptedException {
        GameLoader gameLoader = ScreenFactory.createScreens();
        Connect connect = new Connect();
        ServerManager serverManager = new ServerManager(connect);
        ClientManager clientManager = new ClientManager();

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

        GameManager gM = GameManagerFactory.createGameManager(host, noHost, serverManager);

        if (host.isHost()) {
            gM.playGame();
        }

    }

}
