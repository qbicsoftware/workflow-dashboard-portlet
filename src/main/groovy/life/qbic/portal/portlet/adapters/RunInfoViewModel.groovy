package life.qbic.portal.portlet.adapters

class RunInfoViewModel {

    int pendingWorkflowCount
    int runningWorkflowCount
    int successfulWorkflowCount
    int failedWorkflowCount

    RunInfoViewModel() {
        initWorkflowStatusCount()
    }

    /**
     * Initializes all fields representing a count of workflow status.
     * @return
     */
    def initWorkflowStatusCount() {
        this.pendingWorkflowCount = -1
        this.runningWorkflowCount = -1
        this.successfulWorkflowCount = -1
        this.failedWorkflowCount = -1
    }
}
