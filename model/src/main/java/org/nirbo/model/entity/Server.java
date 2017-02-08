package org.nirbo.model.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="SERVER")
public class Server {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="id")
    private Long id;

    @Column(name="server_location")
    @NotNull
    private String serverLocation;

    @Column(name="server_name")
    @NotNull
    private String serverName;

    @Column(name="server_mgmt_ip")
    @NotNull
    private String serverMgmtIP;

    @Column(name="server_data_net1")
    @NotNull
    private String serverDataNet1;

    @Column(name="server_data_net2")
    @NotNull
    private String serverDataNet2;

    @Column(name="server_owner")
    @NotNull
    private String serverOwner;

    public Server() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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
                + " Server Location: " + serverLocation
                + " Server Owner: " + serverOwner;
    }
}
