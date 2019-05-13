package com.hyd.filestorage.git;

import com.hyd.filestorage.Content;
import java.io.IOException;
import java.net.URI;
import org.eclipse.jgit.lib.ObjectLoader;

public class GitContent implements Content {

    private URI uri;

    private ObjectLoader objectLoader;

    public GitContent(URI uri, ObjectLoader objectLoader) {
        this.uri = uri;
        this.objectLoader = objectLoader;
    }

    @Override
    public URI getUri() {
        return uri;
    }

    @Override
    public byte[] getContent() throws IOException {
        return this.objectLoader.getBytes();
    }

    @Override
    public void setContent(byte[] content) throws IOException {

    }
}
