package org.nirbo.ui.servertab;

import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;
import org.nirbo.ui.commons.MainUI;
import org.nirbo.utils.CommonStrings;
import org.nirbo.utils.ServerStrings;
import org.springframework.beans.factory.annotation.Autowired;

@org.springframework.stereotype.Component
public class ServersTabUILayoutFactory implements ServersTabUILayoutBuilder {

    @Autowired
    private AddServerWindowLayoutFactory addServerWindowLayoutFactory;

    private Button addServerButton;

    private class ServersTabUILayout extends VerticalLayout implements Button.ClickListener {
        public ServersTabUILayout init() {
            this.setMargin(true);
            this.setSpacing(true);

            addServerButton = new Button();
            addServerButton.setCaption(ServerStrings.ADD_SERVER.getString());
            addServerButton.setStyleName(ValoTheme.BUTTON_FRIENDLY);
            addServerButton.addClickListener(this);

            return this;
        }

        public ServersTabUILayout createLayout() {
            addComponent(addServerButton);
            setComponentAlignment(addServerButton, Alignment.TOP_LEFT);

            return this;
        }

        public void buttonClick(Button.ClickEvent event) {
            if (event.getSource() == addServerButton) {
                MainUI.getCurrent().addWindow((Window) addServerWindowLayoutFactory.createAddServerWindowLayout());
            }
        }
    }

    public Layout createServersTabLayout() {
        return new ServersTabUILayout().init().createLayout();
    }
}
