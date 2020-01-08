package life.qbic.portal.infrastructure.services.workflowtracking

import groovy.json.JsonSlurper
import io.micronaut.http.HttpRequest
import io.micronaut.http.client.RxHttpClient
import life.qbic.datamodel.workflows.RunInfo
import life.qbic.datamodel.workflows.Trace
import life.qbic.portal.domain.listworkflowruns.WorkflowTrackingDataSource
import life.qbic.services.Service

class WorkflowTrackingServiceConnector implements WorkflowTrackingDataSource {

    private Service workflowTrackingService

    WorkflowTrackingServiceConnector(Service workflowTrackingService) {
        this.workflowTrackingService = workflowTrackingService
    }

    static def queryServiceEndpoint(URL endpoint) {

        def parser = new JsonSlurper()

        def client = RxHttpClient.create(endpoint)
        def request = HttpRequest.GET(endpoint.toURI())

        String response = client.withCloseable { it.toBlocking().retrieve(request) }

        return parser.parse(response.toCharArray())
    }

    @Override
    List<RunInfo> listAllWorkflowRunInfo() {
        URL listWorkflowRunsURL = new URL(this.workflowTrackingService.getRootUrl() + Endpoints.WORKFLOW_LIST)
        def response = queryServiceEndpoint(listWorkflowRunsURL)
        return response as List<RunInfo>
    }

    @Override
    List<Trace> listTracesForRunId(String runId) {
        URL listTracesURL = new URL(this.workflowTrackingService.getRootUrl() + Endpoints.WORKFLOW_TRACES + runId)
        def response = queryServiceEndpoint(listTracesURL)
        return response as List<Trace>
    }
}
