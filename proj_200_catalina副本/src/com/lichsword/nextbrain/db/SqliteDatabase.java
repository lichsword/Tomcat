package com.lichsword.nextbrain.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created with IntelliJ IDEA.
 * User: lichsword
 * Date: 14-3-17
 * Time: 下午4:30
 * <p/>
 * TODO
 */
public final class SqliteDatabase extends DatabaseBase implements ISqlStatement {

    private SqliteDatabase(IDriver driver) {
        super(driver);
    }

    public SqliteDatabase() {
        super(new SqliteDriver());
    }

    @Override
    public Connection getConnection() {
        return super.getConnection();    //To change body of overridden methods use File | Settings | File Templates.
    }

    @Override
    public boolean insert(String sql) {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public boolean delete(String sql) {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public ResultSet query(String sql) {
        ResultSet cursor = null;
        try {
            Statement statement = getConnection().createStatement();
            cursor = statement.executeQuery(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cursor;
    }

    @Override
    public boolean update(String url) {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
