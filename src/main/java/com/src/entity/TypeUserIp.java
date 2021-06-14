package com.src.entity;

public class TypeUserIp {

    private boolean typeUser;
    private String ip;
    private int port;

    public boolean isTypeUser() {
        return typeUser;
    }

    public void setTypeUser(boolean typeUser) {
        this.typeUser = typeUser;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }
}
