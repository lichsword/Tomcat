package com.lichsword.nextbrain.business.servlet.webpage;


import com.lichsword.nextbrain.backup.Response;
import com.lichsword.nextbrain.business.servlet.TempHeader;

import javax.servlet.*;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;

/**
 * Created with IntelliJ IDEA.
 * User: lichsword
 * Date: 14-3-18
 * Time: 下午4:14
 * <p/>
 * TODO
 */
public class TestPageServlet implements Servlet {

    @Override
    public void init(ServletConfig servletConfig) throws ServletException {
        // TODO
    }

    @Override
    public ServletConfig getServletConfig() {
        return null;  // TODO
    }

    @Override
    public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {

        Response response = (Response)res;
        OutputStream outputStream = response.getOutputStreamOrigin();
        if (null != outputStream) {
            byte[] bytes = TempHeader.dumpResponseHeaderBytes();
            outputStream.write(bytes, 0, bytes.length);
        }// end if

        PrintWriter out = res.getWriter();
        String type = res.getContentType();
//        out.println("type=" + type);
//        response.setContentType("text/html");
//        response.setContentType("text/html;charset=UTF-8");
        // html struct
        out.println("<HTML>");
        out.println("<HEAD>");
        out.println("<TITLE>Joe's Tools</TITLE>");
        out.println("</HEAD>");
        // header--end
        out.println("<BODY>");
        out.println("<H1>Tools Page</H1>");
        out.println("<H2>Hammers</H2>");
        out.println("<P>Joe's Hardware Online has the largest selection of hammers on the earch.</P>");
        out.println("<H2><A NAME=drills></A>Drills</H2>");
        out.println("<P>Joe's Hardware has a complete line of cordless and corded drills, as well as the latest in plutonium-powered atomic drills, for those big around the house jobs.</P>");
        // button --- end
        // body--end
        out.println("</BODY>");
        out.println("</HTML>");

        out.flush();
        out.close();
    }


    @Override
    public String getServletInfo() {
        return null;  // TODO
    }

    @Override
    public void destroy() {
        // TODO
    }
}
