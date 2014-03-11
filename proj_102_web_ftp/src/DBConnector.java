/**
 * Created with IntelliJ IDEA.
 * User: lichsword
 * Date: 14-3-11
 * Time: 下午2:34
 * To change this template use File | Settings | File Templates.
 */
public class DBConnector {

    private static DBConnector sInstance;

    public static DBConnector getsInstance() {
        if (null == sInstance) {
            sInstance = new DBConnector();
        }// end if
        return sInstance;
    }

    private boolean driverOK = false;

    public boolean isDriverOK(){
        return driverOK;
    }

    private DBConnector() {
        try {
            Class.forName("org.sqlite.JDBC");
            System.out.println("数据库驱动被发现!");
            driverOK = true;
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            // e.printStackTrace();
            System.out.println("数据库驱动未找到!");
            driverOK = false;
        }
    }
}
