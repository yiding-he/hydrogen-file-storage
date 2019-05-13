package com.hyd.filestorage;

import static org.assertj.core.api.Assertions.assertThat;

import com.hyd.filestorage.filesystem.FileSystemStorage;
import java.io.File;
import org.junit.Test;

public class FileSystemStorageTest {

    @Test
    public void readFile() throws Exception {
        Storage storage = new FileSystemStorage(new File("").toURI());
        TextContent content = (TextContent) storage.getContent("src/test/resources/1.txt");

        assertThat(content.getUri().getScheme()).isEqualTo("file");
        assertThat(content.getText()).isEqualTo("12345");

        content.setText("67890");
        assertThat(content.getText()).isEqualTo("67890");

        content.setText("12345");
    }
}
