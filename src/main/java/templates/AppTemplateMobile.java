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

@AppMeta(name = "width", content = "device-width")
@AppMeta(name = "initial-scale", content = "1.0")
@AppMeta(name = "viewport", content = "fit=cover")
@AppMeta(name = "user-scalable", content = "no")

public class AppTemplateMobile extends AppLayout {

    /**The site title displayed in the top navbar */
    private Label title = new Label("DWCJ Application");

    /**A string to the location of the image to be displayed in the drawer */
    private String imgPath = "https://i.ibb.co/1n4n1Nh/logo.png";
    
    /**The logo in the top left of the drawer, creates an image tag with the image path */
    private Label headerLogo = new Label("<html><img src='" + this.imgPath + "'</img></html>");
        
    /**Displays the various classes which extend Div as tabs within the footer */
    private TabControl footerMenu = new TabControl();

    /**Renders the various classes which extend Div in the center of the layout */
    private ContentDisplay contentDisplay = new ContentDisplay();
    
    /**Keeps track of the different classes which extend Div, and whether they've been initially rendered */
    private final ArrayList<SimpleEntry<Div, Boolean>> displayList = new ArrayList<>();


    public AppTemplateMobile(){

        App.addInlineStyleSheet(Assets.contentOf("css/apptemplatemobilestyles.css"));
        
        /*Creates the header and adds the title and logo to it */
        this.setHeaderReveal(true);
        Div headerDiv = new Div().add(headerLogo, title).addClassName("headerContent");
        this.getHeader().add(headerDiv);
        this.title.addClassName("headerTitle");

        /*Creates the footer and adds the menu which will display the pages to it */
        this.setFooterReveal(true);
        this.getFooter().add(footerMenu);
        footerMenu.setAttribute("nobody","true");
        footerMenu.setAttribute("borderless","true");
        footerMenu.setAttribute("placement","bottom");
        footerMenu.setAttribute("alignment","stretch");
        this.setFooterShadow(Shadow.SCROLL);

        /*Hides the left drawer, and sets the content to display the various classes added to the content display */
        this.setDrawerPlacement(DrawerPlacement.HIDDEN);
        this.setContent(this.contentDisplay);

        /*Sets the behavior to be executed when a tab within the drawer is clicked on. If a tab is
        * selected for the first time, the associated class will be initialized within the content
        * display section to implement lazy loading. 
        */
        footerMenu.onTabSelect((ev) -> {
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
    public AppTemplateMobile addPage(String title, Div page){
        this.footerMenu.addTab(title);
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
    public AppTemplateMobile insertPage(int index, String title, Div page){
        this.footerMenu.insertTab(index, title);
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
    public AppTemplateMobile removePage(int index){
        this.footerMenu.removeTab(index);
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
     * Gets the tab control in the footer
     * @return The footer menu tab control
     */
    public TabControl getFooterMenu() {
        return this.footerMenu;
    }

    /**
     * Gets the header logo, which is html code written within label
     * @return The header logo string
     */
    public Label getHeaderLogo() {
        return this.headerLogo;
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
    public AppTemplateMobile setContentDisplay(ContentDisplay contentDisplay) {
        this.contentDisplay = contentDisplay;
        return this;
    }

    /**
     * Sets the footer menu
     * @param footerMenu The new footer menu
     * @return The object itself
     */
    public AppTemplateMobile setFooterMenu(TabControl footerMenu) {
        this.footerMenu = footerMenu;
        return this;
    }

    /**
     * Sets the header logo string
     * @param headerLogo New header logo string - should include html code needed to render image
     * @return The object itself
     */
    public AppTemplateMobile setHeaderLogo(Label headerLogo) {
        this.headerLogo = headerLogo;
        return this;
    }

    /**
     * Sets the image path
     * @param imgPath String of image path
     * @return The object itself
     */
    public AppTemplateMobile setImgPath(String imgPath) {
        this.imgPath = imgPath;
        return this;
    }
    
    /**
     * Sets the application title
     * @param title The title of the application
     * @return The object itself
     */
    public AppTemplateMobile setTitle(Label title) {
        this.title = title;
        return this;
    }








    
}
