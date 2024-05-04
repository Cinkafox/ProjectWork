package org.example.contentProvider;

import java.io.IOException;
import java.io.InputStream;

public class RequestContent implements IContent{

    private final InputStream inputStream;

    public RequestContent(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    @Override
    public int GetLength() {
        int available;
        try {
            available = inputStream.available();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return available;
    }

    @Override
    public InputStream GetStream() {
        return inputStream;
    }
}
