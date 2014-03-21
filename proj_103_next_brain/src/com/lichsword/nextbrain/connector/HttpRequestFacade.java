package com.lichsword.nextbrain.connector;

import com.lichsword.nextbrain.IHttpRequest;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.util.Enumeration;

/**
 * Created with IntelliJ IDEA.
 * User: lichsword
 * Date: 14-3-21
 * Time: 下午5:51
 * <p/>
 * TODO
 */
public class HttpRequestFacade extends  RequestFacade implements HttpServletRequest {

    /**
     * Construct a wrapper for the specified request.
     *
     * @param request The request to be wrapped
     */
    public HttpRequestFacade(IHttpRequest request) {
        super(request);
    }

    @Override
    public String getAuthType() {
       return null;
    }

    @Override
    public Cookie[] getCookies() {
        return new Cookie[0];  // TODO
    }

    @Override
    public long getDateHeader(String name) {
        return 0;  // TODO
    }

    @Override
    public String getHeader(String name) {
        return null;  // TODO
    }

    @Override
    public Enumeration getHeaders(String name) {
        return null;  // TODO
    }

    @Override
    public Enumeration getHeaderNames() {
        return null;  // TODO
    }

    @Override
    public int getIntHeader(String name) {
        return 0;  // TODO
    }

    @Override
    public String getMethod() {
        return null;  // TODO
    }

    @Override
    public String getPathInfo() {
        return null;  // TODO
    }

    @Override
    public String getPathTranslated() {
        return null;  // TODO
    }

    @Override
    public String getContextPath() {
        return null;  // TODO
    }

    @Override
    public String getQueryString() {
        return null;  // TODO
    }

    @Override
    public String getRemoteUser() {
        return null;  // TODO
    }

    @Override
    public boolean isUserInRole(String role) {
        return false;  // TODO
    }

    @Override
    public Principal getUserPrincipal() {
        return null;  // TODO
    }

    @Override
    public String getRequestedSessionId() {
        return null;  // TODO
    }

    @Override
    public String getRequestURI() {
        return null;  // TODO
    }

    @Override
    public StringBuffer getRequestURL() {
        return null;  // TODO
    }

    @Override
    public String getServletPath() {
        return null;  // TODO
    }

    @Override
    public HttpSession getSession(boolean create) {
        return null;  // TODO
    }

    @Override
    public HttpSession getSession() {
        return null;  // TODO
    }

    @Override
    public boolean isRequestedSessionIdValid() {
        return false;  // TODO
    }

    @Override
    public boolean isRequestedSessionIdFromCookie() {
        return false;  // TODO
    }

    @Override
    public boolean isRequestedSessionIdFromURL() {
        return false;  // TODO
    }

    @Override
    public boolean isRequestedSessionIdFromUrl() {
        return false;  // TODO
    }
}
