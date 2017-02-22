package org.nirbo.utils;

public enum CommonStrings {

    SERVERS("Servers"),
    TICKETS("Tickets"),
    ADD_SERVER("Add Server"),
    SHOW_SERVERS("Show Servers"),
    EDIT("Edit");

    private final String string;

    CommonStrings(String string) {
        this.string = string;
    }

    public String getString() {
        return this.string;
    }
}
