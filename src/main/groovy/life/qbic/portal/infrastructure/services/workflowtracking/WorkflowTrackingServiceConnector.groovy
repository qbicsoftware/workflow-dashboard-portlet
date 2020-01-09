package life.qbic.portal.infrastructure.services.workflowtracking

import groovy.json.JsonSlurper
import groovy.util.logging.Log4j2
import io.micronaut.http.HttpRequest
import io.micronaut.http.HttpResponse
import io.micronaut.http.HttpStatus
import io.micronaut.http.client.RxHttpClient
import io.micronaut.http.client.exceptions.HttpClientResponseException
import life.qbic.datamodel.workflows.RunInfo
import life.qbic.datamodel.workflows.Trace
import life.qbic.portal.domain.listworkflowruns.WorkflowTrackingDataSource
import life.qbic.services.Service

@Log4j2
class WorkflowTrackingServiceConnector implements WorkflowTrackingDataSource {

    private Service workflowTrackingService

    WorkflowTrackingServiceConnector(Service workflowTrackingService) {
        this.workflowTrackingService = workflowTrackingService
    }

    static def queryServiceEndpoint(URL endpoint) {

        def parser = new JsonSlurper()

        def client = RxHttpClient.create(endpoint)
        def request = HttpRequest.GET(endpoint.toURI())

        def parsedResponse

        try {
            def response = client.withCloseable { it.toBlocking().exchange(request, String) }
            parsedResponse = parser.parseText(response.getBody() as String)
        } catch (HttpClientResponseException e) {
            log.error("Could not query service endpoint.")
            if (e.getResponse().getStatus() != HttpStatus.OK) {
                log.error("There was a problem with the Response code: " + e.getResponse().getStatus())
            }
            throw e
        }

        return parsedResponse
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
