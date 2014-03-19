package com.lichsword.nextbrain.connector.http;

import com.lichsword.nextbrain.IConnector;
import com.lichsword.nextbrain.IRequest;
import com.lichsword.nextbrain.IResponse;
import com.lichsword.nextbrain.backup.Response;
import com.lichsword.nextbrain.backup.ServletProcessor;
import com.lichsword.nextbrain.backup.StaticResourceProcessor;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created with IntelliJ IDEA.
 * User: lichsword
 * Date: 14-3-3
 * Time: 下午9:46
 * To change this template use File | Settings | File Templates.
 */
public class HttpConnecter implements IConnector, Runnable {

    /**
     * 关闭命令的uri
     */
    private static final String SHUTDOWN_COMMAND = "/SHUTDOWN";

    /**
     * 是否关闭服务
     */
    private boolean shutdown = false;

    //    private final String HOST = "10.68.179.135";
    private final String HOST = "127.0.0.1";

    private int port = 8080;

    /**
     * The input buffer size we should create on input streams.
     */
    private int bufferSize = 2048;

    private void await() {
        ServerSocket serverSocket = null;
        int port = 8080;
        try {
            serverSocket = new ServerSocket(port, 1, InetAddress.getByName(HOST));
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }

        // 循环等待一个请求
        while (!shutdown) {
            Socket socket = null;
            InputStream input = null;
            OutputStream output = null;
            try {
                socket = serverSocket.accept();

                HttpProcessor processor = createProcessor();
                if (null == processor) {
                    System.out.println("HttpConnector.noProcessor");
                    socket.close();
                }// end if

                continue;

                processor.assign(socket);
                processor.process();
                // end ----


                input = socket.getInputStream();
                output = socket.getOutputStream();

                // 创建请求对象，并解析
                Request request = new Request(input);
                request.parse();

                // 创建响应对象
                Response response = new Response(output);
                response.setRequest(request);

                // 检查请求是一个servlet容器还是一个静态资源
                if (request.getUri().startsWith("/servlet")) {
                    ServletProcessor processor = new ServletProcessor();
                    processor.process(request, response);
                } else {
                    StaticResourceProcessor processor = new StaticResourceProcessor();
                    processor.process(request, response);
                }

                // 关闭 socket
                socket.close();
                // 检查是否uri是一个关闭命令
                shutdown = request.getUri().equals(SHUTDOWN_COMMAND);
            } catch (Exception e) {
                e.printStackTrace();
                System.exit(1);
            }
        }// end while
    }

    private HttpProcessor createProcessor() {
        return new HttpProcessor(this);
    }

    public IRequest createRequest() {
        HttpRequestImpl request = new HttpRequestImpl();
        request.setConnector(this);
        return request;
    }

    public IResponse createResponse() {
        HttpResponseImpl response = new HttpResponseImpl();
        response.setConnector(this);
        return response;
    }

    @Override
    public void run() {
        await();
    }

    public int getBufferSize() {
        return bufferSize;
    }

    public int getPort() {
        return this.port;
    }
}
