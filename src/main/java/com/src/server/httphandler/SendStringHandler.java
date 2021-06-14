package com.src.server.httphandler;

import com.src.InformationObtainer.InformationProcessor;
import com.src.server.gson.Mapper;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.OutputStream;

public class SendStringHandler implements HttpHandler {

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        try {
            String message = Mapper.fromJson(exchange.getRequestBody(), String.class);
            InformationProcessor.showStringAndLine(message);
            Thread.sleep(2000);
            exchange.sendResponseHeaders(200,0);

            OutputStream os = exchange.getResponseBody();
            os.write(0);

            os.flush();
            os.close();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }


    }
}
