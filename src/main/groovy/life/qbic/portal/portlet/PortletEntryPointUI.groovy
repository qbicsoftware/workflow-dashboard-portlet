package life.qbic.portal.portlet


import com.vaadin.annotations.Theme
import com.vaadin.annotations.Widgetset
import com.vaadin.server.VaadinRequest
import com.vaadin.ui.Layout
import groovy.transform.CompileStatic
import groovy.util.logging.Log4j2
import life.qbic.portal.infrastructure.view.PortletMainView

/**
 * Entry point for portlet workflow-dashboard. This class derives from {@link QBiCPortletUI}, which is found in the {@code portal-utils-lib} library.
 * This class initializes all components and performs Dependency Injection. It also delivers the {@link com.vaadin.ui.Layout}.
 *
 * @see <ahref="https://github.com/qbicsoftware/portal-utils-lib" > portal-utils-lib</a>
 */
@Theme("mytheme")
@SuppressWarnings("serial")
@Widgetset("life.qbic.portal.portlet.AppWidgetSet")
@Log4j2
@CompileStatic
class PortletEntryPointUI extends QBiCPortletUI {
    private DependencyManager dependencyManager

    PortletEntryPointUI() {
        super()
        this.dependencyManager = new DependencyManager()
    }

    @Override
    protected Layout getPortletContent(final VaadinRequest request) {
        log.info "Generating content for class {}", PortletEntryPointUI.class
        def layout = this.dependencyManager.getView() as Layout
        log.info "Finished content generation for class {}.", PortletEntryPointUI.class
        return layout
    }

    /**
     * Logic and dependency injection class. This class is responsible for creating the key players in this portlet and injecting needed dependencies.
     */
    protected class DependencyManager {

        private def portletMainView
        /**
         * The constructor creates all objects.
         */
        DependencyManager() {
            instantiateWithDependencyInjection()
        }

        /**
         * initializes all objects of the {@link DependencyManager} instance.
         */
        def instantiateWithDependencyInjection() {
            log.info "Instantiating objects and injecting dependencies in {}.", this.class.getSimpleName()
            // After all components are initialized
            this.portletMainView = new PortletMainView()


        }

        def getView() {
            return portletMainView
        }
    }
}


