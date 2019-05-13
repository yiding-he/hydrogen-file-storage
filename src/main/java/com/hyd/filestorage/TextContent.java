package com.hyd.filestorage;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public interface TextContent extends Content {

    Charset DEFAULT_CHARSET = StandardCharsets.UTF_8;

    default Charset getCharset() {
        return DEFAULT_CHARSET;
    }

    default String getText() throws IOException {
        return new String(getContent(), getCharset());
    }

    default void setText(String text) throws IOException {
        setContent(text.getBytes(getCharset()));
    }

}
