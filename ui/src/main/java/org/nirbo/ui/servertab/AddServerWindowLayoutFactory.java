package org.nirbo.ui.servertab;

import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;
import org.nirbo.model.entity.Server;
import org.nirbo.utils.ServerStrings;

@org.springframework.stereotype.Component
public class AddServerWindowLayoutFactory implements AddServerWindowLayoutBuilder {

    private class AddServerWindowLayout extends Window implements Button.ClickListener {

        private TextField serverName;
        private TextField serverMgmtIP;
        private TextField serverDataNet1;
        private TextField serverDataNet2;
        private ComboBox serverLocation;
        private ComboBox serverOwner;
        private Button saveButton;
        private Button clearButton;

        private BeanFieldGroup<Server> fieldGroup;
        private Server server;

        public AddServerWindowLayout init() {
            fieldGroup = new BeanFieldGroup<Server>(Server.class);
            server = new Server();

            serverName = new TextField(ServerStrings.SERVER_NAME.getString());
            serverMgmtIP = new TextField(ServerStrings.SERVER_MGMT_IP.getString());
            serverDataNet1 = new TextField(ServerStrings.SERVER_DATA_NET1.getString());
            serverDataNet2 = new TextField(ServerStrings.SERVER_DATA_NET2.getString());
            serverLocation = new ComboBox(ServerStrings.SERVER_LOCATION.getString());
            serverOwner = new ComboBox(ServerStrings.SERVER_OWNER.getString());

            saveButton = new Button(ServerStrings.ADD_SERVER.getString());
            clearButton = new Button(ServerStrings.BUTTON_CLEAR_FORM.getString());

            saveButton.addClickListener(this);
            clearButton.addClickListener(this);

//          TODO: ADD DB QUERY AND POPULATE THE TWO COMBO BOXES (LOCATION & OWNER)

            serverName.setNullRepresentation("");
            serverMgmtIP.setNullRepresentation("");
            serverDataNet1.setNullRepresentation("");
            serverDataNet2.setNullRepresentation("");

            return this;
        }

        public AddServerWindowLayout bind() {
            fieldGroup.bindMemberFields(this);
            fieldGroup.setItemDataSource(server);

            return this;
        }

        public AddServerWindowLayout createLayout() {
            this.setModal(true);
            this.setClosable(true);
            this.setResizable(false);
            this.setDraggable(true);
            this.setCaption(ServerStrings.ADD_SERVER.getString());

            VerticalLayout addServerWindowLayout = new VerticalLayout();
            addServerWindowLayout.setMargin(true);
            addServerWindowLayout.setSpacing(true);

            addServerWindowLayout.addComponent(serverName);
            addServerWindowLayout.addComponent(serverMgmtIP);
            addServerWindowLayout.addComponent(serverDataNet1);
            addServerWindowLayout.addComponent(serverDataNet2);
            addServerWindowLayout.addComponent(serverLocation);
            addServerWindowLayout.addComponent(serverOwner);

            addServerWindowLayout.setComponentAlignment(serverName, Alignment.MIDDLE_CENTER);
            addServerWindowLayout.setComponentAlignment(serverMgmtIP, Alignment.MIDDLE_CENTER);
            addServerWindowLayout.setComponentAlignment(serverDataNet1, Alignment.MIDDLE_CENTER);
            addServerWindowLayout.setComponentAlignment(serverDataNet2, Alignment.MIDDLE_CENTER);
            addServerWindowLayout.setComponentAlignment(serverLocation, Alignment.MIDDLE_CENTER);
            addServerWindowLayout.setComponentAlignment(serverOwner, Alignment.MIDDLE_CENTER);

            saveButton.setStyleName(ValoTheme.BUTTON_PRIMARY);
            clearButton.setStyleName(ValoTheme.BUTTON_DANGER);

            HorizontalLayout buttonsLayout = new HorizontalLayout();
            buttonsLayout.setSpacing(true);
            buttonsLayout.setMargin(true);

            buttonsLayout.addComponents(saveButton, clearButton);
            buttonsLayout.setComponentAlignment(saveButton, Alignment.MIDDLE_LEFT);
            buttonsLayout.setComponentAlignment(clearButton, Alignment.MIDDLE_RIGHT);

            addServerWindowLayout.addComponent(buttonsLayout);
            setContent(addServerWindowLayout);

            return this;
        }

        public void buttonClick(Button.ClickEvent event) {
            if (event.getSource() == this.saveButton) {
                saveFields();
            } else {
                clearFields();
            }
        }

        private void saveFields() {
            try {
                fieldGroup.commit();
            } catch (FieldGroup.CommitException e) {
                e.printStackTrace();
            }
        }

        private void clearFields() {
            serverName.setValue(null);
            serverMgmtIP.setValue(null);
            serverDataNet1.setValue(null);
            serverDataNet2.setValue(null);
        }
    }

    public Component createAddServerWindowLayout() {
        return new AddServerWindowLayout().init().bind().createLayout();
    }

}
