import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created with IntelliJ IDEA.
 * User: lichsword
 * Date: 14-3-11
 * Time: 下午2:20
 * To change this template use File | Settings | File Templates.
 */
public class HelloSqlite {

        public static void main(String[] args) {
            // 加载驱动
            try {
                Class.forName("org.sqlite.JDBC");
            } catch (ClassNotFoundException e) {
                // TODO Auto-generated catch block
                // e.printStackTrace();
                System.out.println("数据库驱动未找到!");
            }
            // 得到连接 会在你所填写的目录建一个你命名的文件数据库
            Connection conn;
            try {
                conn = DriverManager.getConnection("jdbc:sqlite:c:/test.db",null,null);
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

}
