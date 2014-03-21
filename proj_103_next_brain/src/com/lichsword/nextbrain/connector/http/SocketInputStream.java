package com.lichsword.nextbrain.connector.http;

import java.io.EOFException;
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

    private InputStream is;
    private byte[] buf;

    /**
     * Last valid byte.
     */
    protected int count;

    /**
     * Position in the buffer.
     */
    protected int pos;

    public SocketInputStream(InputStream inputStream, int bufferSize) {
        this.is = inputStream;
        this.buf = new byte[bufferSize];
    }

    @Override
    public int read() throws IOException {
        return 0;  // TODO
    }

    public void readRequestLine(HttpRequestLine requestLine) throws IOException {

        // reset check.
        if (0 != requestLine.methodEnd) {
            requestLine.methodEnd = 0;
        }// end if

        int chr = 0;
        do {
            try {
                chr = read();
            } catch (IOException e) {
                chr = -1;
                e.printStackTrace(); // TODO
            }
        } while ((CR == chr) || (LF == chr));

        if (-1 == chr) {
            throw new EOFException("requestStream.readline.error");
        }// end if

        pos--;

        // Reading the method name.
        int maxRead = requestLine.method.length;
        int readStart = pos;
        int readCount = 0;

        boolean space = false;

        while (!space) {
            // if the buffer is full, extend it.
            if (readCount >= maxRead) {
                if ((2 * maxRead) <= HttpRequestLine.MAX_METHOD_SIZE) {
                    char[] newBuffer = new char[2 * maxRead];
                    System.arraycopy(requestLine.method, 0, newBuffer, 0, maxRead);
                    requestLine.method = newBuffer;
                    maxRead = requestLine.method.length;
                } else {
                    throw new IOException("requestStream.readline.toolong");
                }
            }// end if

            // we're at the end of the internal buffer
            if (pos >= count) {
                int val = read();
                if (-1 == val) {
                    throw new IOException("requestStream.readline.error");
                }// end if

                pos = 0;
                readStart = 0;
            }// end if

            if (SP == buf[pos]) {
                space = true;
            }//end if

            requestLine.method[readCount] = (char) buf[pos];
            readCount++;
            pos++;
        }

        requestLine.methodEnd = readCount - 1;

        // Reading URI
        maxRead = requestLine.uri.length;
        readStart = pos;
        readCount = 0;

        space = true;

        boolean eol = false;

        while (!space) {
            // if the buffer is full, extend it
            if (readCount >= maxRead) {
                if ((2 * maxRead) <= HttpRequestLine.MAX_URI_SIZE) {
                    char[] newBuffer = new char[2 * maxRead];
                    System.arraycopy(requestLine.uri, 0, newBuffer, 0,
                            maxRead);
                    requestLine.uri = newBuffer;
                    maxRead = requestLine.uri.length;
                } else {
                    throw new IOException("requestStream.readline.toolong");
                }
            }// end if

            // we're at the end of the internal buffer
            if (pos >= count) {
                int val = read();
                if (-1 == val) {
                    throw new IOException("requestStream.readline.error");
                }// end if
                pos = 0;
                readStart = 0;
            }

            if (SP == buf[pos]) {
                space = true;
            } else if ((CR == buf[pos]) || (LF == buf[pos])) {
                // HTTP/0.9 style request
                eol = true;
                space = true;
            }// end if

            requestLine.uri[readCount] = (char) buf[pos];
            readCount++;
            pos++;

        }// end while

        requestLine.uriEnd = readCount - 1;

        // Reading protocol
        maxRead = requestLine.protocol.length;
        readStart = pos;
        readCount = 0;

        while (!eol) {
            // if the buffer is full, extend it.
            if (readCount >= maxRead) {
                if ((2 * maxRead) <= HttpRequestLine.MAX_PROTOCOL_SIZE) {
                    char[] newBuffer = new char[2 * maxRead];
                    System.arraycopy(requestLine.protocol, 0, newBuffer, 0,
                            maxRead);
                    requestLine.protocol = newBuffer;
                    maxRead = requestLine.protocol.length;
                } else {
                    throw new IOException("requestStream.readline.toolong");
                }
            }

            // we're at the end of the internal buffer.
            if (pos >= count) {
                // Copying part (or all) of the internal buffer to the line
                // buffer
                int val = read();
                if (val == -1)
                    throw new IOException("requestStream.readline.error");
                pos = 0;
                readStart = 0;
            }// end if

            if (buf[pos] == CR) {
                // Skip CR.
            } else if (buf[pos] == LF) {
                eol = true;
            } else {
                requestLine.protocol[readCount] = (char) buf[pos];
                readCount++;
            }
            pos++;
        }
        requestLine.protocolEnd = readCount;
    }

}
