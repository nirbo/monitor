package org.nirbo.ui.commons;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Title;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.Label;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import org.nirbo.navigator.MonitorNavigator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

@SpringUI(path=MainUI.NAME)
@Title("Monitor App")
@Theme("monitorTheme")
public class MainUI extends UI {

    public static final String NAME = "/";

    @Autowired
    private ApplicationContext applicationContext;

    @Override
    protected void init(VaadinRequest request) {
        VerticalLayout rootLayout = new MainUILayoutFactory().createMainLayout();
        initNavigator();

        setContent(rootLayout);
    }

    private void initNavigator() {
        MonitorNavigator monitorNavigator = new MonitorNavigator(this, container);
    }
}
