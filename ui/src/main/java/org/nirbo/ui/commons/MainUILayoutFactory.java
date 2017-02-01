package org.nirbo.ui.commons;

import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;
import org.nirbo.ui.servertab.ServersTabUILayoutFactory;
import org.nirbo.utils.CommonStrings;
import org.springframework.beans.factory.annotation.Autowired;

@org.springframework.stereotype.Component
public class MainUILayoutFactory implements MainUIComponentBuilder {

    @Autowired
    private ServersTabUILayoutFactory serversTabUILayoutFactory;

    private TabSheet mainTabs;

    private class MainUILayout extends VerticalLayout {

        public MainUILayout init() {
            mainTabs = new TabSheet();
            mainTabs.setSizeFull();
            mainTabs.addStyleName(ValoTheme.TABSHEET_EQUAL_WIDTH_TABS);
            mainTabs.addStyleName(ValoTheme.TABSHEET_PADDED_TABBAR);

            Layout serversTabLayout = serversTabUILayoutFactory.createServersTabLayout();

            VerticalLayout ticketsTabLayout = new VerticalLayout();
            ticketsTabLayout.setMargin(true);
            ticketsTabLayout.addComponent(new Label("Tickets Content Placeholder"));

            mainTabs.addTab(serversTabLayout, CommonStrings.MAIN_TABSHEET_SERVERS.getString());
            mainTabs.addTab(ticketsTabLayout, CommonStrings.MAIN_TABSHEET_TICKETS.getString());

            this.setMargin(true);
            this.setSizeFull();
            this.addComponent(mainTabs);
            this.setExpandRatio(mainTabs, 1);

            return this;
        }

        public MainUILayout createLayout() {
            addComponent(mainTabs);
            setComponentAlignment(mainTabs, Alignment.TOP_LEFT);

            return this;
        }
    }

    public Component createLayoutComponent() {
        return new MainUILayout().init().createLayout();
    }

}
