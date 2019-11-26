package life.qbic.portal.portlet.infrastructure

import groovy.json.JsonSlurper
import groovy.util.logging.Log4j2
import io.micronaut.http.client.exceptions.HttpClientException
import life.qbic.datamodel.workflows.RunInfo
import life.qbic.portal.portlet.entities.WorkflowTrackingService
import life.qbic.portal.portlet.usecases.RetrieveAllRunInfo.RunInfoDataInterface
import life.qbic.portal.utils.ConfigurationManagerFactory
import life.qbic.services.ConsulServiceFactory
import life.qbic.services.ServiceConnector
import life.qbic.services.ServiceType
import life.qbic.services.connectors.ConsulConnector

@Log4j2
class WorkflowTrackingServiceAdapter implements RunInfoDataInterface {

    private URL serviceRegistryUrl
    private List<WorkflowTrackingService> workflowTrackingServices

    WorkflowTrackingServiceAdapter() {
        loadServiceRegistryUrlFromConfig()
        refreshWorkflowTrackingServices()
    }

    /**
     * sets the workflow tracking service instance in this class
     */
    def refreshWorkflowTrackingServices() {
        ServiceConnector connector = new ConsulConnector(this.serviceRegistryUrl)
        def serviceList = []

        // retrieve list of WORKFLOW_TRACKING services
        connector.withCloseable {
            ConsulServiceFactory factory = new ConsulServiceFactory(it)
            serviceList.addAll(factory.getServicesOfType(ServiceType.WORKFLOW_TRACKING))
        }
        // cast them to the correct type
        // refresh the workflowTrackingServices List
        this.workflowTrackingServices.clear()
        for (service in serviceList) {
            this.workflowTrackingServices.add((WorkflowTrackingService) service)
        }
    }

    /**
     * This method queries the configuration for the ServiceRegistryURL
     * @return
     */
    private URL loadServiceRegistryUrlFromConfig() {
        // Read properties
        def config = ConfigurationManagerFactory.getInstance()
        // Setup service registry
        def url_string = config.getServicesRegistryUrl()
        this.serviceRegistryUrl = new URL(url_string)
        log.info("Successfully loaded service registry URL {} from configuration.", this.serviceRegistryUrl.toURI())
    }

    @Override
    List<RunInfo> requestListOfWorkflowRunInfo() {
        def parser = new JsonSlurper()
        List<RunInfo> workflowRunInfoList = []

        if (this.workflowTrackingServices.isEmpty()) {
            refreshWorkflowTrackingServices()
        }

        for (service in this.workflowTrackingServices) {
            try {
                workflowRunInfoList = parser.parse(service.getWorkflowListEndpoint())
                break
            } catch (HttpClientException e) {
                log.error("Could not connect to {} because of {}", service.getWorkflowListEndpoint(), e
                        .toString())
            }
        }

        if (workflowRunInfoList.isEmpty()) {
            log.warn("No workflow run information could be obtained. Returning empty List.")
        }

        return workflowRunInfoList
    }


}
