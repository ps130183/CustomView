package com.ps.customview;

/**
 * Created by PengSong on 17/12/7.
 */

public class MenuEntity {
    private Class className;
    private String menuName;

    public MenuEntity(Class className, String menuName) {
        this.className = className;
        this.menuName = menuName;
    }

    public Class getClassName() {
        return className;
    }

    public void setClassName(Class className) {
        this.className = className;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }
}
