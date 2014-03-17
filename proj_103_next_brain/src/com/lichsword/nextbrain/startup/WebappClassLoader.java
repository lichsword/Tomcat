package com.lichsword.nextbrain.startup;

import java.util.HashMap;

/**
 * Created with IntelliJ IDEA.
 * User: lichsword
 * Date: 14-3-17
 * Time: 下午12:16
 * To change this template use File | Settings | File Templates.
 */
public class WebappClassLoader {

    private HashMap<String, String> servletMapping = new HashMap<String, String>();

    private void addServeletMapping(String path, String servletName) {
        servletMapping.put(path, servletName);
    }

    private void removeServletMapping(String path) {
        servletMapping.remove(path);
    }

}
