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
    List<WorkflowTrackingService> workflowTrackingServiceList

    WorkflowTrackingServiceConnector() {
        this.serviceRegistryURL = loadServiceRegistryURLFromConfig()
        this.workflowTrackingServiceList = listWorkflowTrackingServices()
    }

    private URL loadServiceRegistryURLFromConfig() {
        def config = ConfigurationManagerFactory.getInstance()
        def urlString = config.getServicesRegistryUrl()
        return new URL(urlString)
    }

    private def listWorkflowTrackingServices() {
        ServiceConnector connector = new ConsulConnector(this.serviceRegistryURL)
        def serviceList = []
        def workflowTrackingServiceList = []

        connector.withCloseable {
            ConsulServiceFactory factory = new ConsulServiceFactory(it)
            serviceList.addAll(factory.getServicesOfType(ServiceType.WORKFLOW_TRACKING))
        }

        for (service in serviceList) {
            workflowTrackingServiceList.add(new WorkflowTrackingService((Service) service))
        }

        return workflowTrackingServiceList
    }

    static def queryServiceEndpoint(URL endpoint) {

        def parser = new JsonSlurper()

        def client = RxHttpClient.create(endpoint)
        def request = HttpRequest.GET(endpoint.toURI())

        def response = client.withCloseable { it.toBlocking().retrieve(request) }

        return parser.parse(response.toCharArray())
    }

    @Override
    def listAllWorkflowRunInfo() {
        WorkflowTrackingService workflowTrackingService = this.workflowTrackingServiceList.first()
        return queryServiceEndpoint(workflowTrackingService.getWorkflowListEndpoint()) as List<RunInfo>
    }

    @Override
    def listTracesForRunId(String runId) {
        WorkflowTrackingService workflowTrackingService = this.workflowTrackingServiceList.first()
        return queryServiceEndpoint(workflowTrackingService.getWorkflowTracesEndpoint(runId)) as List<Trace>
    }
}
