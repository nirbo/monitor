package org.nirbo.ui.commons;

import com.vaadin.ui.Alignment;
import com.vaadin.ui.Label;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.VerticalLayout;
import org.nirbo.utils.CommonStrings;

public class MainUILayoutFactory implements MainUILayoutBuilder {

    private class MainUILayout extends VerticalLayout {
        TabSheet mainUITabs;

        public MainUILayout init(){
            mainUITabs = new TabSheet();
            mainUITabs.setWidth("100%");

            VerticalLayout serversTabLayout = new VerticalLayout();
            serversTabLayout.setMargin(true);
            serversTabLayout.addComponent(new Label(CommonStrings.MAIN_TABSHEET_SERVERS.getString()));

            VerticalLayout ticketsTabLayout = new VerticalLayout();
            ticketsTabLayout.setMargin(true);
            ticketsTabLayout.addComponent(new Label(CommonStrings.MAIN_TABSHEET_TICKETS.getString()));

            mainUITabs.addTab(serversTabLayout, CommonStrings.MAIN_TABSHEET_SERVERS.getString());
            mainUITabs.addTab(ticketsTabLayout, CommonStrings.MAIN_TABSHEET_TICKETS.getString());

            return this;
        }

        public MainUILayout layout() {
            this.setWidth("100%");
            this.setMargin(true);
            this.addComponent(mainUITabs);
            this.setComponentAlignment(mainUITabs, Alignment.TOP_LEFT);

            return this;
        }
    }

    public VerticalLayout createMainLayout() {
        return new MainUILayout().init().layout();
    }
}
