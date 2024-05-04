package org.example.contentProvider;

import java.io.InputStream;

public interface IContent{
    int GetLength();
    InputStream GetStream();
}
