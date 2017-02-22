package org.nirbo.ui.components;

import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;
import org.nirbo.model.entity.Server;
import org.nirbo.notifier.ServerNotifier;
import org.nirbo.service.showservers.ShowServersService;
import org.nirbo.service.updateserver.UpdateServerService;
import org.nirbo.utils.LocationStrings;
import org.nirbo.utils.ServerStrings;
import org.nirbo.utils.ValidationLengths;

import java.util.List;

public class EditServerWindow extends Window implements View, Button.ClickListener {

    public static final String NAME = "editserver";

    private static final int nameValidatorLength = ValidationLengths.VALIDATION_NAME_LENGTH.getLength();
    private static final int ipValidatorLength = ValidationLengths.VALIDATION_IP_LENGTH.getLength();

    private TextField serverName;
    private TextField serverMgmtIP;
    private TextField serverDataNet1;
    private TextField serverDataNet2;
    private TextField serverOwner;
    private ComboBox serverLocation;
    private Button updateButton;
    private Button cancelButton;

    private UpdateServerService updateServerService;
    private ShowServersService showServersService;
    private BeanFieldGroup<Server> fieldGroup;
    private Server server;
    private Grid serversTable;

    public EditServerWindow(String caption, Server server, UpdateServerService updateServerService,
                            ShowServersService showServersService, Grid serversTable) {
        this.server = server;
        this.updateServerService = updateServerService;
        this.showServersService = showServersService;
        this.serversTable = serversTable;
        setCaption(caption);
        createLayout();
    }

    public void enter(ViewChangeListener.ViewChangeEvent event) {
        createLayout();
    }

    private void createLayout() {
        setDraggable(true);
        setModal(true);
        setResizable(false);
        setClosable(true);

        fieldGroup = new BeanFieldGroup<Server>(Server.class);

        serverName = new TextField(ServerStrings.SERVER_NAME.getString());
        serverMgmtIP = new TextField(ServerStrings.SERVER_MGMT_IP.getString());
        serverDataNet1 = new TextField(ServerStrings.SERVER_DATA_NET1.getString());
        serverDataNet2 = new TextField(ServerStrings.SERVER_DATA_NET2.getString());
        serverOwner = new TextField(ServerStrings.SERVER_OWNER.getString());
        serverLocation = new ComboBox(ServerStrings.SERVER_LOCATION.getString());

        updateButton = new Button(ServerStrings.BUTTON_UPDATE_SERVER.getString());
        cancelButton = new Button(ServerStrings.BUTTON_CANCEL.getString());

        updateButton.addClickListener(this);
        cancelButton.addClickListener(this);

        serverLocation.addItem(LocationStrings.DURHAM.getString());
        serverLocation.addItem(LocationStrings.HERZELIYA.getString());

        serverName.setMaxLength(nameValidatorLength);
        serverMgmtIP.setMaxLength(ipValidatorLength);
        serverDataNet1.setMaxLength(ipValidatorLength);
        serverDataNet2.setMaxLength(ipValidatorLength);
        serverOwner.setMaxLength(nameValidatorLength);

        serverName.setValue(server.getServerName());
        serverMgmtIP.setValue(server.getServerMgmtIP());
        serverDataNet1.setValue(server.getServerDataNet1());
        serverDataNet2.setValue(server.getServerDataNet2());
        serverOwner.setValue(server.getServerOwner());

        fieldGroup.bindMemberFields(this);
        fieldGroup.setItemDataSource(server);

        VerticalLayout editServerLayout = new VerticalLayout();
        editServerLayout.setMargin(true);
        editServerLayout.setSpacing(true);

        editServerLayout.addComponent(serverName);
        editServerLayout.addComponent(serverMgmtIP);
        editServerLayout.addComponent(serverDataNet1);
        editServerLayout.addComponent(serverDataNet2);
        editServerLayout.addComponent(serverOwner);
        editServerLayout.addComponent(serverLocation);

        editServerLayout.setComponentAlignment(serverName, Alignment.MIDDLE_CENTER);
        editServerLayout.setComponentAlignment(serverMgmtIP, Alignment.MIDDLE_CENTER);
        editServerLayout.setComponentAlignment(serverDataNet1, Alignment.MIDDLE_CENTER);
        editServerLayout.setComponentAlignment(serverDataNet2, Alignment.MIDDLE_CENTER);
        editServerLayout.setComponentAlignment(serverOwner, Alignment.MIDDLE_CENTER);
        editServerLayout.setComponentAlignment(serverLocation, Alignment.MIDDLE_CENTER);

        updateButton.setStyleName(ValoTheme.BUTTON_PRIMARY);
        cancelButton.setStyleName(ValoTheme.BUTTON_DANGER);

        HorizontalLayout buttonsLayout = new HorizontalLayout();
        buttonsLayout.setSpacing(true);
        buttonsLayout.setMargin(true);

        buttonsLayout.addComponents(updateButton, cancelButton);
        buttonsLayout.setComponentAlignment(updateButton, Alignment.MIDDLE_LEFT);
        buttonsLayout.setComponentAlignment(cancelButton, Alignment.MIDDLE_RIGHT);

        editServerLayout.addComponent(buttonsLayout);
        editServerLayout.setComponentAlignment(buttonsLayout, Alignment.MIDDLE_CENTER);

        setContent(editServerLayout);
    }

    public void buttonClick(Button.ClickEvent event) {
        if (event.getSource() == this.updateButton) {
            updateServerInDb();
            refreshGrid();
        } else {
            cancelEdit();
        }
    }

    private void updateServerInDb() {
        try {
            fieldGroup.commit();
        } catch (FieldGroup.CommitException e) {
            e.printStackTrace();
        }

        updateServerService.updateServer(server);
        close();
        ServerNotifier.updateServerSuccessNotify();
    }

    private void refreshGrid() {
        List<com.vaadin.data.sort.SortOrder> sortOrder = serversTable.getSortOrder();
        List<Server> serverList = showServersService.getAllServers();
        BeanItemContainer<Server> container = new BeanItemContainer<Server>(Server.class, serverList);
        serversTable.setContainerDataSource(container);
        serversTable.setSortOrder(sortOrder);
    }

    private void cancelEdit() {
        close();
    }
}