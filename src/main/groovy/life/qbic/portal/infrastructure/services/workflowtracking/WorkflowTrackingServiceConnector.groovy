package life.qbic.portal.infrastructure.services.workflowtracking

import groovy.json.JsonSlurper
import io.micronaut.http.HttpRequest
import io.micronaut.http.client.RxHttpClient
import life.qbic.datamodel.workflows.RunInfo
import life.qbic.datamodel.workflows.Trace
import life.qbic.portal.domain.listworkflowruns.WorkflowTrackingDataSource
import life.qbic.portal.utils.ConfigurationManagerFactory
import life.qbic.services.ConsulServiceFactory
import life.qbic.services.Service
import life.qbic.services.ServiceConnector
import life.qbic.services.ServiceType
import life.qbic.services.connectors.ConsulConnector

class WorkflowTrackingServiceConnector implements WorkflowTrackingDataSource {

    URL serviceRegistryURL
    List<Service> workflowTrackingServiceList

    private Service workflowTrackingService

    WorkflowTrackingServiceConnector(Service workflowTrackingService) {
        this.serviceRegistryURL = loadServiceRegistryURLFromConfig()
        this.workflowTrackingServiceList = listWorkflowTrackingServices()
        this.workflowTrackingService = workflowTrackingService
    }

    private URL loadServiceRegistryURLFromConfig() {
        def config = ConfigurationManagerFactory.getInstance()
        def urlString = config.getServicesRegistryUrl()
        return new URL(urlString)
    }

    private def listWorkflowTrackingServices() {
        ServiceConnector connector = new ConsulConnector(this.serviceRegistryURL)
        def serviceList = []

        connector.withCloseable {
            ConsulServiceFactory factory = new ConsulServiceFactory(it)
            serviceList.addAll(factory.getServicesOfType(ServiceType.WORKFLOW_TRACKING))
        }

        return serviceList
    }

    static def queryServiceEndpoint(URL endpoint) {

        def parser = new JsonSlurper()

        def client = RxHttpClient.create(endpoint)
        def request = HttpRequest.GET(endpoint.toURI())

        String response = client.withCloseable { it.toBlocking().retrieve(request) }

        return parser.parse(response.toCharArray())
    }

    @Override
    def listAllWorkflowRunInfo() {
        URL listWorkflowRunsURL = new URL(this.workflowTrackingService.getRootUrl() + WorkflowTrackingServiceEndpoints.WORKFLOW_LIST)
        return queryServiceEndpoint(listWorkflowRunsURL)
    }

    @Override
    def listTracesForRunId(String runId) {
        URL listTracesURL = new URL(this.workflowTrackingService.getRootUrl() + WorkflowTrackingServiceEndpoints.WORKFLOW_TRACES + runId)
        return queryServiceEndpoint(listTracesURL)
    }
}
