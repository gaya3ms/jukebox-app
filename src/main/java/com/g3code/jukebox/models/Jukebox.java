package com.g3code.jukebox.models;

import java.util.ArrayList;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnore;

public class Jukebox {
    private String id;
    private String model;
    private List<NameComponent> components;
    @JsonIgnore
    private List<String> componentList = new ArrayList<>();

    public Jukebox() {}

    public Jukebox(String id, List<NameComponent> components, String model) {
        this.id = id;
        this.setComponents(components);
        this.model = model;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<String> getComponentList() {
        return componentList;
    }

    public List<NameComponent> getComponents() {
        return components;
    }

    public void setComponents(List<NameComponent> components) {
        this.components = components;
        this.componentList.clear(); 
        this.components.forEach(jbox->this.componentList.add(jbox.getName()));
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }
}

