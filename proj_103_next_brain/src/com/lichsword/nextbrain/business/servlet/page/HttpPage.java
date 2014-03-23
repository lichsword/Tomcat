package com.lichsword.nextbrain.business.servlet.page;

import com.lichsword.nextbrain.HttpHeader;
import com.lichsword.nextbrain.ServerInfo;
import com.lichsword.nextbrain.backup.Response;
import com.lichsword.nextbrain.http.HttpResponse;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;
import java.io.OutputStream;

/**
 * Created with IntelliJ IDEA.
 * User: lichsword
 * Date: 14-3-23
 * Time: 下午10:07
 * <p/>
 * TODO
 */
public abstract class HttpPage extends PageBase implements IHttpPage {

    /**
     * Pool initial size.
     */
    protected static final int INITIAL_POOL_SIZE = 10;

    /**
     * Pool size increment.
     */
    protected static final int POOL_SIZE_INCREMENT = 5;

    /**
     * TODO version + status, need divider version?
     */
    private String statusLine = "HTTP/1.1 200 OK";

    protected HttpHeader[] headerPool = new HttpHeader[INITIAL_POOL_SIZE];

    /**
     * @deprecated
     */
    protected HttpResponse httpResponse;

    /**
     * Position of the next available header in the pool.
     */
    protected int nextHeader = 0;

    public void addHeader(String name, String value) {
        if (nextHeader == headerPool.length) {
            // Grow the pool.
            HttpHeader[] newHeaderPool = new HttpHeader[headerPool.length + POOL_SIZE_INCREMENT];
            for (int i = 0; i < nextHeader; i++) {
                newHeaderPool[i] = headerPool[i];
            }
            headerPool = newHeaderPool;
        }
        headerPool[nextHeader++] = new HttpHeader(name, value);
    }

    @Override
    public final void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
        // TODO need outputStream? or just PrintWriter?
        if (null == httpResponse) {
            if (res instanceof Response) {
                Response response = (Response) res;
                renderHttpHeader(response.getOutputStreamOrigin(), headerPool);
            } else {
                System.out.println("HttpPage.service.response class error.");
                return;
            }
        } else {
            System.out.println("HttpPage.service.response ok.");
        }

        // TODO 这个要在下一版本中实现 HttpResponse.
//                       renderHttpHeader(httpResponse.getOutputStreamOrigin(), headerPool);
        renderPage(res.getWriter());
    }

    private int contentLength = 0;

    protected void setContentLength(int length) {
        this.contentLength = length;
    }

    protected void setContentType(String contentType) {
        // TOD
        addHeader("ContentType", "Content-Type: text/html; charset=UTF-8");
    }

    private String lastModifyTime = "Last-modified: Tue, 04 Jul 2000 09:46:21 GMT";

    @Override
    public final void renderHttpHeader(OutputStream outputStream, HttpHeader[] httpHeaders) {
        try {
            outputStream.write(dumpHttpHeaderByte());
        } catch (IOException e) {
            e.printStackTrace(); // TODO
        }
    }

    private String dumpHttpHeader() {
        StringBuilder sb = new StringBuilder();
        sb.append(statusLine);
        sb.append('\n');
        sb.append(ServerInfo.getInstance().get());
        sb.append('\n');
        sb.append(lastModifyTime);
        sb.append('\n');
        sb.append("Content-length: " + contentLength);
        sb.append('\n');
        HttpHeader headerItem;
        for (int i = 0; i < nextHeader; i++) {
            headerItem = headerPool[i];
            sb.append(headerItem.value, 0, headerItem.valueEnd);
            sb.append('\n');
        }// end for

        sb.append('\n');
        return sb.toString();
    }


    private byte[] dumpHttpHeaderByte() {
        String headrString = dumpHttpHeader();
        System.out.print("----- response start -----\n");
        System.out.print(headrString);
        System.out.print("----- response end -----\n");
        return headrString.getBytes();
    }
}