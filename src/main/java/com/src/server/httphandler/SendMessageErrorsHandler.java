package com.src.server.httphandler;

import com.src.errorManagement.Error;
import com.src.server.gson.Mapper;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

public class SendMessageErrorsHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        List<Error> errors = Mapper.fromJsonListErrors(exchange.getRequestBody());

        for (Error element : errors) {
            System.out.println(element.getMessage());
        }

        System.out.println();
        System.out.println();

        exchange.sendResponseHeaders(200,0);
        OutputStream oS = exchange.getResponseBody();
        oS.write(0);

        oS.flush();
        oS.close();
    }
}
