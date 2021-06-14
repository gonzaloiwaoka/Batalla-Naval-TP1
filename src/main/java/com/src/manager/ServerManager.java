package com.src.manager;

import com.src.entity.*;
import com.src.errorManagement.Error;
import com.src.server.connect.Connect;
import com.src.server.gson.Mapper;

import java.io.IOException;
import java.util.List;

public class ServerManager {

    private static final String GET_POSITION_ENDPOINT = "/getPosition";
    private static final String GET_PLAYER_ACTION_ENDPOINT = "/getAction";
    private static final String SEND_ERRORS_ENDPOINT = "/sendMessageErrors";
    private static final String SEND_OBJECT_ENDPOINT = "/sendObjectToClient";
    private static final String SEND_STRING_ENDPOINT = "/sendString";
    private Connect connect;

    public ServerManager () {
        this.connect = new Connect();
    }


    public Position obtainPosition(Player player) throws IOException {
        String stringPosition = connect.sendPost(player.getIpPort(), GET_POSITION_ENDPOINT, "Dame una coordenadas, para tu ninja!");
        Position position = Mapper.fromJson(stringPosition, Position.class);
        return position;
    }


    public PlayerAction obtainPlayerAction(Player player, String string) throws IOException {
        // Verifico que la accion sea valida, si no la es burbujeo el error y lo devuelvo

        String stringAction = connect.sendPost(player.getIpPort(), GET_PLAYER_ACTION_ENDPOINT, string);
        PlayerAction playerAction = Mapper.fromJson(stringAction, PlayerAction.class);

        return playerAction;
    }

    public void modifyValues(Player player, DataClient dataClient) throws IOException {
        connect.sendPost(player.getIpPort(), SEND_OBJECT_ENDPOINT, dataClient);
        // Va a modificar los valores en caso de que sean validos
    }

    public void sendErrors(Player player, List<Error> errors) throws IOException {
        connect.sendPost(player.getIpPort(), SEND_ERRORS_ENDPOINT, errors);
    }

    public void sendString(Player player, String string) throws IOException {
        connect.sendPost(player.getIpPort(), SEND_STRING_ENDPOINT, string);
    }

}
