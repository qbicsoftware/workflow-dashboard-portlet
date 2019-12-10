package life.qbic.portal.infrastructure.services.workflowtracking

import life.qbic.services.Service
import life.qbic.services.ServiceType


class WorkflowTrackingService extends Service {


    URL workflowListEndpoint

    private URL workflowInfoRootURL
    private URL workflowTracesRootURL
    private URL workflowMetadataRootURL

    WorkflowTrackingService(URL rootUrl) {
        super(ServiceType.WORKFLOW_TRACKING, rootUrl)
        this.workflowListEndpoint = new URL(this.rootUrl.toExternalForm() + '/workflows')
        this.workflowInfoRootURL = new URL(this.rootUrl.toExternalForm() + '/workflows/info')
        this.workflowTracesRootURL = new URL(this.rootUrl.toExternalForm() + '/workflows/traces')
        this.workflowMetadataRootURL = new URL(this.rootUrl.toExternalForm() + '/workflows/metadata')
    }

    URL getWorkflowInfoEndpoint(def runID) {
        return new URL(this.workflowInfoRootURL + '/' + runID)
    }

    URL getWorkflowTracesEndpoint(def runID) {
        return new URL(this.workflowTracesRootURL + '/' + runID)
    }

    URL getWorkflowMetadataEndpoint(def runID) {
        return new URL(this.workflowMetadataRootURL + '/' + runID)
    }
}
