package life.qbic.portal.portlet.usecases.RetrieveAllRunInfo

import life.qbic.datamodel.workflows.RunInfo

interface RunInfoDataSourceInterface {

    List<RunInfo> requestListOfWorkflowRunInfo()
}