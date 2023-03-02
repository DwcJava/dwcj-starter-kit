package templates;

import org.dwcj.App;
import org.dwcj.controls.panels.Div;
import org.dwcj.util.Assets;

/**
 * AppTemplateStickyHeader extends the AppLayout web component, and allows users to quickly
 * start developing an application with some preset information given to the 
 * AppLayout class.
 * 
 * The template is configured with a sticky header and a some menu items displayed at
 * the top of the application. Classes which extend Div can be displayed in this template, 
 * and entries for these programs will be added to the menu at the top.
 */

public class AppTemplateStickyHeader extends AppTemplate {
    
    public AppTemplateStickyHeader(){
        App.addInlineStyleSheet(Assets.contentOf("css/stickyheaderstyles.css"));

        /*Creates the header and adds an additional bar which will be used to display different screens */
        this.setHeaderReveal(true);
        Div secondHeader = new Div();

        /*Adds a title and logo to the top of the header*/
        Div headerDiv = new Div().add(this.logo, this.title).addClassName("header__content");
        this.getHeader().add(headerDiv);
        title.addClassName("header__content--title");
        this.getHeader().add(secondHeader);

        /*Adds the tab control to the bar beneath the site header */
        secondHeader.add(this.menu);

        /*Hides the drawer on the left side of the site */
        this.setDrawerPlacement(DrawerPlacement.HIDDEN);
    }
}
