package org.example.handles;

import org.example.contentProvider.SimpleStringContent;
import org.example.reqAndRes.Request;
import org.example.reqAndRes.Response;

public class AuthHandle extends BaseHandle{
    @Override
    protected boolean OnRequest(Request request, Response response) {
        if(super.OnRequest(request, response)) //Is already handled?
            return true;
        
        if(!request.GetDatum("Authorization").equals("Bearer fuckwizden228")) {
            response.Content = new SimpleStringContent("<h1>FUCK! NO AUTH!</h1>");
            return true;
        }
        
        return false;
    }
}
