package org.nirbo.notifier;

import com.vaadin.server.Page;
import com.vaadin.shared.Position;
import com.vaadin.ui.Notification;
import org.nirbo.utils.NotificationStrings;

public class ServerNotifier {

    public static void addServerSuccessNotify() {
        Notification notification = new Notification(
                NotificationStrings.NOTIFICATION_SUCCESS.getString(),
                NotificationStrings.NOTIFICATION_SERVER_ADDED.getString(),
                Notification.Type.HUMANIZED_MESSAGE);

        notification.setDelayMsec(2500);
        notification.setPosition(Position.MIDDLE_CENTER);
        notification.show(Page.getCurrent());
    }

    public static void addServerEmptyFieldsNotify() {
        Notification notification = new Notification(
                NotificationStrings.NOTIFICATION_ERROR.getString(),
                NotificationStrings.NOTIFICATION_EMPTY_FIELDS_MESSAGE.getString(),
                Notification.Type.ERROR_MESSAGE);

        notification.setDelayMsec(5000);
        notification.setPosition(Position.MIDDLE_CENTER);
        notification.show(Page.getCurrent());
    }

    public static void removeServerSuccessNotify() {
        Notification notification = new Notification(
                NotificationStrings.NOTIFICATION_SUCCESS.getString(),
                NotificationStrings.NOTIFICATION_SERVER_REMOVED.getString(),
                Notification.Type.HUMANIZED_MESSAGE);

        notification.setDelayMsec(2500);
        notification.setPosition(Position.MIDDLE_CENTER);
        notification.show(Page.getCurrent());
    }

    public static void noRowSelectedNotify() {
        Notification notification = new Notification(
                NotificationStrings.NOTIFICATION_ERROR.getString(),
                NotificationStrings.NOTIFICATION_SERVER_LIST_NO_ROW_SELECTED.getString(),
                Notification.Type.ERROR_MESSAGE);

        notification.setDelayMsec(5000);
        notification.setPosition(Position.MIDDLE_CENTER);
        notification.show(Page.getCurrent());
    }

    public static void updateServerSuccessNotify() {
        Notification notification = new Notification(
                NotificationStrings.NOTIFICATION_SUCCESS.getString(),
                NotificationStrings.NOTIFICATION_SERVER_UPDATED.getString(),
                Notification.Type.HUMANIZED_MESSAGE);

        notification.setDelayMsec(2500);
        notification.setPosition(Position.MIDDLE_CENTER);
        notification.show(Page.getCurrent());
    }
}
