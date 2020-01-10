package life.qbic.portal.portlet

import com.vaadin.ui.Layout
import com.vaadin.ui.VerticalLayout
import groovy.util.logging.Log4j2
import life.qbic.portal.infrastructure.services.ServiceFinder
import life.qbic.portal.infrastructure.services.workflowtracking.WorkflowTrackingServiceConnector
import life.qbic.portal.infrastructure.view.PortletMainView
import life.qbic.portal.utils.ConfigurationManagerFactory
import life.qbic.services.Service

/**
 * Logic and dependency injection class. This class is responsible for creating the key players in this portlet and injecting needed dependencies.
 */
@Log4j2
class DependencyManager {

    private def portletMainView
    /**
     * The constructor creates all objects.
     */
    DependencyManager() {
        try {
            instantiateWithDependencyInjection()
        } catch (Exception e) {
            log.error(e)
            System.exit(1)
        }
    }

    /**
     * initializes all objects of the {@link DependencyManager} instance.
     */
    def instantiateWithDependencyInjection() {
        log.info "Instantiating objects and injecting dependencies in {}.", this.class.getSimpleName()

        // variables that need instantiation
        WorkflowTrackingServiceConnector workflowTrackingServiceConnector
        Layout portletMainView


//        try {
            portletMainView = new PortletMainView()
/*        } catch (Exception e) {
            log.error("Error during Vaadin View creation. Creating empty View.")
            log.error(e)
            portletMainView = new VerticalLayout()
        }*/

//        try {
            def workflowTrackingService = lookupWorkflowTrackingService()
            workflowTrackingServiceConnector = new WorkflowTrackingServiceConnector(workflowTrackingService)
/*        } catch (Exception e) {
            log.error("Error during Service Connector creation.")
            log.error(e)
        }*/

//        try {
            // make selected objects available as fields
            this.portletMainView = portletMainView
            log.debug("Making View available: " + portletMainView)
/*        } catch (Exception e) {
            // If this fails initialization has failed.
            log.error("Could not publish object instances.")
            log.error(e)
            System.exit(1)
        }*/

    }

    Service lookupWorkflowTrackingService() {

        def config = ConfigurationManagerFactory.getInstance()
        def urlString = config.getServicesRegistryUrl()
        def serviceRegistryURL = new URL(urlString)

        List<Service> workflowTrackingServices
        Service workflowTrackingService

        workflowTrackingServices = ServiceFinder.findWorkflowTrackingServices(serviceRegistryURL)

        //try {
            workflowTrackingService = workflowTrackingServices.first()
        /*} catch (NoSuchElementException e) {
            log.error("Could not find any workflow tracking services.")
            log.error(e)
        }*/


        return workflowTrackingService
    }

    def getView() {
        return portletMainView
    }
}
