package life.qbic.portal.infrastructure.view.workflowruninfolist

import life.qbic.portal.domain.listworkflowruns.ListWorkflowRunsOutput
import life.qbic.portal.domain.listworkflowruns.ListWorkflowRunsResponse

class WorkflowRunInfoListPresenter implements ListWorkflowRunsOutput{

    private WorkflowRunInfoListViewModel viewModel

    WorkflowRunInfoListPresenter(WorkflowRunInfoListViewModel viewModel) {
        this.viewModel = viewModel
    }

    @Override
    def receive(ListWorkflowRunsResponse response) {
        return null
    }
}
