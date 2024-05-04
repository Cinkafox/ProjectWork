package org.example.data;

public class ResponseStatus{
    public final int Code;
    public final String Message;

    public ResponseStatus(int code, String message) {
        Code = code;
        Message = message;
    }
    
    public String toString(){
        return Code + " " + Message;
    }
}
