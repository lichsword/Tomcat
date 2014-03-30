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
        public static final String VISIT_LEVEL = "visit_level";
        public static final String READ_COUNT = "read_count";
        public static final String STATUS = "status";
        public static final String QUESTION = "question";
        public static final String DESC = "desc";
        public static final String LABELS = "labels";
        public static final String TRUTH = "truth";
        public static final String PATTERN = "pattern";
        public static final String REFERENCE = "reference";
        public static final String EXAMPLE = "example";
        public static final String CREATE_TIME = "create_time";
        public static final String MODIFIED_TIME = "modified_time";
    }

    /**
     * Column Display Name
     */
    public class ColumnDN{
        public static final String ID = "ID";
        public static final String VISIT_LEVEL = "权限";
        public static final String READ_COUNT = "阅读";
        public static final String STATUS = "状态";
        public static final String QUESTION = "问题";
        public static final String DESC = "描述";
        public static final String LABELS = "标签";
        public static final String TRUTH = "本质";
        public static final String PATTERN = "范式";
        public static final String REFERENCE = "引用参考";
        public static final String EXAMPLE = "示例";
        public static final String CREATE_TIME = "创建日期";
        public static final String MODIFIED_TIME = "最新修改";
    }

    /**
     * 私有
     */
    public static final int VL_PRIVAET = 1;

    /**
     * 公开
     */
    public static final int VL_PUBLIC = 2;

    /**
     * 未解决
     */
    public static final int ST_OPEN = 0;

    /**
     * 解决
     */
    public static final int ST_FIXED = 1;

    /**
     * 示例：2014-03-29 16:40:32
     */
    public static final String TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";

    /**
     * Create Sql not prepared, use exist db file.
     */
    static final String Sql_Create = "create table " + TABLE_NAME + "("
            + Column.ID + " int,"
            + Column.VISIT_LEVEL + " int,"
            + Column.READ_COUNT + " int,"
            + Column.STATUS + " int,"
            + Column.QUESTION + " text,"
            + Column.DESC + " text,"
            + Column.LABELS + " text,"
            + Column.TRUTH + " text,"
            + Column.PATTERN + " text,"
            + Column.REFERENCE + " text,"
            + Column.CREATE_TIME + " text,"
            + Column.MODIFIED_TIME + " text,"
            + Column.EXAMPLE + " text"
            + ")";

    private int id;
    private int visitLevel;
    private int readCount;
    private int status;
    private String question;
    private String desc;
    private String labels;
    private String truth;
    private String pattern;
    private String reference;
    private String example;
    private String createTime;
    private String modifiedTime;

    public NBArticle() {
    }

    public NBArticle(ResultSet cursor) {
        try {
            id = cursor.getInt(Column.ID);
            visitLevel = cursor.getInt(Column.VISIT_LEVEL);
            readCount = cursor.getInt(Column.READ_COUNT);
            status = cursor.getInt(Column.STATUS);
            question = cursor.getString(Column.QUESTION);
            desc = cursor.getString(Column.DESC);
            labels = cursor.getString(Column.LABELS);
            truth= cursor.getString(Column.TRUTH);
            pattern = cursor.getString(Column.PATTERN);
            reference = cursor.getString(Column.REFERENCE);
            example = cursor.getString(Column.EXAMPLE);
            createTime = cursor.getString(Column.CREATE_TIME);
            modifiedTime = cursor.getString(Column.MODIFIED_TIME);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int getId() {
        return id;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getQuestion() {
        return question;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }

    public void setLabels(String labels) {
        this.labels = labels;
    }

    public void setLabels(String[] labels) {
        StringBuilder sb = new StringBuilder();
        for (String label : labels) {
            sb.append(label);
        }
        this.labels = sb.toString();
    }

    public String getLabels() {
        return labels;
    }

    public int getVisitLevel() {
        return visitLevel;
    }

    public void setVisitLevel(int visitLevel) {
        this.visitLevel = visitLevel;
    }

    public int getReadCount() {
        return readCount;
    }

    public void setReadCount(int readCount) {
        this.readCount = readCount;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getTruth() {
        return truth;
    }

    public void setTruth(String truth) {
        this.truth = truth;
    }

    public String getPattern() {
        return pattern;
    }

    public void setPattern(String pattern) {
        this.pattern = pattern;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public String getExample() {
        return example;
    }

    public void setExample(String example) {
        this.example = example;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getModifiedTime() {
        return modifiedTime;
    }

    public void setModifiedTime(String modifiedTime) {
        this.modifiedTime = modifiedTime;
    }

    public String dump() {
        StringBuilder sb = new StringBuilder();
        sb.append("\nid=" + id);
        sb.append("\nquestion=" + question);
        sb.append("\ndesc=" + desc);
        sb.append("\nlabels=" + labels);
        return sb.toString();
    }
}
