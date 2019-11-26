package life.qbic.portal.portlet.adapters

import life.qbic.datamodel.workflows.RunInfo
import life.qbic.portal.portlet.usecases.RetrieveAllRunInfo.RetrieveAllRunInfoOutputBoundary

class RunInfoViewPresenter implements RetrieveAllRunInfoOutputBoundary {
    List<RunInfo> runInfoList
    RunInfoViewModel viewModel

    RunInfoViewPresenter(RunInfoViewModel viewModel) {
        this.viewModel = viewModel
    }

    def present() {
        countByRunStatus()
    }

    def countByRunStatus() {
        def byRunStatus = { runInfo -> runInfo.getRunStatus()}
    }

    @Override
    void receiveRunInfoList(List<RunInfo> runInfoList) {
        this.runInfoList = runInfoList
    }
}
