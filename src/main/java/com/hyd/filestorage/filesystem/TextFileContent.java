package com.hyd.filestorage.filesystem;

import com.hyd.filestorage.TextContent;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;

public class TextFileContent implements TextContent {

    private File file;

    public TextFileContent(URI uri) {
        this.file = new File(uri);
    }

    @Override
    public URI getUri() {
        return file.toURI();
    }

    @Override
    public byte[] getContent() throws IOException {
        return Files.readAllBytes(this.file.toPath());
    }

    @Override
    public void setContent(byte[] content) throws IOException {
        Files.write(this.file.toPath(), content);
    }
}
