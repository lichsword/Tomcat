package com.lichsword.nextbrain.core.res;

import java.io.OutputStream;

/**
 * Created with IntelliJ IDEA.
 * User: lichsword
 * Date: 14-3-24
 * Time: 上午11:29
 * <p/>
 * TODO
 */
public class BinaryResource extends Resource implements IBinary {

    public BinaryResource(String uri) {
        super(uri);
    }

    @Override
    public boolean write(OutputStream outputStream) {
        return false;  // TODO
    }
}
