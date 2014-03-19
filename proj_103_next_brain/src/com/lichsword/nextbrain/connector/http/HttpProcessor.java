package com.lichsword.nextbrain.connector.http;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

/**
 * Created with IntelliJ IDEA.
 * User: lichsword
 * Date: 14-3-19
 * Time: 下午5:27
 * <p/>
 * TODO
 */
public class HttpProcessor {

    private Socket socket = null;

    private HttpConnecter connecter;

    private HttpRequestImpl request;
    private HttpResponseImpl response;

    private int serverPort = 0;

    public HttpProcessor(HttpConnecter connecter) {
        this.connecter = connecter;
        request = (HttpRequestImpl) connecter.createRequest();
        response = (HttpResponseImpl) connecter.createResponse();
        serverPort = connecter.getPort();
    }

    protected void assign(Socket socket) {
        this.socket = socket;
    }

    public void process() {
        if (null == socket) {
            System.out.println("process.socket is null");
            return;
        }

        SocketInputStream socketInputStream = null;
        OutputStream outputStream = null;

        try {
            socketInputStream = new SocketInputStream(socket.getInputStream(), connecter.getBufferSize());
        } catch (IOException e) {
            System.out.println("process.create" + e);
            e.printStackTrace();
        }

        // process start

        try {
            request.setStream(socketInputStream);
            request.setResponse(response);
            outputStream = socket.getOutputStream();
            response.setStream(outputStream);
            response.setRequest(request);

            response.getResponse();// TODO
        } catch (IOException e) {
            e.printStackTrace(); // TODO
        }

    }

}
