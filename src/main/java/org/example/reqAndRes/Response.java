package org.example.reqAndRes;

import org.example.helpers.StreamHelper;
import org.example.data.BasicStatus;
import org.example.data.ResponseStatus;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Map;

public final class Response extends ReqResBase{
    public ResponseStatus Status = BasicStatus.ST_200;
    
    public void Flush(OutputStream outputStream) throws IOException {
        outputStream.write(StreamHelper.WriteLine(this.HttpVersion + " " + Status.toString()));

        if(Content != null) {
            this.SetDatum("Content-length", String.valueOf(Content.GetLength()));
        }
        
        this.SetDatum("Connection","close");
        
        for (Map.Entry<String, String> entry : this.Datum.entrySet()) {
            outputStream.write(StreamHelper.WriteLine(entry.getKey() + ": " + entry.getValue()));
        }
        
        outputStream.write("\r\n".getBytes());
        
        if(Content != null) {
            try(var inputStream = Content.GetStream()) {
                inputStream.transferTo(outputStream);
            }
        }
    }
}
