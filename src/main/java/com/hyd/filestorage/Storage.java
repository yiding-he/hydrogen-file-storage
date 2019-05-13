package com.hyd.filestorage;

import java.net.URI;

public interface Storage {

    String getScheme();

    Content getContent(URI uri);
}
