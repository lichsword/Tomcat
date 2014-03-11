/**
 * Created with IntelliJ IDEA.
 * User: lichsword
 * Date: 14-3-11
 * Time: 下午2:22
 *
 * 启动器类
 */
public class Bootstrap {

    public static void main(String[] argvs){
        HttpConnecter connector = new HttpConnecter();
        connector.run();
    }
}
