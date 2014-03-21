package com.lichsword.nextbrain.connector.http;

import com.lichsword.nextbrain.Globals;
import com.lichsword.nextbrain.IHttpRequest;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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

    /**
     * The debugging detail level for this component.
     */
    private int debug = 0;

    /**
     * The match string for identifying a session ID parameter.
     */
    private static final String match =
            ";" + Globals.SESSION_PARAMETER_NAME + "=";

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

    /**
     * Server information string for this server.
     */
    private static final String SERVER_INFO = null;
    // TODO
//            ServerInfo.getServerInfo() + " (HTTP/1.1 Connector)";

    public void process() {

        boolean ok = true;

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

            ((HttpServletResponse) response.getResponse()).setHeader("Server", SERVER_INFO);// TODO
        } catch (IOException e) {
            System.out.println("process.create" + e);
            e.printStackTrace(); // TODO
            ok = false;
        }


        try {
            if (ok) {
//               parseConnection();
                parseRequest(socketInputStream, outputStream);

                if (!request.getRequest().getProtocol()
                        .startsWith("HTTP/0")) {
                    parseHeaders(socketInputStream);
                }// end if

                if (http11){

                }// end if

            }// end if
        } catch (ServletException e) {
            e.printStackTrace(); // TODO
        } catch (IOException e) {
            e.printStackTrace(); // TODO
        }

    }

    private HttpRequestLine requestLine = new HttpRequestLine();
    /**
     * Processor state
     */
    private int status = Constants.PROCESSOR_IDLE;

    /**
     * Keep alive indicator.
     */
    private boolean keepAlive = false;


    /**
     * HTTP/1.1 client.
     */
    private boolean http11 = true;

    /**
     * True if the client has asked to recieve a request acknoledgement. If so
     * the server will send a preliminary 100 Continue response just after it
     * has successfully parsed the request headers, and before starting
     * reading the request entity body.
     */
    private boolean sendAck = false;

    private void parseRequest(SocketInputStream socketInputStream, OutputStream outputStream)
            throws IOException, ServletException {
        // Parse the incoming request line.
        socketInputStream.readRequestLine(requestLine);

        //When the previous mehtod returns, we're actually processing a request.
        status = Constants.PROCESSOR_ACTIVE;

        String method = new String(requestLine.method, 0, requestLine.methodEnd);
        String uri = null;
        String protocol = new String(requestLine.protocol, 0,
                requestLine.protocolEnd);


        if (0 == protocol.length()) {
            protocol = "HTTP/0.9";
        }// end if

        if (protocol.equals("HTTP/1.1")) {
            http11 = true;
            sendAck = false;
        } else {
            http11 = false;
            sendAck = false;
            // For HTTP/1.0, connection are not persistent by default,
            // unless specified with a Connection: Keep-Alive header.
            keepAlive = false;
        }


        // Validate the incoming request line
        if (method.length() < 1) {
            throw new ServletException
                    ("httpProcessor.parseRequest.method");
        } else if (requestLine.uriEnd < 1) {
            throw new ServletException
                    ("httpProcessor.parseRequest.uri");
        }

        // Parse any query parameters out of the request URI
        int question = requestLine.indexOf("?");
        if (question >= 0) {
            request.setQueryString
                    (new String(requestLine.uri, question + 1,
                            requestLine.uriEnd - question - 1));
            if (debug >= 1)
                System.out.println(" Query string is " +
                        ((HttpServletRequest) request.getRequest())
                                .getQueryString());
            uri = new String(requestLine.uri, 0, question);
        } else {
            request.setQueryString(null);
            uri = new String(requestLine.uri, 0, requestLine.uriEnd);
        }

        // Checking for an absolute URI (with the HTTP protocol)
        if (!uri.startsWith("/")) {
            int pos = uri.indexOf("://");
            // Parsing out protocol and host name
            if (pos != -1) {
                pos = uri.indexOf('/', pos + 3);
                if (pos == -1) {
                    uri = "";
                } else {
                    uri = uri.substring(pos);
                }
            }
        }

        // Parse any requested session ID out of the request URI
        int semicolon = uri.indexOf(match);
        if (semicolon >= 0) {
            String rest = uri.substring(semicolon + match.length());
            int semicolon2 = rest.indexOf(';');
            if (semicolon2 >= 0) {
                request.setRequestedSessionId(rest.substring(0, semicolon2));
                rest = rest.substring(semicolon2);
            } else {
                request.setRequestedSessionId(rest);
                rest = "";
            }
            request.setRequestedSessionURL(true);
            uri = uri.substring(0, semicolon) + rest;
            if (debug >= 1)
                System.out.println(" Requested URL session id is " +
                        ((HttpServletRequest) request.getRequest())
                                .getRequestedSessionId());
        } else {
            request.setRequestedSessionId(null);
            request.setRequestedSessionURL(false);
        }

        // Normalize URI (using String operations at the moment)
        String normalizedUri = normalize(uri);
        if (debug >= 1)
            System.out.println("Normalized: '" + uri + "' to '" + normalizedUri + "'");

        // Set the corresponding request properties
        ((IHttpRequest) request).setMethod(method);
        request.setProtocol(protocol);
        if (normalizedUri != null) {
            ((IHttpRequest) request).setRequestURI(normalizedUri);
        } else {
            ((IHttpRequest) request).setRequestURI(uri);
        }

        // TODO
//        request.setSecure(connector.getSecure());
//        request.setScheme(connector.getScheme());

        if (normalizedUri == null) {
            System.out.println(" Invalid request URI: '" + uri + "'");
            throw new ServletException("Invalid URI: " + uri + "'");
        }

        if (debug >= 1)
            System.out.println(" Request is '" + method + "' for '" + uri +
                    "' with protocol '" + protocol + "'");

    }

    /**
     * Return a context-relative path, beginning with a "/", that represents
     * the canonical version of the specified path after ".." and "." elements
     * are resolved out.  If the specified path attempts to go outside the
     * boundaries of the current context (i.e. too many ".." path elements
     * are present), return <code>null</code> instead.
     *
     * @param path Path to be normalized
     */
    protected String normalize(String path) {

        if (path == null)
            return null;

        // Create a place for the normalized path
        String normalized = path;

        // Normalize "/%7E" and "/%7e" at the beginning to "/~"
        if (normalized.startsWith("/%7E") ||
                normalized.startsWith("/%7e"))
            normalized = "/~" + normalized.substring(4);

        // Prevent encoding '%', '/', '.' and '\', which are special reserved
        // characters
        if ((normalized.indexOf("%25") >= 0)
                || (normalized.indexOf("%2F") >= 0)
                || (normalized.indexOf("%2E") >= 0)
                || (normalized.indexOf("%5C") >= 0)
                || (normalized.indexOf("%2f") >= 0)
                || (normalized.indexOf("%2e") >= 0)
                || (normalized.indexOf("%5c") >= 0)) {
            return null;
        }

        if (normalized.equals("/."))
            return "/";

        // Normalize the slashes and add leading slash if necessary
        if (normalized.indexOf('\\') >= 0)
            normalized = normalized.replace('\\', '/');
        if (!normalized.startsWith("/"))
            normalized = "/" + normalized;

        // Resolve occurrences of "//" in the normalized path
        while (true) {
            int index = normalized.indexOf("//");
            if (index < 0)
                break;
            normalized = normalized.substring(0, index) +
                    normalized.substring(index + 1);
        }

        // Resolve occurrences of "/./" in the normalized path
        while (true) {
            int index = normalized.indexOf("/./");
            if (index < 0)
                break;
            normalized = normalized.substring(0, index) +
                    normalized.substring(index + 2);
        }

        // Resolve occurrences of "/../" in the normalized path
        while (true) {
            int index = normalized.indexOf("/../");
            if (index < 0)
                break;
            if (index == 0)
                return (null);  // Trying to go outside our context
            int index2 = normalized.lastIndexOf('/', index - 1);
            normalized = normalized.substring(0, index2) +
                    normalized.substring(index + 3);
        }

        // Declare occurrences of "/..." (three or more dots) to be invalid
        // (on some Windows platforms this walks the directory tree!!!)
        if (normalized.indexOf("/...") >= 0)
            return (null);

        // Return the normalized path that we have completed
        return (normalized);

    }

    /**
     * Parse the incoming HTTP request headers, and set the appropriate
     * request headers.
     *
     * @param input The input stream connected to our socket
     *
     * @exception java.io.IOException if an input/output error occurs
     * @exception javax.servlet.ServletException if a parsing error occurs
     */
    private void parseHeaders(SocketInputStream input)
            throws IOException, ServletException {

        while (true) {

            HttpHeader header = request.allocateHeader();

            // Read the next header
            input.readHeader(header);
            if (header.nameEnd == 0) {
                if (header.valueEnd == 0) {
                    return;
                } else {
                    throw new ServletException
                            (sm.getString("httpProcessor.parseHeaders.colon"));
                }
            }

            String value = new String(header.value, 0, header.valueEnd);
            if (debug >= 1)
                log(" Header " + new String(header.name, 0, header.nameEnd)
                        + " = " + value);

            // Set the corresponding request headers
            if (header.equals(DefaultHeaders.AUTHORIZATION_NAME)) {
                request.setAuthorization(value);
            } else if (header.equals(DefaultHeaders.ACCEPT_LANGUAGE_NAME)) {
                parseAcceptLanguage(value);
            } else if (header.equals(DefaultHeaders.COOKIE_NAME)) {
                Cookie cookies[] = RequestUtil.parseCookieHeader(value);
                for (int i = 0; i < cookies.length; i++) {
                    if (cookies[i].getName().equals
                            (Globals.SESSION_COOKIE_NAME)) {
                        // Override anything requested in the URL
                        if (!request.isRequestedSessionIdFromCookie()) {
                            // Accept only the first session id cookie
                            request.setRequestedSessionId
                                    (cookies[i].getValue());
                            request.setRequestedSessionCookie(true);
                            request.setRequestedSessionURL(false);
                            if (debug >= 1)
                                log(" Requested cookie session id is " +
                                        ((HttpServletRequest) request.getRequest())
                                                .getRequestedSessionId());
                        }
                    }
                    if (debug >= 1)
                        log(" Adding cookie " + cookies[i].getName() + "=" +
                                cookies[i].getValue());
                    request.addCookie(cookies[i]);
                }
            } else if (header.equals(DefaultHeaders.CONTENT_LENGTH_NAME)) {
                int n = -1;
                try {
                    n = Integer.parseInt(value);
                } catch (Exception e) {
                    throw new ServletException
                            (sm.getString
                                    ("httpProcessor.parseHeaders.contentLength"));
                }
                request.setContentLength(n);
            } else if (header.equals(DefaultHeaders.CONTENT_TYPE_NAME)) {
                request.setContentType(value);
            } else if (header.equals(DefaultHeaders.HOST_NAME)) {
                int n = value.indexOf(':');
                if (n < 0) {
                    if (connector.getScheme().equals("http")) {
                        request.setServerPort(80);
                    } else if (connector.getScheme().equals("https")) {
                        request.setServerPort(443);
                    }
                    if (proxyName != null)
                        request.setServerName(proxyName);
                    else
                        request.setServerName(value);
                } else {
                    if (proxyName != null)
                        request.setServerName(proxyName);
                    else
                        request.setServerName(value.substring(0, n).trim());
                    if (proxyPort != 0)
                        request.setServerPort(proxyPort);
                    else {
                        int port = 80;
                        try {
                            port =
                                    Integer.parseInt(value.substring(n+1).trim());
                        } catch (Exception e) {
                            throw new ServletException
                                    (sm.getString
                                            ("httpProcessor.parseHeaders.portNumber"));
                        }
                        request.setServerPort(port);
                    }
                }
            } else if (header.equals(DefaultHeaders.CONNECTION_NAME)) {
                if (header.valueEquals
                        (DefaultHeaders.CONNECTION_CLOSE_VALUE)) {
                    keepAlive = false;
                    response.setHeader("Connection", "close");
                }
                //request.setConnection(header);
                /*
                  if ("keep-alive".equalsIgnoreCase(value)) {
                  keepAlive = true;
                  }
                */
            } else if (header.equals(DefaultHeaders.EXPECT_NAME)) {
                if (header.valueEquals(DefaultHeaders.EXPECT_100_VALUE))
                    sendAck = true;
                else
                    throw new ServletException
                            (sm.getString
                                    ("httpProcessor.parseHeaders.unknownExpectation"));
            } else if (header.equals(DefaultHeaders.TRANSFER_ENCODING_NAME)) {
                //request.setTransferEncoding(header);
            }

            request.nextHeader();

        }

    }
}
