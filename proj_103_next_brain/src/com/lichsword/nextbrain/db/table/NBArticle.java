package com.lichsword.nextbrain.db.table;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created with IntelliJ IDEA.
 * User: lichsword
 * Date: 14-3-17
 * Time: 下午2:38
 * 一篇文章
 */
public class NBArticle {


    private static final String TABLE_NAME = "article";

    private class Column {
        static final String ID = "_id";
        static final String TITLE = "title";
        static final String SUMMARY = "summary";
        static final String LABELS = "labels";
    }

    static final String Sql_Create = "create table " + TABLE_NAME + "("
            + Column.ID + " int,"
            + Column.TITLE + " text,"
            + Column.SUMMARY + " text,"
            + Column.LABELS + " text,"
            + ")";

    public NBArticle fillArticle(ResultSet cursor) {
        if (null == cursor)
            return null;

        NBArticle result = new NBArticle();
        try {
            result.id = cursor.getInt(Column.ID);
            result.title = cursor.getString(Column.TITLE);
            result.summary = cursor.getString(Column.SUMMARY);
            result.labels = cursor.getString(Column.LABELS);

            // more
        } catch (SQLException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        return result;
    }


    private int id;
    private String title;
    private String summary;
    private String labels;
    private ExtraData data;

    class ExtraData {
        private String nature;// 本质
        private String formula;// 公式
        private String usage;// 示例
    }


    public NBArticle() {
    }


    public NBArticle(ResultSet cursor) {
        try {
            id = cursor.getInt(Column.ID);
            title = cursor.getString(Column.TITLE);
            summary = cursor.getString(Column.SUMMARY);
            labels = cursor.getString(Column.LABELS);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public String dump() {
        StringBuilder sb = new StringBuilder();
        sb.append("\nid=" + id);
        sb.append("\ntitle=" + title);
        sb.append("\nsummary=" + summary);
        sb.append("\nlabels=" + labels);
        return sb.toString();
    }
}
