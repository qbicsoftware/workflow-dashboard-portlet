package life.qbic.portal.infrastructure.view.workflowruninfolist


import com.vaadin.ui.VerticalLayout

class WorkflowRunInfoListView extends VerticalLayout {

    private def viewModel

    WorkflowRunInfoListView(WorkflowRunInfoListViewModel viewModel) {
        super()
        this.viewModel = viewModel
        this.createContent()
    }

    private def createContent() {

    }
}
