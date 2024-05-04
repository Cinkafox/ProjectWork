package org.example.reqAndRes;

import org.example.contentProvider.RequestContent;
import org.example.data.RequestPath;
import org.example.data.RequestMethod;

import java.io.InputStream;
import java.util.*;

public final class Request extends ReqResBase{
    public final RequestMethod CurrentRequestMethod;
    public final RequestPath Path;
    
    public Request(InputStream inputStream){
        super();
        Scanner scanner = new Scanner(inputStream);
        var baseInfo = scanner.nextLine().split(" ");
        CurrentRequestMethod = RequestMethod.valueOf(baseInfo[0]);
        Path = new RequestPath(baseInfo[1]);

        var line = scanner.nextLine();
        
        while (!line.isEmpty()) {
            var splited = line.split(": ");
            super.Datum.put(splited[0],splited[1]);
   
            line = scanner.nextLine();
        }
        
        Content = new RequestContent(inputStream);
    }
}

