package org.nirbo.ui.servers;

import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;
import org.nirbo.model.entity.Server;
import org.nirbo.service.removeServer.RemoveServerService;
import org.nirbo.service.showservers.ShowServersService;
import org.nirbo.ui.commons.MainUI;
import org.nirbo.utils.ServerStrings;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@SpringView(name = ShowServersLayoutFactory.NAME, ui = MainUI.class)
@SpringComponent
public class ShowServersLayoutFactory extends VerticalLayout implements View {

    public static final String NAME = "showservers";

    @Autowired
    private ShowServersService showServersService;

    @Autowired
    private RemoveServerService removeServerService;

    private HorizontalLayout actionButtons;
    private List<Server> serverList;
    private BeanItemContainer<Server> container;
    private Grid serversTable;

    public void enter(ViewChangeListener.ViewChangeEvent event) {
        removeAllComponents();
        createLayout();
    }

    private void createLayout() {
        setSizeFull();
        setMargin(true);
        setSpacing(true);

        serverList = showServersService.getAllServers();
        container = new BeanItemContainer<Server>(Server.class, serverList);

        serversTable = new Grid(container);
        serversTable.setSizeFull();
        serversTable.setColumnOrder("serverName", "serverMgmtIP", "serverDataNet1", "serverDataNet2", "serverLocation", "serverOwner");
        serversTable.removeColumn("id");
        serversTable.setImmediate(true);

        createActionButtonsLayout();

        addComponent(actionButtons);
        addComponent(serversTable);
        setExpandRatio(actionButtons, 0.05f);
        setExpandRatio(serversTable, 0.95f);
    }

    private void createActionButtonsLayout() {
        actionButtons = new HorizontalLayout();
        actionButtons.setSpacing(true);

        Button editButton = new Button(ServerStrings.BUTTON_EDIT_SERVER.getString());
        Button deleteButton = new Button(ServerStrings.BUTTON_DELETE_SERVER.getString());

        editButton.setStyleName(ValoTheme.BUTTON_FRIENDLY);
        deleteButton.setStyleName(ValoTheme.BUTTON_DANGER);

        actionButtons.addComponent(editButton);
        actionButtons.addComponent(deleteButton);
        actionButtons.setComponentAlignment(editButton, Alignment.TOP_LEFT);
        actionButtons.setComponentAlignment(deleteButton, Alignment.TOP_LEFT);
    }
}
