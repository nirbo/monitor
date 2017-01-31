package org.nirbo.ui.serverstab;

import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Layout;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;
import org.nirbo.utils.CommonStrings;

@org.springframework.stereotype.Component
public class ServersTabUILayoutFactory implements ServersTabUILayoutBuilder {

    private Button addServerButton;

    private class ServersTabUILayout extends VerticalLayout {
        public ServersTabUILayout init() {
            this.setMargin(true);
            this.setSpacing(true);

            addServerButton = new Button();
            addServerButton.setCaption(CommonStrings.ADD_SERVER.getString());
            addServerButton.setStyleName(ValoTheme.BUTTON_FRIENDLY);

            return this;
        }

        public ServersTabUILayout createLayout() {
            addComponent(addServerButton);
            setComponentAlignment(addServerButton, Alignment.TOP_LEFT);

            return this;
        }
    }

    public Layout createServersTabLayout() {
        return new ServersTabUILayout().init().createLayout();
    }
}
