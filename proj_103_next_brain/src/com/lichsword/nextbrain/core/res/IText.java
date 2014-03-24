package com.lichsword.nextbrain.core.res;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;

/**
 * Created with IntelliJ IDEA.
 * User: lichsword
 * Date: 14-3-24
 * Time: 上午11:19
 * <p/>
 * TODO
 */
public interface IText extends IResource {

    /**
     * @return char length of content.
     */
    public int getCharLength();

    public boolean write(PrintWriter writer) throws IOException;
}
