package com.hyd.filestorage.filesystem;

import com.hyd.filestorage.Content;
import com.hyd.filestorage.Storage;
import java.net.URI;

public class FileSystemStorage implements Storage {

    private URI root;

    public FileSystemStorage(URI root) {
        this.root = root;
    }

    @Override
    public URI getUri() {
        return root;
    }

    @Override
    public Content getContent(String path) {
        return new TextFileContent(this.root.resolve(path));
    }
}
