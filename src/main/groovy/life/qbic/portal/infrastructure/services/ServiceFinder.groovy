package life.qbic.portal.infrastructure.services

import groovy.util.logging.Log4j2
import life.qbic.services.ConsulServiceFactory
import life.qbic.services.Service
import life.qbic.services.ServiceConnector
import life.qbic.services.ServiceType
import life.qbic.services.connectors.ConsulConnector

@Log4j2
class ServiceFinder {

    static List<Service> findWorkflowTrackingServices(URL serviceRegistryUrl) {
        ServiceConnector connector = new ConsulConnector(serviceRegistryUrl)
        def serviceList = []

        connector.withCloseable {
            ConsulServiceFactory factory = new ConsulServiceFactory(it)
            serviceList.addAll(factory.getServicesOfType(ServiceType.WORKFLOW_TRACKING))
        }

        if (serviceList.isEmpty()) {
            log.warn("Could not find a workflow tracking service in the repository. Returning empty List.")
        }

        return serviceList
    }
}
