package life.qbic.portal.portlet.usecases.RetrieveAllRunInfo

import life.qbic.datamodel.workflows.RunInfo

interface RetrieveAllRunInfoOutputBoundary {

    void receiveRunInfoList(List<RunInfo> runInfoList)

}