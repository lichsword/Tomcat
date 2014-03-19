package com.lichsword.nextbrain.backup;

/**
 * Created with IntelliJ IDEA.
 * User: lichsword
 * Date: 14-3-3
 * Time: 下午9:58
 * To change this template use File | Settings | File Templates.
 */
public class StaticResourceProcessor {

    public StaticResourceProcessor() {
    }

    public void process(Request request, Response response) {

        try {
            response.sendStaticResource();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
