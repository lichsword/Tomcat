package com.lichsword.nextbrain.nb.business;

import com.lichsword.nextbrain.core.db.SqliteDatabase;
import com.lichsword.nextbrain.nb.model.NBArticle;
import com.lichsword.nextbrain.nb.model.NBArticle.Column;

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

    /**
     * 把单引号 转义为 双单引号
     * TODO 高级的方法是使用存储过程 或 参数化语句，并防止注入。
     *
     * @param string
     * @return
     */
    private String escapeString(String string) {
        return string.replaceAll("'", "''");
    }

    public ArrayList<NBArticle> queryAllArticle() {
        ArrayList<NBArticle> result = null;
        ResultSet cursor = mDatabase.query("select * from article");

        try {
            if (null != cursor) {
                result = new ArrayList<NBArticle>();
                NBArticle item;
                while (cursor.next()) {
                    item = new NBArticle(cursor);
                    result.add(item);
                }// end if
            }// end if
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }

    public boolean addArticle(NBArticle article) {
        // TODO update to for loop Or design pattern?
        String sql = "insert into " + NBArticle.TABLE_NAME
                + " ("
                + Column.VISIT_LEVEL + ", "
                + Column.READ_COUNT + ", "
                + Column.STATUS + ", "
                + Column.QUESTION + ", "
                + Column.DESC + ", "
                + Column.LABELS + ", "
                + Column.TRUTH + ", "
                + Column.PATTERN + ", "
                + Column.REFERENCE + ", "
                + Column.EXAMPLE + ", "
                + Column.CREATE_TIME + ", "
                + Column.MODIFIED_TIME
                + ")"
                + " values "
                + "("
                + article.getVisitLevel() + ", "
                + article.getReadCount() + ", "
                + article.getStatus() + ", "
                + "'" + escapeString(article.getQuestion()) + "', "
                + "'" + escapeString(article.getDesc()) + "', "
                + "'" + escapeString(article.getLabels()) + "', "
                + "'" + escapeString(article.getTruth()) + "', "
                + "'" + escapeString(article.getPattern()) + "', "
                + "'" + escapeString(article.getReference()) + "', "
                + "'" + escapeString(article.getExample()) + "', "
                + "'" + escapeString(article.getCreateTime()) + "', "
                + "'" + escapeString(article.getModifiedTime()) + "'"
                + ")";
        System.out.println(sql);
        boolean result = mDatabase.insert(sql);
        return result;
    }

    public boolean delete(int id) {
        String sql = "delete from " + NBArticle.TABLE_NAME + " where " + Column.ID + " = " + id;
        System.out.println(sql);
        boolean result = mDatabase.delete(sql);
        return result;
    }
}