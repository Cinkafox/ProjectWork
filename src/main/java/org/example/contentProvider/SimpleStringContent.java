package org.example.contentProvider;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

public class SimpleStringContent implements IContent{
    public StringBuilder stringBuilder = new StringBuilder();
    public SimpleStringContent(){
    }
    
    public SimpleStringContent(String input){
        stringBuilder.append(input);
    }
    
    @Override
    public int GetLength() {
        return stringBuilder.length();
    }

    @Override
    public InputStream GetStream() {
        return new ByteArrayInputStream(stringBuilder.toString().getBytes());
    }
}
