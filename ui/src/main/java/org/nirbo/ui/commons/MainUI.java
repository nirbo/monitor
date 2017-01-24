package org.nirbo.ui.commons;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Title;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.Label;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

@SpringUI(path=MainUI.NAME)
@Title("Monitor App Title")
@Theme("monitorTheme")
public class MainUI extends UI {

    public static final String NAME = "/";

    @Override
    protected void init(VaadinRequest request) {
        VerticalLayout rootLayout = new VerticalLayout();
        rootLayout.addComponent(new Label("Testing"));
        rootLayout.setMargin(true);

        setContent(rootLayout);
    }
}
