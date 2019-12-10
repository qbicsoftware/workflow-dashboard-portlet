package life.qbic.portal.domain.deprecated

import life.qbic.datamodel.workflows.RunInfo

interface RunInfoDataSourceInterface {

    List<RunInfo> requestListOfWorkflowRunInfo()
}