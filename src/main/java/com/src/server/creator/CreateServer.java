package com.src.server.creator;


import com.src.entity.Player;
import com.src.entity.TypeUserIp;

import com.src.manager.ClientManager;
import com.src.server.httphandler.*;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;

public class CreateServer {

    public static String createServer(TypeUserIp typeUserIp, ClientManager clientManager, Player host, Player noHost) throws IOException {
        System.out.println(InetAddress.getLocalHost());
        InetAddress localHost = InetAddress.getByName(typeUserIp.getIp());
        InetSocketAddress sockAddr = new InetSocketAddress(localHost, typeUserIp.getPort());
        HttpServer server = HttpServer.create(sockAddr, 0);

        server.createContext("/getAction", new GetActionHandler());
        server.createContext("/getPosition", new GetPositionHandler());
        server.createContext("/sendMessageErrors", new SendMessageErrorsHandler());
        server.createContext("/sendObjectToClient", new SendObjectToClient(clientManager));
        server.createContext("/inviteClient", new InviteClientHandler(host, noHost));
        server.createContext("/joinGame", new JoinGameHandler(noHost));
        //server.createContext("/joinGame", new JoinGameHandler());
        //server.createContext("/sendOk", new sendOkHandler());

        String ipPort = typeUserIp.getIp() + ":" + typeUserIp.getPort();

        server.setExecutor(null);
        server.start();
        return ipPort;
    }


}
