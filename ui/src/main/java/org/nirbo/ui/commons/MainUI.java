package org.nirbo.ui.commons;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Title;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.spring.navigator.SpringViewProvider;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Panel;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import org.nirbo.ui.navigator.AppNavigator;
import org.nirbo.ui.servers.AddServerLayoutFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

@SpringUI(path = MainUI.NAME)
@Title("Monitor App")
@Theme("monitorTheme")
public class MainUI extends UI {

    public static final String NAME = "/";

    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    private MainMenuLayoutFactory mainMenuLayoutFactory;

    @Autowired
    private SpringViewProvider viewProvider;

    private Panel contentPanel;

    @Override
    protected void init(VaadinRequest request) {
        initMainLayout();
    }

    private void initMainLayout() {
        HorizontalLayout rootLayout = new HorizontalLayout();
        rootLayout.setSizeFull();
        rootLayout.setSpacing(true);
        rootLayout.setMargin(true);

        Panel menuPanel = new Panel();
        menuPanel.setSizeFull();

        contentPanel = new Panel();
        contentPanel.setSizeFull();

        initNavigator();

        VerticalLayout menuLayout = (VerticalLayout) mainMenuLayoutFactory.createComponent();

        rootLayout.addComponent(menuPanel);
        rootLayout.addComponent(contentPanel);
        rootLayout.setExpandRatio(menuPanel, 0.15f);
        rootLayout.setExpandRatio(contentPanel, 0.85f);

        menuPanel.setContent(menuLayout);
        setContent(rootLayout);
    }

    private void initNavigator() {
        AppNavigator navigator = new AppNavigator(this, contentPanel);
        applicationContext.getAutowireCapableBeanFactory().autowireBean(navigator);
        navigator.addProvider(viewProvider);
        navigator.navigateTo(AddServerLayoutFactory.NAME);
    }
}
