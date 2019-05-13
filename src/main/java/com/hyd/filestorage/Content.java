package com.hyd.filestorage;

import java.io.IOException;
import java.net.URI;

public interface Content {

    URI getUri();

    byte[] getContent() throws IOException;

    void setContent(byte[] content) throws IOException;
}
