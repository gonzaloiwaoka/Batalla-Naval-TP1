package com.src;

import com.src.InformationObtainer.InfoObtainer;
import com.src.checker.DataChecker;
import com.src.entity.Player;
import com.src.entity.PlayerAction;
import com.src.entity.TypeUserIp;
import com.src.errorManagement.ErrorFunctions;
import com.src.factory.PlayerFactory;
import com.src.manager.ClientManager;
import com.src.manager.GameManager;
import com.src.loader.GameLoader;
import com.src.manager.GameManagerV2;
import com.src.manager.ServerManager;
import com.src.mapper.GridShower;
import com.src.screens.ScreenCreateGame;
import com.src.screens.ScreenJoinGame;
import com.src.screens.ScreenLoad;
import com.src.screens.ScreenMain;
import com.src.server.creator.CreateServer;
import com.src.setuper.SetUperData;

import java.io.IOException;
import java.util.Map;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {
        GameLoader gameLoader = new GameLoader(new ScreenLoad(), new ScreenMain(), new ScreenCreateGame(), new ScreenJoinGame());
        GameManager gameManager = new GameManager(new SetUperData(), new DataChecker(), new ErrorFunctions());
        ServerManager serverManager = new ServerManager();

        ClientManager clientManager = new ClientManager();

        Player host = PlayerFactory.create();
        Player noHost = PlayerFactory.create();
        //TypeUserIp typeUserIp = new TypeUserIp();
        TypeUserIp typeUserIp = gameLoader.loadGame();
        //typeUserIp.setIp("169.254.219.216");
        //typeUserIp.setPort(8000);
        //typeUserIp.setTypeUser(true);
        // Si es true, es host, si no es No host
        if (typeUserIp.isTypeUser()) {
            host.setIpPort(CreateServer.createServer(typeUserIp, clientManager));
            host.setHost(true);
            noHost.setIpPort("192.168.56.1" + ":" + "8080");
        } else {
            noHost.setIpPort(CreateServer.createServer(typeUserIp, clientManager));
        }
        /*
         Forma de hacer un set up de ninjas, determinar quien va primero, etc.
        if (gameLoader.loadGame()) {
            //gameManager.executeGame();
        }

        if (host.isHost()) {
            serverManager.playGame();
        }*/
        GameManagerV2 gM = new GameManagerV2(host, noHost);
        if (host.isHost()) {
            gM.playGame();
        }

        System.out.println("Juego terminado");
    }

}
