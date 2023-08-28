package com.mizuledevelopment.zpractice.kit;

import java.util.List;

public class Kit {

    private String name;
    private List<String> items;

    public Kit(String name, List<String> items) {
        this.name = name;
        this.items = items;
    }

    public String getName() {
        return name;
    }

    public List<String> getItems() {
        return items;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public void setItems(final List<String> items) {
        this.items = items;
    }
}
