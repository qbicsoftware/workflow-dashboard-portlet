package life.qbic.portal.infrastructure.services

import groovy.util.logging.Log4j2
import life.qbic.portal.utils.ConfigurationManagerFactory
import life.qbic.services.ConsulServiceFactory
import life.qbic.services.Service
import life.qbic.services.ServiceConnector
import life.qbic.services.ServiceType
import life.qbic.services.connectors.ConsulConnector

@Log4j2
class ServiceFinder {

    static List<Service> findWorkflowTrackingServices(URL serviceRegistryUrl) throws NullPointerException {
        ServiceConnector connector = new ConsulConnector()
        def serviceList = []

        connector.withCloseable {
            ConsulServiceFactory factory = new ConsulServiceFactory(it)
            serviceList.addAll(factory.getServicesOfType(ServiceType.WORKFLOW_TRACKING))
        }

        if (serviceList.isEmpty()) {
            def nullPointer = new NullPointerException("No workflow tracking service was found in repository: " + this.serviceRegistryURL.getText())
            log.error(nullPointer.getMessage())
            throw nullPointer
        } else {
            log.info("Found at least one workflow tracking service.")
            log.debug("Address of first service: " + serviceList.get(0).getRootUrl())
        }
        return serviceList
    }
}
