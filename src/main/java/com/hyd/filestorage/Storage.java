package com.hyd.filestorage;

import java.net.URI;

public interface Storage {

    URI getUri();

    Content getContent(String path);
}
