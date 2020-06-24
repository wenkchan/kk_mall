package com.kk.mall.common.log;
import org.apache.ibatis.logging.Log;

/**
 * @author Wenk.Chan
 * @version 1.0
 * @date 2020/5/26 11:48 上午
 */
public class SqlLogImpl implements Log {
    public SqlLogImpl(String clazz) {
        // Do Nothing
    }
    @Override
    public boolean isDebugEnabled() {
        return true;
    }

    @Override
    public boolean isTraceEnabled() {
        return true;
    }

    @Override
    public void error(String s, Throwable e) {
        System.err.println(s);
        e.printStackTrace(System.err);

    }

    @Override
    public void error(String s) {
        System.err.println(s);

    }

    @Override
    public void debug(String s) {
        System.out.println("\033[36;1m" + s + "\033[0m");
    }

    @Override
    public void trace(String s) {
        System.out.println("\033[34;1m" + s + "\033[0m");

    }

    @Override
    public void warn(String s) {
        System.out.println("\033[33;1m" + s + "\033[0m");

    }
}



