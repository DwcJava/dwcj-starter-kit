package org.dwcj.templates;

import org.dwcj.controls.panels.AbstractDwcjPanel;
import org.dwcj.controls.panels.Div;
import org.dwcj.controls.tabcontrol.TabControl;

public class ContentDisplay extends Div {
    
    TabControl panels = new TabControl();

    @Override
    protected void create(AbstractDwcjPanel p) {
        super.create(p);
        this.add(this.panels);
        this.panels.addClassName("contentDisplayTabs");
    }

    public ContentDisplay addPage(String title, Div page){
        this.panels.addTab(title, page);
        return this;    
    }

    public ContentDisplay removePage(int index){
        this.panels.removeTab(index);
        return this;
    }

    public ContentDisplay displayPage(int index){
        this.panels.selectIndex(index);
        return this;
    }

    public TabControl getPanels(){
        return this.panels;
    }


    
}
