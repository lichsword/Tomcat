package com.lichsword.nextbrain.backup;

import com.lichsword.nextbrain.core.connector.http.SocketInputStream;
import com.lichsword.nextbrain.core.connector.http.model.HttpRequestLine;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletInputStream;
import javax.servlet.ServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.Enumeration;
import java.util.Locale;
import java.util.Map;

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
     * Request line buffer.
     */
    private HttpRequestLine httpRequestLine = new HttpRequestLine();

    public Request(InputStream input) {
        this.inputStream = input;
        this.socketInputStream = new SocketInputStream(input, 2048);
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
            socketInputStream.readRequestLine(httpRequestLine);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Read a set of characters from the socket.
        StringBuffer requestStringBuffer = new StringBuffer(2048);
        int i = -1;
        byte[] buffer = new byte[2048];

        //----- start read -----
        try {
            // reset.
            if (inputStream.markSupported()) {
                inputStream.reset();
                i = inputStream.read(buffer);
            }// end if
        } catch (Exception e) {
            e.printStackTrace();
            i = -1;
        }
        //----- end read -----

        if (-1 != i) {
            for (int j = 0; j < i; j++) {
                requestStringBuffer.append((char) buffer[j]);
            }
        } else {
            System.out.println("[INFO]requestStringBuffer length is -1");
        }

        System.out.println("[INFO]-----request data start----------");
        System.out.print(requestStringBuffer.toString());
        System.out.println("[INFO]-----request data end----------");

//        uri = parseUri(requestStringBuffer.toString());
        System.out.println("uriEnd=" + httpRequestLine.uriEnd);
        uri = new String(httpRequestLine.uri, 0, httpRequestLine.uriEnd);

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
        return 0;
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
