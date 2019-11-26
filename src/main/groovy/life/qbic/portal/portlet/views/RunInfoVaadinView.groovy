package life.qbic.portal.portlet.views

import com.vaadin.annotations.Theme
import com.vaadin.annotations.Widgetset
import com.vaadin.server.VaadinRequest
import com.vaadin.ui.*
import groovy.transform.CompileStatic
import groovy.util.logging.Log4j2
import life.qbic.portal.portlet.QBiCPortletUI
import life.qbic.portal.portlet.infrastructure.WorkflowTrackingServiceAdapter

/**
 * Entry point for portlet workflow-dashboard. This class derives from {@link QBiCPortletUI}, which is found in the {@code portal-utils-lib} library.
 *
 * @see <ahref=https://github.com/qbicsoftware/portal-utils-lib>     portal-utils-lib</a>
 */
@Theme("mytheme")
@SuppressWarnings("serial")
@Widgetset("life.qbic.portal.portlet.AppWidgetSet")
@Log4j2
@CompileStatic
class RunInfoVaadinView extends QBiCPortletUI {
    @Override
    protected Layout getPortletContent(final VaadinRequest request) {
        log.info "Generating content for ${RunInfoVaadinView}"

        // TODO: generate content for your portlet
        //       this method returns any non-null layout to avoid a NullPointerException later on
        def layout = new VerticalLayout()

        // TODO remove
        WorkflowTrackingServiceAdapter workflowTrackingServiceAdapter = new WorkflowTrackingServiceAdapter()

        def name = new TextField()
        name.setCaption("Type your name here:")

        Button button = new Button("Click Me please!")
        button.addClickListener({ e ->
            layout.addComponent(new Label("Thanks " + name.getValue()
                    + ", it works!"))
        })
        layout.addComponents(name, button)
        return layout
    }
}


