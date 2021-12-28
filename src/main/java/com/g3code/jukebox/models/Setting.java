package com.g3code.jukebox.models;

public class Setting {
    private String id;
    private String[] requires;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String[] getRequires() {
        return requires;
    }

    public void setRequires(String[] requires) {
        this.requires = requires;
    }
}
