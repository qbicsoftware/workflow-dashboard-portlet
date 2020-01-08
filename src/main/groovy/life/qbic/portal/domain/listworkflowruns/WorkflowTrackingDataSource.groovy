package life.qbic.portal.domain.listworkflowruns

import life.qbic.datamodel.workflows.RunInfo
import life.qbic.datamodel.workflows.Trace

interface WorkflowTrackingDataSource {


    List<RunInfo> listAllWorkflowRunInfo()
    List<Trace> listTracesForRunId(String runId)

}
