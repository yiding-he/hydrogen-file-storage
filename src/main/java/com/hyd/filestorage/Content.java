package com.hyd.filestorage;

import java.io.IOException;
import java.net.URI;

public interface Content {

    URI getUri();

    // read bytes from Content
    byte[] getContent() throws IOException;

    // write bytes to Content
    void setContent(byte[] content) throws IOException;
}
