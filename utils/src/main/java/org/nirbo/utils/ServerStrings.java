package org.nirbo.utils;

public enum ServerStrings {

    ADD_SERVER("Add Server"),
    DELETE_SERVER("Delete Server"),
    EDIT_SERVER("Edit Server"),
    SERVER_NAME("Server Name"),
    SERVER_MGMT_IP("Mgmt IP"),
    SERVER_DATA_NET1("Data Network 1"),
    SERVER_DATA_NET2("Data Network 2"),
    SERVER_LOCATION("Location"),
    SERVER_OWNER("Owner"),

    BUTTON_ADD_SERVER("Add Server"),
    BUTTON_CLEAR_FORM("Clear Form"),
    BUTTON_UPDATE_SERVER("Update"),
    BUTTON_CANCEL("Cancel"),
    BUTTON_MULTI_SELECT("Multi-Select"),
    BUTTON_EDIT_SERVER("Edit"),
    BUTTON_DELETE_SERVER("Delete");

    private final String string;

    private ServerStrings(String string) {
        this.string = string;
    }

    public String getString() {
        return this.string;
    }
}
