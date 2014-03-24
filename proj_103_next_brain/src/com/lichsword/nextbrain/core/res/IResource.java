package com.lichsword.nextbrain.core.res;

import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: lichsword
 * Date: 14-3-24
 * Time: 上午11:23
 * <p/>
 * TODO
 */
public interface IResource {

    /**
     *
     * @return  byte length of resource.
     */
    public int getContentLength() throws IOException;
}
