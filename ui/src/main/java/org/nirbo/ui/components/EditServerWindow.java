package org.nirbo.ui.components;

import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Grid;
import com.vaadin.ui.themes.ValoTheme;
import org.nirbo.model.entity.Server;
import org.nirbo.notifier.ServerNotifier;
import org.nirbo.service.showservers.ShowServersService;
import org.nirbo.service.updateserver.UpdateServerService;
import org.nirbo.utils.LocationStrings;
import org.nirbo.utils.ServerStrings;
import org.nirbo.utils.ValidationLengths;
import org.vaadin.viritin.BeanBinder;
import org.vaadin.viritin.MBeanFieldGroup;
import org.vaadin.viritin.button.MButton;
import org.vaadin.viritin.button.PrimaryButton;
import org.vaadin.viritin.fields.MTextField;
import org.vaadin.viritin.layouts.MHorizontalLayout;
import org.vaadin.viritin.layouts.MVerticalLayout;
import org.vaadin.viritin.layouts.MWindow;

import java.util.List;

public class EditServerWindow extends MWindow implements View, Button.ClickListener {

    public static final String NAME = "editserver";

    private static final int nameValidatorLength = ValidationLengths.VALIDATION_NAME_LENGTH.getLength();
    private static final int ipValidatorLength = ValidationLengths.VALIDATION_IP_LENGTH.getLength();

    private MTextField serverName;
    private MTextField serverMgmtIP;
    private MTextField serverDataNet1;
    private MTextField serverDataNet2;
    private MTextField serverOwner;
    private ComboBox serverLocation;
    private MButton updateButton;
    private MButton cancelButton;

    private UpdateServerService updateServerService;
    private ShowServersService showServersService;
    private MBeanFieldGroup<Server> fieldGroup;
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

    public void enter(ViewChangeListener.ViewChangeEvent event) {}

    private void createLayout() {
        setDraggable(true);
        setModal(true);
        setResizable(false);
        setClosable(true);

        createInputFields();
        createButtons();

        serverLocation.addItem(LocationStrings.DURHAM.getString());
        serverLocation.addItem(LocationStrings.HERZELIYA.getString());

        bindDataToFields();
        createWindowLayout();
    }

    public void buttonClick(Button.ClickEvent event) {
        if (event.getSource() == this.updateButton) {
            updateServerInDb();
            refreshGrid();
        } else {
            cancelEdit();
        }
    }

    private void bindDataToFields() {
        fieldGroup = new MBeanFieldGroup<Server>(Server.class);
        BeanBinder.bind(server, this);
    }

    private void createWindowLayout() {
        MHorizontalLayout buttonsLayout = new MHorizontalLayout(
                updateButton, cancelButton)
                .withMargin(true)
                .withSpacing(true)
                .withAlign(updateButton, Alignment.MIDDLE_LEFT)
                .withAlign(cancelButton, Alignment.MIDDLE_RIGHT);

        MVerticalLayout editServerLayout = new MVerticalLayout(
                serverName, serverMgmtIP, serverDataNet1, serverDataNet2, serverOwner, serverLocation, buttonsLayout)
                .withMargin(true)
                .withSpacing(true)
                .withAlign(serverName, Alignment.MIDDLE_CENTER)
                .withAlign(serverMgmtIP, Alignment.MIDDLE_CENTER)
                .withAlign(serverDataNet1, Alignment.MIDDLE_CENTER)
                .withAlign(serverDataNet2, Alignment.MIDDLE_CENTER)
                .withAlign(serverOwner, Alignment.MIDDLE_CENTER)
                .withAlign(serverLocation, Alignment.MIDDLE_CENTER)
                .withAlign(buttonsLayout, Alignment.MIDDLE_CENTER);

        setContent(editServerLayout);
    }

    private void createInputFields() {
        serverName = new MTextField(ServerStrings.SERVER_NAME.getString());
        serverMgmtIP = new MTextField(ServerStrings.SERVER_MGMT_IP.getString());
        serverDataNet1 = new MTextField(ServerStrings.SERVER_DATA_NET1.getString());
        serverDataNet2 = new MTextField(ServerStrings.SERVER_DATA_NET2.getString());
        serverOwner = new MTextField(ServerStrings.SERVER_OWNER.getString());
        serverLocation = new ComboBox(ServerStrings.SERVER_LOCATION.getString());

        setFieldValidations();
    }

    private void setFieldValidations() {
        serverName.setMaxLength(nameValidatorLength);
        serverMgmtIP.setMaxLength(ipValidatorLength);
        serverDataNet1.setMaxLength(ipValidatorLength);
        serverDataNet2.setMaxLength(ipValidatorLength);
        serverOwner.setMaxLength(nameValidatorLength);
    }

    private void createButtons() {
        updateButton = new PrimaryButton(ServerStrings.BUTTON_UPDATE_SERVER.getString());
        cancelButton = new MButton(ServerStrings.BUTTON_CANCEL.getString())
                .withStyleName(ValoTheme.BUTTON_DANGER);

        updateButton.addClickListener(this);
        cancelButton.addClickListener(this);
    }

    private void updateServerInDb() {
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