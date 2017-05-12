package com.cisco.unittesting;

import com.cisco.unittesting.bean.Item;
import com.cisco.unittesting.json.JsonParser;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by pchangul on 12/12/2016.
 */

public class JsonParserTest {


    @Test
    public void serialize_deserialize() throws Exception {
        JsonParser jsonParser = new JsonParser();
        Item item = new Item("myKey", true);

        String s = jsonParser.serialize(item);
        Item item2 = jsonParser.deserialize(s);

        // Verify that the received data is correct;
       assertEquals(item, item2);
    }
}
