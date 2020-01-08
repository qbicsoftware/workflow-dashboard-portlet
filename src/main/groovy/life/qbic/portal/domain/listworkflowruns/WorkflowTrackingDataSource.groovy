package life.qbic.portal.domain.listworkflowruns

interface WorkflowTrackingDataSource {


    List<RunInfo> listAllWorkflowRunInfo()
    def listTracesForRunId(String runId)

}
