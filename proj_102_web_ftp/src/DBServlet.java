import javax.servlet.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

/**
 * Created with IntelliJ IDEA.
 * User: lichsword
 * Date: 14-3-11
 * Time: 下午2:36
 * To change this template use File | Settings | File Templates.
 */
public class DBServlet implements Servlet {

    @Override
    public void init(ServletConfig servletConfig) throws ServletException {
        System.out.println("init");
    }

    @Override
    public ServletConfig getServletConfig() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
        System.out.println("from service");
        PrintWriter out = servletResponse.getWriter();
        if(DBConnector.getsInstance().isDriverOK()){
            out.println("Hello. database driver is ok.");
        }else{
            out.println("Hello. database driver is unknown.");
            return;// stop here.
        }

        // build connect and do sql work.
        Connection conn;
        try {
            conn = DriverManager.getConnection("jdbc:sqlite:test.db", null, null);
            // 设置自动提交为false
            conn.setAutoCommit(false);
            Statement stmt = conn.createStatement();

            //判断表是否存在
            ResultSet rsTables = conn.getMetaData().getTables(null, null, "student", null);
            if(rsTables.next()){
                System.out.println("表存在,创建表的事情不要做了");
            } else {
                stmt.executeUpdate("create table student (id,name);");
            }

            stmt.executeUpdate("insert into student values (1,'hehe');");
            stmt.executeUpdate("insert into student values (2,'xixi');");
            stmt.executeUpdate("insert into student values (3,'haha');");
            // 提交
            conn.commit();
            // 得到结果集
            ResultSet rs = stmt.executeQuery("select * from student;");
            while (rs.next()) {
                System.out.println("id = " + rs.getString("id"));
                System.out.println("name = " + rs.getString("name"));
            }
            rs.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("SQL异常!");
        }

    }

    @Override
    public String getServletInfo() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void destroy() {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
