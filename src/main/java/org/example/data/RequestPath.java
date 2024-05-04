package org.example.data;

import java.util.*;

public class RequestPath{
    public final LinkedList<String> Path;
    public final String FullPath;
    public final Map<String,String> Options = new HashMap<>();
    public RequestPath(String input){
        var splited = input.split("\\?");
        var path = splited[0].substring(1); // remove first /
        this.FullPath = path;
        
        Path = new LinkedList<>(Arrays.asList(path.split("/")));
        
        if(splited.length == 1) 
            return;
        
        for (var item : splited[1].split("&")) {
            var spl = item.split("=");
            Options.put(spl[0],spl[1]);
        }
    }
    
    public String ShiftPath(){
        if(!Path.isEmpty())
            return Path.pollFirst();
        return "";
    }
    
    public String PopPath(){
        if(!Path.isEmpty())
            return Path.pollLast();
        return "";
    }
}
