package life.qbic.portal.portlet;

import life.qbic.portal.portlet.views.RunInfoVaadinView;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/** Tests for {@link RunInfoVaadinView}. */
public class RunInfoVaadinViewTest {

  @Test
  public void mainUIExtendsQBiCPortletUI() {
    assertTrue(
        "The main UI class must extend life.qbic.portlet.QBiCPortletUI",
        QBiCPortletUI.class.isAssignableFrom(RunInfoVaadinView.class));
  }

  @Test
  public void mainUIIsNotQBiCPortletUI() {
    assertFalse(
        "The main UI class must be different to life.qbic.portlet.QBiCPortletUI",
        QBiCPortletUI.class.equals(RunInfoVaadinView.class));
  }
}
