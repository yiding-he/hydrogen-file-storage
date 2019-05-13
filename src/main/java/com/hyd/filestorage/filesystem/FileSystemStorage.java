package com.hyd.filestorage.filesystem;

import com.hyd.filestorage.Content;
import com.hyd.filestorage.Storage;
import java.net.URI;

public class FileSystemStorage implements Storage {

    @Override
    public String getScheme() {
        return "file";
    }

    @Override
    public Content getContent(URI uri) {
        return new TextFileContent(uri);
    }
}
