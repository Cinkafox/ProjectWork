package org.example;

import org.example.handles.IHandle;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;
import java.util.logging.Logger;

public class HTTPServer extends Thread{
    private final Logger logger = Logger.getLogger("HTTPServer");
    private final ServerSocket _serverSocket;
    private final int _port;
    private final IHandle _handle;
    private boolean _isRunning = false;
    
    public HTTPServer(int port, IHandle handle) throws Exception {
        if(port > 65535 || port < 0) {
            throw new Exception("port number is too big or small");
        }
        this._port = port;
        this._serverSocket = new ServerSocket(this._port);
        this._handle = handle;
    }
    
    public boolean IsRunning(){
        return this._isRunning;
    }
    
    public void Stop(){
        this._isRunning = false;
    }
    
    public void run(){
        this._isRunning = true;
        logger.info("Server started in: http://"+ this._serverSocket.getInetAddress().getHostAddress() + ":" + this._port);
        while (this._isRunning) {
            try {
                Socket socket = this._serverSocket.accept();
                var task = new ServerTask(_handle,socket,logger);
                task.start();
            } catch (Exception e) {
                logger.info("Error while starting task " + e.getMessage() + " " + Arrays.toString(e.getStackTrace()));
            }
        }
    }
}

class ServerTask extends Thread{
    public final IHandle Handle;
    public final Socket Socket;
    public final Logger Logger;

    ServerTask(IHandle handle, Socket socket, Logger logger) {
        Handle = handle;
        Socket = socket;
        Logger = logger;
    }

    public void run(){
        try(InputStream input = Socket.getInputStream(); OutputStream output = Socket.getOutputStream()) {
            Logger.info("START SERVING " + Socket.getInetAddress().getHostAddress());
            Handle.HandleRequest(input,output);

            Socket.close();
            Logger.info("END SERVING " + Socket.getInetAddress().getHostAddress());
        } catch (Exception e) {
            Logger.info("Error while running task " + e.getMessage() + " " + Arrays.toString(e.getStackTrace()));
        }
    }
}

