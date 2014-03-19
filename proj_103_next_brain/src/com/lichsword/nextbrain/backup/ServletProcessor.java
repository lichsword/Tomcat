package com.lichsword.nextbrain.backup;

import javax.servlet.Servlet;

/**
 * Created with IntelliJ IDEA.
 * User: lichsword
 * Date: 14-3-3
 * Time: 下午9:56
 * To change this template use File | Settings | File Templates.
 */
public class ServletProcessor {

    public ServletProcessor() {
    }

    public void process(Request request, Response response) {
        String uri = request.getUri();

        Class myClass = null;
        try {
            myClass = WebappClassLoader.getInstance().loadServletClass(uri);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }

        if (null == myClass) {
            System.out.println("[INFO]class not found");
            return;
        }

        Servlet servlet;
        try {
            servlet = (Servlet) myClass.newInstance();
            servlet.service(request, response);
        } catch (Exception e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (Throwable e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }

    }
}
