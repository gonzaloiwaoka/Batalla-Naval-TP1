package com.src.server.httphandler;

import com.src.entity.Player;
import com.src.server.gson.Mapper;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetAddress;

public class InviteClientHandler implements HttpHandler {

    private Player host;
    private Player noHost;
    public InviteClientHandler(Player host, Player noHost) {
        this.host = host;
        this.noHost = noHost;
    }

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        try {
            String string = Mapper.fromJson(exchange.getRequestBody(), String.class);

            System.out.println(string);

            System.out.println(noHost.getIpPort());

            exchange.sendResponseHeaders(200, noHost.getIpPort().length());
            OutputStream os = exchange.getResponseBody();
            os.write(noHost.getIpPort().getBytes());

            os.flush();
            os.close();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

    }
}
