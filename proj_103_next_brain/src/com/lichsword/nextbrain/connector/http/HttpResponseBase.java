package com.lichsword.nextbrain.connector.http;

import com.lichsword.nextbrain.connector.ResponseBase;

import javax.servlet.http.Cookie;
import  javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: lichsword
 * Date: 14-3-19
 * Time: 下午7:42
 * <p/>
 * TODO
 */
public class HttpResponseBase extends ResponseBase implements HttpServletResponse {

    @Override
    public void addCookie(Cookie cookie) {
        // TODO
    }

    @Override
    public boolean containsHeader(String name) {
        return false;  // TODO
    }

    @Override
    public String encodeURL(String url) {
        return null;  // TODO
    }

    @Override
    public String encodeRedirectURL(String url) {
        return null;  // TODO
    }

    @Override
    public String encodeUrl(String url) {
        return null;  // TODO
    }

    @Override
    public String encodeRedirectUrl(String url) {
        return null;  // TODO
    }

    @Override
    public void sendError(int sc, String msg) throws IOException {
        // TODO
    }

    @Override
    public void sendError(int sc) throws IOException {
        // TODO
    }

    @Override
    public void sendRedirect(String location) throws IOException {
        // TODO
    }

    @Override
    public void setDateHeader(String name, long date) {
        // TODO
    }

    @Override
    public void addDateHeader(String name, long date) {
        // TODO
    }

    @Override
    public void setHeader(String name, String value) {
        // TODO
    }

    @Override
    public void addHeader(String name, String value) {
        // TODO
    }

    @Override
    public void setIntHeader(String name, int value) {
        // TODO
    }

    @Override
    public void addIntHeader(String name, int value) {
        // TODO
    }

    @Override
    public void setStatus(int sc) {
        // TODO
    }

    @Override
    public void setStatus(int sc, String sm) {
        // TODO
    }

    public ServletResponse getResponse() {
        return null;  // TODO
    }
}
