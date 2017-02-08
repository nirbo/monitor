package org.nirbo.ui.servers;

import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Grid;
import com.vaadin.ui.VerticalLayout;
import org.nirbo.model.entity.Server;
import org.nirbo.service.showservers.ShowServersService;
import org.nirbo.ui.commons.MainUI;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@SpringView(name = ShowServersLayoutFactory.NAME, ui = MainUI.class)
@SpringComponent
public class ShowServersLayoutFactory extends VerticalLayout implements View {

    public static final String NAME = "showservers";

    @Autowired
    private ShowServersService showServersService;

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

        addComponent(serversTable);
    }
}
