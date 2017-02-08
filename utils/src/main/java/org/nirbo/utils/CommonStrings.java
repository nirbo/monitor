package org.nirbo.utils;

public enum CommonStrings {

    SERVERS("Servers"),
    TICKETS("Tickets"),
    ADD_SERVER("Add Server"),
    SHOW_SERVERS("Show Servers");

    private final String string;

    private CommonStrings(String string) {
        this.string = string;
    }

    public String getString() {
        return this.string;
    }
}
