package org.nirbo.utils;

public enum LocationStrings {

    DURHAM("Durham"),
    HERZELIYA("Herzeliya");

    private final String string;

    LocationStrings(String string) {
        this.string = string;
    }

    public String getString() {
        return this.string;
    }

}
