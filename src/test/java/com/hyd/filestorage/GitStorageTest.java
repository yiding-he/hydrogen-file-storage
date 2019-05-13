package com.hyd.filestorage;

import com.hyd.filestorage.git.GitStorage;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import org.junit.Test;

public class GitStorageTest {

    @Test
    public void readFile() throws Exception {
        String uri = "https://github.com/yiding-he/hydrogen-file-storage.git";
        String path = "README.md";

        Storage storage = new GitStorage(new URI(uri));
        Content content = storage.getContent(path);
        byte[] bytes = content.getContent();

        System.out.println(new String(bytes, StandardCharsets.UTF_8));
    }
}
