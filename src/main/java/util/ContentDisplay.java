package util;

import org.dwcj.controls.panels.AbstractDwcjPanel;
import org.dwcj.controls.panels.Div;
import org.dwcj.controls.tabcontrol.TabControl;

/**
 * Helper class which facilitates proper behavior between the tab controls for
 * selection
 * and display within the various templates, and the TabControl component
 */

public class ContentDisplay extends Div {

  TabControl panels = new TabControl();

  @Override
  protected void create(AbstractDwcjPanel p) {
    App.addInlineStyleSheet(Assets.contentOf("css/contentdisplaystyles.css"));

    super.create(p);
    this.add(this.panels);
    this.panels.addClassName("contentDisplayTabs");
  }

  /**
   * Adds a page to the content section of an AppLayout class
   * 
   * @param title Title of the page
   * @param page  The object extending or implementing Div to be displayed
   * @return The object itself
   */
  public ContentDisplay addPage(String title, Div page) {
    this.panels.addTab(title, page);
    return this;
  }

  /**
   * Removes a page from the content section of the AppLayout class's available
   * for display
   * 
   * @param index Index of the desired object to be removed
   * @return The object itself
   */
  public ContentDisplay removePage(int index) {
    this.panels.removeTab(index);
    return this;
  }

  /**
   * Selects a page/object to be displayed in the content section of the AppLayout
   * 
   * @param index Index of the desired page/object to be displayed
   * @return The class itself
   */
  public ContentDisplay displayPage(int index) {
    this.panels.selectIndex(index);
    return this;
  }

  /**
   * Returns the TabControl that stores the various objects/pages for display
   * 
   * @return The tab control responsible for page display
   */
  public TabControl getPanels() {
    return this.panels;
  }
}
