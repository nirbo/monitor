package org.nirbo.ui.commons;

import com.vaadin.data.Property;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Layout;
import com.vaadin.ui.Tree;
import com.vaadin.ui.VerticalLayout;
import org.nirbo.ui.navigator.AppNavigator;
import org.nirbo.utils.CommonStrings;

@org.springframework.stereotype.Component
public class MainMenuLayoutFactory implements UILayoutBuilder {

    private class MainUILayout extends VerticalLayout implements Property.ValueChangeListener {

        private Tree mainMenu;

        public MainUILayout init() {
            mainMenu = new Tree();
            mainMenu.addValueChangeListener(this);

            return this;
        }

        public MainUILayout layout() {
            setMargin(true);

            mainMenu.addItem(CommonStrings.SERVERS.getString());
            mainMenu.setChildrenAllowed(CommonStrings.SERVERS.getString(), true);
            mainMenu.addItem(CommonStrings.ADD_SERVER.getString());
            mainMenu.setParent(CommonStrings.ADD_SERVER.getString(), CommonStrings.SERVERS.getString());
            mainMenu.setChildrenAllowed(CommonStrings.ADD_SERVER.getString(), false);
            mainMenu.expandItem(CommonStrings.SERVERS.getString());

            mainMenu.addItem(CommonStrings.TICKETS.getString());
            mainMenu.setChildrenAllowed(CommonStrings.TICKETS.getString(), true);

            addComponent(mainMenu);
            setComponentAlignment(mainMenu, Alignment.TOP_LEFT);

            return this;
        }

        public void valueChange(Property.ValueChangeEvent event) {
            String selectedItemPath = (String) event.getProperty().getValue();
            if (selectedItemPath == null) return;

            String path = selectedItemPath.toLowerCase().replaceAll("\\s+", "");
            AppNavigator.navigate(path);
        }
    }

    public Layout createComponent() {
        return new MainUILayout().init().layout();
    }

}
