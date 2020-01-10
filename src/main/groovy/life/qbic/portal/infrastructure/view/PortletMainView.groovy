package life.qbic.portal.infrastructure.view

import com.vaadin.ui.TextField
import com.vaadin.ui.VerticalLayout

class PortletMainView extends VerticalLayout {

    PortletMainView() {
        super()
        createContent()
    }

    private def createContent() {
        TextField textField = new TextField("Test text", "Lorem ipsum and so on...")
        this.addComponent(textField)
    }
}
