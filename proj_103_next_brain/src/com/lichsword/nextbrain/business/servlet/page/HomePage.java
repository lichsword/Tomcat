package com.lichsword.nextbrain.business.servlet.page;

import com.lichsword.nextbrain.core.page.StaticHttpPage;

/**
 * Created with IntelliJ IDEA.
 * User: lichsword
 * Date: 14-3-24
 * Time: 上午11:54
 * <p/>
 * TODO
 */
public class HomePage extends StaticHttpPage {

    public HomePage(){
        this("html/home.html");
    }
    public HomePage(String uri) {
        super(uri);
    }

}
