package life.qbic.portal.infrastructure.view.workflowruninfolist

import life.qbic.portal.domain.listworkflowruns.ListWorkflowRunsOutput

class WorkflowRunInfoListPresenter implements ListWorkflowRunsOutput{

    private WorkflowRunInfoListViewModel viewModel

    WorkflowRunInfoListPresenter(WorkflowRunInfoListViewModel viewModel) {
        this.viewModel = viewModel
    }
}
