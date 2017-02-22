package org.nirbo.model.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
@Table(name="SERVER")
public class Server {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="id")
    private Long id;

    @Column(name="server_name")
    @NotNull(message = "Field may not be left empty")
    @Size(min = 1, max = 29, message = "A server name must be provided")
    private String serverName;

    @Column(name="server_mgmt_ip")
    @NotNull(message = "Field may not be left empty")
    @Size(max = 15)
    @Pattern(message = "Please provide an IPv4 Address",
            regexp = "^(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)$")
    private String serverMgmtIP;

    @Column(name="server_data_net1")
    @NotNull(message = "Field may not be left empty")
    @Size(max = 15)
    @Pattern(message = "Please provide an IPv4 Address",
            regexp = "^(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)$")
    private String serverDataNet1;

    @Column(name="server_data_net2")
    @NotNull(message = "Field may not be left empty")
    @Size(max = 15)
    @Pattern(message = "Please provide an IPv4 Address",
            regexp = "^(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)$")
    private String serverDataNet2;

    @Column(name="server_owner")
    @NotNull(message = "Field may not be left empty")
    @Size(min = 1, max = 29, message = "An owner name must be provided")
    private String serverOwner;

    @Column(name="server_location")
    @NotNull(message = "Field may not be left empty")
    private String serverLocation;

    public Server() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getServerLocation() {
        return serverLocation;
    }

    public void setServerLocation(String serverLocation) {
        this.serverLocation = serverLocation;
    }

    @Override
    public String toString() {
        return "Server ID: " + id
                + " | Server Name: " + serverName
                + " | Server MGMT IP: " + serverMgmtIP
                + " | Server Location: " + serverLocation
                + " | Server Owner: " + serverOwner;
    }
}
