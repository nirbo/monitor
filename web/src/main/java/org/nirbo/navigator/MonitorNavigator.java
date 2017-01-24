package org.nirbo.navigator;

import com.vaadin.navigator.Navigator;
import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.UI;

public class MonitorNavigator extends Navigator {

    public MonitorNavigator(UI ui, ComponentContainer container) {
        super(ui, container);
    }

    private static MonitorNavigator getNavigator() {
        UI ui = UI.getCurrent();
        Navigator navigator = ui.getNavigator();

        return (MonitorNavigator) navigator;
    }

    public static void navigate(String path) {
        try {
            MonitorNavigator.getNavigator().navigateTo(path);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void navigateTo(String viewName) {
//        super.navigateTo(Strings.nullToEmpty(viewName));
    }

}
