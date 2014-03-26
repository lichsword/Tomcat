package com.lichsword.nextbrain.backup;

import com.lichsword.nextbrain.core.connector.HttpHeader;
import com.lichsword.nextbrain.core.connector.http.SocketInputStream;
import com.lichsword.nextbrain.core.connector.http.model.HttpRequestLine;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.ServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: lichsword
 * Date: 14-3-3
 * Time: 下午6:04
 * To change this res use File | Settings | File Templates.
 */
public class Request implements ServletRequest {

    private InputStream inputStream;
    private SocketInputStream socketInputStream;

    private String uri;

    /**
     * Content Length(byte)
     */
    private int contentLength;
    /**
     * Content-Type
     */
    private String contentType;
    /**
     * Request line buffer.
     */
    private HttpRequestLine httpRequestLine = new HttpRequestLine();
    private HttpHeader[] httpHeaders;

    private HashMap httpHeaderMap = new HashMap();
    /**
     * Position result.
     */
    private HashMap<String, String[]> httpParametersHashMap = new HashMap<String, String[]>();

    public Request(InputStream input) {
        this.inputStream = input;
        this.socketInputStream = new SocketInputStream(input, 2048);
    }

    public void parseRequestHeader() throws ServletException {
        // TODO
        try {
            /**
             * while loop break by finding no more header.
             */
            while (true) {
                HttpHeader httpHeader = new HttpHeader();

                socketInputStream.readHeader(httpHeader);

                if (0 == httpHeader.nameEnd) {
                    if (0 == httpHeader.valueEnd) {
                        return;
                    } else {
                        throw new ServletException("Request.parseHttpHeaders.colon");
                    }
                }// end if

                // save to hash map.
                String name = new String(httpHeader.name, 0, httpHeader.nameEnd);
                String value = new String(httpHeader.value, 0, httpHeader.valueEnd);
                addHeader(name, value);

                // save value to members.
                if (name.equals("content-length")) {
                    int n = -1;
                    try {
                        n = Integer.parseInt(value);
                        contentLength = n;
                    } catch (NumberFormatException e) {
                        throw new ServletException("Request.parseHttpHeader.contentLength");
                    }
                } else if (name.equals("content-type")) {
                    contentType = value;
                }
            }// end while

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void parse() {

        // reset
        if (inputStream.markSupported()) {
            System.out.println("[INFO]mark supported.");
            try {
                inputStream.mark(socketInputStream.available() + 1);
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("[INFO]mark failed.");
            }
        }

        // Read request line.
        try {
            /**
             * Parse request line.
             */
            socketInputStream.readRequestLine(httpRequestLine);
            /**
             * Parse request header.
             */
            try {
                parseRequestHeader();
            } catch (ServletException e) {
                e.printStackTrace();
            }
            /**
             * Parse request httpParametersHashMap(contain post httpParametersHashMap).
             */
            socketInputStream.readHttpParameters(httpRequestLine, getContentLength(), httpParametersHashMap);
        } catch (IOException e) {
            e.printStackTrace();
        }

//        // Read a set of characters from the socket.
//        StringBuffer requestStringBuffer = new StringBuffer(2048);
//        int i = -1;
//        byte[] buffer = new byte[2048];

//       // ----- start read -----
//        try {
//            // reset.
//            if (inputStream.markSupported()) {
//                inputStream.reset();
//                i = inputStream.read(buffer);
//            }// end if
//        } catch (Exception e) {
//            e.printStackTrace();
//            i = -1;
//        }
        //----- end read -----

//        if (-1 != i) {
//            for (int j = 0; j < i; j++) {
//                requestStringBuffer.append((char) buffer[j]);
//            }
//        } else {
//            System.out.println("[INFO]requestStringBuffer length is -1");
//        }

//        System.out.println("[INFO]-----request data start----------");
//        System.out.print(requestStringBuffer.toString());
//        System.out.println("[INFO]-----request data end----------");

//        uri = parseUri(requestStringBuffer.toString());
//        System.out.println("uriEnd=" + httpRequestLine.uriEnd);
        uri = new String(httpRequestLine.uri, 0, httpRequestLine.uriEnd);

    }

    private void addHeader(String name, String value) {
        name = name.toLowerCase();
        synchronized (httpHeaderMap) {
            ArrayList values = (ArrayList) httpHeaderMap.get(name);
            if (values == null) {
                values = new ArrayList();
                httpHeaderMap.put(name, values);
            }
            values.add(value);
        }
    }

    private String parseUri(String requestString) {
        int index1, index2;
        index1 = requestString.indexOf(' ');
        if (-1 != index1) {
            index2 = requestString.indexOf(' ', index1 + 1);
            if (index2 > index1) {
                return requestString.substring(index1 + 1, index2);
            } // end if
        } // end if

        return null;
    }

    public String getMethod() {
        return new String(httpRequestLine.method, 0, httpRequestLine.methodEnd);
    }

    /**
     * TODO 要把这一层从Request中取出，扩展成 HashMap 和 Json.
     *
     * @return
     */
    public HashMap<String, String[]> getHttpParametersHashMap() {
        return httpParametersHashMap;
    }

    /**
     * @return uri (剔除 host & port 之后的 uri)
     */
    public String getUri() {
        return uri;
    }

    @Override
    public Object getAttribute(String s) {
        return null;
    }

    @Override
    public Enumeration getAttributeNames() {
        return null;
    }

    @Override
    public String getCharacterEncoding() {
        return null;
    }

    @Override
    public void setCharacterEncoding(String s) throws UnsupportedEncodingException {
    }

    @Override
    public int getContentLength() {
        return contentLength;
    }

    @Override
    public String getContentType() {
        return null;
    }

    @Override
    public ServletInputStream getInputStream() throws IOException {
        return null;
    }

    @Override
    public String getParameter(String s) {
        return null;
    }

    @Override
    public Enumeration getParameterNames() {
        return null;
    }

    @Override
    public String[] getParameterValues(String s) {
        return new String[0];
    }

    @Override
    public Map getParameterMap() {
        return null;
    }

    @Override
    public String getProtocol() {
        return null;
    }

    @Override
    public String getScheme() {
        return null;
    }

    @Override
    public String getServerName() {
        return null;
    }

    @Override
    public int getServerPort() {
        return 0;
    }

    @Override
    public BufferedReader getReader() throws IOException {
        return null;
    }

    @Override
    public String getRemoteAddr() {
        return null;
    }

    @Override
    public String getRemoteHost() {
        return null;
    }

    @Override
    public void setAttribute(String s, Object o) {
    }

    @Override
    public void removeAttribute(String s) {
    }

    @Override
    public Locale getLocale() {
        return null;
    }

    @Override
    public Enumeration getLocales() {
        return null;
    }

    @Override
    public boolean isSecure() {
        return false;
    }

    @Override
    public RequestDispatcher getRequestDispatcher(String s) {
        return null;
    }

    @Override
    public String getRealPath(String s) {
        return null;
    }

    @Override
    public int getRemotePort() {
        return 0;
    }

    @Override
    public String getLocalName() {
        return null;
    }

    @Override
    public String getLocalAddr() {
        return null;
    }

    @Override
    public int getLocalPort() {
        return 0;
    }
}
