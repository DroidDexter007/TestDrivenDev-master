package com.cisco.unittesting.bean;

import java.io.Serializable;

/**
 * Created by pchangul on 12/12/2016.
 */

public class Item implements Serializable {

    private String key;
    private boolean value;

    public String getKey() {
        return key;
    }

    public Item(String key, boolean value) {
        this.key = key;
        this.value = value;
    }

    public void setKey(String key) {
        this.key = key;
    }


    public boolean isValue() {
        return value;
    }

    public void setValue(boolean value) {
        this.value = value;
    }
}
