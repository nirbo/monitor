package org.nirbo.ui.commons;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Title;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.*;
import org.springframework.beans.factory.annotation.Autowired;

@SpringUI
@Title("Monitor App")
@Theme("monitorTheme")
public class MainUI extends UI {

    @Autowired
    private MainUILayoutFactory mainUILayoutFactory;

    @Override
    protected void init(VaadinRequest request) {
        initMainLayout();
    }

    private void initMainLayout() {
        VerticalLayout rootLayout = new VerticalLayout();
        Panel mainContentPanel = new Panel();

        rootLayout.setSizeFull();
        rootLayout.setMargin(true);

        mainContentPanel.setSizeFull();
        mainContentPanel.setContent(mainUILayoutFactory.createLayoutComponent());

        rootLayout.addComponent(mainContentPanel);
        rootLayout.setComponentAlignment(mainContentPanel, Alignment.TOP_LEFT);
        rootLayout.setExpandRatio(mainContentPanel, 1);

        setContent(rootLayout);
    }

}
