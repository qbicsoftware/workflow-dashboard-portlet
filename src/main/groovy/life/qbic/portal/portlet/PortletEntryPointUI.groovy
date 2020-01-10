package life.qbic.portal.portlet

import com.vaadin.annotations.Theme
import com.vaadin.annotations.Widgetset
import com.vaadin.server.VaadinRequest
import com.vaadin.ui.Layout
import com.vaadin.ui.VerticalLayout
import groovy.transform.CompileStatic
import groovy.util.logging.Log4j2

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
        init()
    }


    private def init() {
        try {
            this.dependencyManager = new DependencyManager()
        } catch (Exception e) {

        }
    }

    @Override
    protected Layout getPortletContent(final VaadinRequest request) {
        def layout
        log.info "Generating content for class {}", PortletEntryPointUI.class
        try {
            layout = this.dependencyManager.getView() as Layout
            log.info "Finished content generation for class {}.", PortletEntryPointUI.class
        } catch (Exception e) {
            log.error(e)
            log.error("Failed generating portlet content. Creating empty Layout.")
            layout = new VerticalLayout()
        }
        return layout
    }
}


