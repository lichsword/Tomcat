package com.lichsword.nextbrain.core.connector.http;

import com.lichsword.nextbrain.core.Log;
import com.lichsword.nextbrain.core.connector.HttpHeader;
import com.lichsword.nextbrain.core.connector.http.model.HttpRequestLine;

import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Set;

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

//    /**
//     * Position result.
//     */
//    private HashMap<String, String[]> parameters = null;

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


        // Reading protocol
        maxRead = httpRequestLine.protocol.length;
        readStart = pos;
        readCount = 0;

        while (!eol) {
            // if the buffer is full, extend it
            if (readCount >= maxRead) {
                if ((2 * maxRead) <= HttpRequestLine.MAX_PROTOCOL_SIZE) {
                    char[] newBuffer = new char[2 * maxRead];
                    System.arraycopy(httpRequestLine.protocol, 0, newBuffer, 0,
                            maxRead);
                    httpRequestLine.protocol = newBuffer;
                    maxRead = httpRequestLine.protocol.length;
                } else {
                    throw new IOException
                            ("requestStream.readline.toolong");
                }
            }
            // We're at the end of the internal buffer
            if (pos >= count) {
                // Copying part (or all) of the internal buffer to the line
                // buffer
                int val = read();
                if (val == -1)
                    throw new IOException
                            ("requestStream.readline.error");
                pos = 0;
                readStart = 0;
            }
            if (buffer[pos] == CR) {
                // Skip CR.
            } else if (buffer[pos] == LF) {
                eol = true;
            } else {
                httpRequestLine.protocol[readCount] = (char) buffer[pos];
                readCount++;
            }
            pos++;
        }

        httpRequestLine.protocolEnd = readCount;
        // print info
        Log.d(TAG, "[INFO]method=" + new String(httpRequestLine.method, 0, httpRequestLine.methodEnd));
        Log.d(TAG, "[INFO]uri=" + new String(httpRequestLine.uri, 0, httpRequestLine.uriEnd));
        Log.d(TAG, "[INFO]protocol=" + new String(httpRequestLine.protocol, 0, httpRequestLine.protocolEnd));
    }

    public void readHeader(HttpHeader httpHeader)
            throws IOException {
        // Recycling check
        if (httpHeader.nameEnd != 0) {
            httpHeader.recycle();
        }// end if

        // Checking for a blank line
        int chr = read();
        if ((chr == CR) || (chr == LF)) { // Skipping CR
            if (chr == CR)
                read(); // Skipping LF
            httpHeader.nameEnd = 0;
            httpHeader.valueEnd = 0;
            return;
        } else {
            pos--;
        }

        /**
         * Reading the header name
         */
        int maxRead = httpHeader.name.length;
        int readStart = pos;
        int readCount = 0;

        /** colon : 冒号 */
        boolean colon = false;

        while (!colon) {
            // if the buffer is full, extend it
            if (readCount >= maxRead) {
                if ((2 * maxRead) <= HttpHeader.MAX_NAME_SIZE) {
                    char[] newBuffer = new char[2 * maxRead];
                    System.arraycopy(httpHeader.name, 0, newBuffer, 0, maxRead);
                    httpHeader.name = newBuffer;
                    maxRead = httpHeader.name.length;
                } else {
                    throw new IOException
                            ("requestStream.readline.toolong");
                }
            }// end if

            // We're at the end of the internal buffer
            if (pos >= count) {
                int val = read();
                if (-1 == val) {
                    throw new IOException
                            ("requestStream.readline.error");
                }
                pos = 0;
                readStart = 0;
            }// end if

            if (buffer[pos] == COLON) {
                colon = true;
            }// end if

            char val = (char) buffer[pos];
            if ((val >= 'A') && (val <= 'Z')) {
                // 全部小写
                val = (char) (val - LC_OFFSET);
            }// end if

            httpHeader.name[readCount] = val;
            readCount++;
            pos++;
        }// end while

        httpHeader.nameEnd = readCount - 1;


        /**
         * Reading the header value (which can be spanned over multiple lines)
         */
        maxRead = httpHeader.value.length;
        readStart = pos;
        readCount = 0;

        int crPos = -2;

        boolean eol = false;
        boolean validLine = true;

        while (validLine) {

            boolean space = true;

            // Skipping spaces
            // Note : Only leading white spaces are removed. Trailing white
            // spaces are not.
            while (space) {
                // We're at the end of the internal buffer
                if (pos >= count) {
                    // Copying part (or all) of the internal buffer to the line
                    // buffer
                    int val = read();
                    if (val == -1)
                        throw new IOException
                                ("requestStream.readline.error");
                    pos = 0;
                    readStart = 0;
                }
                if ((buffer[pos] == SP) || (buffer[pos] == HT)) {
                    pos++;
                } else {
                    space = false;
                }
            }

            while (!eol) {
                // if the buffer is full, extend it
                if (readCount >= maxRead) {
                    if ((2 * maxRead) <= HttpHeader.MAX_VALUE_SIZE) {
                        char[] newBuffer = new char[2 * maxRead];
                        System.arraycopy(httpHeader.value, 0, newBuffer, 0,
                                maxRead);
                        httpHeader.value = newBuffer;
                        maxRead = httpHeader.value.length;
                    } else {
                        throw new IOException
                                ("requestStream.readline.toolong");
                    }
                }// end if

                // We're at the end of the internal buffer
                if (pos >= count) {
                    // Copying part (or all) of the internal buffer to the line
                    // buffer
                    int val = read();
                    if (val == -1)
                        throw new IOException
                                ("requestStream.readline.error");
                    pos = 0;
                    readStart = 0;
                }// end if

                if (buffer[pos] == CR) {
                } else if (buffer[pos] == LF) {
                    eol = true;
                } else {
                    // FIXME : Check if binary conversion is working fine
                    int ch = buffer[pos] & 0xff;
                    httpHeader.value[readCount] = (char) ch;
                    readCount++;
                }
                pos++;
            }

            int nextChr = read();

            if ((nextChr != SP) && (nextChr != HT)) {
                pos--;
                validLine = false;
            } else {
                eol = false;
                // if the buffer is full, extend it
                if (readCount >= maxRead) {
                    if ((2 * maxRead) <= HttpHeader.MAX_VALUE_SIZE) {
                        char[] newBuffer = new char[2 * maxRead];
                        System.arraycopy(httpHeader.value, 0, newBuffer, 0,
                                maxRead);
                        httpHeader.value = newBuffer;
                        maxRead = httpHeader.value.length;
                    } else {
                        throw new IOException
                                ("requestStream.readline.toolong");
                    }
                }// end if
                httpHeader.value[readCount] = ' ';
                readCount++;
            }

        }

        httpHeader.valueEnd = readCount;
    }

    /**
     * Parse parameters.
     * GET ?***
     * POST name:value&name:value
     */
    public void readHttpParameters(HttpRequestLine httpRequestLine, int contentLength, HashMap parameters) {
        if (null == httpRequestLine) {
            Log.e(TAG, "readHttpParameters.param.httpRequestLine.null");
            return;
        }// end if

        // Parse parameters from content.
        String method = new String(httpRequestLine.method, 0, httpRequestLine.methodEnd);
        if (!"POST".equals(method)) {
            Log.d(TAG, "readHttpParameters.httpRequestLine.method not POST");
            return;
        }// end if

        if (contentLength <= 0) {
            Log.d(TAG, "readHttpParameters.contenLength <= 0");
            return;
        }// end if

        HashMap<String, String[]> results = parameters;
        if (null == results) {
            results = new HashMap<String, String[]>();
        }// end if

        String encoding = "utf-8";

        try {
            int max = contentLength;
            int len = 0;
            byte buf[] = new byte[contentLength];
            while (len < max) {
                int next = 0;
                System.arraycopy(buffer, pos, buf, 0, contentLength);
                next = max - len;
//                next = inputStream.read(buf, len, max - len);
                if (next < 0) {
                    break;
                }// end if
                len += next;
            }// end while
//            inputStream.close();
            if (len < max) {
                throw new RuntimeException("Content length mismatch");
            }// end if

            // format element.
            RequestUtil.parseParameters(results, buf, encoding);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // save back.
        parameters = results;

        // debug print.
        Set set = parameters.entrySet();
        Iterator<Entry> iterator = set.iterator();
        Entry<String, String[]> entry;
        String entryKey;
        String[] entryValues;
        StringBuilder sb;
        while (iterator.hasNext()) {
            entry = iterator.next();
            entryKey = entry.getKey();
            entryValues = entry.getValue();
            sb = new StringBuilder();
            sb.append("\n{\nkey=" + entryKey);
            sb.append('\n');
            for (String string : entryValues) {
                sb.append("value=" + string);
            }
            sb.append("\n}");
            Log.d(TAG, sb.toString());
        }// end while

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
            Log.d(TAG, "[INFO]==request message==");
            Log.d(TAG, "[INFO]\n" + new String(buffer, 0, nRead));
            Log.d(TAG, "[INFO]===================");
        } else {
            Log.d(TAG, "[INFO]nRead=" + nRead);
        }

    }


}
