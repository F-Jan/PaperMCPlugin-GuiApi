package com.github.papermcplugin_guiapi.gui.inventory.location;

public class GuiLocation {

    private int x;
    private int y;
    private int page;

    public GuiLocation(int x, int y, int page) {
        this.x = x;
        this.y = y;
        this.page = page;
    }

    public GuiLocation(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getPage() {
        return page;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
