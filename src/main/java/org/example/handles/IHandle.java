package org.example.handles;



import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public interface IHandle{
    void HandleRequest(InputStream inputStream, OutputStream outputStream) throws IOException;
}
