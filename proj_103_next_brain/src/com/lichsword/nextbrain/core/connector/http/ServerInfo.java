package com.lichsword.nextbrain.core.connector.http;

/**
 * Created with IntelliJ IDEA.
 * User: lichsword
 * Date: 14-3-23
 * Time: 下午11:14
 * <p/>
 * TODO
 */
public class ServerInfo {

    private static ServerInfo sInstance;

    private ServerInfo(){

    }

    public static ServerInfo getInstance() {
        if (null == sInstance)
            sInstance = new ServerInfo();
        return sInstance;
    }

    /**
     * TODO need set() method?
     */
    private String server = "Server: Apache/1.3/11 BSafe-SSL/1.38 (Unix)";

    public String get(){
           return server;
    }

}
