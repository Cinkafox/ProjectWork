package org.example;

import org.example.handles.BaseHandle;
import org.example.handles.DictionaryHandle;
import org.example.handles.MainHandle;

import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws Exception {
        Map<String, BaseHandle> prik = new HashMap<>();
        prik.put("nya",new MainHandle());
        HTTPServer httpServer = new HTTPServer(3000, new DictionaryHandle(prik));
        httpServer.start();
    }
}

