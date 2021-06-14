package com.src.server.httphandler;

import ConsoleCleaner.ClearConsole;
import com.src.InformationObtainer.InfoObtainer;
import com.src.entity.PlayerAction;
import com.src.server.gson.Mapper;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.OutputStream;

public class GetActionHandler implements HttpHandler {

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        try {
            String string = Mapper.fromJson(exchange.getRequestBody(), String.class);
            System.out.println(string);

            PlayerAction playerAction = InfoObtainer.getActionRowColumn();
            String stringPlayerAction = Mapper.toJson(playerAction);

            ClearConsole.clearScreen();

            exchange.sendResponseHeaders(200, stringPlayerAction.length());
            OutputStream os = exchange.getResponseBody();
            os.write(stringPlayerAction.getBytes());

            os.flush();
            os.close();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

    }
}