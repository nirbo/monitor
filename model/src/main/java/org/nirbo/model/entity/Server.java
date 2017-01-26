package org.nirbo.model.entity;

public class Server {

    private Integer id;
    private String serverLocation;
    private String serverName;
    private String serverMgmtIP;
    private String serverDataNet1;
    private String serverDataNet2;
    private String serverOwner;

    public Server() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getServerLocation() {
        return serverLocation;
    }

    public void setServerLocation(String serverLocation) {
        this.serverLocation = serverLocation;
    }

    public String getServerName() {
        return serverName;
    }

    public void setServerName(String serverName) {
        this.serverName = serverName;
    }

    public String getServerMgmtIP() {
        return serverMgmtIP;
    }

    public void setServerMgmtIP(String serverMgmtIP) {
        this.serverMgmtIP = serverMgmtIP;
    }

    public String getServerDataNet1() {
        return serverDataNet1;
    }

    public void setServerDataNet1(String serverDataNet1) {
        this.serverDataNet1 = serverDataNet1;
    }

    public String getServerDataNet2() {
        return serverDataNet2;
    }

    public void setServerDataNet2(String serverDataNet2) {
        this.serverDataNet2 = serverDataNet2;
    }

    public String getServerOwner() {
        return serverOwner;
    }

    public void setServerOwner(String serverOwner) {
        this.serverOwner = serverOwner;
    }

    @Override
    public String toString() {
        return "Server Name: " + serverName
                + " Server MGMT IP: " + serverMgmtIP
                + " Server Location: " + serverLocation;
    }
}
