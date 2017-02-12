package org.nirbo.utils;

public enum NotificationStrings {

    NOTIFICATION_ERROR("ERROR: "),
    NOTIFICATION_SUCCESS("SUCCESS: "),
    NOTIFICATION_EMPTY_FIELDS_MESSAGE("All fields are required."),
    NOTIFICATION_SERVER_ADDED("The server has been successfully added"),
    NOTIFICATION_SERVER_REMOVED("The server has been successfully removed");

    private final String string;

    private NotificationStrings(String string) {
        this.string = string;
    }

    public String getString() {
        return this.string;
    }
}
