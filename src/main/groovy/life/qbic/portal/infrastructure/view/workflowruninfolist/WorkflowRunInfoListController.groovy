package life.qbic.portal.infrastructure.view.workflowruninfolist

import life.qbic.portal.domain.listworkflowruns.ListWorkflowRunsInput

class WorkflowRunInfoListController implements WorkflowRunInfoListViewModelObserver{

    private def listWorkflowRunsInput

    WorkflowRunInfoListController(ListWorkflowRunsInput listWorkflowRunsInput) {
        this.listWorkflowRunsInput = listWorkflowRunsInput
    }
}
