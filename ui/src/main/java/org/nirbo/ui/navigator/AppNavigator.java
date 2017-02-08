package org.nirbo.ui.navigator;

import com.google.common.base.Strings;
import com.vaadin.navigator.Navigator;
import com.vaadin.ui.SingleComponentContainer;
import com.vaadin.ui.UI;

public class AppNavigator extends Navigator {

    public AppNavigator(UI ui, SingleComponentContainer container) {
        super(ui, container);
    }

    private static AppNavigator getNavigator() {
        UI ui = UI.getCurrent();
        Navigator navigator = ui.getNavigator();

        return (AppNavigator) navigator;
    }

    public static void navigate(String path) {
        try {
            AppNavigator.getNavigator().navigateTo(path);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void navigateTo(String viewName) {
        super.navigateTo(Strings.nullToEmpty(viewName));
    }
}
