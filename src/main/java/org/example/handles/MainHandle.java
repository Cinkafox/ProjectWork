package org.example.handles;

import org.example.reqAndRes.Request;
import org.example.reqAndRes.Response;
import org.example.contentProvider.SimpleStringContent;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public final class MainHandle extends BaseHandle{
    @Override
    protected boolean OnRequest(Request request, Response response) {
        if(super.OnRequest(request, response)) //Is already handled?
            return true;
        
        var con = new SimpleStringContent("<h1>HI</h1>");
        
        con.stringBuilder.append("<h1>").append(request.Path.FullPath).append("</h1>");
        for(var i : request.Path.Path) {
            con.stringBuilder.append("<h1>").append(i).append("</h1>");
        }

        for (Map.Entry<String, String> entry : request.Path.Options.entrySet()) {
            con.stringBuilder.append("<h1>").append(entry.getKey()).append(" is ").append(entry.getValue()).append("</h1>");
        }
        
        response.Content = con;
        
        return true;
    }

    @Override
    protected List<BaseHandle> GetMiddleware() {
        return Arrays.asList(new BaseHandle[]{
                new AuthHandle()
        });
    }
}


