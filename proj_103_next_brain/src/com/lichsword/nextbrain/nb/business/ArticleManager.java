package com.lichsword.nextbrain.nb.business;

import com.lichsword.nextbrain.core.db.SqliteDatabase;
import com.lichsword.nextbrain.nb.table.NBArticle;
import com.lichsword.nextbrain.nb.table.NBArticle.Column;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: lichsword
 * Date: 14-3-17
 * Time: 下午3:44
 * To change this res use File | Settings | File Templates.
 */
public class ArticleManager {

    private static ArticleManager sInstance;
    SqliteDatabase mDatabase = null;

    private ArticleManager() {
        mDatabase = new SqliteDatabase();
    }

    public static ArticleManager getInstance() {
        if (null == sInstance) {
            sInstance = new ArticleManager();
        }// end if
        return sInstance;
    }

    public ArrayList<NBArticle> queryAllArticle() {
        ArrayList<NBArticle> result = null;
        ResultSet cursor = mDatabase.query("select * from article");

        try {
            if (null != cursor) {
                result = new ArrayList<NBArticle>();
                NBArticle item = null;
                while (cursor.next()) {
                    item = new NBArticle(cursor);
                    result.add(item);
                }
            }// end if
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }

    public boolean addArticle(NBArticle article) {
        String sql = "insert into " + NBArticle.TABLE_NAME + " (" + Column.TITLE + ", " + Column.SUMMARY + ", " + Column.LABELS + ")"
                + " values " + "('" + article.getTitle() + "', '" + article.getSummary() + "', '" + article.getLabels() + "')";
        System.out.println(sql);
        boolean result = mDatabase.insert(sql);
        return result;
    }
}