package org.nirbo.utils;

public enum CommonStrings {

    MAIN_TABSHEET_SERVERS("Servers"),
    MAIN_TABSHEET_TICKETS("Tickets")

    ;

    private final String string;

    private CommonStrings(String string) {
        this.string = string;
    }

    public String getString() {
        return this.string;
    }
}
