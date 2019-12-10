package life.qbic.portal.infrastructure.view

import com.vaadin.ui.Layout
import com.vaadin.ui.VerticalLayout
import life.qbic.portal.infrastructure.view.workflowruninfolist.WorkflowRunInfoListViewModelObserver

class PortletMainView extends VerticalLayout implements WorkflowRunInfoListViewModelObserver, Layout {

    PortletMainView() {
        super()
        createContent()
    }

    private def createContent() {

    }
}
