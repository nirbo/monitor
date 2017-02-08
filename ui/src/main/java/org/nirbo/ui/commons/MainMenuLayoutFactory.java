package org.nirbo.ui.commons;

import com.vaadin.data.Property;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Layout;
import com.vaadin.ui.Tree;
import com.vaadin.ui.VerticalLayout;
import org.nirbo.ui.navigator.AppNavigator;
import org.nirbo.utils.CommonStrings;

@SpringComponent
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
            mainMenu.addItem(CommonStrings.ADD_SERVER.getString());
            mainMenu.addItem(CommonStrings.SHOW_SERVERS.getString());
            mainMenu.addItem(CommonStrings.TICKETS.getString());

            mainMenu.setChildrenAllowed(CommonStrings.SERVERS.getString(), true);
            mainMenu.setChildrenAllowed(CommonStrings.ADD_SERVER.getString(), false);
            mainMenu.setChildrenAllowed(CommonStrings.SHOW_SERVERS.getString(), false);
            mainMenu.setChildrenAllowed(CommonStrings.TICKETS.getString(), true);

            mainMenu.setParent(CommonStrings.ADD_SERVER.getString(), CommonStrings.SERVERS.getString());
            mainMenu.setParent(CommonStrings.SHOW_SERVERS.getString(), CommonStrings.SERVERS.getString());
            mainMenu.expandItem(CommonStrings.SERVERS.getString());

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
