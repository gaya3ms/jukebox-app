package com.g3code.jukebox.models;

import java.util.List;

public class Setting {
    public String id;
    public List<String> requires;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<String> getRequires() {
        return requires;
    }

    public void setRequires(List<String> requires) {
        this.requires = requires;
    }
}
