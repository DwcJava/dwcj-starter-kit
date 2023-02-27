package org.dwcj.templates;

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
 * AppTemplate extends the AppLayout web component, and allows users to quickly
 * start developing an application with some preset information given to the 
 * AppLayout class.
 * 
 * The template is configured with a drawer on the left side and a title bar at
 * the top. Classes which extend Div can be displayed in this template, and entries
 * for these programs will be added to the left side bar.
 */

@AppMeta(name = "width", content = "device-width")
@AppMeta(name = "initial-scale", content = "1.0")
@AppMeta(name = "viewport", content = "fit=cover")
@AppMeta(name = "user-scalable", content = "no")

public class AppTemplate extends AppLayout{

    /**The site title displayed in the top navbar */
    private Label title = new Label("DWCJ Application");

    /**An icon which toggles the drawer to display or close */
    private Label drawerToggle = new Label("<html><bbj-icon-button name='menu-2' data-drawer-toggle></bbj-icon-button></html>");

    /**A string to the location of the image to be displayed in the drawer */
    private String imgPath = "https://i.ibb.co/1n4n1Nh/logo.png";

    /**The logo in the top left of the drawer, creates an image tag with the image path */
    private Label logo = new Label("<html><img src='" + this.imgPath + "'</img></html>");

    /**Displays the various classes which extend Div as tabs within the drawer */
    private TabControl drawerMenu = new TabControl();

    /**Renders the various classes which extend Div in the center of the layout */
    private ContentDisplay contentDisplay = new ContentDisplay();

    /**Keeps track of the different classes which extend Div, and whether they've been initially rendered */
    private final ArrayList<SimpleEntry<Div, Boolean>> displayList = new ArrayList<>();
             

    public AppTemplate(){
        App.addInlineStyleSheet(Assets.contentOf("css/apptemplatestyles.css"));

        /*Creates the App Layout's header, and adds the toggle icon and title to it */
         Div header = new Div();
         this.getHeader().add(header);
         header.add(this.drawerToggle, this.title);
         header.addClassName("dwcj-toolbar");
         this.title.addClassName("headerTitle");

         /*Creating and adding/styling the logo which is displayed in the drawer */
         Div logoDiv = new Div().addClassName("logo");
         this.getDrawer().add(logoDiv);
         logoDiv.add(logo);

         /*Creates a tab control and adds the menu items to the drawer */
         this.getDrawer().add(drawerMenu);
         drawerMenu.setAttribute("nobody","true");
         drawerMenu.setAttribute("borderless","true");
         drawerMenu.setAttribute("placement","left");

         /*Sets the content of the AppLayout to be the ContentDisplay object created to handle page displaying */
         this.setContent(this.contentDisplay);

        /*Sets the behavior to be executed when a tab within the drawer is clicked on. If a tab is
        * selected for the first time, the associated class will be initialized within the content
        * display section to implement lazy loading. 
        */
        drawerMenu.onTabSelect((ev) -> {
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
     * content display area, and adds this program to an displayList, which helps facilitate lazy
     * loading of the class.
     * 
     * @param title The title of the tab in the drawer menu to be added
     * @param page The class which extends the Div class and is desired to be shown when the tab with
     * the given title is clicked
     * @return The control itself
     * 
     */
    public AppTemplate addPage(String title, Div page){

        this.drawerMenu.addTab(title);
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
    public AppTemplate insertPage(int index, String title, Div page){
        this.drawerMenu.insertTab(index, title);
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
    public AppTemplate removePage(int index){
        this.drawerMenu.removeTab(index);
        if(this.displayList.get(index).getValue().equals(Boolean.TRUE)){
            this.contentDisplay.getPanels().removeTab(index);
        }
        this.displayList.remove(index);
        return this;
    }

    /**
     * Gets the title of the application's header
     * @return The title
     */
    public Label getHeaderTitle(){
        return this.title;
    }
    
    /**
     * Gets the text which is used to create the drawer toggle,
     * which may be HTML code within a label control
     * 
     * @return The drawer toggle text
     */
    public Label getDrawerToggle(){
        return this.drawerToggle;
    }

    /**
     * Returns the string of the image filepath
     * 
     * @return Image filepath
     */
    public String getSidebarImagePath(){
        return this.imgPath;
    }

    /**
     * Returns the tab control inside the side drawer
     * 
     * @return Drawer menu
     */
    public TabControl getDrawerMenu (){
        return this.drawerMenu;
    }

    /**
     * Returns the control built to facilitate the display of various pages in
     * the center of the application
     * 
     * @return The content display control
     */
    public ContentDisplay getContentDisplay(){
        return this.contentDisplay;
    }

    /**
     * Sets the toggle for the drawer. This will likely need to be a Label which displays a bbj-icon
     * using HTML and a <bbj-icon> tag.
     * @param toggle
     * @return The control itself
     */
    public AppTemplate setDrawerToggle(Label toggle){
        this.drawerToggle = toggle;
        return this;
    }

    /**
     * Sets the title in the header
     * @param title Desired title
     * @return The control itself
     */
    public AppTemplate setHeaderTitle(Label title){
        this.title = title;
        return this;
    }

    /**
     * Sets the image path to be displayed in the drawer
     * @param imagePath Desired filepath to an image
     * @return The control itself
     */
    public AppTemplate setSidebarImagePath(String imagePath){
        this.imgPath = imagePath;
        return this;
    }

    /**
     * Sets the tab control within the drawer menu
     * @param menu Tab control for the drawer menu
     * @return The control itself
     */
    public AppTemplate setDrawerMenu (TabControl menu){
        this.drawerMenu = menu;
        return this;
    }

    /**
     * Sets the content display component
     * @param content Content display to be shown
     * @return The app itself
     */
    public AppTemplate setContentDisplay(ContentDisplay content){
        this.contentDisplay = content;
        return this;
    }


}
