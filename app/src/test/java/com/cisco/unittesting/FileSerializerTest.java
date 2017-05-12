package com.cisco.unittesting;

import com.cisco.unittesting.bean.Item;
import com.cisco.unittesting.json.FileSerializer;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;
import java.io.File;
import static junit.framework.Assert.assertEquals;

public class FileSerializerTest {

    @Rule
    public TemporaryFolder folder = new TemporaryFolder();

    @Test
    public void save_and_load_file() throws Exception {
        FileSerializer serializer = new FileSerializer();
        File myFile = folder.newFile("myfile.txt");
        Item item1 = new Item("myKey", true);

        serializer.save(myFile, item1);
        Item item2 = serializer.load(myFile);

        // Verify that the received data is correct.
        assertEquals(item1, item2);
    }
}