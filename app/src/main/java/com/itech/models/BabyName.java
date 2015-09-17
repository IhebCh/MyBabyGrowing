package com.itech.models;

/**
 * Created by oSunshine on 08/08/2015.
 */
public class BabyName {
    String name ;
    boolean checked ;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public BabyName(String name, boolean checked) {
        this.name = name;
        this.checked = checked;
    }
}
