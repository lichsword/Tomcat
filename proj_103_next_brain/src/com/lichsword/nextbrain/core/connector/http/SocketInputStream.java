package com.lichsword.nextbrain.core.connector.http;

import com.lichsword.nextbrain.core.Log;
import com.lichsword.nextbrain.core.connector.http.model.HttpRequestLine;

import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created with IntelliJ IDEA.
 * User: lichsword
 * Date: 14-3-25
 * Time: 上午11:50
 * <p/>
 * 是一个字节流的中转接口，到此，数据被中转到此对象内部
 * 并自动解析 http request status & head.
 * 对外提供读取接口.
 * TODO
 * 我觉得更应该是组合，而非继承自 InputStream
 */
public class SocketInputStream extends InputStream {

    private static final String TAG = SocketInputStream.class.getSimpleName();

    /**
     * CR.
     */
    private static final byte CR = (byte) '\r';

    /**
     * LF.
     */
    private static final byte LF = (byte) '\n';

    /**
     * SP.
     */
    private static final byte SP = (byte) ' ';


    /**
     * HT.
     */
    private static final byte HT = (byte) '\t';


    /**
     * COLON.
     */
    private static final byte COLON = (byte) ':';


    /**
     * Lower case offset.
     */
    private static final int LC_OFFSET = 'A' - 'a';

    /**
     * Underlying input stream.
     */
    private InputStream inputStream;

    /**
     * Internal buffer.
     */
    protected byte buffer[];

    /**
     * Last valid byte.
     */
    protected int count;


    /**
     * Position in the buffer.
     */
    protected int pos;

    public SocketInputStream(InputStream inputStream, int bufferSize) {
        this.inputStream = inputStream;
        buffer = new byte[bufferSize];
    }

    public void readRequestLine(HttpRequestLine httpRequestLine)
            throws IOException {
        // Recycle check.
        if (0 != httpRequestLine.methodEnd)
            httpRequestLine.recycle();

        // check blank line
        int chr = 0;// char read count
        do {
            chr = read();
        } while ((CR == chr) || (LF == chr));

        if (-1 == chr) {
            throw new EOFException("SocketInputStream.readRequestLine.error");
        }// end if
        pos--;

        // Reading the method name.
        int maxRead = httpRequestLine.method.length;
        int readStart = pos;
        int readCount = 0;

        boolean space = false;
        while (!space) {
            // if the buffer is full, extend it.
            if (readCount >= maxRead) {
                if ((2 * maxRead) <= HttpRequestLine.MAX_METHOD_SIZE) {
                    char[] newBuffer = new char[2 * maxRead];
                    System.arraycopy(httpRequestLine.method, 0, newBuffer, 0, maxRead);
                    httpRequestLine.method = newBuffer;
                    maxRead = httpRequestLine.method.length;
                } else {
                    System.out.println("readCount=" + readCount + ", maxRead=" + maxRead);
                    throw new IOException("SocketInputStream.readRequestLine.toolong");
                }
            }// end if

            // We're at the end of the internal buffer.
            if (pos >= count) {
                int val = read();
                if (-1 == val) {
                    throw new IOException("SocketInputStream.readRequestLine.error");
                }// end if
                pos = 0;
                readStart = 0;
            }// end if

            if (SP == buffer[pos]) {
                space = true;
            }// end if

            httpRequestLine.method[readCount] = (char) buffer[pos];
            readCount++;
            pos++;
        }// end while

        httpRequestLine.methodEnd = readCount - 1;

        // Reading URI.
        maxRead = httpRequestLine.uri.length;
        readStart = pos;
        readCount = 0;

        space = false;

        boolean eol = false;

        while (!space) {
            // if the buffer is full, extend it
            if (readCount >= maxRead) {
                if ((2 * maxRead) <= HttpRequestLine.MAX_URI_SIZE) {
                    char[] newBuffer = new char[2 * maxRead];
                    System.arraycopy(httpRequestLine.uri, 0, newBuffer, 0,
                            maxRead);
                    httpRequestLine.uri = newBuffer;
                    maxRead = httpRequestLine.uri.length;
                } else {
                    throw new IOException
                            ("SocketInputStream.readRequestLine.toolong");
                }
            }// end if

            // We're at the end of the internal buffer
            if (pos >= count) {
                int val = read();
                if (val == -1)
                    throw new IOException
                            ("SocketInputStream.readRequestLine.error");
                pos = 0;
                readStart = 0;
            }
            if (buffer[pos] == SP) {
                space = true;
            } else if ((CR == buffer[pos]) || (LF == buffer[pos])) {
                // HTTP/0.9 style request
                eol = true;
                space = true;
            }
            httpRequestLine.uri[readCount] = (char) buffer[pos];
            readCount++;
            pos++;
        }

        httpRequestLine.uriEnd = readCount - 1;

        Log.d(TAG, "[INFO]method=" + new String(httpRequestLine.method, 0, httpRequestLine.methodEnd));
        Log.d(TAG, "[INFO]uri=" + new String(httpRequestLine.uri, 0, httpRequestLine.uriEnd));

        // Reading protocol
        // TODO can copy SocketInputStream.java line 238-th.
    }


    /**
     * fill() && check first byte.
     *
     * @return 首字节 的值
     * @throws IOException
     */
    @Override
    public int read() throws IOException {
        if (pos >= count) {
            fill();
            if (pos >= count)
                return -1;
        }
        // First byte not empty.
        return buffer[pos++] & 0xff;
    }

    public int available() throws IOException {
        return (count - pos) + inputStream.available();
    }

    /**
     * Close the input stream.
     */
    public void close()
            throws IOException {
        if (inputStream == null)
            return;

        inputStream.close();
        inputStream = null;
        buffer = null;
    }


    /**
     * 把 inputStream 的全部字节转存于内部的 buffer.
     * pos = 0
     * count = readCount.
     */
    protected void fill()
            throws IOException {
        pos = 0;
        count = 0;
        int nRead = inputStream.read(buffer, 0, buffer.length);
        if (nRead > 0) {
            count = nRead;
        }// end if

        Log.d(TAG, "[INFO]==request message==");
        Log.d(TAG, "[INFO]\n" + new String(buffer, 0, nRead));
        Log.d(TAG, "[INFO]===================");
    }
}
