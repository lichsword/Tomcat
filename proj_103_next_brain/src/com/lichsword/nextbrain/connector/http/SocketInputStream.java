package com.lichsword.nextbrain.connector.http;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created with IntelliJ IDEA.
 * User: lichsword
 * Date: 14-3-19
 * Time: 下午5:54
 * <p/>
 * TODO
 */
public class SocketInputStream extends InputStream {
    private InputStream is;
    private byte[] buf;

    public SocketInputStream(InputStream inputStream, int bufferSize) {
        this.is = inputStream;
        this.buf = new byte[bufferSize];
    }

    @Override
    public int read() throws IOException {
        return 0;  // TODO
    }
}
