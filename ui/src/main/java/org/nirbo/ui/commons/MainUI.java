package org.nirbo.ui.commons;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Title;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.spring.navigator.SpringViewProvider;
import com.vaadin.ui.*;
import org.nirbo.navigator.MonitorNavigator;
import org.nirbo.utils.CommonStrings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

@SpringUI(path=MainUI.NAME)
@Title("Monitor App")
@Theme("monitorTheme")
public class MainUI extends UI {

    public static final String NAME = "/";

    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    private SpringViewProvider viewProvider;

    Panel mainContentPanel = new Panel();
    TabSheet mainTabs = createMainTabsLayout();

    @Override
    protected void init(VaadinRequest request) {
        VerticalLayout rootLayout = new VerticalLayout();
        rootLayout.setSizeFull();
        rootLayout.setMargin(true);

        mainContentPanel.setSizeFull();

        HorizontalLayout mainUiLayout = new HorizontalLayout();
        mainUiLayout.setMargin(true);
        mainUiLayout.setSizeFull();
        mainUiLayout.addComponent(mainTabs);
        mainUiLayout.setExpandRatio(mainTabs, 1);

        mainContentPanel.setContent(mainUiLayout);

        rootLayout.addComponent(mainContentPanel);
        rootLayout.setComponentAlignment(mainContentPanel, Alignment.TOP_LEFT);
        rootLayout.setExpandRatio(mainContentPanel, 1);

//        initNavigator();

        setContent(rootLayout);
    }

    private TabSheet createMainTabsLayout() {
        TabSheet mainTabs = new TabSheet();
        mainTabs.setSizeFull();

        VerticalLayout serversTabLayout = new VerticalLayout();
        serversTabLayout.setMargin(true);
        serversTabLayout.addComponent(new Label("Servers Content Placeholder"));

        VerticalLayout ticketsTabLayout = new VerticalLayout();
        ticketsTabLayout.setMargin(true);
        ticketsTabLayout.addComponent(new Label("Tickets Content Placeholder"));

        mainTabs.addTab(serversTabLayout, CommonStrings.MAIN_TABSHEET_SERVERS.getString());
        mainTabs.addTab(ticketsTabLayout, CommonStrings.MAIN_TABSHEET_TICKETS.getString());

        return mainTabs;
    }

    private void initNavigator() {
        MonitorNavigator navigator = new MonitorNavigator(this, mainContentPanel);
        applicationContext.getAutowireCapableBeanFactory().autowireBean(navigator);
        navigator.addProvider(viewProvider);
        navigator.navigateTo(this.NAME);
    }
}
