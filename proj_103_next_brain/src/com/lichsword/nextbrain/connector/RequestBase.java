package com.lichsword.nextbrain.connector;

import com.lichsword.nextbrain.IConnector;
import com.lichsword.nextbrain.IRequest;
import com.lichsword.nextbrain.IResponse;

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
 * Date: 14-3-19
 * Time: 下午6:09
 * <p/>
 * TODO
 */
public class RequestBase implements ServletRequest, IRequest {

    /**
     * The Connector through which this Request was received.
     */
    protected IConnector connector = null;

    /**
     * The protocol name and version associated with this Request.
     */
    protected String protocol = null;

    public void setConnector(IConnector connector) {
        this.connector = connector;
    }

    /**
     * Set the protocol name and version associated with this Request.
     *
     * @param protocol Protocol name and version
     */
    public void setProtocol(String protocol) {

        this.protocol = protocol;
    }

    @Override
    public Object getAttribute(String name) {
        return null;  // TODO
    }

    @Override
    public Enumeration getAttributeNames() {
        return null;  // TODO
    }

    @Override
    public String getCharacterEncoding() {
        return null;  // TODO
    }

    @Override
    public void setCharacterEncoding(String env) throws UnsupportedEncodingException {
        // TODO
    }

    @Override
    public int getContentLength() {
        return 0;  // TODO
    }

    @Override
    public String getContentType() {
        return null;  // TODO
    }

    @Override
    public ServletInputStream getInputStream() throws IOException {
        return null;  // TODO
    }

    @Override
    public String getParameter(String name) {
        return null;  // TODO
    }

    @Override
    public Enumeration getParameterNames() {
        return null;  // TODO
    }

    @Override
    public String[] getParameterValues(String name) {
        return new String[0];  // TODO
    }

    @Override
    public Map getParameterMap() {
        return null;  // TODO
    }

    @Override
    public String getProtocol() {
        return null;  // TODO
    }

    @Override
    public String getScheme() {
        return null;  // TODO
    }

    @Override
    public String getServerName() {
        return null;  // TODO
    }

    @Override
    public int getServerPort() {
        return 0;  // TODO
    }

    @Override
    public BufferedReader getReader() throws IOException {
        return null;  // TODO
    }

    @Override
    public String getRemoteAddr() {
        return null;  // TODO
    }

    @Override
    public String getRemoteHost() {
        return null;  // TODO
    }

    @Override
    public void setAttribute(String name, Object o) {
        // TODO
    }

    @Override
    public void removeAttribute(String name) {
        // TODO
    }

    @Override
    public Locale getLocale() {
        return null;  // TODO
    }

    @Override
    public Enumeration getLocales() {
        return null;  // TODO
    }

    @Override
    public boolean isSecure() {
        return false;  // TODO
    }

    @Override
    public RequestDispatcher getRequestDispatcher(String path) {
        return null;  // TODO
    }

    @Override
    public String getRealPath(String path) {
        return null;  // TODO
    }

    @Override
    public int getRemotePort() {
        return 0;  // TODO
    }

    @Override
    public String getLocalName() {
        return null;  // TODO
    }

    @Override
    public String getLocalAddr() {
        return null;  // TODO
    }

    @Override
    public int getLocalPort() {
        return 0;  // TODO
    }

    protected InputStream inputStream = null;

    public void setStream(InputStream socketInputStream) {
        this.inputStream = socketInputStream;
    }

    protected IResponse response = null;

    public void setResponse(IResponse response) {
        this.response = response;
    }
}
