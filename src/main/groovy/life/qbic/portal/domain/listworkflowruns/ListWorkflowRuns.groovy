package life.qbic.portal.domain.listworkflowruns

class ListWorkflowRuns implements ListWorkflowRunsInput {

    private def input
    private def data

    ListWorkflowRuns(ListWorkflowRunsInput input, WorkflowTrackingDataSource data) {
        this.input = input
        this.data = data
    }
}
