package com.lichsword.nextbrain.core.res;

import java.io.OutputStream;

/**
 * Created with IntelliJ IDEA.
 * User: lichsword
 * Date: 14-3-24
 * Time: 上午11:24
 * <p/>
 * TODO
 */
public interface IBinary {

    /**
     *
     * @param outputStream
     * @return
     */
    public boolean write(OutputStream outputStream);

}
