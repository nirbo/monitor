package org.nirbo.ui.servers;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Label;
import com.vaadin.ui.themes.ValoTheme;
import org.nirbo.model.entity.Server;
import org.nirbo.notifier.ServerNotifier;
import org.nirbo.service.addserver.AddServerService;
import org.nirbo.ui.commons.MainUI;
import org.nirbo.utils.CommonStrings;
import org.nirbo.utils.LocationStrings;
import org.nirbo.utils.ServerStrings;
import org.nirbo.utils.ValidationLengths;
import org.springframework.beans.factory.annotation.Autowired;
import org.vaadin.viritin.BeanBinder;
import org.vaadin.viritin.button.MButton;
import org.vaadin.viritin.button.PrimaryButton;
import org.vaadin.viritin.fields.MTextField;
import org.vaadin.viritin.layouts.MHorizontalLayout;
import org.vaadin.viritin.layouts.MVerticalLayout;

@SpringView(name = AddServerLayoutFactory.NAME, ui = MainUI.class)
@SpringComponent
public class AddServerLayoutFactory extends MVerticalLayout implements View, Button.ClickListener {

    public static final String NAME = "addserver";

    private static final int nameValidatorLength = ValidationLengths.VALIDATION_NAME_LENGTH.getLength();
    private static final int ipValidatorLength = ValidationLengths.VALIDATION_IP_LENGTH.getLength();

    @Autowired
    private AddServerService addServerService;

    private MTextField serverName;
    private MTextField serverMgmtIP;
    private MTextField serverDataNet1;
    private MTextField serverDataNet2;
    private MTextField serverOwner;
    private ComboBox serverLocation;
    private PrimaryButton saveButton;
    private MButton clearButton;

    private MVerticalLayout addServerLayout;
    private Server server;

    public void enter(ViewChangeListener.ViewChangeEvent event) {
        removeAllComponents();
        createLayout();
    }

    private void createLayout() {
        setMargin(true);
        setSizeFull();

        server = new Server();

        createInputFields();
        createButtons();

        serverLocation.addItem(LocationStrings.DURHAM.getString());
        serverLocation.addItem(LocationStrings.HERZELIYA.getString());

        bindDataToFields();
        createFieldsLayout();
    }

    private void createInputFields() {
        serverName = new MTextField(ServerStrings.SERVER_NAME.getString())
                .withWidth("25%");
        serverMgmtIP = new MTextField(ServerStrings.SERVER_MGMT_IP.getString())
                .withWidth("25%");
        serverDataNet1 = new MTextField(ServerStrings.SERVER_DATA_NET1.getString())
                .withWidth("25%");
        serverDataNet2 = new MTextField(ServerStrings.SERVER_DATA_NET2.getString())
                .withWidth("25%");
        serverOwner = new MTextField(ServerStrings.SERVER_OWNER.getString())
                .withWidth("25%");
        serverLocation = new ComboBox(ServerStrings.SERVER_LOCATION.getString());
        serverLocation.setWidth("25%");

        setFieldValidations();
    }

    private void setFieldValidations() {
        serverName.setMaxLength(nameValidatorLength);
        serverMgmtIP.setMaxLength(ipValidatorLength);
        serverDataNet1.setMaxLength(ipValidatorLength);
        serverDataNet2.setMaxLength(ipValidatorLength);
        serverOwner.setMaxLength(nameValidatorLength);
    }

    private void bindDataToFields() {
        BeanBinder.bind(server, this);
    }

    private void createFieldsLayout() {
        Label addServerTitle = new Label("<H1><CENTER>" + CommonStrings.ADD_SERVER.getString() + "</CENTER></H1>", ContentMode.HTML);

        MHorizontalLayout buttonsLayout = new MHorizontalLayout(
                saveButton, clearButton)
                .withMargin(true)
                .withSpacing(true)
                .withAlign(saveButton, Alignment.MIDDLE_LEFT)
                .withAlign(clearButton, Alignment.MIDDLE_RIGHT);

        addServerLayout = new MVerticalLayout(
                addServerTitle, serverName, serverMgmtIP, serverDataNet1, serverDataNet2, serverOwner, serverLocation, buttonsLayout)
                .withMargin(true)
                .withSpacing(true)
                .withAlign(serverName, Alignment.MIDDLE_CENTER)
                .withAlign(serverMgmtIP, Alignment.MIDDLE_CENTER)
                .withAlign(serverDataNet1, Alignment.MIDDLE_CENTER)
                .withAlign(serverDataNet2, Alignment.MIDDLE_CENTER)
                .withAlign(serverOwner, Alignment.MIDDLE_CENTER)
                .withAlign(serverLocation, Alignment.MIDDLE_CENTER)
                .withAlign(buttonsLayout, Alignment.MIDDLE_CENTER);

        addComponent(addServerLayout);
    }

    public void buttonClick(Button.ClickEvent event) {
        if (event.getSource() == this.saveButton) {
            saveServerToDb();
        }

        clearFields();
        ServerNotifier.addServerSuccessNotify();
    }

    private void createButtons() {
        saveButton = new PrimaryButton(ServerStrings.ADD_SERVER.getString());
        clearButton = new MButton(ServerStrings.BUTTON_CLEAR_FORM.getString())
                .withStyleName(ValoTheme.BUTTON_DANGER);

        saveButton.addClickListener(this);
        clearButton.addClickListener(this);
    }

    private void saveServerToDb() {
        addServerService.saveServer(server);
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

