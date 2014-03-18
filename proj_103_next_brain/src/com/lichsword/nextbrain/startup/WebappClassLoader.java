package com.lichsword.nextbrain.startup;

import com.lichsword.nextbrain.business.servlet.DBServlet;
import com.lichsword.nextbrain.business.servlet.webpage.ArticlePageServlet;
import com.lichsword.nextbrain.business.servlet.webpage.MainPageServlet;
import com.lichsword.nextbrain.business.servlet.webpage.TestPageServlet;

import java.util.HashMap;

/**
 * Created with IntelliJ IDEA.
 * User: lichsword
 * Date: 14-3-17
 * Time: 下午12:16
 */
public class WebappClassLoader extends ClassLoader {

    private HashMap<String, String> servletMapping = new HashMap<String, String>();

    private static WebappClassLoader sInstance;

    private WebappClassLoader() {
        // test pages
        addServeletMapping("/servlet/test", TestPageServlet.class.getCanonicalName());
        // real pages
        addServeletMapping("/servlet/db", DBServlet.class.getCanonicalName());
        addServeletMapping("/servlet/home", MainPageServlet.class.getCanonicalName());
        addServeletMapping("/servlet/article", ArticlePageServlet.class.getCanonicalName());
    }

    public static WebappClassLoader getInstance() {
        if (null == sInstance) {
            sInstance = new WebappClassLoader();
        }// end if
        return sInstance;
    }

    /**
     * @param uri
     * @return
     */
    public Class loadServletClass(String uri) throws ClassNotFoundException {
        String servletName = servletMapping.get(uri);
        System.out.println("[INFO]servletName=" + servletName);
        Class result = loadClass(servletName);
        return result;
    }


    private void addServeletMapping(String path, String servletName) {
        servletMapping.put(path, servletName);
    }

    private void removeServletMapping(String path) {
        servletMapping.remove(path);
    }


}
