package org.dwcj.templates;

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
 * AppTemplateStickyHeader extends the AppLayout web component, and allows users to quickly
 * start developing an application with some preset information given to the 
 * AppLayout class.
 * 
 * The template is configured with a sticky header and a some menu items displayed at
 * the top of the application. Classes which extend Div can be displayed in this template, 
 * and entries for these programs will be added to the menu at the top.
 */

@AppMeta(name = "width", content = "device-width")
@AppMeta(name = "initial-scale", content = "1.0")
@AppMeta(name = "viewport", content = "fit=cover")
@AppMeta(name = "user-scalable", content = "no")

public class AppTemplateStickyHeader extends AppLayout {
    
    /**The site title displayed in the top navbar */
    private Label title = new Label("DWCJ Application");

    /**A string to the location of the image to be displayed in the drawer */
    private String imgPath = "https://i.ibb.co/1n4n1Nh/logo.png";
    
    /**The logo in the top left of the drawer, creates an image tag with the image path */
    private Label headerLogo = new Label("<html><img src='" + this.imgPath + "'</img></html>");
        
    /**Displays the various classes which extend Div as tabs within the header */
    private TabControl headerMenu = new TabControl();

    /**Renders the various classes which extend Div in the center of the layout */
    private ContentDisplay contentDisplay = new ContentDisplay();
    
    /**Keeps track of the different classes which extend Div, and whether they've been initially rendered */
    private final ArrayList<SimpleEntry<Div, Boolean>> displayList = new ArrayList<>();
    
    public AppTemplateStickyHeader(){
        App.addInlineStyleSheet(Assets.contentOf("css/stickyheaderstyles.css"));

        /*Creates the header and adds an additional bar which will be used to display different screens */
        this.setHeaderReveal(true);
        Div secondHeader = new Div();

        /*Adds a title and logo to the top of the header*/
        Div headerDiv = new Div().add(headerLogo, title).addClassName("headerContent");
        this.getHeader().add(headerDiv);
        title.addClassName("headerTitle");
        this.getHeader().add(secondHeader);

        /*Adds the tab control to the bar beneath the site header */
        secondHeader.add(headerMenu);
        headerMenu.setAttribute("nobody","true");
        headerMenu.setAttribute("borderless","true");

        /*Hides the drawer on the left side of the site */
        this.setDrawerPlacement(DrawerPlacement.HIDDEN);

        /*Sets the content of the AppLayout to be the ContentDisplay object created to handle page displaying */
        this.setContent(this.contentDisplay);

        /*Sets the behavior to be executed when a tab within the drawer is clicked on. If a tab is
        * selected for the first time, the associated class will be initialized within the content
        * display section to implement lazy loading. 
        */
        headerMenu.onTabSelect((ev) -> {
            int idx = ev.getIndex();
            if(displayList.get(idx).getValue().equals(Boolean.FALSE)){
                contentDisplay.addPage(String.valueOf(idx), displayList.get(idx).getKey());
                displayList.get(idx).setValue(Boolean.TRUE);
            }
            contentDisplay.displayPage(idx);
         });
    }

    /** 
     * Adds a tab in the drawer menu representing the desired class to be displayed in the
     * content display area, and adds this program to an ArrayList, which helps facilitate lazy
     * loading of the class.
     * 
     * @param title The title of the tab in the drawer menu to be added
     * @param page The class which extends the Div class and is desired to be shown when the tab with
     * the given title is clicked
     * @return The control itself
     * 
     */
    public AppTemplateStickyHeader addPage(String title, Div page){

        this.headerMenu.addTab(title);
        this.displayList.add(new SimpleEntry<>(page, false));
        return this;
    }
    
    /** 
     * Adds a tab in the drawer menu at the designated index representing the class to be displayed in the
     * content display area, and adds an entry to the displayList, which helps facilitate lazy
     * loading of the class.
     * 
     * @param index Desired index for the new tab to display for the new program
     * @param title The title of the tab in the drawer menu to be added
     * @param page The class which extends the Div class and is desired to be shown when the tab with
     * the given title is clicked
     * @return The control itself
     * 
     */
    public AppTemplateStickyHeader insertPage(int index, String title, Div page){
        this.headerMenu.insertTab(index, title);
        this.displayList.add(index, new SimpleEntry<>(page, false));
        return this;
    }

    /** 
     * Removes a tab from the drawer menu tab control, and also from the content display if
     * this tab has already been initialized.
     * 
     * @param index The index of the tab/page to be removed
     * @return The control itself
     * 
     */
    public AppTemplateStickyHeader removePage(int index){
        this.headerMenu.removeTab(index);
        if(this.displayList.get(index).getValue().equals(Boolean.TRUE)){
            this.contentDisplay.getPanels().removeTab(index);
        }
        this.displayList.remove(index);
        return this;
    }

    /**
     * Gets the content display
     * @return The content display
     */
    public ContentDisplay getContentDisplay() {
        return this.contentDisplay;
    }

    /**
     * Gets the header logo, which is html code written within label
     * @return The header logo string
     */
    public Label getHeaderLogo() {
        return this.headerLogo;
    }

    /**
     * Gets the tab control in the header
     * @return The header menu tab control
     */
    public TabControl getHeaderMenu() {
        return this.headerMenu;
    }

    /**
     * Gets the logo path
     * @return The logo path
     */
    public String getImgPath() {
        return this.imgPath;
    }

    /**
     * Gets the application title
     * @return The app title
     */
    public Label getTitle() {
        return this.title;
    }

    /**
     * Sets the content display
     * @param contentDisplay The new content display
     * @return The object itself
     */
    public AppTemplateStickyHeader setContentDisplay(ContentDisplay contentDisplay) {
        this.contentDisplay = contentDisplay;
        return this;
    }

    /**
     * Sets the header menu
     * @param headerMenu The new header menu
     * @return The object itself
     */
    public AppTemplateStickyHeader setHeaderMenu(TabControl headerMenu) {
        this.headerMenu = headerMenu;
        return this;
    }
    
    /**
     * Sets the header logo string
     * @param headerLogo New header logo string - should include html code needed to render image
     * @return The object itself
     */
    public AppTemplateStickyHeader setHeaderLogo(Label headerLogo) {
        this.headerLogo = headerLogo;
        return this;
    }

    /**
     * Sets the image path
     * @param imgPath String of image path
     * @return The object itself
     */
    public AppTemplateStickyHeader setImgPath(String imgPath) {
        this.imgPath = imgPath;
        return this;
    }
    
    /**
     * Sets the application title
     * @param title The title of the application
     * @return The object itself
     */
    public AppTemplateStickyHeader setTitle(Label title) {
        this.title = title;
        return this;
    }




    
}
