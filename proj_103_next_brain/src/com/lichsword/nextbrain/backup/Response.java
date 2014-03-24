package com.lichsword.nextbrain.backup;

import javax.servlet.ServletOutputStream;
import javax.servlet.ServletResponse;
import java.io.*;
import java.util.Locale;

/**
 * Created with IntelliJ IDEA.
 * User: lichsword
 * Date: 14-3-3
 * Time: 下午6:07
 * To change this res use File | Settings | File Templates.
 */
public class Response implements ServletResponse {

    private static final int BUFFER_SIZE = 1024;
    Request request;
    OutputStream output;
    PrintWriter writer;

    public Response(OutputStream output) {
        this.output = output;
    }

    public void setRequest(Request request) {
        this.request = request;
    }

    public void sendStaticResource() throws IOException {
        byte[] bytes = new byte[BUFFER_SIZE];
        FileInputStream fis = null;

        try {
            File file = new File(Constants.WEB_ROOT, request.getUri());
            if (file.exists()) {
                fis = new FileInputStream(file);
                int ch = fis.read(bytes, 0, BUFFER_SIZE);
                while (-1 != ch) {
                    output.write(bytes, 0, ch);
                    ch = fis.read(bytes, 0, BUFFER_SIZE);
                } // end while
            } else {
                // file not found.
                String errorMessage = "HTTP/1.1 404 File Not Found\r\n"
                        +
                        "Content-Type: text/html\r\n"
                        + "Content-Length: 23\r\n" + "\r\n"
                        + "<h1>File Not Found</h1>";
                output.write(errorMessage.getBytes());
            }
        } catch (Exception e) {
            // thrown if cannot instantiate a File object.
            System.out.println(e.toString());

        } finally {
            if (null != fis) {
                fis.close();
            }// end if
        }
    }

    @Override
    public String getCharacterEncoding() {
//        return "utf-8";
        return null;
    }

    @Override
    public String getContentType() {
        return "text/html2";
//        return null;
    }

    public OutputStream getOutputStreamOrigin(){
        return output;
    }

    @Override
    public ServletOutputStream getOutputStream() throws IOException {
        return null;
    }

    @Override
    public PrintWriter getWriter() throws IOException {
        // 自动清缓冲区为true,则 println()会flush，但 print()却不会。
        writer = new PrintWriter(output, true);
        return writer;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void setCharacterEncoding(String s) {
    }

    @Override
    public void setContentLength(int i) {
    }

    @Override
    public void setContentType(String s) {
    }

    @Override
    public void setBufferSize(int i) {
    }

    @Override
    public int getBufferSize() {
        return 0;
    }

    @Override
    public void flushBuffer() throws IOException {
    }

    @Override
    public void resetBuffer() {
    }

    @Override
    public boolean isCommitted() {
        return false;
    }

    @Override
    public void reset() {
    }

    @Override
    public void setLocale(Locale locale) {
    }

    @Override
    public Locale getLocale() {
        return null;
    }
}
