package com.lichsword.nextbrain.nb.table;

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

    public static final String TABLE_NAME = "article";

    public class Column {
        public static final String ID = "_id";
        public static final String TITLE = "title";
        public static final String SUMMARY = "summary";
        public static final String LABELS = "labels";
    }

    static final String Sql_Create = "create table " + TABLE_NAME + "("
            + Column.ID + " int,"
            + Column.TITLE + " text,"
            + Column.SUMMARY + " text,"
            + Column.LABELS + " text,"
            + ")";

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

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getSummary() {
        return summary;
    }

    public String getLabels(){
        return labels;
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
