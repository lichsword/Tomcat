package com.lichsword.nextbrain.core.connector.http;

/**
 * Created with IntelliJ IDEA.
 * User: lichsword
 * Date: 14-3-26
 * Time: 下午2:22
 * <p/>
 * TODO
 */
public abstract class ParametersBase implements IHttpParameter {

    protected String action;

    protected Object values;

    @Override
    public String getAction() {
        return action;
    }

    @Override
    public Object getValues() {
        return values;
    }

    /**
     * parse parameters to action & values.
     */
    protected abstract void parse();
}
