package templates;

import java.util.ArrayList;

import org.dwcj.App;
import org.dwcj.annotations.AppMeta;
import org.dwcj.controls.applayout.AppLayout;
import org.dwcj.controls.label.Label;
import org.dwcj.controls.panels.Div;
import org.dwcj.controls.tabcontrol.TabControl;
import org.dwcj.util.Assets;
import java.util.AbstractMap.SimpleEntry;

/**
 * AppTemplateDrawer extends the AppTemplate, and allows users to quickly
 * start developing an application with some preset information given to the
 * AppLayout class.
 * 
 * The template is configured with a drawer on the left side and a title bar at
 * the top.
 */

public class AppTemplateDrawer extends AppTemplate {

  /** An icon which toggles the drawer to display or close */
  private Label drawerToggle = new Label(
      "<html><bbj-icon-button name='menu-2' data-drawer-toggle></bbj-icon-button></html>");

  public AppTemplateDrawer() {
    App.addInlineStyleSheet(Assets.contentOf("css/apptemplatedrawerstyles.css"));

    /* Creates the App Layout's header, and adds the toggle icon and title to it */
    Div header = new Div();
    this.getHeader().add(header);
    header.add(this.drawerToggle, this.title)
        .addClassName("layout__header");
    this.title.addClassName("layout__header--title");

    /* Creating and adding/styling the logo which is displayed in the drawer */
    Div logoDiv = new Div().addClassName("drawer__logo");
    this.getDrawer().add(logoDiv);
    logoDiv.add(logo);

    /* Creates a tab control and adds the menu items to the drawer */
    this.getDrawer().add(this.menu);

    /* Configures the menu for display on the left */
    this.menu.setAttribute("placement", "left");

  }

  /**
   * Gets the text which is used to create the drawer toggle,
   * which may be HTML code within a label control
   * 
   * @return The drawer toggle text
   */
  public Label getDrawerToggle() {
    return this.drawerToggle;
  }

  /**
   * Sets the toggle for the drawer. This will likely need to be a Label which
   * displays a bbj-icon
   * using HTML and a <bbj-icon> tag.
   * 
   * @param toggle Label with the desired toggle text, will likely be a bbj-icon
   *               element wrapped in an html tag
   * @return The control itself
   */
  public AppTemplateDrawer setDrawerToggle(Label toggle) {
    this.drawerToggle = toggle;
    return this;
  }
}
