package com.lichsword.nextbrain.core.res;

import com.lichsword.nextbrain.backup.Constants;

import java.io.File;

/**
 * Created with IntelliJ IDEA.
 * User: lichsword
 * Date: 14-3-24
 * Time: 上午11:11
 * <p/>
 * TODO
 */
public class Resource {

    /**
     * Error content length
     */
    protected final int ERROR_CONTENT_LENGTH = -1;

    protected final int BUFFER_SIZE = 1024;

    protected String uri;

    protected File file;

    public Resource(String uri) {
        this.uri = uri;
        file = new File(Constants.WEB_ROOT, uri);
    }

}
