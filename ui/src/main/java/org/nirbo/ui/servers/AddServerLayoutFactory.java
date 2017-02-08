package org.nirbo.ui.servers;

import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.Page;
import com.vaadin.shared.Position;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;
import org.nirbo.model.entity.Server;
import org.nirbo.service.addserver.AddServerService;
import org.nirbo.ui.commons.MainUI;
import org.nirbo.utils.CommonStrings;
import org.nirbo.utils.LocationStrings;
import org.nirbo.utils.NotificationStrings;
import org.nirbo.utils.ServerStrings;
import org.springframework.beans.factory.annotation.Autowired;

@SpringView(name = AddServerLayoutFactory.NAME, ui = MainUI.class)
@SpringComponent
public class AddServerLayoutFactory extends VerticalLayout implements View, Button.ClickListener {

    public static final String NAME = "addserver";

    @Autowired
    private AddServerService addServerService;

    private TextField serverName;
    private TextField serverMgmtIP;
    private TextField serverDataNet1;
    private TextField serverDataNet2;
    private TextField serverOwner;
    private ComboBox serverLocation;
    private Button saveButton;
    private Button clearButton;

    private BeanFieldGroup<Server> fieldGroup;
    private Server server;

    public void enter(ViewChangeListener.ViewChangeEvent event) {
        removeAllComponents();
        createLayout();
    }

    private void createLayout() {
        this.setMargin(true);
        this.setSizeFull();

        fieldGroup = new BeanFieldGroup<Server>(Server.class);
        server = new Server();

        serverName = new TextField(ServerStrings.SERVER_NAME.getString());
        serverMgmtIP = new TextField(ServerStrings.SERVER_MGMT_IP.getString());
        serverDataNet1 = new TextField(ServerStrings.SERVER_DATA_NET1.getString());
        serverDataNet2 = new TextField(ServerStrings.SERVER_DATA_NET2.getString());
        serverOwner = new TextField(ServerStrings.SERVER_OWNER.getString());
        serverLocation = new ComboBox(ServerStrings.SERVER_LOCATION.getString());

        saveButton = new Button(ServerStrings.ADD_SERVER.getString());
        clearButton = new Button(ServerStrings.BUTTON_CLEAR_FORM.getString());

        saveButton.addClickListener(this);
        clearButton.addClickListener(this);

        serverLocation.addItem(LocationStrings.DURHAM.getString());
        serverLocation.addItem(LocationStrings.HERZELIYA.getString());

        serverName.setNullRepresentation("");
        serverMgmtIP.setNullRepresentation("");
        serverDataNet1.setNullRepresentation("");
        serverDataNet2.setNullRepresentation("");
        serverOwner.setNullRepresentation("");

        serverName.setMaxLength(29);
        serverMgmtIP.setMaxLength(15);
        serverDataNet1.setMaxLength(15);
        serverDataNet2.setMaxLength(15);
        serverOwner.setMaxLength(29);

        serverName.setWidth("25%");
        serverMgmtIP.setWidth("25%");
        serverDataNet1.setWidth("25%");
        serverDataNet2.setWidth("25%");
        serverOwner.setWidth("25%");
        serverLocation.setWidth("25%");

        fieldGroup.bindMemberFields(this);
        fieldGroup.setItemDataSource(server);

        VerticalLayout addServerLayout = new VerticalLayout();
        addServerLayout.setMargin(true);
        addServerLayout.setSpacing(true);

        Label addServerTitle = new Label("<H1><CENTER><U>" + CommonStrings.ADD_SERVER.getString() + "</U></CENTER></H1>", ContentMode.HTML);
        addServerLayout.addComponent(addServerTitle);

        addServerLayout.addComponent(serverName);
        addServerLayout.addComponent(serverMgmtIP);
        addServerLayout.addComponent(serverDataNet1);
        addServerLayout.addComponent(serverDataNet2);
        addServerLayout.addComponent(serverOwner);
        addServerLayout.addComponent(serverLocation);

        addServerLayout.setComponentAlignment(serverName, Alignment.MIDDLE_CENTER);
        addServerLayout.setComponentAlignment(serverMgmtIP, Alignment.MIDDLE_CENTER);
        addServerLayout.setComponentAlignment(serverDataNet1, Alignment.MIDDLE_CENTER);
        addServerLayout.setComponentAlignment(serverDataNet2, Alignment.MIDDLE_CENTER);
        addServerLayout.setComponentAlignment(serverOwner, Alignment.MIDDLE_CENTER);
        addServerLayout.setComponentAlignment(serverLocation, Alignment.MIDDLE_CENTER);

        saveButton.setStyleName(ValoTheme.BUTTON_PRIMARY);
        clearButton.setStyleName(ValoTheme.BUTTON_DANGER);

        HorizontalLayout buttonsLayout = new HorizontalLayout();
        buttonsLayout.setSpacing(true);
        buttonsLayout.setMargin(true);

        buttonsLayout.addComponents(saveButton, clearButton);
        buttonsLayout.setComponentAlignment(saveButton, Alignment.MIDDLE_LEFT);
        buttonsLayout.setComponentAlignment(clearButton, Alignment.MIDDLE_RIGHT);

        addServerLayout.addComponent(buttonsLayout);
        addServerLayout.setComponentAlignment(buttonsLayout, Alignment.MIDDLE_CENTER);
        addComponent(addServerLayout);
    }

    public void buttonClick(Button.ClickEvent event) {
        if (event.getSource() == this.saveButton) {
            saveFields();
        } else {
            clearFields();
        }

        addServerService.saveServer(server);
        clearFields();

//  TODO: CREATE A GENERIC, MULTI-USE STATIC METHOD WITH DIFFERENT NOTIFICATION OPTIONS ACCORDING TO PASSED VARIABLES
        Notification successNotification = new Notification(NotificationStrings.NOTIFICATION_SUCCESS.getString(),
                                                            NotificationStrings.NOTIFICATION_SERVER_ADDED.getString(),
                                                            Notification.Type.HUMANIZED_MESSAGE);
        successNotification.setDelayMsec(2500);
        successNotification.setPosition(Position.MIDDLE_CENTER);
        successNotification.show(Page.getCurrent());
    }

    private void saveFields() {
        try {
            fieldGroup.commit();
        } catch (FieldGroup.CommitException e) {
            Notification.show(NotificationStrings.NOTIFICATION_ERROR.getString(),
                    NotificationStrings.NOTIFICATION_EMPTY_FIELDS_MESSAGE.getString(),
                    Notification.Type.ERROR_MESSAGE);
        }
    }

    private void clearFields() {
        serverName.setValue(null);
        serverMgmtIP.setValue(null);
        serverDataNet1.setValue(null);
        serverDataNet2.setValue(null);
        serverOwner.setValue(null);
        serverLocation.setValue(null);
    }
}

