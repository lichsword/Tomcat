package com.lichsword.nextbrain.core.connector.http;

/**
 * Created with IntelliJ IDEA.
 * User: lichsword
 * Date: 14-3-26
 * Time: 下午12:18
 * <p/>
 * TODO
 */
public interface IHttpParameter {

    // default methods.
    public static final String METHOD_GET ="GET";
    public static final String METHOD_POST ="POST";
    public static final String METHOD_PUT ="PUT";
    public static final String METHOD_DELETE ="DELETE";

    // default keys.
    public static final String KEY_ACTION = "action";

    // default values of action.
    public static final String ACTION_INSERT = "insert";
    public static final String ACTION_DELETE = "delete";
    public static final String ACTION_QUERY = "query";
    public static final String ACTION_UPDATE = "update";

    /**
     *
     * @return "POST/GET"
     */
    public String getAction();

    public Object getValues();

}

