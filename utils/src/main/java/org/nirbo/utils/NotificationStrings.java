package org.nirbo.utils;

public enum NotificationStrings {

    NOTIFICATION_ERROR("ERROR: "),
    NOTIFICATION_EMPTY_FIELDS_MESSAGE("All fields are required.");

    private final String string;

    private NotificationStrings(String string) {
        this.string = string;
    }

    public String getString() {
        return this.string;
    }
}
