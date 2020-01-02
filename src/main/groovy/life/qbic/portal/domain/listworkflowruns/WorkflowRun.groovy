package life.qbic.portal.domain.listworkflowruns

class WorkflowRun {
    String runName
    String runningTaskName
    Date startTime
    def currentRunStatus
    int submittedCount
    int runningCount
    int completedCount
    int failedCount
}
