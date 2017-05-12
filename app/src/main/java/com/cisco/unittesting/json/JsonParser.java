package com.cisco.unittesting.json;

/**
 * Created by pchangul on 12/12/2016.
 */


import com.cisco.unittesting.bean.Item;

import org.json.JSONObject;

/**
 * Json parser.
 */
public class JsonParser {
    public static final String KEY_TAG = "key";
    public static final String VALUE_TAG = "value";

    public String serialize(Item item) throws Exception {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put(KEY_TAG, item.getKey());
        jsonObject.put(VALUE_TAG, item.isValue());
        return jsonObject.toString();
    }

    public Item deserialize(String s) throws Exception {
        JSONObject jsonObject = new JSONObject(s);
        String key = jsonObject.getString(KEY_TAG);
        boolean value = jsonObject.getBoolean(VALUE_TAG);
        return new Item(key, value);
    }
}
