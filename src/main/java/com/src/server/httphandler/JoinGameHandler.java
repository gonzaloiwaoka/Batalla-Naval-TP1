package com.src.server.httphandler;

import com.src.InformationObtainer.InformationProcessor;
import com.src.entity.Player;
import com.src.server.gson.Mapper;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.OutputStream;


public class JoinGameHandler implements HttpHandler {

    private Player noHost;

    public JoinGameHandler(Player noHost) {
        this.noHost = noHost;
    }

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        String ipPort = Mapper.fromJson(exchange.getRequestBody(), String.class);
        System.out.println(ipPort);

        noHost.setIpPort(ipPort);
        InformationProcessor.showStringAndLine("Alguien se te esta uniendo...");
        String ok = "OK";
        exchange.sendResponseHeaders(200, ok.length());
        OutputStream os = exchange.getResponseBody();
        os.write(ok.getBytes());

        os.flush();
        os.close();
    }
}
