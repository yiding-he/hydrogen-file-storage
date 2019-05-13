package com.hyd.filestorage;

import static org.assertj.core.api.Assertions.assertThat;

import com.hyd.filestorage.filesystem.FileSystemStorage;
import java.io.File;
import java.net.URI;
import org.junit.Test;

public class FileSystemStorageTest {

    @Test
    public void readFile() throws Exception {
        String pwd = new File("").getAbsolutePath().replace("\\", "/");

        Storage storage = new FileSystemStorage();
        URI uri = new URI("file:///" + pwd + "/src/test/resources/1.txt");
        TextContent content = (TextContent) storage.getContent(uri);

        assertThat(content.getUri().getScheme()).isEqualTo("file");
        assertThat(content.getText()).isEqualTo("12345");

        content.setText("67890");
        assertThat(content.getText()).isEqualTo("67890");

        content.setText("12345");
    }
}
