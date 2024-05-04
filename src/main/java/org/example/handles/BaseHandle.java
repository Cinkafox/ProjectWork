package org.example.handles;

import org.example.contentProvider.SimpleStringContent;
import org.example.data.BasicStatus;
import org.example.reqAndRes.Request;
import org.example.reqAndRes.Response;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class BaseHandle implements IHandle{
    protected final Logger logger = Logger.getLogger("BaseHandle");
    
    protected List<BaseHandle> Middleware = new ArrayList<>();
    
    @Override
    public void HandleRequest(InputStream inputStream, OutputStream outputStream) throws IOException {
        var request = new Request(inputStream);
        var response = new Response();
        logger.info(request.CurrentRequestMethod + " " + request.Path.FullPath);
        
        response.SetDatum("Server","Custom Server");
        response.SetDatum("Content-Type","text/html");
        
        try {
            if(!OnRequest(request,response)) {
                response.Status = BasicStatus.ST_404;
                response.Content = new SimpleStringContent("<h1>ERROR! NOT FOUND</h1>");
            }
        }catch (Exception e) {
            response.Status = BasicStatus.ST_500;
            var con = new SimpleStringContent("<h1>ERROR! " + e.getLocalizedMessage() + "</h1>");
            for(var a : e.getStackTrace()) {
                con.stringBuilder.append("<p>at ").append(a.toString()).append("</p>");
            }
            response.Content = con;
        }

        response.Flush(outputStream);
        outputStream.flush();
    }
    
    protected List<BaseHandle> GetMiddleware(){
        return Middleware;
    }
    
    protected boolean OnRequest(Request request, Response response){
        for(var mid : this.GetMiddleware()) {
            if(mid.OnRequest(request,response)) return true;
        }
        
        return false;
    }
}


