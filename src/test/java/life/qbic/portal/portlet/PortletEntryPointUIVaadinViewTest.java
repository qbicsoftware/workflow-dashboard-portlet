package life.qbic.portal.portlet;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/** Tests for {@link PortletEntryPointUI}. */
public class PortletEntryPointUIVaadinViewTest {

  @Test
  public void mainUIExtendsQBiCPortletUI() {
    assertTrue(
        "The main UI class must extend life.qbic.portlet.QBiCPortletUI",
        QBiCPortletUI.class.isAssignableFrom(PortletEntryPointUI.class));
  }

  @Test
  public void mainUIIsNotQBiCPortletUI() {
    assertFalse(
        "The main UI class must be different to life.qbic.portlet.QBiCPortletUI",
        QBiCPortletUI.class.equals(PortletEntryPointUI.class));
  }
}
