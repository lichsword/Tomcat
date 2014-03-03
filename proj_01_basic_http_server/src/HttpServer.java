
import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created with IntelliJ IDEA.
 * User: lichsword
 * Date: 14-3-3
 * Time: 上午11:03
 * To change this template use File | Settings | File Templates.
 */
public class HttpServer {

    /**
     * WEB_ROOT is the directory where our HTML and other files reside.
     * For this package, WEB_ROOT is the "webroot" directory under the working directory.
     * The working directory is the location in the file system from where the java command was invoked.
     */
    public static final String WEB_ROOT = System.getProperty("user.dir") + File.separator + "webroot";

    // shutdown command.
    private static final String SHUTDOWN_COMMAND = "/SHUTDOWN";

    // the shutdown command received.
    private boolean shutdown = false;

    public static void main(String[] args) {
        System.out.println("Hellow World");
        HttpServer server = new HttpServer();
        System.out.println("WEBROOT="+WEB_ROOT);
        server.await();
    }

    public void await() {
        ServerSocket serverSocket = null;
        int port = 8080;
        try {
            serverSocket = new ServerSocket(port, 1, InetAddress.getByName("127.0.0.1"));
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }

        // Loop waiting for a request
        while (!shutdown) {
            Socket socket = null;
            InputStream input = null;
            OutputStream output = null;
            try {
                socket = serverSocket.accept();
                input = socket.getInputStream();
                output = socket.getOutputStream();

                // create request object and parse
                Request request;
                request = new Request(input);
                request.parse();


                // create response object
                Response response = new Response(output);
                response.setRequest(request);
                response.sendStaticResource();

                // close socket
                socket.close();

                // check if the previous URI is a shutdown command.
                shutdown = request.getUri().equals(SHUTDOWN_COMMAND);
            } catch (Exception e) {
                e.printStackTrace();
                continue;
            }
        }   // end while
    }

}