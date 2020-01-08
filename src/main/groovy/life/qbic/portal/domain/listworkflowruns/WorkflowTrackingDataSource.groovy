package life.qbic.portal.domain.listworkflowruns

interface WorkflowTrackingDataSource {


    List<RunInfo> listAllWorkflowRunInfo()
    List<Trace> listTracesForRunId(String runId)

}
