package com.src.server.httphandler;

import com.src.ConsoleCleaner.ClearConsole;
import com.src.InformationObtainer.InfoObtainer;
import com.src.entity.Position;
import com.src.server.gson.Mapper;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.OutputStream;

public class GetPositionHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange exchange) throws IOException {

        try {
            String message = Mapper.fromJson(exchange.getRequestBody(), String.class);
            System.out.println(message);

            Position position = InfoObtainer.getRowColumn();

            ClearConsole.clearScreen();

            String stringPosition = Mapper.toJson(position);

            exchange.sendResponseHeaders(200, stringPosition.length());
            OutputStream os = exchange.getResponseBody();
            os.write(stringPosition.getBytes());

            os.flush();
            os.close();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

    }
}
