package com.lichsword.nextbrain.core.res;

import java.io.*;

/**
 * Created with IntelliJ IDEA.
 * User: lichsword
 * Date: 14-3-24
 * Time: 上午11:15
 * <p/>
 * TODO
 */
public class TextResource extends Resource implements IText {

    public TextResource(String uri) {
        super(uri);
    }

    @Override
    public int getCharLength() {
        return 0;  // TODO
    }

    private boolean debug = false;

    @Override
    public boolean write(PrintWriter writer) throws IOException {
        boolean success = true;
        if (null == file || !file.exists()) {
            success = false;
            return success;
        }// end if

        char[] buffer = new char[BUFFER_SIZE];
        FileReader fileReader = new FileReader(file);
        int ch = fileReader.read(buffer);
        if (debug) {
            System.out.print(new String(buffer, 0, ch));
        }// end if
        while (-1 != ch) {
            writer.write(buffer, 0, ch);
            ch = fileReader.read(buffer);
            if (debug) {
                System.out.print(new String(buffer, 0, ch));
            }// end if
        }// end while

        writer.flush();
        if (null != fileReader)
            fileReader.close();
        return success;
    }

    @Override
    public int getContentLength() throws IOException {
        int length;
        if (null == file || !file.exists()) {
            length = ERROR_CONTENT_LENGTH;
            return length;
        }// end if

        InputStream inputStream = null;
        try {
            inputStream = new FileInputStream(file);
            length = inputStream.available();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            length = ERROR_CONTENT_LENGTH;
        } finally {
            if (null != inputStream)
                inputStream.close();
        }

        return length;
    }
}
