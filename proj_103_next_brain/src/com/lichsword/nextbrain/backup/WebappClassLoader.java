package com.lichsword.nextbrain.backup;

import com.lichsword.nextbrain.nb.business.servlet.DBServlet;
import com.lichsword.nextbrain.nb.business.servlet.TipServlet;
import com.lichsword.nextbrain.nb.business.servlet.page.HomePage;
import com.lichsword.nextbrain.nb.business.servlet.page.MainPageServlet;
import com.lichsword.nextbrain.nb.business.servlet.page.TestPageServlet;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.net.URLStreamHandler;
import java.util.HashMap;

/**
 * Created with IntelliJ IDEA.
 * User: lichsword
 * Date: 14-3-17
 * Time: 下午12:16
 */
public class WebappClassLoader extends URLClassLoader {

    private HashMap<String, String> servletMapping = new HashMap<String, String>();

    private static WebappClassLoader sInstance;

    private WebappClassLoader(URL[] urls) {
        super(urls);


        // test pages
        addServeletMapping("/servlet/test", TestPageServlet.class.getCanonicalName());
        // real pages
        addServeletMapping("/servlet/db", DBServlet.class.getCanonicalName());
        addServeletMapping("/servlet/tip", TipServlet.class.getCanonicalName());
        addServeletMapping("/servlet/home", MainPageServlet.class.getCanonicalName());
        addServeletMapping("/servlet-home", HomePage.class.getCanonicalName());
    }

    public static WebappClassLoader getInstance() {
        URL[] urls = new URL[1];
        if (null == sInstance) {
            try {
                // 创建一个 Class loader
                URLStreamHandler streamHandler = null;
                File classPath = new File(Constants.WEB_ROOT);

                // 格式化url字符
                String repository = (new URL("file", null, classPath.getCanonicalPath() + File.separator)).toString();
                urls[0] = new URL(null, repository, streamHandler);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            sInstance = new WebappClassLoader(urls);
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
