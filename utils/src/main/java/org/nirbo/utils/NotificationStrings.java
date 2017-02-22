package org.nirbo.utils;

public enum NotificationStrings {

    NOTIFICATION_ERROR("ERROR: "),
    NOTIFICATION_SUCCESS("SUCCESS: "),
    NOTIFICATION_EMPTY_FIELDS_MESSAGE("All fields are required"),
    NOTIFICATION_SERVER_ADDED("The server has been successfully added"),
    NOTIFICATION_SERVER_REMOVED("The server has been successfully removed"),
    NOTIFICATION_SERVER_UPDATED("The server has been successfully updated"),

    NOTIFICATION_SERVER_LIST_NO_ROW_SELECTED("Unable to delete - no table rows selected");

    private final String string;

    NotificationStrings(String string) {
        this.string = string;
    }

    public String getString() {
        return this.string;
    }
}
