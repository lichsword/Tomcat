import java.io.InputStream;

/**
 * Created with IntelliJ IDEA.
 * User: lichsword
 * Date: 14-3-3
 * Time: 下午6:04
 * To change this template use File | Settings | File Templates.
 */
public class Request {
    private InputStream input;
    private String uri;

    public Request(InputStream input) {
        this.input = input;
    }

    public void parse() {
        // Read a set of characters from the socket.
        StringBuffer requestStringBuffer = new StringBuffer(2048);
        int i;
        byte[] buffer = new byte[2048];
        try {
            i = input.read(buffer);

        } catch (Exception e) {
            e.printStackTrace();
            i = -1;
        }

        for (int j = 0; j < i; j++) {
            requestStringBuffer.append((char) buffer[j]);
        }

        System.out.print(requestStringBuffer.toString());

        uri = parseUri(requestStringBuffer.toString());
    }

    private String parseUri(String requestString) {
        int index1, index2;
        index1 = requestString.indexOf(' ');
        if (-1 != index1) {
            index2 = requestString.indexOf(' ', index1 + 1);
            if (index2 > index1) {
                return requestString.substring(index1 + 1, index2);
            } // end if
        } // end if

        return null;
    }


    public String getUri() {
        return uri;
    }

}
