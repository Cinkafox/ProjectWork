package org.example.handles;

import org.example.contentProvider.SimpleStringContent;
import org.example.reqAndRes.Request;
import org.example.reqAndRes.Response;

import java.util.Map;

public final class DictionaryHandle extends BaseHandle{
    
    public final Map<String,BaseHandle> DictionaryHandle;
    
    public DictionaryHandle(Map<String,BaseHandle> dictionaryHandle){
        this.DictionaryHandle = dictionaryHandle;
        
    }
    
    @Override
    protected boolean OnRequest(Request request, Response response) {
        if(super.OnRequest(request, response)) //Is already handled?
            return true;
        
        var path = request.Path.PopPath();

        if(path.isEmpty()) {
            var con = new SimpleStringContent("<h1>pages:</h1>");
            for (Map.Entry<String, BaseHandle> entry : this.DictionaryHandle.entrySet()) {
                con.stringBuilder.append("<a href=\"").append(request.Path.FullPath).append("/").append(entry.getKey()).append("\">").append(entry.getKey()).append("</a>");
            }
            response.Content = con;
            return true;
        }
        
        if(this.DictionaryHandle.containsKey(path)) {
            var c = this.DictionaryHandle.get(path);
            return c.OnRequest(request,response);
        }
        
        return false;
    }
}
