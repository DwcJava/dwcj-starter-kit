package templates;

import java.util.ArrayList;
import java.util.AbstractMap.SimpleEntry;

import org.dwcj.App;
import org.dwcj.annotations.AppMeta;
import org.dwcj.controls.applayout.AppLayout;
import org.dwcj.controls.label.Label;
import org.dwcj.controls.panels.Div;
import org.dwcj.controls.tabcontrol.TabControl;
import org.dwcj.util.Assets;

/**
 * AppTemplateMobile extends the AppTemplate and allows users to quickly
 * start developing an application with some preset information given to the
 * AppLayout class.
 * 
 * The template is configured with mobile display in mind, and
 */
public class AppTemplateMobile extends AppTemplate {

  public AppTemplateMobile() {

    App.addInlineStyleSheet(Assets.contentOf("css/apptemplatemobilestyles.css"));

    /* Creates the header and adds the title and logo to it */
    this.setHeaderReveal(true);
    Div headerDiv = new Div().add(this.logo, this.title).addClassName("header__content");
    this.getHeader().add(headerDiv);
    this.title.addClassName("header__content--title");

    /* Creates the footer and adds the menu which will display the pages to it */
    this.setFooterReveal(true);
    this.getFooter().add(this.menu);
    /* Configures the menu for placement at the bottom, and stretches it */
    this.menu.setAttribute("placement", "bottom");
    this.menu.setAttribute("alignment", "stretch");
    this.setFooterShadow(Shadow.SCROLL);

    /*
     * Hides the left drawer, and sets the content to display the various classes
     * added to the content display
     */
    this.setDrawerPlacement(DrawerPlacement.HIDDEN);

  }
}
