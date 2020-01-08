package life.qbic.portal.domain.listworkflowruns

interface WorkflowTrackingDataSource {


    def listAllWorkflowRunInfo()
    def listTracesForRunId(String runId)

}