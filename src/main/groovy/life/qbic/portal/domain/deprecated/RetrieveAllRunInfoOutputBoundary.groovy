package life.qbic.portal.domain.deprecated

import life.qbic.datamodel.workflows.RunInfo

interface RetrieveAllRunInfoOutputBoundary {

    void receiveRunInfoList(List<RunInfo> runInfoList)

}