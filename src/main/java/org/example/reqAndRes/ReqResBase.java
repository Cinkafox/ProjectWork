package org.example.reqAndRes;

import org.example.contentProvider.IContent;

import java.util.HashMap;
import java.util.Map;

public class ReqResBase{
    public final String HttpVersion = "HTTP/1.1";
    protected final Map<String,String> Datum = new HashMap<String,String>();
    public IContent Content;

    public void SetDatum(String input,String value){
        Datum.put(input,value);
    }

    public String GetDatum(String input){
        return Datum.getOrDefault(input,"");
    }
}
