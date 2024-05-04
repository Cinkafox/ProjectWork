package org.example.helpers;

public class StreamHelper{
    public static byte[] WriteLine(String input){
        return (input + "\r\n").getBytes();    
    }
}
