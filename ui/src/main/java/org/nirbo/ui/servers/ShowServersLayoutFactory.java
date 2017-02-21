package org.nirbo.ui.servers;

import com.vaadin.addon.contextmenu.ContextMenu;
import com.vaadin.addon.contextmenu.GridContextMenu;
import com.vaadin.addon.contextmenu.Menu;
import com.vaadin.addon.contextmenu.MenuItem;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.event.SelectionEvent;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.FontAwesome;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;
import org.nirbo.model.entity.Server;
import org.nirbo.notifier.ServerNotifier;
import org.nirbo.service.addserver.AddServerService;
import org.nirbo.service.removeServer.RemoveServerService;
import org.nirbo.service.showservers.ShowServersService;
import org.nirbo.service.updateserver.UpdateServerService;
import org.nirbo.ui.commons.MainUI;
import org.nirbo.ui.components.EditServerWindow;
import org.nirbo.utils.CommonStrings;
import org.nirbo.utils.ServerStrings;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@SpringView(name = ShowServersLayoutFactory.NAME, ui = MainUI.class)
@SpringComponent
public class ShowServersLayoutFactory extends VerticalLayout implements View, Button.ClickListener, SelectionEvent.SelectionListener,
                                                                GridContextMenu.GridContextMenuOpenListener {

    public static final String NAME = "showservers";

    @Autowired
    private ShowServersService showServersService;

    @Autowired
    private RemoveServerService removeServerService;

    @Autowired
    private AddServerService addServerService;

    @Autowired
    private UpdateServerService updateServerService;

    private HorizontalLayout actionButtons;
    private Button multiSelectButton;
    private Button editButton;
    private Button deleteButton;
    private Boolean isMultiSelectMode = false;
    private Boolean isGridRowSelected = false;

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

        getServersFromDb();

        serversTable = new Grid(container);
        serversTable.setSizeFull();
        serversTable.setColumnOrder("serverName", "serverMgmtIP", "serverDataNet1", "serverDataNet2", "serverLocation", "serverOwner");
        serversTable.removeColumn("id");
        serversTable.setImmediate(true);
        serversTable.addSelectionListener(this);

        GridContextMenu gridContextMenu = new GridContextMenu(serversTable);
        gridContextMenu.addGridBodyContextMenuListener(this);

        createActionButtonsLayout();

        addComponent(actionButtons);
        addComponent(serversTable);
        setExpandRatio(actionButtons, 0.05f);
        setExpandRatio(serversTable, 0.95f);
    }

    private void getServersFromDb() {
        serverList = showServersService.getAllServers();
        container = new BeanItemContainer<Server>(Server.class, serverList);
    }

    private void createActionButtonsLayout() {
        actionButtons = new HorizontalLayout();
        actionButtons.setSpacing(true);

        multiSelectButton = new Button(ServerStrings.BUTTON_MULTI_SELECT.getString());
        editButton = new Button(ServerStrings.BUTTON_EDIT_SERVER.getString());
        deleteButton = new Button(ServerStrings.BUTTON_DELETE_SERVER.getString());

        multiSelectButton.setStyleName(ValoTheme.BUTTON_FRIENDLY);
        editButton.setStyleName(ValoTheme.BUTTON_FRIENDLY);
        deleteButton.setStyleName(ValoTheme.BUTTON_DANGER);

        multiSelectButton.addClickListener(this);
        editButton.addClickListener(this);
        deleteButton.addClickListener(this);

        actionButtons.addComponent(multiSelectButton);
        actionButtons.addComponent(editButton);
        actionButtons.addComponent(deleteButton);
        actionButtons.setComponentAlignment(multiSelectButton, Alignment.TOP_LEFT);
        actionButtons.setComponentAlignment(editButton, Alignment.TOP_LEFT);
        actionButtons.setComponentAlignment(deleteButton, Alignment.TOP_LEFT);
    }

    public void buttonClick(Button.ClickEvent event) {
        if (event.getSource() == this.multiSelectButton) {
            setMultiSelectMode();
        } else if (event.getSource() == this.editButton){
            editServer();
        } else {
            removeServer();
        }
    }

    private void setMultiSelectMode() {
        if (! isMultiSelectMode) {
            serversTable.setSelectionMode(Grid.SelectionMode.MULTI);
            isMultiSelectMode = true;
        } else {
            serversTable.setSelectionMode(Grid.SelectionMode.SINGLE);
            isMultiSelectMode = false;
        }

    }

    private void editServer() {
    }

    private void removeServer() {
        if (isGridRowSelected) {
            if (isMultiSelectMode) {
                Grid.MultiSelectionModel selectionModel = (Grid.MultiSelectionModel) serversTable.getSelectionModel();

                for (Object selectedItem : selectionModel.getSelectedRows()) {
                    Server server = (Server) selectedItem;
                    serversTable.getContainerDataSource().removeItem(server);
                    removeServerService.removeServer(server);
                    serversTable.deselect(server);
                }

                serversTable.getSelectionModel().reset();
                setMultiSelectMode();
                ServerNotifier.removeServerSuccessNotify();

            } else {
                Grid.SingleSelectionModel selectionModel = (Grid.SingleSelectionModel) serversTable.getSelectionModel();
                Object selectedItem = selectionModel.getSelectedRow();
                if (serversTable.isSelected(selectedItem)) {
                    Server server = (Server) selectedItem;
                    serversTable.getContainerDataSource().removeItem(server);
                    removeServerService.removeServer(server);
                    serversTable.deselect(server);
                    ServerNotifier.removeServerSuccessNotify();
                } else {
                    ServerNotifier.noRowSelectedNotify();
                }
            }

        } else {
            ServerNotifier.noRowSelectedNotify();
        }
    }

    public void select(SelectionEvent event) {
        if (event.getSelected() != null) {
            isGridRowSelected = true;
        }
    }

    public void onContextMenuOpen(final GridContextMenuOpenEvent event) {
        ContextMenu contextMenu = event.getContextMenu();
        contextMenu.removeItems();
        final Object itemId = event.getItemId();
        if (itemId != null) {
            contextMenu.addItem(CommonStrings.EDIT.getString(), FontAwesome.EDIT, new Menu.Command() {

                public void menuSelected(MenuItem selectedItem) {
                    Grid.SingleSelectionModel selectionModel = (Grid.SingleSelectionModel) serversTable.getSelectionModel();
                    selectionModel.select(event.getItemId());
                    Object item = selectionModel.getSelectedRow();
                    if (serversTable.isSelected(item)) {
                        Server server = (Server) item;
                        Server serverFromDb = showServersService.getServerById(server.getId());
                        EditServerWindow editWindow = new EditServerWindow(ServerStrings.EDIT_SERVER.getString(), serverFromDb,
                                                                                updateServerService, showServersService, serversTable);
                        UI.getCurrent().addWindow(editWindow);
                    } else {
                        ServerNotifier.noRowSelectedNotify();
                    }
                }
            });
        }
    }
}
